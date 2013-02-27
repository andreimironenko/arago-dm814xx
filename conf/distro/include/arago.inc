#AM: Deployment of the images, ipk and sdk must be defined by PRODUCT/RELEASE
#    and the MACHINE
DEPLOY_DIR_IMAGE = "${DEPLOY_DIR}/images/${PRODUCT}.${PRODUCT_RELEASE}/${MACHINE}"
DEPLOY_DIR_IPK = "${DEPLOY_DIR}/ipk/${PRODUCT}.${PRODUCT_RELEASE}/${MACHINE}"
SDK_DEPLOY = "${DEPLOY_DIR}/sdk/${PRODUCT}.${PRODUCT_RELEASE}/${MACHINE}"

def filter_out(f, str, d):
    from re import match
    return " ".join(filter(lambda x: not match(f, x, 0), str.split()))
