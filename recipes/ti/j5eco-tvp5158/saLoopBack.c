/*
 *  Copyright (c) 2010-2011, Texas Instruments Incorporated
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  *  Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *  *  Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *  *  Neither the name of Texas Instruments Incorporated nor the names of
 *     its contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 *  OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 *  OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  Contact information for paper mail:
 *  Texas Instruments
 *  Post Office Box 655303
 *  Dallas, Texas 75265
 *  Contact information:
 *  http://www-k.ext.ti.com/sc/technical-support/product-information-centers.htm?
 *  DCMP=TIHomeTracking&HQS=Other+OT+home_d_contact
 *  ============================================================================
 *
 */

/*!
 *  @file       video_loopback_app.c
 *
 *  @brief      Out-Of-Box Video loopback app for TI811x platfrom
 * 
 *              This application captures through V4L2 capture driver 
 *              on TVP5158 input and displays the captured content on the V4L2 
 *              display driver. Application uses userpointer mechanism for
 *              both capture and display drivers. Buffers for the userpointer 
 *              are taken from framebuffer driver (fbdev).
 *
 *  @todo       Initialize all the variables
 *  @todo       Remove system calls
 *  @todo       Remove the configuration at capture 
 *  @todo       Identify what 0xFF in display buffer mean
 */

#include <stdint.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/ioctl.h>
#include <sys/mman.h>
#include <errno.h>
#include <string.h>
#include <linux/videodev2.h>
#include <linux/fb.h>
#include <linux/ti81xxfb.h>
#include <linux/ti81xxhdmi.h>
#include <linux/ti81xxvin.h>
#include <linux/ti81xxscalar.h>
#include <linux/i2c.h>
#include <linux/i2c-dev.h>

/* Number of buffers required for application. Less that this may cause
 * considerable frame drops
 */
#define MAX_BUFFER       (8)

/* device node to be used for capture */
#define CAPTURE_DEVICE   "/dev/video0"
#define CAPTURE_NAME     "Capture"

/* device node to be used for display */
#define DISPLAY_DEVICE   "/dev/video1"
#define DISPLAY_NAME     "Display"

/* number of frames to be captured and displayed
 * Increase this for long runs
 */
#define MAXFRAMECOUNT    (500)

/* Pixel format for capture and display. Capture supports
 * V4L2_PIX_FMT_NV12, V4L2_PIX_FMT_NV16, V4L2_PIX_FMT_RGB24 but display
 * supports only V4L2_PIX_FMT_YUYV. So this has to be V4L2_PIX_FMT_YUYV
 */
#define DEF_PIX_FMT      V4L2_PIX_FMT_YUYV

#define MAP_SIZE         (4096UL)
#define MAP_MASK         (MAP_SIZE - 1 )


/* Debugging Subsystem */
#define TRACE_MUST       (0)
#define TRACE_ERROR      (1)
#define TRACE_WARNING    (2)
#define TRACE_INFO       (3)
#define TRACE_DEBUG      (4)
#define TRACE_LOG        (5)

#define UTL_TRACE(level, fmt...) {             \
            if (level <= currDebugLevel) {     \
              printf (fmt);                    \
            }                                  \
          }                                    \

/* Define boolean macros */
#ifdef TRUE
#undef TRUE
#endif
#define TRUE (1)

#ifdef FALSE
#undef FALSE
#endif
#define FALSE (0)

/*! @var char currDebugLevel
 *  @brief Stores configrued debug level, Default set to TRACE_WARNING
 *         Default Can be over written by the environment variable 
 *          VLBD_DEBUG=error|warning|info|debug|log
 *           Ex: export VLBD_DEBUG=error
 *
 *  @warning Not thread safe!
 */
char currDebugLevel = TRACE_WARNING;

/*! @var int numFrames
 *  @brief Number of frames to process, This value defults to MAXFRAMECOUNT,
 *         This can be overwritten by the command line parameter.
 *
 *  @warning Not thread safe!
 */
int  numFrames      = MAXFRAMECOUNT;

/*! @brief	Structure for storing application data like file pointers, format etc.
 */
typedef struct VLBAAppObj_tag {
  /*! File  Descriptior for device */
  int  fd;
  struct v4l2_capability cap;
  struct v4l2_format     fmt;
  struct v4l2_dv_preset  dv_preset;
  struct v4l2_requestbuffers reqbuf;
  struct v4l2_buffer     buf;
  struct ti81xxvin_overflow_status over_flow;
} VLBAAppObj;

/*! @var struct VLBAAppObj dispObj
 *  @brief Display Device Object
 *
 *  @warning Not thread safe!
 */
VLBAAppObj dispObj;

/*! @var struct VLBAAppObj captObj
 *  @brief Capture Device Object
 *
 *  @warning Not thread safe!
 */
VLBAAppObj captObj;

/*! @var static int fdDevMem
 *  @brief File Descriptor for /dev/mem, this is used to get contiguous buffer
 *
 *  @warning Not thread safe!
 */
static int fdDevMem;

/*! @var unsigned char *bufferAddr[MAX_BUFFER];
 *  @brief Arrya of pointers to stor buffer addresses
 *
 *  @warning Not thread safe!
 */
unsigned char *bufferAddr[MAX_BUFFER];

/*Global variable ot check video is ntsc/pal */
#define FMT_NTSC  (0)
#define FMT_PAL   (1)

/*! @var static int captStd
 *  @brief Capture format
 *
 *  @warning Not thread safe!
 */
static int captStd = FMT_NTSC;

/*! 
 *  @def	EEPROM_I2C_ADDR
 *  @brief	I2C address for EEPROM in the app board
 */
#define EEPROM_I2C_ADDR             0x51

/*! 
 *  @def	EEPROM_PAGE_SIZE
 *  @brief	EEPROM page size
 */
