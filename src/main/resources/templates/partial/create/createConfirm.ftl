<div class="row" style="margin-top: 15px">
    <div class="col-sm-offset-8 col-sm-2">
        <button class="btn btn-primary btn-block" onclick="createCrawler()">创建爬虫</button>
    </div>
    <div class="col-sm-2">
        <button class="btn btn-danger btn-block">重新设置</button>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <div class="panel panel-default basic-panel">
            <div class="panel-heading">
                <h3 class="panel-title">基础设置</h3>
            </div>
            <table class="table adv-opt-table">
                <tbody>
                <tr>
                    <td>爬虫名称</td>
                    <td id="confirm-basic-name"></td>
                </tr>
                <tr>
                    <td>种子地址</td>
                    <td id="confirm-basic-seed-size"></td>
                </tr>
                <tr>
                    <td>允许访问域名</td>
                    <td id="confirm-basic-domain-size"></td>
                </tr>
                <tr>
                    <td>爬取地址前缀</td>
                    <td id="confirm-basic-crawl-size"></td>
                </tr>
                <tr>
                    <td>字段</td>
                    <td id="confirm-basic-column-size"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="panel panel-default basic-panel">
            <div class="panel-heading">
                <h3 class="panel-title">高级设置</h3>
            </div>
            <table class="table adv-opt-table">
                <tbody>
                <tr>
                    <td>爬虫工人数</td>
                    <td id="confirm-adv-workers">1</td>
                </tr>
                <tr>
                    <td>最大爬取深度</td>
                    <td id="confirm-adv-max-depth">20</td>
                </tr>
                <tr>
                    <td>最大访问页面数</td>
                    <td id="confirm-adv-max-page">10000</td>
                </tr>
                <tr>
                    <td>等待时间</td>
                    <td id="confirm-adv-polite-wait">1000ms</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4"  id="seed-confirm">
        <div class="panel panel-default basic-panel">
            <div class="panel-heading">
                <h3 class="panel-title">种子地址</h3>
            </div>
            <ul class="list-group">

            </ul>
        </div>
    </div>
    <div class="col-sm-4" id="domain-confirm">
        <div class="panel panel-default basic-panel">
            <div class="panel-heading">
                <h3 class="panel-title">允许访问的域名</h3>
            </div>
            <ul class="list-group">

            </ul>
        </div>
    </div>
    <div class="col-sm-4" id="crawl-confirm">
        <div class="panel panel-default basic-panel">
            <div class="panel-heading">
                <h3 class="panel-title">爬取地址前缀</h3>
            </div>
            <ul class="list-group">

            </ul>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-8" id="column-confirm">
        <div class="panel panel-default basic-panel">
            <div class="panel-heading">
                <h3 class="panel-title">字段</h3>
            </div>
            <table class="table" style="table-layout: fixed">
                <thead>
                <tr>
                    <td style="width: 10%;">字段</td>
                    <td style="width: 10%">类型</td>
                    <td class="table-column-rule">规则</td>
                </tr>
                </thead>
                <tbody >
                </tbody>
            </table>
        </div>
    </div>
</div>