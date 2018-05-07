var leftSlider = {
    collapsed: false,
    activePanel: "#content-config"
};

var rightSlider = {
    collapsed: false
};

function collapseLeftSlider() {
    var left_slider = $("#left");
    var content = $("#content");
    if (!leftSlider.collapsed) {
        left_slider.addClass("slider-collapse");
        left_slider.removeClass("col-sm-2");
        $(".slider-label").hide();
        content.attr("class", "col-sm-12 content content-collapse");

        $("#collapse-button>span:first").hide();
        $("#collapse-button>span:last").show();

        leftSlider.collapsed = true;
    } else {
        left_slider.addClass("col-sm-2");
        $(".slider-label").show();
        left_slider.removeClass("slider-collapse");

        content.attr("class", "col-sm-10 content");

        $("#collapse-button>span:first").show();
        $("#collapse-button>span:last").hide();

        leftSlider.collapsed = false;
    }
}

function selectPanel(labelId) {
    // $(leftSlider.activePanel).removeClass("active");
    // $(labelId).addClass("active");
    // leftSlider.activePanel=labelId;
    $(labelId).tab('show');
}

function collapseRightSlider() {
    if (!rightSlider.collapsed) {
        $("#content-slider").hide();
        $("#slider-right-collapsed").show();
        $("#content-frame").attr("class", "col-sm-11");
        rightSlider.collapsed = true;
    } else {
        $("#content-frame").attr("class", "col-sm-9");
        $("#content-slider").show();
        $("#slider-right-collapsed").hide();
        rightSlider.collapsed = false;
    }

}