#define EEPROM_PAGE_SIZE            (256)


/*! 
 *  @def	I2C_DEVICE_NAME
 *  @brief	I2C device node name
 */
#define I2C_DEVICE_NAME             "/dev/i2c-1"

#define JAMR21_APP_BOARD_HEADER     (0xAA5533EE)
/*!
 *  @def        E_EEPROM_XXXXXX
 *  @brief      Error codes for EEPROM reading
 */
#define E_EEPROM_SUCCESS   (1)
#define E_EEPROM_I2CREAD   (2)
#define E_EEPROM_INTERNAL  (3)

/*! @brief	A structure to represent app board info in EEPROM in
 *              app board
 */
typedef struct TI811XAppBoardInfo_tag
{
  /*!
   * Key to identify that the structure stored in the EEPROM corresponds 
   * to board identification related information. This value does not change. 
   */
  unsigned long  header;
  
  /*! JAMR2.1. .2.1. is the board name and is not a revision identifier. 
   */
  char           board_name[16];

  /*! Value incremented upon each revision of the PCB assembly or PCB design. 
   */
  unsigned short version_major;

  /*! Reserved for future use. 
   */
  unsigned short version_minor;
  
  /*! Reserved for future use.
   */
  unsigned long  config_option;

  char           reserved[28];
} TI811XAppBoardInfo;


/* Function prototypes */
static void printV4L2Format(char *dev_name, struct v4l2_format *fmt);

static int setupBuffers (void);

static void configTraceLevel (char *pCurrLevel);

static void calc_result_time(struct timeval *result, struct timeval *after,
		struct timeval *before);

static void vlbd_show_usage(char *app_name);

static void configTraceLevel (char *pCurrLevel);

static int readEEPROM(char *buffer, unsigned int size);

/******************************************************************************/
/*                        Capture Related functions                           */
/******************************************************************************/

/*!
 * Open and query dv preset for capture driver
 * 
 * @param app_name Name of this app
 * 
 * @return 0  For success
 *         -1 For any failure
 */
static int initCapture(void)
{
  int mode = O_RDWR;
  int retVal = 0;

  UTL_TRACE (TRACE_DEBUG, "Initializing capture device...\n");

  /* Open capture driver */
  captObj.fd = open((const char *)CAPTURE_DEVICE, mode);
  if (-1 == captObj.fd) {
    UTL_TRACE(TRACE_ERROR, "failed to open capture device: %s\n", 
              CAPTURE_DEVICE);
    return -1;
  }

  /* Query for capabilities */
  if (ioctl(captObj.fd, VIDIOC_QUERYCAP, &captObj.cap)) {

    UTL_TRACE(TRACE_ERROR, "Query capability failed\n");
    exit(2);
  } 
  else {

    UTL_TRACE (TRACE_INFO, "Driver Name: %s\n", captObj.cap.driver);
    UTL_TRACE (TRACE_INFO, "Driver bus info: %s\n", captObj.cap.bus_info);

    if (captObj.cap.capabilities & V4L2_CAP_VIDEO_CAPTURE) {
      UTL_TRACE (TRACE_INFO, "Driver is capable of doing capture\n");
    }
    if (captObj.cap.capabilities & V4L2_CAP_VIDEO_OVERLAY) {
      UTL_TRACE (TRACE_INFO, "Driver is capabled of scaling and cropping\n");
    }
  }

  system("echo 0 > /sys/devices/platform/vpss/display0/enabled");

  /* Query the preset. Set it to invalid and query for it */
  captObj.dv_preset.preset = V4L2_DV_INVALID;
  if (ioctl(captObj.fd, VIDIOC_QUERY_DV_PRESET, &captObj.dv_preset)) {

    UTL_TRACE (TRACE_ERROR, "Querying DV Preset failed\n");
    exit(2);
  }

  if (V4L2_DV_480P59_94 == captObj.dv_preset.preset) {
    UTL_TRACE (TRACE_INFO, "Std NTSC found at capture src\n");
  }
  else if (V4L2_DV_576P50 == captObj.dv_preset.preset) {
    UTL_TRACE (TRACE_INFO, "Std PAL found at capture src\n");
  }

  if (FMT_NTSC == captStd) {

    captObj.dv_preset.preset = V4L2_DV_480P59_94;

    if (ioctl(captObj.fd, VIDIOC_S_DV_PRESET, &captObj.dv_preset)) {
      UTL_TRACE (TRACE_ERROR, "Setting DV Preset failed\n");
      exit(2);
    }
  } 
  else if (FMT_PAL == captStd) {

    captObj.dv_preset.preset = V4L2_DV_576P50;

    if (ioctl(captObj.fd, VIDIOC_S_DV_PRESET, &captObj.dv_preset)) {
      UTL_TRACE (TRACE_ERROR, "Setting DV Preset failed\n");
      exit(2);
    }
  }

  switch (captObj.dv_preset.preset) {

    case V4L2_DV_480P59_94:
      UTL_TRACE (TRACE_INFO, "Mode set is 480P59_94\n");
      system ("echo 720p-60 > /sys/devices/platform/vpss/display0/mode");
      break;

    case V4L2_DV_576P50:
      UTL_TRACE (TRACE_INFO, "Mode set is V4L2_DV_576P50\n");
      system ("echo 720p-60 > /sys/devices/platform/vpss/display0/mode");
      break;

    case V4L2_DV_720P60:
     UTL_TRACE (TRACE_INFO, "Mode set is 720P60\n");
     system ("echo 720p-60 > /sys/devices/platform/vpss/display0/mode");
     break;

    case V4L2_DV_1080I60:
      UTL_TRACE (TRACE_INFO, "Mode set is 1080I60\n");
      system ("echo 1080p-30 > /sys/devices/platform/vpss/display0/mode");
      break;

    case V4L2_DV_1080P60:
      UTL_TRACE (TRACE_INFO, "Mode set is 1080P60\n");
      system ("echo 1080p-60 > /sys/devices/platform/vpss/display0/mode");
      break;

    case V4L2_DV_1080P30:
      UTL_TRACE (TRACE_INFO, "Mode set is 1080P30\n");
      system ("echo 1080p-30 > /sys/devices/platform/vpss/display0/mode");
      break;

    default:
      UTL_TRACE (TRACE_INFO, "Mode set is %d\n", captObj.dv_preset.preset);
      break;
  }

  system("echo 1 > /sys/devices/platform/vpss/display0/enabled");

  return retVal;
}

