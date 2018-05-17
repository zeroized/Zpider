<a class="btn btn-default btn-block btn-collapse" id="collapse-button"
   onclick="collapseLeftSidebar()">
    <span class="sidebar-label">|||</span>
    <span class="glyphicon glyphicon-menu-hamburger" style="display: none"></span>
</a>
<div class="btn-group-vertical sidebar-content">
    <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#crawler-status" class="btn btn-default" id="crawler-status-nav"
                              onclick="selectPanel('#crawler-status')"
                              data-toggle="tab" aria-controls="crawler-status">
            <span class="glyphicon glyphicon-dashboard"></span>
            <span class="sidebar-label">爬虫状态</span>
        </a></li>
        <li><a href="#crawler-result" class="btn btn-default" id="crawler-result-nav"
                              onclick="selectPanel('#crawler-result')"
                              data-toggle="tab" aria-controls="crawler-result">
            <span class="glyphicon glyphicon-dashboard"></span>
            <span class="sidebar-label">查看结果</span>
        </a></li>
    </ul>
</div>