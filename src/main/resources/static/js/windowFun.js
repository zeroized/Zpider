var configs = {
    seed: {
        id: 0
    },
    column: {
        id: 0
    },
    domain: {
        id: 0
    },
    crawl: {
        id: 0
    }
};

function changeProtocol(protocol) {
    $("#protocol").text(protocol);
}
function changeThisProtocol(id,protocol) {
    $(id).text(protocol);
}

function loadPage(url) {
    if (url) {
        var st = url.lastIndexOf("//");
        url = url.substring(st + 2);
        $("#url").val(url);
    }
    var protocol = $("#protocol").text();
    url = "/load?url=" + protocol + $("#url").val().replace(/&/g, '%26');
    $("#target_page").attr("src", url);
    // if (window.event) window.event.preventDefault();
    $("#loading").show();
    $("#view-allow").hide();
    $("#view-forbid").hide();
    return false;
}

function finishLoading() {
    $("#loading").hide();
    $("#view-allow").show();
}

function refresh() {
    var frame = $("#target_page");
    frame.attr("src", frame.attr("src"));
    $("#loading").show();
    $("#view-allow").hide();
    $("#view-forbid").hide();
}

function turn(bool) {
    document.getElementById("target_page").contentWindow.turn(bool);
    if (document.getElementById("target_page").contentWindow.showSwitch) {
        $("#view-allow").hide();
        $("#loading").hide();
        $("#view-forbid").show();
    } else {
        $("#view-forbid").hide();
        $("#loading").hide();
        $("#view-allow").show();
    }
}

function frame_forward() {
    document.getElementById("target_page").contentWindow.history.forward();
}

function frame_backward() {
    document.getElementById("target_page").contentWindow.history.back();
}

var showElement = function (event) {
    if (event) {
        var ele = event.target;
        var obj = {
            id: ele.id,
            html: ele.innerHTML,
            class: ele.className,
            tag: ele.tagName.toLowerCase()
        };
        // alert(JSON.stringify(obj));
        $("#tagName").val(obj.tag);
        $("#id").val(obj.id);
        $("#class").val(obj.class);
        $("#html").val(obj.html);
        var parents = getDomTree(ele);
        $("#column-input-dom").val(parents);
    }
};

function getDomTree(e) {
    var parents = getDomInfo(e);
    var ele = e.parentNode;
    while (ele.tagName.toLowerCase() !== 'body') {
        var domInfo = getDomInfo(ele);
        parents = domInfo + ">" + parents;
        console.log(domInfo);
        ele = ele.parentNode;
    }
    parents = "body>" + parents;
    return parents;
}

function getDomInfo(e) {
    var dom = e.tagName.toLowerCase();
    if (e.id) {
        dom = dom + "#" + e.id;
    }
    if (e.className) {
        var classNames = e.className.split(" ").filter(function (e) {
            return e.length > 0;
        });
        classNames.forEach(function (className) {
            dom = dom + "." + className;
        });
    }
    return dom;
}

function testDom() {
    var rule = $("#column-input-dom").val();
    // console.log(rule)
    var ele = document.getElementById("target_page").contentWindow.getEle(rule);
    // console.log(JSON.stringify(ele));
    var length = ele.length;
    var type = $("#column-input-type").val();
    $("#test-dom-select>li").remove();
    if (length === 0) {
        $("#test-dom-select").append("<li>未找到对应内容，可能是动态加载的节点</li>")
    } else {
        // var result = "";
        for (var i = 0; i < length; i++) {
            if (type === 'text') {
                // result += " " + $(ele[i]).text();
                $("#test-dom-select").append("<li>" + $(ele[i]).text() + "</li>")
            } else if (type === 'html') {
                // result += " " + $(ele[i]).html();
            }

        }
        // $("#test-select").val(result);
        $("#test-select-length").text("共" + length + "个结果");
    }
}

function addTableRow(tableId, id, tdValue, enable_tri) {
    var template;
    var tri_column = "<tr id=\"%tableId%-%id%\">" +
        "<td>%tdValue%</td>" +
        "<td><span class=\"glyphicon glyphicon-edit\" onclick=\"showTableRow('%tableId%','%id%')\"></span></td>" +
        "<td><span class=\"glyphicon glyphicon-minus\" onclick=\"removeTableRow('%tableId%','%id%')\"></span></td>" +
        "</tr>";
    var bi_column = "<tr id=\"%tableId%-%id%\">" +
        "<td>%tdValue%</td>" +
        "<td><span class=\"glyphicon glyphicon-minus\" onclick=\"removeTableRow('%tableId%','%id%')\"></span></td>" +
        "</tr>";
    if (enable_tri) {
        template = tri_column;
    } else {
        template = bi_column;
    }
    template = template.replace(/%tableId%/g, tableId);
    template = template.replace(/%id%/g, id);
    template = template.replace(/%tdValue%/g, tdValue);
    $("#" + tableId).append(template);
}