/*!
 * deInit Capture
 * 
 * @return 0  For success
 *         -1 For any failure
 */
static int deInitCapture(void)
{
  UTL_TRACE (TRACE_DEBUG, "Un-Initializing capture device...\n");

  if (close(captObj.fd)) {
    return -1;
  }

  return 0;
}

/*!
 * Setup capture driver
 *
 * @return 0  For success
 *         -1 For any failure
 */
static int setupCapture(void)
{
  int retVal;

  /* Get current format */
  captObj.fmt.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

  retVal = ioctl (captObj.fd, VIDIOC_G_FMT, &captObj.fmt);
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Set Format failed\n");
    return -1;
  }

  /* Set format according to mode detected */
  if (V4L2_DV_480P59_94 == captObj.dv_preset.preset) {
    captObj.fmt.fmt.pix.width = 720;
    captObj.fmt.fmt.pix.height = 480;
  } 
  else if (V4L2_DV_576P50 == captObj.dv_preset.preset) {
    captObj.fmt.fmt.pix.width = 720;
    captObj.fmt.fmt.pix.height = 576;
  } 
  else if (V4L2_DV_720P60 == captObj.dv_preset.preset) {
    captObj.fmt.fmt.pix.width = 1280;
    captObj.fmt.fmt.pix.height = 720;
  } 
  else if (V4L2_DV_1080P60 == captObj.dv_preset.preset) {
    captObj.fmt.fmt.pix.width = 1920;
    captObj.fmt.fmt.pix.height = 1080;
  } 
  else {
    captObj.fmt.fmt.pix.width = 1920;
    captObj.fmt.fmt.pix.height = 1080;
  }

  captObj.fmt.fmt.pix.bytesperline = (captObj.fmt.fmt.pix.width * 2);
  captObj.fmt.fmt.pix.sizeimage = (captObj.fmt.fmt.pix.bytesperline *
                                captObj.fmt.fmt.pix.height);

  retVal = ioctl (captObj.fd, VIDIOC_S_FMT, &captObj.fmt);
  if (0 > retVal) {
    UTL_TRACE(TRACE_ERROR, "Set Format failed\n");
    return -1;
  }

  /* Get format again and print it on console */
  captObj.fmt.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;
  retVal = ioctl(captObj.fd, VIDIOC_G_FMT, &captObj.fmt);
  if (0 > retVal) {
    UTL_TRACE(TRACE_ERROR, "Set Format failed\n");
    return -1;
  }

  printV4L2Format("Capture", &captObj.fmt);

  /* Request buffers. We are operating in userPtr mode */
  captObj.reqbuf.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;
  captObj.reqbuf.count = MAX_BUFFER;
  captObj.reqbuf.memory = V4L2_MEMORY_USERPTR;

  retVal = ioctl(captObj.fd, VIDIOC_REQBUFS, &captObj.reqbuf);
  if (0 > retVal) {
    UTL_TRACE(TRACE_ERROR, "Could not allocate the buffers\n");
    return -1;
  }

  return retVal;
}

/*!
 * Start capture
 *
 * @return 0  For success
 *         -1 For any failure
 */
static int startCapture(void)
{
  int a = V4L2_BUF_TYPE_VIDEO_CAPTURE;
  int retVal = 0;

  retVal = ioctl(captObj.fd, VIDIOC_STREAMON, &a);
  if (0 > retVal) {
    perror("VIDIOC_STREAMON\n");
    UTL_TRACE (TRACE_ERROR, "VIDIOC_STREAMON\n");
    return -1;
  }

  return 0;
}

/*!
 * Stop capture
 *
 * @return 0  For success
 *         -1 For any failure
 */
static int stopCapture(void)
{
  int a = V4L2_BUF_TYPE_VIDEO_CAPTURE;
  int retVal = 0;

  retVal = ioctl(captObj.fd, VIDIOC_STREAMOFF, &a);
  if (0 > retVal) {
    perror("VIDIOC_STREAMOFF\n");
    UTL_TRACE (TRACE_ERROR, "VIDIOC_STREAMOFF\n");
    return -1;
  }

  return 0;
}

/*!
 * Prime capture buffers
 *
 * @return 0  For success
 *         -1 For any failure
 */
static int queueCaptureBuffers(void)
{
  int retVal = 0;
  int i      = 0;

  for (i = 0; i < (MAX_BUFFER / 2); i++) {

    captObj.buf.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;
    captObj.buf.memory = V4L2_MEMORY_USERPTR;
    captObj.buf.index = i;
    captObj.buf.m.userptr = (unsigned long)bufferAddr[captObj.buf.index];
    captObj.buf.length = captObj.fmt.fmt.pix.sizeimage;

    retVal = ioctl(captObj.fd, VIDIOC_QBUF, &captObj.buf);
    if (0 > retVal) {
      perror("VIDIOC_QBUF\n");
      UTL_TRACE (TRACE_ERROR, "VIDIOC_QBUF\n");
      return -1;
    }
  }

  return retVal;
}

/******************************************************************************/
/*                        Display Related functions                           */
/******************************************************************************/

/*!
 * Open display driver and set format according to resolution on capture
 * 
 * @param app_name Name of this app
 * 
 * @return 0  For success
 *        -1 For any failure
 */
