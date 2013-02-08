DEPLOY_DIR_IMAGE = "${DEPLOY_DIR}/images/${PRODUCT}.${PRODUCT_RELEASE}/${MACHINE}"

def filter_out(f, str, d):
    from re import match
    return " ".join(filter(lambda x: not match(f, x, 0), str.split()))