function showTableRow(tableId, id) {
    var tdValue = $("#" + tableId + "-" + id + ">td:first").text();
    alert(configs[tableId][tdValue]);
}

function removeTableRow(tableId, id) {
    var tdValue = $("#" + tableId + "-" + id + ">td:first").text();
    $("#" + tableId + "-" + id).remove();
    delete configs[tableId][tdValue];
    var url = "/crawl/config/basic/" + tableId + "/delete";
    $.post(url, {
        value: tdValue
    }, function (data) {
        alert(JSON.stringify(data));
    })
}

function addSeed() {
    var seed = $("#seed-input").val();
    var protocol=$("#protocol-seed").text();
    var id = configs.seed.id;
    seed = protocol + seed;
    if (configs.seed[seed] === "") {
        alert("该种子地址已存在!");
    } else {
        addTableRow("seed", id, seed);
        configs.seed[seed] = "";
        configs.seed.id++;
    }
    $.post("/crawl/config/basic/seed/add", {
        value: seed
    }, function (data) {
        alert(JSON.stringify(data));
    });
}

function addDomain() {
    var domain = $("#domain-input").val();
    var protocol=$("#protocol-domain").text();
    domain=protocol+domain;
    var id = configs.domain.id;
    if (configs.domain[domain] === "") {
        alert("该允许访问域名已存在")
    } else {
        addTableRow("domain", id, domain);
        configs.domain[domain] = "";
        configs.domain.id++;
    }
    $.post("/crawl/config/basic/domain/add", {
        value: domain
    }, function (data) {
        alert(JSON.stringify(data));
    });
}

function addCrawl() {
    var crawl = $("#crawl-input").val();
    var protocol=$("#protocol-crawl").text();
    crawl=protocol+crawl;
    var id = configs.crawl.id;
    if (configs.crawl[crawl] === "") {
        alert("该爬取地址前缀已存在!");
    } else {
        addTableRow("crawl", id, crawl);
        configs.crawl[crawl] = "";
        configs.crawl.id++;
    }
    $.post("/crawl/config/basic/crawl/add", {
        value: crawl
    }, function (data) {
        alert(JSON.stringify(data));
    });
}

function addColumn() {
    var name = $("#column-input-name").val();
    var dom = $("#column-input-dom").val();
    var id = configs.column.id;
    var type = $("#column-input-type").val();
    if (configs.column[name]) {
        alert("该字段已存在!");
    } else {
        addTableRow("column", id, name, true);
        configs.column[name] = {
            dom: dom,
            type: type
        };
        configs.crawl.id++;
    }
    $.post("/crawl/config/basic/column/add", {
        value: name,
        rule: dom,
        type: type
    }, function (data) {
        alert(JSON.stringify(data));
    });
    return false;
}

function saveName() {
    var name = $("#spider-name").val();
    $.post("/crawl/config/basic/name/revise", {
        value: name
    }, function (data) {
        alert(JSON.stringify(data));
    });
}

function saveAdvOpt(){
    $.post("/crawl/config/adv",{
        workers: $("#adv-workers").val(),
        maxDepth: $("#adv-max-depth").val(),
        maxPage: $("#adv-max-page").val(),
        politeWait: $("#adv-polite-wait").val()
    },function(data){
        alert(JSON.stringify(data.message));
        var advOpt=data.message.data;
        $("#show-adv-workers").text(advOpt.workers);
        $("#show-adv-max-depth").text(advOpt.maxDepth);
        $("#show-adv-max-page").text(advOpt.maxPage);
        $("#show-adv-polite-wait").text(advOpt.politeWait+"ms");
    })
}