static int initDisplay (void)
{
  int flags = O_RDWR;
  int retVal  = 0;

  UTL_TRACE (TRACE_DEBUG, "Initializing display device...\n");

  /* Open display driver */
  dispObj.fd = open ((const char *)DISPLAY_DEVICE, flags);
  if (-1 == dispObj.fd) {
    UTL_TRACE (TRACE_ERROR, "failed to open display device: %s\n", 
               DISPLAY_DEVICE);
    return -1;
  }

  /* Query driver capability */
  if (ioctl (dispObj.fd, VIDIOC_QUERYCAP, &dispObj.cap)) {
    UTL_TRACE (TRACE_ERROR, "Query capability failed for display\n");
    return -1;
  }
  else {
    UTL_TRACE (TRACE_INFO, "Driver Name: %s\n", dispObj.cap.driver);
    UTL_TRACE (TRACE_INFO, "Driver bus info: %s\n", dispObj.cap.bus_info);

    if (V4L2_CAP_VIDEO_OUTPUT & dispObj.cap.capabilities) {
      UTL_TRACE (TRACE_INFO, "Driver is capable of doing Display\n");
    }
    if (V4L2_CAP_VIDEO_OVERLAY & dispObj.cap.capabilities) {
      UTL_TRACE (TRACE_INFO, "Driver is capabled of scaling and cropping\n");
    }
  }

  return retVal;
}

/*!
 * unInit display
 *
 * @param app_name Name of this app
 *
 * @return 0  For success
 *        -1 For any failure
 */
static int deInitDisplay(void)
{
  UTL_TRACE (TRACE_DEBUG, "Un-Initializing display device...\n");

  if (close(dispObj.fd)) {
    return -1;
  }

  return 0;
}

/*!
 * Setup display driver
 *
 * @return 0  For success
 *        -1 For any failure
 */
static int setupDisplay(void)
{
  int    retVal = 0;
  struct v4l2_format fmt;
  struct ti81xxvid_scalarparam scalarprm;

  /* Get format */
  dispObj.fmt.type = V4L2_BUF_TYPE_VIDEO_OUTPUT;
  retVal = ioctl (dispObj.fd, VIDIOC_G_FMT, &dispObj.fmt);
  if (0 > retVal) {
    UTL_TRACE(TRACE_ERROR, "Get Format failed\n");
    return -1;
  }

  /* Set format according to display mode */
  if (V4L2_DV_480P59_94 == captObj.dv_preset.preset) {
    dispObj.fmt.fmt.pix.width = 720;
    dispObj.fmt.fmt.pix.height = 480;
  } 
  else if (V4L2_DV_576P50 == captObj.dv_preset.preset) {
    dispObj.fmt.fmt.pix.width = 720;
    dispObj.fmt.fmt.pix.height = 576;
  } 
  else if (V4L2_DV_720P60 == captObj.dv_preset.preset) {
    dispObj.fmt.fmt.pix.width = 1280;
    dispObj.fmt.fmt.pix.height = 720;
  } 
  else if (V4L2_DV_1080P60 == captObj.dv_preset.preset) {
    dispObj.fmt.fmt.pix.width = 1920;
    dispObj.fmt.fmt.pix.height = 1080;
  }  
  else {
    dispObj.fmt.fmt.pix.width = 1920;
    dispObj.fmt.fmt.pix.height = 1080;
  }

  scalarprm.inframe_width = dispObj.fmt.fmt.pix.width;
  scalarprm.inframe_height = dispObj.fmt.fmt.pix.height;
  scalarprm.scalar_enable = 1;
  scalarprm.yframe_offset = 0;
  scalarprm.yoffset_flag = 0;
  scalarprm.yframe_size =  0;
  scalarprm.chroma_offset = 0;
  scalarprm.scoutbuf = NULL;

  retVal = ioctl(dispObj.fd, TISET_SCALAR_PARAMS, &scalarprm);
  if (0 > retVal) {
    perror("Set scalar Format failed\n");
    UTL_TRACE (TRACE_ERROR, "Set scalar Format failed\n");
    return -1;
  }

  /*display input format config*/
  dispObj.fmt.fmt.pix.pixelformat = V4L2_PIX_FMT_YUYV;
  dispObj.fmt.fmt.pix.bytesperline = dispObj.fmt.fmt.pix.width * 2;
  dispObj.fmt.fmt.pix.sizeimage = dispObj.fmt.fmt.pix.bytesperline *
  dispObj.fmt.fmt.pix.height;
  retVal = ioctl(dispObj.fd, VIDIOC_S_FMT, &dispObj.fmt);
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Set Format failed\n");
    return -1;
  }
  /* Get format again and display it on console */
  dispObj.fmt.type = V4L2_BUF_TYPE_VIDEO_OUTPUT;
  retVal = ioctl(dispObj.fd, VIDIOC_G_FMT, &dispObj.fmt);
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Get Format failed for display\n");
    return -1;
  }

  printV4L2Format("Display", &dispObj.fmt);

  /* Get the parameters before setting and
   * set only required parameters */
  fmt.type = V4L2_BUF_TYPE_VIDEO_OVERLAY;
  retVal = ioctl(dispObj.fd, VIDIOC_G_FMT, &fmt);
  if(0 > retVal) {
    perror("Set Format failed\n");
    UTL_TRACE (TRACE_ERROR, "Set Format failed\n");
    exit(1);
  }

  /* Set the window size */
  fmt.fmt.win.w.left = 0;
  fmt.fmt.win.w.top = 0;
  fmt.fmt.win.w.width = 720;
  fmt.fmt.win.w.height = 480;

  retVal = ioctl(dispObj.fd, VIDIOC_S_FMT, &fmt);
  if (0 > retVal) {
    perror("Set Format failed\n");
    UTL_TRACE (TRACE_ERROR, "Set Format failed\n");
    exit(1);
  }

  /* Requests buffers, we are operating in userPtr mode */
  dispObj.reqbuf.type = V4L2_BUF_TYPE_VIDEO_OUTPUT;
  dispObj.reqbuf.count = MAX_BUFFER;
  dispObj.reqbuf.memory = V4L2_MEMORY_USERPTR;
  retVal = ioctl(dispObj.fd, VIDIOC_REQBUFS, &dispObj.reqbuf);
  if (0 > retVal) {
    perror("Could not allocate the buffers\n");
    UTL_TRACE (TRACE_ERROR, "Could not allocate the buffers\n");
    return -1;
  }

  return 0;
}

