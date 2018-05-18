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
    <div class="tab-content row">
        <div role="tabpanel" class="tab-pane active" id="crawler-status">
        <#include 'partial/list/crawlerStatus.ftl'>
        </div>
        <div role="tabpanel" class="tab-pane" id="crawler-result">
        <#include 'partial/list/crawlerResult.ftl'>
        </div>
    </div>
</div>
</body>
</html>