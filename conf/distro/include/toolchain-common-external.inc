# Black magic to automatically set SDK_PATH out of PATH - don't touch this!
# Works with pretty much any external toolchain, like CS Lite or MV Pro 5
# Handles cross-toolchain vs. native toolchain
# SDK_PATH can be overwritten externally by META_SDK_PATH, e.g. for SDK
TOOLCHAIN_SYSPATH := "${@os.path.abspath(os.path.dirname(bb.which(bb.data.getVar('PATH', d, 1), bb.data.getVar('TARGET_PREFIX', d, 1)+'cpp'))+'/../'+bb.data.getVar('TARGET_SYS', d, 1))}"
TOOLCHAIN_PATH := "${@bool(bb.data.getVar('TOOLCHAIN_SYSPATH', d, 1)) and (os.path.exists(bb.data.getVar('TOOLCHAIN_SYSPATH', d, 1)) and os.path.dirname(bb.data.getVar('TOOLCHAIN_SYSPATH', d, 1)) or bb.fatal('No valid toolchain in PATH')) or ''}"