/*!
 * Start display
 *
 * @return 0  For success
 *        -1 For any failure
 */
static int startDisplay(void)
{
  int a = V4L2_BUF_TYPE_VIDEO_OUTPUT;
  int retVal = 0;

  retVal = ioctl(dispObj.fd, VIDIOC_STREAMON, &a);
  if (0 > retVal) {
    perror("VIDIOC_STREAMON\n");
    UTL_TRACE (TRACE_ERROR, "VIDIOC_STREAMON\n");
    return -1;
  }

  return 0;
}


/*!
 * Stop display 
 *
 * @return 0  For success
 *        -1 For any failure
 */
static int stopDisplay(void)
{
  int a = V4L2_BUF_TYPE_VIDEO_OUTPUT;
  int retVal = 0;

  retVal = ioctl(dispObj.fd, VIDIOC_STREAMOFF, &a);
  if (0 > retVal) {
    perror("VIDIOC_STREAMOFF\n");
    UTL_TRACE (TRACE_ERROR, "VIDIOC_STREAMOFF\n");
    return -1;
  }

  return 0;
}

/*!
 * Prime display buffers
 * 
 * @return 0  For success
 *        -1 For any failure
 */
static int queueDisplayBuffers(void)
{
  int retVal = 0;
  int i      = 0;

  for (i = (MAX_BUFFER / 2); i < MAX_BUFFER; i++) {
    dispObj.buf.type = V4L2_BUF_TYPE_VIDEO_OUTPUT;
    dispObj.buf.memory = V4L2_MEMORY_USERPTR;
    dispObj.buf.index = i;// - (MAX_BUFFER / 2);
    dispObj.buf.m.userptr = (unsigned long)bufferAddr[i];
    dispObj.buf.length = captObj.fmt.fmt.pix.sizeimage;

    retVal = ioctl(dispObj.fd, VIDIOC_QBUF, &dispObj.buf);
    if (0 > retVal) {
      perror("VIDIOC_QBUF\n");
      UTL_TRACE (TRACE_ERROR, "VIDIOC_QBUF\n");
      return -1;
    } /* if retVal */
  } /* for i MAX_BUFFER */

  return retVal;
}

/*!
 * Main entry point for video loopback app
 * 
 * @param argc Number of Arguments passed to this app
 * @param argv Argument array
 *
 * @return 0   for success
 *        -1  for any failure
 */
