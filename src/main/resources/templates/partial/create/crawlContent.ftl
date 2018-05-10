<div class="col-sm-9" style="height: 100%;" id="content-frame">
    <div class="panel panel-default frame-panel">
        <div class="panel-heading">
            <div class="row">
                <form class="form col-sm-10" onsubmit="return loadPage()">
                    <label class="sr-only" for="url">URL</label>
                    <div class="input-group">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-default" onclick="frame_forward()">
                                <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
                            </button>
                            <button type="button" class="btn btn-default" onclick="frame_backward()">
                                <span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
                            </button>
                            <button type="button" class="btn btn-default dropdown-toggle" id="protocol"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">http://<span
                                    class="caret"></span></button>
                            <ul class="dropdown-menu protocol-dropdown">
                                <li><a onclick="changeProtocol('http://')">http://</a></li>
                                <li><a onclick="changeProtocol('https://')">https://</a></li>
                            </ul>
                        </div>
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
                <div class="btn-group frame-control-btn" style="margin-top: 0px">
                    <button type="button" class="btn btn-primary" onclick="turn(true)"
                            data-toggle="tooltip" data-placement="top" title="开始解析页面，将不能正常浏览页面">解析
                    </button>
                    <button type="button" class="btn btn-danger" onclick="turn(false)"
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
<div class="col-sm-3" style="height: 100%;" id="content-slider">
    <div class="panel panel-default sidebar-right">
        <ul class="nav nav-pills nav-justified">
            <li class="active">
                <a href="#column-tool-tab"
                   aria-controls="column-tool-tab" role="tab" data-toggle="tab">工具</a>
            </li>
            <li>
                <a href="#column-show-tab"
                   aria-controls="column-tool-tab" role="tab" data-toggle="tab">字段</a>
            </li>
            <li><a onclick="collapseRightSidebar()">隐藏</a></li>
        </ul>
        <div class="panel-body">
            <div class="tab-content sidebar-right-content">
                <div role="tabpanel" class="tab-pane active" id="column-tool-tab">
                    <form id="add-rule" onsubmit="return addColumn()">
                        <div class="form-group">
                            <label for="column-input-name">字段名称</label>
                            <input type="text" class="form-control" id="column-input-name" required>
                        </div>
                        <div class="form-group">
                            <label for="column-input-dom">选择器规则</label>
                            <textarea id="column-input-dom" class="form-control"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="column-input-type">爬取对象</label>
                            <select class="form-control" id="column-input-type">
                                <option value="text" selected>文本</option>
                                <option value="img">图片</option>
                            <#--<option value="html">html</option>-->
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default">添加字段</button>
                        <a onclick="testDom()">点击测试选择器规则</a>
                        <div class="help-block" id="test-select-length"></div>
                        <ul class="list-unstyled" id="test-dom-select">
                        </ul>
                    </form>
                </div>
                <div role="tabpanel" class="tab-pane" id="column-show-tab">
                    <table class="table">
                        <thead>
                        <tr>
                            <td>字段</td>
                            <td>规则</td>
                            <td>移除</td>
                        </tr>
                        </thead>
                        <tbody id="column">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="col-sm-1" id="slider-right-collapsed" style="display: none;">
    <ul class="nav nav-tabs nav-justified sidebar-right-show">
        <li><a href="#column-show-tab" onclick="collapseRightSidebar()">显示</a></li>
    </ul>
</div>