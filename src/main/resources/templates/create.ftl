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
<nav class="navbar navbar-static-top navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Zpider</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="#">新建爬虫</a></li>
            <li><a href="#">查看爬虫</a></li>
        </ul>
        <p class="navbar-text navbar-right version-label">version 0.3.0</p>
    </div><!-- /.container-fluid -->
</nav>
<div class="col-sm-2 slider slider-left" id="left">
    <#include 'partial/leftSlider.ftl'>
</div>
<div class="col-sm-10 content" id="content">
    <div class="tab-content row">
        <div role="tabpanel" class="tab-pane active" id="basic-tab">
            <#include 'partial/basicConfig.ftl'>
        </div>
        <div role="tabpanel" class="tab-pane" id="adv-tab">
            <#include 'partial/advConfig.ftl'>
        </div>
        <div role="tabpanel" class="tab-pane" id="content-tab">
            <#include 'partial/crawlContent.ftl'>
        </div>
        <div role="tabpanel" class="tab-pane confirm-panel" id="confirm-tab">
            <#include 'partial/createConfirm.ftl'>
        </div>
    </div>
</div>
</body>
</html>