int main(int argc, char *argv[])
{
  int    i   = 0;
  int    retVal   = 0;
  int    cap_disp_restart = 0;
  int    eepromStatus = E_EEPROM_SUCCESS;
  TI811XAppBoardInfo *appBoardInfo = NULL;
  char   eepromData[EEPROM_PAGE_SIZE];

  struct v4l2_buffer temp_buf;
  struct timeval     before;
  struct timeval     after;
  struct timeval     result;

  /* Check number of parameter and show help */
  if(argc<2) {
    vlbd_show_usage(argv[0]);
    retVal = -1;
    goto exit1;
  }

  /* Verify Command line parameter and show help */
  if(0 == strcmp(argv[1], "ntsc")) {
    captStd = FMT_NTSC;
  }
  else if(0 == strcmp(argv[1], "pal")) {
    captStd = FMT_PAL;
  }
  else {
    UTL_TRACE(TRACE_ERROR, "Invalid capture source: %s\n", argv[1]);
    vlbd_show_usage(argv[0]);
    retVal = -1;
    goto exit1;
  }

  /* Max frames is passed from cmd line */
  if (argc >2) {
    numFrames = atol (argv[2]);
  }
  else {
    /* Defaults to MAXFRAMECOUNT */
    numFrames = MAXFRAMECOUNT;
  }

  /* Check if daughter card is present or not */
  memset (eepromData, 0, sizeof(eepromData));
  eepromStatus = readEEPROM (eepromData, sizeof(eepromData));

  if (E_EEPROM_SUCCESS == eepromStatus) {
    printf( "App board connected.\n" );
    appBoardInfo = (TI811XAppBoardInfo *)eepromData;
    if (0xFFFFFFFF == appBoardInfo->header) {

      printf ("EEPROM memory reads 0xFF, It is possible that EEPROM in this" \
              " app board is not programmed with board identification details.\n");
    }
    else {

      if (JAMR21_APP_BOARD_HEADER != appBoardInfo->header) {

        printf ("Unknown App board. Header must be 0x%x, but had: 0x%x\n", 
                JAMR21_APP_BOARD_HEADER, (unsigned int) appBoardInfo->header);
      }
      else {

        /* Display App board info */
        printf("App board details:\r\n");
        printf ("Header         :0x%02lx\n", appBoardInfo->header);
        printf ("Board_name     = %s\n", appBoardInfo->board_name);
	      printf ("EEPROM version :%d.%d \n", appBoardInfo->version_major, 
                appBoardInfo->version_minor);
        printf ("Config option  = 0x%02lx \n", appBoardInfo->config_option);
      }
    }
  }
  else {
    if (E_EEPROM_I2CREAD == eepromStatus) {

      UTL_TRACE (TRACE_ERROR, "App board not connected\n");
      UTL_TRACE (TRACE_ERROR, "JAMR2.1 App board required to run this application\n");

      retVal = -1;
      goto exit1;
    }
    else {
      UTL_TRACE (TRACE_ERROR, "Unable to read EEPROM in app board\n");
    }
  }

  UTL_TRACE(TRACE_INFO, "Video capture std: %s\n", argv[1]);
  UTL_TRACE(TRACE_INFO, "Maximum frames   : %d\n", numFrames);

  /* Check if the debug level is set */
  configTraceLevel (&currDebugLevel);
  
  /* 
   * Open the capture driver. Query the resolution from the capture driver
   */
  retVal = initCapture();
  if (0 > retVal) {
    UTL_TRACE(TRACE_ERROR, "Error in opening capture device\n");
    return retVal;
  }

  /* Open the Display driver. */
  retVal = initDisplay();
  if (0 > retVal) {
    UTL_TRACE(TRACE_ERROR, "Error in opening display device\n");
    return retVal;
  }

restart:

  /* Setup the capture driver Step includes
   * Set the format according to the resolution queried.
   * request for buffer descriptors for userpointer buffers
   */
  retVal = setupCapture ();
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Error in setting up of capture\n");
    return retVal;
  }

  /* Setup the display driver Step includes
   * Set the format according to the resolution queried by capture.
   * request for buffer descriptors for userpointer buffers
   */
  retVal = setupDisplay();
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Error in setting up of Display\n");
    return retVal;
  }

  /* As application works on userPointer, Physically contiguoues buffer needs 
   * to be allocated for this.  Below API allocates this setsup the buffers.
   * later capture and display uses these buffers
   */
  retVal = setupBuffers();
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Error in setting up of Buffers\n");
    return retVal;
  }

  /* Total 8 buffers are allocated using fbdev, 4 buffers are primed to
   * capture driver.
   */
  retVal = queueCaptureBuffers();
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Error in queuing capture buffers\n");
    return retVal;
  }

  /* Total 8 buffers are allocated using fbdev, 4 buffers are primed to
   * display driver.
   */
  retVal = queueDisplayBuffers();
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Error in queuing display buffers\n");
    return retVal;
  }

  /* Start display driver always first. This is because display driver
   * takes 2 frames to come out of start. If capture is started first
   * frame drops will be seen, since capture will already complete two
   * frame by time display starts
   */
  retVal = startDisplay();
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Error starting Display \n");
    return retVal;
  }

  /* Start capture driver after display driver */
  retVal = startCapture();
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Error starting capture \n");
    return retVal;
  }

  /* Get time of day to calculate FPS */
  gettimeofday(&before, NULL);

  /* Start the steady state loop. Following steps are followed
   * 1 dequeue buffer from capture
   * 2 dequeue buffer from display
   * 3 exchange capture and display buffer pointers
   * 4 queue dislay buffer pointer to capture
   * 5 queue capture buffer pointer to display
   */
  for (i = 0; ((i < numFrames) || (0 == numFrames)); i++) {

    /* DQ capture buffer */
    retVal = ioctl(captObj.fd, VIDIOC_DQBUF, &captObj.buf);
    if (0 > retVal) {
      perror("VIDIOC_DQBUF\n");
      UTL_TRACE (TRACE_ERROR, "VIDIOC_DQBUF\n");
      return -1;
    }

    /* Because of IP bugs, capture hardware gets locked up once in
     * a while. In that case DQbuf will return  V4L2_BUF_FLAG_ERROR
     * in flags.
     */
    if (V4L2_BUF_FLAG_ERROR & captObj.buf.flags) {

      UTL_TRACE (TRACE_ERROR, "Capture hardware lock up error, resetting capture & Display" \
                              "\nThis message can be ignored\n");

      /* If DQbuf returned error check for the hardware lockup
       */
      retVal = ioctl (captObj.fd, TICAPT_CHECK_OVERFLOW, &captObj.over_flow);
      if (0 > retVal) {
        perror("TICAPT_CHECK_OVERFLOW\n");
        UTL_TRACE (TRACE_ERROR, "TICAPT_CHECK_OVERFLOW\n");
        return -1;
      } 
      else {
        /* If hardware locked up, restart display and
         * capture driver
	       */
        if (captObj.over_flow.porta_overflow) {
          UTL_TRACE (TRACE_ERROR, "Port a overflowed...............\n");
          cap_disp_restart = 1;
        }

        if (captObj.over_flow.portb_overflow) {
          UTL_TRACE (TRACE_ERROR, "Port b overflowed...............\n");
          cap_disp_restart = 1;
        }

        if (cap_disp_restart) {

          stopCapture ();
          stopDisplay ();
          goto restart;
        } /* if cap_disp_restart */
      } /* else retVal */
    } /* if V4L2_BUF_FLAG_ERROR */

    /* DQ display buffer */
    retVal = ioctl(dispObj.fd, VIDIOC_DQBUF, &dispObj.buf);
    if (0 > retVal) {
      perror("VIDIOC_DQBUF Display\n");
      UTL_TRACE (TRACE_ERROR, "VIDIOC_DQBUF Display\n");
      return -1;
    }

    /* Exchange display and capture buffer pointers */
    temp_buf.m.userptr = captObj.buf.m.userptr;
    captObj.buf.m.userptr = dispObj.buf.m.userptr;
    dispObj.buf.m.userptr = temp_buf.m.userptr;

    /* Queue the capture buffer with updated address */
    captObj.buf.type = V4L2_BUF_TYPE_VIDEO_CAPTURE;

    retVal = ioctl(captObj.fd, VIDIOC_QBUF, &captObj.buf);
    if (0 > retVal) {
      perror("VIDIOC_QBUF\n");
      UTL_TRACE (TRACE_ERROR, "VIDIOC_QBUF\n");
      return -1;
    }

    dispObj.buf.type = V4L2_BUF_TYPE_VIDEO_OUTPUT;
    dispObj.buf.memory = V4L2_MEMORY_USERPTR;
    dispObj.buf.length = captObj.fmt.fmt.pix.sizeimage;

    /* Queue the display buffer back with updated address */
    retVal = ioctl(dispObj.fd, VIDIOC_QBUF, &dispObj.buf);
    if (0 > retVal) {
      perror("VIDIOC_QBUF Display\n");
      UTL_TRACE (TRACE_ERROR, "VIDIOC_QBUF Display\n");
      return -1;
    }

    if (0 == (i % 100)) {
      UTL_TRACE (TRACE_DEBUG, "Frames processed=%d\n", i);
    }

    /* Mandatory status message once in a 30 seconds */
    if (0 == (i % (30*30))) {

      UTL_TRACE (TRACE_MUST, "Frames processed=%d\n", i);
    }

  }

  /* Get end time to calculate FPS */
  gettimeofday(&after, NULL);

  /* Calculate FPS */
  calc_result_time(&result, &after, &before);

  UTL_TRACE (TRACE_INFO, "Frame rate = %lu\n", numFrames/result.tv_sec);

  /* Stop capture driver */
  retVal = stopCapture();
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Error in stopping capture\n");
    return retVal;
  }

  /* Stop display driver */
  retVal = stopDisplay();
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Error in stopping display\n");
    return retVal;
  }

  /* Deinit capture driver */
  retVal = deInitCapture();
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Error in capture deInit\n");
    return retVal;
  }

  /* Deinit display driver */
  retVal = deInitDisplay ();
  if (0 > retVal) {
    UTL_TRACE (TRACE_ERROR, "Error in display deInit\n");
    return retVal;
  }

  close (fdDevMem);

  system ("echo 0 > /sys/devices/platform/vpss/display0/enabled");
  system ("echo 1080p-60 > /sys/devices/platform/vpss/display0/mode");
  system ("echo 1 > /sys/devices/platform/vpss/display0/enabled");

