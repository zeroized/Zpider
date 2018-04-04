var showSwitch = false;

window.onload = function () {
    window.addEventListener("click", function (event) {
        if (showSwitch) {
            window.parent.showElement(event);
            event.preventDefault();
        }
    }, false);
    $("a").click(function () {
        if (!showSwitch) {
            window.parent.loadPage($(this).attr("data-href"));
        }
    });
    window.parent.finishLoading();
};

function getEle(rule) {
    // console.log(rule);
    return $(rule);
}

function turn(bool) {
    showSwitch = bool;
}