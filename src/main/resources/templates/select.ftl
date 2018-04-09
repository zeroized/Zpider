<!DOCTYPE html>
<html lang="en">
<head>
    <title>spider</title>
    <meta charset="UTF-8">
    <script src="/js/jquery.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/windowFun.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<nav class="navbar navbar-default navbar-static-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spider</a>
        </div>

        <p class="navbar-text navbar-right">version 0.0.1</p>
    </div><!-- /.container-fluid -->
</nav>
<div class="col-md-3 slider" id="left">
    <a style="float: right;margin-top: 20px;color: #cc450c" href="javascript:void " onclick="myClick(0);"><<</a>
    <h3>新建爬虫</h3>
    <form>
        <div class="form-group">
            <label for="spider-name">爬虫名称</label>
            <input type="text" class="form-control" name="spider-name" id="spider-name">
        </div>
        <div class="form-group">
            <label for="seed">种子地址</label>
            <div class="input-group">
                <span class="input-group-addon">http://</span>
                <input type="text" class="form-control" name="seed" id="seed-input" placeholder="输入地址" required>
                <span class="input-group-btn">
                    <button type="button" class="btn btn-default" onclick="addSeed()">添加</button>
                </span>
            </div>
            <span class="help-block">将从以上地址开始爬取网页</span>
        </div>
        <div class="form-group">
            <label for="domain-input">允许访问的域名</label>
            <div class="input-group">
                <input type="text" class="form-control" id="domain-input">
                <span class="input-group-btn">
                    <button type="button" class="btn btn-default" onclick="addDomain()">添加</button>
                </span>
            </div>
        </div>
        <div class="form-group">
            <label for="crawl-input">爬取地址前缀</label>
            <div class="input-group">
                <input type="text" class="form-control" id="crawl-input">
                <span class="input-group-btn">
                    <button type="button" class="btn btn-default" onclick="addCrawl()">添加</button>
                </span>
            </div>
        </div>
        <a href="#advanceOpt" data-toggle="collapse" aria-expanded="false" aria-controls="advanceOpt">
            查看高级选项
        </a>
        <div class="collapse" id="advanceOpt">
            <div class="form-group">
                <label for="adv-workers">爬虫线程数</label>
                <input type="number" class="form-control" id="adv-workers" value="1">
            </div>
            <div class="form-group">
                <label for="adv-max-depth">最大爬取深度</label>
                <input type="number" class="form-control" id="adv-max-depth" value="10">
            <#--<span class="help-block"></span>-->
            </div>
            <div class="form-group">
                <label for="adv-max-page">最大爬取页面数</label>
                <input type="number" class="form-control" id="adv-max-page" value="500">
            </div>
            <div class="form-group">
                <label for="adv-polite-wait">等待时间</label>
                <div class="input-group">
                    <input type="number" class="form-control" id="adv-polite-wait" value="1000">
                    <div class="input-group-addon">ms</div>
                </div>
                <span class="help-block">每个爬虫线程爬取下一个页面前等待的时间</span>
            </div>
        </div>
        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#panel-seed"
                           aria-expanded="true" aria-controls="panel-seed">
                            种子地址
                        </a>
                    </h4>
                </div>
                <div id="panel-seed" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                    <table class="table">
                        <thead>
                        <tr>
                            <td>URL</td>
                        </tr>
                        </thead>
                        <tbody id="seed">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#panel-domain" aria-expanded="false" aria-controls="panel-domain">
                            允许访问的域名
                        </a>
                    </h4>
                </div>
                <div id="panel-domain" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <table class="table">
                        <thead>
                        <tr>
                            <td>URL</td>
                        </tr>
                        </thead>
                        <tbody id="domain">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#panel-crawl" aria-expanded="false" aria-controls="panel-crawl">
                            爬取地址前缀
                        </a>
                    </h4>
                </div>
                <div id="panel-crawl" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <table class="table">
                        <thead>
                        <tr>
                            <td>prefix</td>
                        </tr>
                        </thead>
                        <tbody id="crawl">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFour">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#panel-column" aria-expanded="false" aria-controls="panel-column">
                            字段
                        </a>
                    </h4>
                </div>
                <div id="panel-column" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                    <table class="table">
                        <thead>
                        <tr>
                            <td>字段名</td>
                        </tr>
                        </thead>
                        <tbody id="column">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <button type="button" class="btn btn-primary" onclick="startCrawler(event)">启动爬虫</button>
    </form>