exit1:

  return 0; 
}

/******************************************************************************/
/*                  Utility functions Used in this app                        */
/******************************************************************************/

/*!
 * Utility function for displaying V4L2 format
 *
 * @param[IN] dev_name Name of the device (Capture/Display)
 *
 * @param[IN] fmt v4l2_format info
 * 
 * @return void 
 */
static void printV4L2Format(char *dev_name, struct v4l2_format *fmt)
{
  UTL_TRACE(TRACE_MUST, "=================================================\n");
  UTL_TRACE(TRACE_MUST, "%s Format:\n", dev_name);
  UTL_TRACE(TRACE_MUST, "=================================================\n");
  UTL_TRACE(TRACE_MUST, "fmt.type          = %d\n", fmt->type);
  UTL_TRACE(TRACE_MUST, "fmt.width         = %d\n", fmt->fmt.pix.width);
  UTL_TRACE(TRACE_MUST, "fmt.height        = %d\n", fmt->fmt.pix.height);
  UTL_TRACE(TRACE_MUST, "fmt.pixelformat   = %d\n", fmt->fmt.pix.pixelformat);
  UTL_TRACE(TRACE_MUST, "fmt.bytesperline  = %d\n",
            fmt->fmt.pix.bytesperline);
  UTL_TRACE(TRACE_MUST, "fmt.sizeimage     = %d\n", fmt->fmt.pix.sizeimage);
  UTL_TRACE(TRACE_MUST, "=================================================\n");
}

/*!
 * We derive buffers from fbdev for userpointer operation. We have to setupBuffers
 * fbdev to get enough number of buffers and with enough size
 *
 * @return 0  For success
 *        -1 For any failure
 */
static int setupBuffers (void)
{
  int bufferSize = 0;
  int i          = 0;
  unsigned int phyAddr = 0x9C000000;

  fdDevMem = open("/dev/mem", O_RDWR | O_SYNC);
  if (-1 == fdDevMem) {
    UTL_TRACE (TRACE_ERROR, "Unable to open /dev/mem\n");
    return -1;
  }

  UTL_TRACE (TRACE_DEBUG, "/dev/mem opened.\n");

  bufferSize = captObj.fmt.fmt.pix.sizeimage;
  bufferSize = (bufferSize + MAP_MASK ) & ~MAP_MASK;

  /* Mmap multiple pages appropriate for contains max number of bufs */
  bufferAddr[0] = mmap(0, (bufferSize *  MAX_BUFFER), (PROT_READ | PROT_WRITE),
                        MAP_SHARED, fdDevMem, (phyAddr & ~MAP_MASK));

  if (((void *) -1) == bufferAddr[0]) {
    return -1;
  }

  UTL_TRACE (TRACE_INFO, "Physically contiguous buffer at : 0x%x, size: %d\n",
             phyAddr, (bufferSize *  MAX_BUFFER));
  UTL_TRACE (TRACE_INFO, "Memory mapped at address %p.\n", bufferAddr[0]);

  for (i = 1; i < MAX_BUFFER; i++) {
    bufferAddr[i] = bufferAddr[i-1] + bufferSize;
  }

  /* Fill the buffer with blank */
  memset(bufferAddr[0], 0xFF, (bufferSize * MAX_BUFFER));

  return 0;
}

/*!
 * This is a utility function to calculate time  between start and stop. This
 * is used to calculate FPS
 *
 * @param[OUT] result timeval structure with subtracted time
 * @param[IN]  after  end time
 * @param[IN]  before start time
 *
 * @return void
 */
