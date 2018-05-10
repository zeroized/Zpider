var leftSidebar = {
    collapsed: false,
    activePanel: "#content-config"
};

var rightSidebar = {
    collapsed: false
};

function collapseLeftSidebar() {
    var left_sidebar = $("#left");
    var content = $("#content");
    if (!leftSidebar.collapsed) {
        $(".sidebar-label").hide();
        left_sidebar.attr("class","sidebar sidebar-left sidebar-collapse");
        content.attr("class", "col-sm-12 content content-collapse");

        $("#collapse-button>span:first").hide();
        $("#collapse-button>span:last").show();

        leftSidebar.collapsed = true;
    } else {
        $(".sidebar-label").show();
        left_sidebar.attr("class","col-sm-2 sidebar sidebar-left");
        content.attr("class", "col-sm-10 content");

        $("#collapse-button>span:first").show();
        $("#collapse-button>span:last").hide();

        leftSidebar.collapsed = false;
    }
}

function selectPanel(labelId) {
    $(labelId).tab('show');
}

function collapseRightSidebar() {
    if (!rightSidebar.collapsed) {
        $("#content-slider").hide();
        $("#slider-right-collapsed").show();
        $("#content-frame").attr("class", "col-sm-11");
        rightSidebar.collapsed = true;
    } else {
        $("#content-frame").attr("class", "col-sm-9");
        $("#content-slider").show();
        $("#slider-right-collapsed").hide();
        rightSidebar.collapsed = false;
    }

}