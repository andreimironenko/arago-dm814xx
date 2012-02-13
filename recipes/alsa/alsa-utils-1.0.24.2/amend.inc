# Fix running aplay and arecord in background
# https://bugtrack.alsa-project.org/alsa-bug/view.php?id=5462
SRC_URI += "file://alsa-aplay-interactive-patch.diff"

PR_append = "-arago1"