static void calc_result_time(struct timeval *result, struct timeval *after,
		struct timeval *before)
{
  /* Perform the carry for the later subtraction by updating "before" */
  if (after->tv_usec < before->tv_usec) {
    int nsec = (before->tv_usec - after->tv_usec) / 1000000 + 1;

    before->tv_usec -= 1000000 * nsec;
                before->tv_sec += nsec;
  }

  if (after->tv_usec - before->tv_usec > 1000000) {
    int nsec = (after->tv_usec - before->tv_usec) / 1000000;

    before->tv_usec += 1000000 * nsec;
    before->tv_sec -= nsec;
  }

  /* Compute the time remaining to wait, tv_usec is certainly positive.
   * */
  result->tv_sec = after->tv_sec - before->tv_sec;
  result->tv_usec = after->tv_usec - before->tv_usec;
	
  return;
}

/*!
 * Display usage help for this application
 * 
 * @param app_name Name of this app
 * 
 * @return void Nothing to return
 */
static void vlbd_show_usage(char *app_name)
{
  UTL_TRACE(TRACE_MUST, " \n");
  UTL_TRACE(TRACE_MUST, " Video Loopback app, \r\n");
  UTL_TRACE(TRACE_MUST, " Usage: %s <ntsc/pal> [frame cnt]\r\n", app_name);
  
  UTL_TRACE(TRACE_MUST, " \n");

  return;
}

/*!
 *  @brief      Configure the debugging system, This functio queries the
 *              environment varible VLBD_DEBUG (Firmware Loader debug), &
 *              if it is present then set the debug trace level as per that
 *              otherwise defauls teh debug trace to warning. This function
 *              also displays the help for VLBD_DEBUG.
 *
 *  @param[OUT]  pCurrLevel - Updated with the debug trace level
 *
 *  @retval     void
 *
 *  @pre        None
 *
 *  @sa         None
 */
static void configTraceLevel (char *pCurrLevel)
{
  uint8_t *dbgLevelStr = NULL;
  uint8_t status = TRUE;

  dbgLevelStr = (uint8_t *) getenv ("VLBD_DEBUG");

  if (NULL != dbgLevelStr) {
    if(0 == strcmp ((char *) dbgLevelStr, "error")) {
      *pCurrLevel = TRACE_ERROR;
    }
    else if(0 == strcmp ((char *) dbgLevelStr, "warning")) {
      *pCurrLevel = TRACE_WARNING;
    }
    else if(0 == strcmp ((char *) dbgLevelStr, "info")) {
      *pCurrLevel = TRACE_INFO;
    }
    else if(0 == strcmp ((char *) dbgLevelStr, "debug")) {
      *pCurrLevel = TRACE_DEBUG;
    }
    else if(0 == strcmp ((char *) dbgLevelStr, "log")) {
      *pCurrLevel = TRACE_LOG;
    }
    else {
      status = FALSE;
      UTL_TRACE (TRACE_MUST, "Invalid Firmware Loader debugging : %s\n",
                 dbgLevelStr);
    }
  }
  else {
      status = FALSE;
      UTL_TRACE (TRACE_MUST, "Firmware Loader debugging not configured\n");
  }

  if (FALSE == status) {
      UTL_TRACE (TRACE_MUST, "Default VLBD_DEBUG: warning\n");
      *pCurrLevel = TRACE_WARNING;
  }
  else {
      UTL_TRACE (TRACE_MUST, "Current VLBD_DEBUG = %s\n", dbgLevelStr);
  }

  UTL_TRACE (TRACE_MUST, "Allowed VLBD_DEBUG levels: error, warning, info, debug, log\n");

}


/*============================================================================*/
/*             Utility functions for identifying daughter card                */
/*============================================================================*/
/*!
 *  @brief      Read the content of EEPROM present in app board of the EVM
 *
 *  @param[OUT]  Filled with the content of EEPROM 
 *
 *  @param[IN]  size of the buffer
 *
 *  @retval     E_EEPROM_SUCCESS on successful read of EEPROM
 *              E_EEPROM_I2CREAD On I2C read error, which happens if daughter card
 *                               note present
 *              E_EEPROM_INTERNAL Any other failure
 *
 *  @sa         None
 */
static int readEEPROM(char *buffer, unsigned int size)
{
  int i2cfd = 0;
  int sysCallStatus = 0;
  struct i2c_msg i2cMsgs[2];
  struct i2c_rdwr_ioctl_data i2cIoctldata;
  int eepromOffset = 0;
  unsigned char srcAddr[2];
  int retVal = E_EEPROM_INTERNAL;

  /* open I2c channel */
  i2cfd = open((const char *)I2C_DEVICE_NAME, O_RDWR);
  if (0 > i2cfd) {

    perror("Failed to open i2c device\n");
    retVal = E_EEPROM_INTERNAL;
  }
  else {

    srcAddr[0] = (eepromOffset >> 8);
    srcAddr[1] = (eepromOffset & 0xff);

    i2cMsgs[0].addr  = EEPROM_I2C_ADDR;
    i2cMsgs[0].flags = 0;
    i2cMsgs[0].len   = 1;
    i2cMsgs[0].buf   = (unsigned char *) srcAddr;
    i2cMsgs[1].addr  = EEPROM_I2C_ADDR;
    i2cMsgs[1].flags = I2C_M_RD /* Read/write flag */;
    i2cMsgs[1].len   = size;
    i2cMsgs[1].buf   = (unsigned char *) buffer;

    i2cIoctldata.msgs = i2cMsgs;
    i2cIoctldata.nmsgs = 2;

    sysCallStatus = ioctl (i2cfd, I2C_RDWR, &i2cIoctldata);

    if (sysCallStatus < 0) {
      perror ("ioctl I2C_RDWR failure");
      retVal = E_EEPROM_I2CREAD;
    }
    else {

      close(i2cfd);
      retVal = E_EEPROM_SUCCESS;
    }
  }

  return retVal;
}

/* End Of File */
