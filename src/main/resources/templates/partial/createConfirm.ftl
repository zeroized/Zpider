<div class="row">
    <div class="col-sm-4">
        <div class="panel panel-default basic-panel">
            <div class="panel-heading">
                <h3 class="panel-title">种子地址</h3>
            </div>
            <ul class="list-group" id="seed-confirm">

            </ul>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="panel panel-default basic-panel">
            <div class="panel-heading">
                <h3 class="panel-title">允许访问的域名</h3>
            </div>
            <ul class="list-group" id="domain-confirm">

            </ul>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="panel panel-default basic-panel">
            <div class="panel-heading">
                <h3 class="panel-title">爬取地址前缀</h3>
            </div>
            <ul class="list-group" id="crawl-confirm">

            </ul>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-8">
        <div class="panel panel-default basic-panel">
            <div class="panel-heading">
                <h3 class="panel-title">字段</h3>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <td style="width: 20%;">字段</td>
                    <td style="width: 10%;">类型</td>
                    <td style="width: 70%;">规则</td>
                </tr>
                </thead>
                <tbody id="column-confirm">
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-sm-4">
        <div class="panel panel-default basic-panel">
            <div class="panel-heading">
                <h3 class="panel-title">高级设置</h3>
            </div>
            <table class="table adv-opt-table">
                <tbody>
                <tr>
                    <td>爬虫工人数</td>
                    <td>1</td>
                </tr>
                <tr>
                    <td>最大爬取深度</td>
                    <td>20</td>
                </tr>
                <tr>
                    <td>最大访问页面数</td>
                    <td>10000</td>
                </tr>
                <tr>
                    <td>等待时间</td>
                    <td>1000ms</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-2 col-sm-offset-10">
        <button class="btn btn-primary btn-block" onclick="startCrawler(event)">创建爬虫</button>
    </div>
</div>