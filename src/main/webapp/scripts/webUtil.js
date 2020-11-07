function getContextPath() {
    return location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}
