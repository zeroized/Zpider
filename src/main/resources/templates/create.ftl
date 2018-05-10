<!DOCTYPE html>
<html lang="en">
<head>
    <title>新建爬虫</title>
    <meta charset="UTF-8">
    <script src="/js/jquery.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/windowFun.js"></script>
    <script src="/js/layout.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<#include 'partial/nav.ftl'>
<div class="col-sm-2 sidebar sidebar-left" id="left">
    <#include 'partial/create/leftSidebar.ftl'>
</div>
<div class="col-sm-10 content" id="content">
    <div class="tab-content row">
        <div role="tabpanel" class="tab-pane active" id="basic-tab">
            <#include 'partial/create/basicConfig.ftl'>
        </div>
        <div role="tabpanel" class="tab-pane" id="adv-tab">
            <#include 'partial/create/advConfig.ftl'>
        </div>
        <div role="tabpanel" class="tab-pane" id="content-tab">
            <#include 'partial/create/crawlContent.ftl'>
        </div>
        <div role="tabpanel" class="tab-pane confirm-panel" id="confirm-tab">
            <#include 'partial/create/createConfirm.ftl'>
        </div>
    </div>
</div>
</body>
</html>