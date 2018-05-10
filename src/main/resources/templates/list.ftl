<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>爬虫列表</title>
    <script src="/js/jquery.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/windowFun.js"></script>
    <script src="/js/layout.js"></script>
    <script src="/js/listFun.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<#include 'partial/nav.ftl'>
<div class="col-sm-2 sidebar sidebar-left" id="left">
    <#include 'partial/list/leftSidebar.ftl'>
</div>
<div class="col-sm-10 content" id="content">
    <div class="col-sm-8">
        <div class="panel panel-default basic-panel">
            <table class="table table-striped">
                <thead>
                <tr>
                    <td>爬虫序列号</td>
                    <td>爬虫状态</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody id="crawl-info">
                <#--<tr>-->
                    <#--<td><p>2d9210c1f582499c91fdbc78d6b3c00f</p></td>-->
                    <#--<td><p>READY</p></td>-->
                    <#--<td>-->
                        <#--<a onclick="">查看配置</a>-->
                        <#--<a>修改配置</a>-->
                        <#--<a>启动</a>-->
                        <#--<a>删除</a>-->
                    <#--</td>-->
                <#--</tr>-->
                <#--<tr>-->
                    <#--<td><p>2d9210c1f582499c91fdbc78d6b3c00f</p></td>-->
                    <#--<td><p>STARTED</p></td>-->
                    <#--<td>-->
                        <#--<a>查看配置</a>-->
                        <#--<a>停止</a>-->
                        <#--<a>查看当前状态</a>-->
                    <#--</td>-->
                <#--</tr>-->
                <#--<tr>-->
                    <#--<td><p>2d9210c1f582499c91fdbc78d6b3c00f</p></td>-->
                    <#--<td><p>ERROR</p></td>-->
                    <#--<td>-->
                        <#--<a>查看配置</a>-->
                        <#--<a>查看错误信息</a>-->
                    <#--</td>-->
                <#--</tr>-->
                <#--<tr>-->
                    <#--<td><p>2d9210c1f582499c91fdbc78d6b3c00f</p></td>-->
                    <#--<td><p>FINISHED</p></td>-->
                    <#--<td>-->
                        <#--<a>查看配置</a>-->
                        <#--<a>查看结果</a>-->
                    <#--</td>-->
                <#--</tr>-->
                <#--<tr>-->
                    <#--<td><p>2d9210c1f582499c91fdbc78d6b3c00f</p></td>-->
                    <#--<td><p>STOPPED</p></td>-->
                    <#--<td>-->
                        <#--<a>查看配置</a>-->
                        <#--<a>查看结果</a>-->
                        <#--<a>继续</a>-->
                    <#--</td>-->
                <#--</tr>-->
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>