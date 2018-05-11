<div class="col-sm-7">
    <div class="panel panel-default basic-panel">
        <table class="table table-hover">
            <thead>
            <tr>
                <td>爬虫序列号</td>
                <td>爬虫状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody id="crawl-info">
            </tbody>
        </table>
    </div>
</div>
<div class="col-sm-5" style="display: none" id="status-config-info">
    <div class="panel panel-default basic-panel">
        <div class="panel-heading">
            <h3 class="panel-title">爬虫配置</h3>
        </div>
        <table class="table table-hover adv-opt-table">
            <tbody>
            <tr>
                <td>序列号</td>
                <td id="status-uuid"></td>
            </tr>
            <tr>
                <td>爬虫名称</td>
                <td id="status-name"></td>
            </tr>
            <tr>
                <td>种子地址</td>
                <td id="status-seed-size"></td>
            </tr>
            <tr>
                <td>允许访问的域名</td>
                <td id="status-domain-size"></td>
            </tr>
            <tr>
                <td>爬取地址前缀</td>
                <td id="status-crawl-size"></td>
            </tr>
            <tr>
                <td>字段</td>
                <td id="status-column-size"></td>
            </tr>
            <tr>
                <td>爬虫工人数</td>
                <td id="status-workers"></td>
            </tr>
            <tr>
                <td>最大爬取深度</td>
                <td id="status-max-depth"></td>
            </tr>
            <tr>
                <td>最大访问页面数</td>
                <td id="status-max-page"></td>
            </tr>
            <tr>
                <td>等待时间</td>
                <td id="status-polite-wait"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="panel panel-default sidebar-right">
        <ul class="nav nav-pills nav-justified">
            <li class="active">
                <a href="#status-seed"
                   aria-controls="status-seed" role="tab" data-toggle="tab">种子</a>
            </li>
            <li>
                <a href="#status-domain"
                   aria-controls="status-domain" role="tab" data-toggle="tab">域名</a>
            </li>
            <li>
                <a href="#status-crawl"
                   aria-controls="status-crawl" role="tab" data-toggle="tab">抓取</a>
            </li>
            <li>
                <a href="#status-column"
                   aria-controls="status-column" role="tab" data-toggle="tab">字段</a>
            </li>
        </ul>
        <div class="panel-body">
            <div class="tab-content sidebar-right-content">
                <div role="tabpanel" class="tab-pane active" id="status-seed">
                    <ul class="list-group" id="status-seed-list">

                    </ul>
                </div>
                <div role="tabpanel" class="tab-pane" id="status-domain">
                    <ul class="list-group" id="status-domain-list">

                    </ul>
                </div>
                <div role="tabpanel" class="tab-pane" id="status-crawl">
                    <ul class="list-group" id="status-crawl-list">

                    </ul>
                </div>
                <div role="tabpanel" class="tab-pane" id="status-column">
                    <table class="table" style="table-layout: fixed">
                        <thead>
                        <tr>
                            <td style="width: 20%">字段名</td>
                            <td style="width: 15%">类型</td>
                            <td class="table-column-rule">规则</td>
                        </tr>
                        </thead>
                        <tbody id="status-column-list">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>