</div>
<div class="col-md-6 content"  id="content">
    <div id="leftshow"  style="float:left;width: 18px;margin-top: 15px;display: none">
    <a href="javascript:void " onclick="myClick(1);"style="color: #cc2b3b">>></a>
    </div>
    <div id="rightshow"  style="float:right;width: 18px;margin-top: 15px;display: none">
        <a href="javascript:void " onclick="myClick(2);"style="color: #cc2b3b"><<</a>
    </div>
    <div class="panel panel-default"style="margin-left: 18px;margin-right: 18px">
        <div class="panel-heading">
            <div class="row">
                <form class="form col-md-9" onsubmit="return loadPage()">
                    <label class="sr-only" for="url">URL</label>
                    <div class="input-group">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default" onclick="frame_forward()">
                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
                            </button>
                            <button type="button" class="btn btn-default" onclick="frame_backward()">
                                <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
                            </button>
                        </div>
                        <div class="input-group-addon">http://</div>
                        <input class="form-control" id="url" type="text" value="meishij.net">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default" onclick="refresh()">
                                <span class="glyphicon glyphicon-refresh"></span>
                            </button>
                            <button type="button" class="btn btn-default hidden" id="loading">
                                <span class="glyphicon glyphicon-time"></span>
                            </button>
                            <button type="button" class="btn btn-primary" id="view-allow"
                                    data-toggle="tooltip" data-placement="top" title="正常浏览页面">
                                <span class="glyphicon glyphicon-eye-open"></span>
                            </button>
                            <button type="button" class="btn btn-default hidden" id="view-forbid"
                                    data-toggle="tooltip" data-placement="top" title="页面被解析，点击链接将无法跳转">
                                <span class="glyphicon glyphicon-eye-close"></span>
                            </button>
                            <button type="submit" class="btn btn-primary">
                                GO
                            </button>
                        </div>
                    </div>
                </form>
                <div class="btn-group col-md-3" style="margin-top: 0px">
                    <button type="button" class="btn btn-primary pull-right" onclick="turn(true)"
                            data-toggle="tooltip" data-placement="top" title="开始解析页面，将不能正常浏览页面">解析
                    </button>
                    <button type="button" class="btn btn-danger pull-right" onclick="turn(false)"
                            data-toggle="tooltip" data-placement="top" title="停止解析，可以正常浏览页面">停止
                    </button>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <iframe class="frame" id="target_page" frameborder="0"></iframe>
        </div>
    </div>
</div>
<div class="col-md-3 slider" id="right">
    <a style="float: right;margin-top: 20px;color: #cc450c" href="javascript:void " onclick="myClick(3);">>></a>
    <h5>节点信息</h5>
    <form>
        <div class="form-group-sm">
            <label for="tagName">Tag Name</label>
            <input id="tagName" class="form-control" disabled>
        </div>
        <div class="form-group-sm">
            <label for="id">ID</label>
            <input id="id" class="form-control" disabled>
        </div>
        <div class="form-group-sm">
            <label for="class">class</label>
            <input id="class" class="form-control" disabled>
        </div>
        <div class="form-group-sm">
            <label for="html">HTML</label>
            <input id="html" class="form-control" disabled>
        </div>
        <div class="form-group-sm">
            <label for="column-input-dom">DOM结构</label>
            <textarea id="column-input-dom" class="form-control"></textarea>
        </div>
    </form>
    <form id="add-rule" onsubmit="return addColumn()">
        <h5>为此节点添加爬取规则</h5>
        <div class="form-group-sm">
            <label for="column-input-type">爬取对象</label>
            <select class="form-control" id="column-input-type">
                <option value="text" selected>text</option>
            <#--<option value="html">html</option>-->
            </select>
        </div>
        <div class="form-group-sm">
            <label for="column-input-name">数据字段名称</label>
            <input type="text" class="form-control" id="column-input-name" required>
        </div>
        <div class="form-group-sm">
            <div class="row">
                <div class="col-md-9">
                    <label for="test-select">测试爬取此条规则</label>
                </div>
                <div class="col-md-3">
                    <button type="button" class="btn btn-default btn-sm" onclick="testDom()">测试</button>
                </div>
            </div>
            <textarea class="form-control" id="test-select" disabled></textarea>
            <div class="help-block" id="test-select-length"></div>
        </div>
        <button type="submit" class="btn btn-default">确认添加此规则</button>
    </form>
</div>
</body>
<script type="text/javascript">
    var a1=1; //左侧隐藏为0，显示为1
    var a2=1; //右侧隐藏为0，显示为1
function myClick(t) {
    if(t==0){
        $('#left').fadeOut(150);
        $('#leftshow').show();
        a1=0;
    }
    if(t==1){
        $('#left').fadeIn(150);
        $('#leftshow').hide();
        a1=1;
    }
    if(t==3){
        $('#right').fadeOut(150);
        $('#rightshow').show();
        a2=0;
    } if(t==2){
        $('#right').fadeIn(150);
        $('#rightshow').hide();
        a2=1;
    }
    if(a1==0&&a2==0) {
        $('#content').attr('class', 'col-md-12 content');
    }else if(a1==1&&a2==1){
        $('#content').attr('class', 'col-md-6 content');
    }else {
        $('#content').attr('class', 'col-md-9 content');
    }



}

</script>
</html>