function loadConfirm() {
    $.get("/crawl/config/confirm",function(data){
        var name=data.message.data.name;
        if (name) {
            $("#confirm-basic-name").text(name);
        }else{
            $("#confirm-basic-name").html("<span style='color: red;' class='glyphicon glyphicon-exclamation-sign'></span><span>尚未命名</span>");
        }

        var seeds=data.message.data.seeds;
        setBasicConfigPanel("#seed-confirm",seeds);
        setBasicConfirm($("#confirm-basic-seed-size"),seeds.length);

        var domains=data.message.data.allowDomains;
        setBasicConfigPanel("#domain-confirm",domains);
        setBasicConfirm($("#confirm-basic-domain-size"),domains.length);

        var crawls=data.message.data.crawlUrlPrefixes;
        setBasicConfigPanel("#crawl-confirm",crawls);
        setBasicConfirm($("#confirm-basic-crawl-size"),crawls.length);

        var columns=data.message.data.columns;
        if (columns.length !== 0) {
            $("#column-confirm").show();
            $("#column-confirm>div>table>tbody>tr").remove();
            var ele=$("#column-confirm>div>table>tbody");
            columns.forEach(function(e){
                ele.append("<tr>" +
                    "<td>" + e.column + "</td>" +
                    "<td>" + e.type + "</td>" +
                    "<td class='table-column-rule'>" + e.rule + "</td>" +
                    "</tr>");
            });
        }else{
            $("#column-confirm").hide();
        }
        setBasicConfirm($("#confirm-basic-column-size"),columns.length);

        var advopt=data.message.data.advancedOpt;
        $("#confirm-adv-workers").text(advopt.workers);
        $("#confirm-adv-max-depth").text(advopt.maxDepth);
        $("#confirm-adv-max-page").text(advopt.maxPage);
        $("#confirm-adv-polite-wait").text(advopt.politeWait+"ms");
    })
}

function setBasicConfigPanel(ele_id,array){
    var panel_ele=$(ele_id);
    var ele=$(ele_id+">div>ul");
    if (array.length !== 0) {
        panel_ele.show();
        $(ele_id+">div>ul>li").remove();
        array.forEach(function(e){
            ele.append("<li class='list-group-item'>" + e + "</li>");
        });
    }else{
        panel_ele.hide();
    }
}

function setBasicConfirm(ele,value){
    if (value !== 0) {
        ele.text(value+"条目");
    }else{
        ele.html("<span style='color: red;' class='glyphicon glyphicon-exclamation-sign'></span><span>尚未添加</span>")
    }
}

function startCrawler(e) {
    // alert(JSON.stringify(configs));
    e.preventDefault();
    var key;
    var seeds = configs.seed;
    var seed_array = [];
    for (key in seeds) {
        if (seeds.hasOwnProperty(key)) {
            if (key !== 'id') {
                seed_array.push(key);
            }
        }
    }

    var domains = configs.domain;
    var domain_array = [];
    for (key in domains) {
        if (domains.hasOwnProperty(key)) {
            if (key !== 'id') {
                domain_array.push("http://" + key);
            }
        }
    }

    var crawls = configs.crawl;
    var crawl_array = [];
    for (key in crawls) {
        if (crawls.hasOwnProperty(key)) {
            if (key !== 'id') {
                crawl_array.push(key);
            }
        }
    }

    var columns = configs.column;
    var column_array = [];
    for (key in columns) {
        if (columns.hasOwnProperty(key)) {
            if (key !== 'id') {
                column_array.push({
                    column: key,
                    rule: columns[key].dom,
                    type: columns[key].type
                });
            }
        }
    }

    var data = {
        seeds: seed_array,
        allowDomains: domain_array,
        crawlUrlPrefixes: crawl_array,
        columns: column_array,
        advancedOpt: {
            workers: $("#adv-workers").val(),
            maxDepth: $("#adv-max-depth").val(),
            maxPage: $("#adv-max-page").val(),
            politeWait: $("#adv-polite-wait").val()
        },
        name: $("#spider-name").val()
    };

    // alert(JSON.stringify(data));
    // $.post("http://localhost:8080/crawl/start",JSON.stringify(data),function(data,status){
    //     alert("Data: " + data + "\nStatus: " + status);
    // },"json");
    $.ajax({
        type: 'POST',
        url: "http://localhost:8080/crawl/start",
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: 'text',
        complete: function (msg) {
            alert(msg);
        }
    });
    return false;
}

function createCrawler(){
    $.get("/crawl/create",function(data){
        if (data.status === 1) {
            window.location.href="/list";
        }else{
            alert(data.message.error+"尚未完成");
        }
    })
}
