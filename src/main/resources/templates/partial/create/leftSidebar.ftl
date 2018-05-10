<a class="btn btn-default btn-block btn-collapse" id="collapse-button"
   onclick="collapseLeftSidebar()">
    <span class="sidebar-label">|||</span>
    <span class="glyphicon glyphicon-menu-hamburger" style="display: none"></span>
</a>
<div class="btn-group-vertical sidebar-content">
    <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#basic-tab" class="btn btn-default" id="basic-config"
                              onclick="selectPanel('#basic-config')"
                              data-toggle="tab" aria-controls="basic-tab">
            <span class="glyphicon glyphicon-cog"></span>
            <span class="sidebar-label">基本配置</span>
        </a></li>
        <li><a href="#adv-tab" class="btn btn-default" id="adv-config"
               onclick="selectPanel('#adv-config')"
               data-toggle="tab" aria-controls="adv-tab">
            <span class="glyphicon glyphicon-wrench"></span>
            <span class="sidebar-label">高级配置</span>
        </a></li>
        <li><a href="#content-tab" class="btn btn-default" id="content-config"
               onclick="selectPanel('#content-config')"
               data-toggle="tab" aria-controls="content-tab">
            <span class="glyphicon glyphicon-modal-window"></span>
            <span class="sidebar-label">内容设置</span>
        </a></li>
        <li><a href="#confirm-tab" class="btn btn-primary btn-block btn-create" id="content-confirm"
               onclick="selectPanel('#content-confirm');loadConfirm()"
               data-toggle="tab" aria-controls="confirm-tab">
            <span class="glyphicon glyphicon-play"></span>
            <span class="sidebar-label">创建爬虫</span>
        </a></li>
    </ul>
</div>