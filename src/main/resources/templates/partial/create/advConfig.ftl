<div class="col-sm-7">
    <div class="panel panel-default basic-panel">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-3 control-label" for="adv-workers">爬虫工人数</label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" id="adv-workers" value="1">
                </div>
                <div class="col-sm-9 col-sm-offset-3">
                    <span class="help-block">同时进行工作的爬虫数量</span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="adv-max-depth">最大爬取深度</label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" id="adv-max-depth" value="10">
                </div>
                <div class="col-sm-9 col-sm-offset-3">
                    <span class="help-block">从种子地址开始的最大链接跳转次数</span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="adv-max-page">最大访问页面数</label>
                <div class="col-sm-9">
                    <input type="number" class="form-control" id="adv-max-page" value="1000">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="adv-polite-wait">等待时间</label>
                <div class="col-sm-9">
                    <div class="input-group">
                        <input type="number" class="form-control" id="adv-polite-wait" value="1000">
                        <div class="input-group-addon">ms</div>
                    </div>
                </div>
                <div class="col-sm-9 col-sm-offset-3">
                    <span class="help-block">每个爬虫线程爬取下一个页面前等待的时间</span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 col-sm-offset-10">
                    <button type="button" class="btn btn-primary btn-block" onclick="saveAdvOpt()">保存</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="col-sm-5">
    <div class="panel panel-default basic-panel">
        <div class="panel-heading">
            <h3 class="panel-title">高级设置</h3>
        </div>
        <table class="table adv-opt-table">
            <tbody>
            <tr>
                <td>爬虫工人数</td>
                <td id="show-adv-workers">1</td>
            </tr>
            <tr>
                <td>最大爬取深度</td>
                <td id="show-adv-max-depth">20</td>
            </tr>
            <tr>
                <td>最大访问页面数</td>
                <td id="show-adv-max-page">10000</td>
            </tr>
            <tr>
                <td>等待时间</td>
                <td id="show-adv-polite-wait">1000ms</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>