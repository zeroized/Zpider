<div class="col-md-7">
    <div class="panel panel-default basic-panel">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label" for="spider-name">爬虫名称</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="spider-name" id="spider-name">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2 col-sm-offset-10">
                    <button class="btn btn-primary btn-block" onclick="saveName()">保存</button>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label" for="seed">种子地址</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <input type="text" class="form-control" name="seed" id="seed-input" placeholder="输入地址" required>
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-default" onclick="addSeed()">添加</button>
                        </span>
                    </div>
                </div>
                <div class="col-md-9 col-md-offset-3">
                    <span class="help-block">将从以上地址开始爬取网页</span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label" for="domain-input">允许访问的域名</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <input type="text" class="form-control" id="domain-input">
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-default" onclick="addDomain()">添加</button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label" for="crawl-input">爬取地址前缀</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <input type="text" class="form-control" id="crawl-input">
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-default" onclick="addCrawl()">添加</button>
                        </span>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="col-md-5" style="height: 100%;">
    <div class="panel panel-default slider-right">
        <ul class="nav nav-pills nav-justified">
            <li class="active">
                <a href="#seed-show-tab"
                   aria-controls="seed-show-tab" role="tab" data-toggle="tab">种子</a>
            </li>
            <li>
                <a href="#domain-show-tab"
                   aria-controls="domain-show-tab" role="tab" data-toggle="tab">域名</a>
            </li>
            <li>
                <a href="#crawl-show-tab"
                   aria-controls="crawl-show-tab" role="tab" data-toggle="tab">抓取</a>
            </li>
        </ul>
        <div class="panel-body">
            <div class="tab-content slider-right-content">
                <div role="tabpanel" class="tab-pane active" id="seed-show-tab">
                    <table class="table">
                        <thead>
                        <tr>
                            <td>种子地址</td>
                            <td>移除</td>
                        </tr>
                        </thead>
                        <tbody id="seed">
                        </tbody>
                    </table>
                </div>
                <div role="tabpanel" class="tab-pane" id="domain-show-tab">
                    <table class="table">
                        <thead>
                        <tr>
                            <td>允许的域名</td>
                            <td>移除</td>
                        </tr>
                        </thead>
                        <tbody id="domain">
                        </tbody>
                    </table>
                </div>
                <div role="tabpanel" class="tab-pane" id="crawl-show-tab">
                    <table class="table">
                        <thead>
                        <tr>
                            <td>抓取域名前缀</td>
                            <td>移除</td>
                        </tr>
                        </thead>
                        <tbody id="crawl">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>