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

function loadPage(url) {
    if (url) {
        var st = url.lastIndexOf("//");
        url = url.substring(st + 2);
        $("#url").val(url);
    }
    url = "http://localhost:8080/load?url=http://" + $("#url").val();
    $("#target_page").attr("src", url);
    // if (window.event) window.event.preventDefault();
    $("#loading").removeClass("hidden");
    $("#view-allow").addClass("hidden");
    $("#view-forbid").addClass("hidden");
    return false;
}

function finishLoading() {
    $("#loading").addClass("hidden");
    $("#view-allow").removeClass("hidden");
}

function refresh() {
    var frame = $("#target_page");
    frame.attr("src", frame.attr("src"));
    $("#loading").removeClass("hidden");
    $("#view-allow").addClass("hidden");
    $("#view-forbid").addClass("hidden");
    $("form").reset();
}

function turn(bool) {
    document.getElementById("target_page").contentWindow.turn(bool);
    if (document.getElementById("target_page").contentWindow.showSwitch) {
        $("#view-allow").addClass("hidden");
        $("#loading").addClass("hidden");
        $("#view-forbid").removeClass("hidden");
    } else {
        $("#view-forbid").addClass("hidden");
        $("#loading").addClass("hidden");
        $("#view-allow").removeClass("hidden");
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
    if (length === 0) {
        $("#test-select").val("未找到对应内容，可能是动态加载的节点")
    } else {
        var result = "";
        for (var i = 0; i < length; i++) {
            if (type === 'text') {
                result += " " + $(ele[i]).text();
            } else if (type === 'html') {
                result += " " + $(ele[i]).html();
            }
        }
        $("#test-select").val(result);
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
}

function addSeed() {
    var seed = $("#seed-input").val();
    var id = configs.seed.id;
    seed = "http://" + seed;
    if (configs.seed[seed] === "") {
        alert("该种子地址已存在!");
    } else {
        addTableRow("seed", id, seed);
        configs.seed[seed] = "";
        configs.seed.id++;
    }
}

function addDomain() {
    var domain = $("#domain-input").val();
    var id = configs.domain.id;
    if (configs.domain[domain] === "") {
        alert("该允许访问域名已存在")
    } else {
        addTableRow("domain", id, domain);
        configs.domain[domain] = "";
        configs.domain.id++;
    }
}

function addCrawl() {
    var crawl = $("#crawl-input").val();
    var id = configs.crawl.id;
    if (configs.crawl[crawl] === "") {
        alert("该爬取地址前缀已存在!");
    } else {
        addTableRow("crawl", id, crawl);
        configs.crawl[crawl] = "";
        configs.crawl.id++;
    }
}

function addColumn() {
    var name = $("#column-input-name").val();
    var dom = $("#column-input-dom").val();
    var id = configs.column.id;
    if (configs.column[name]) {
        alert("该字段已存在!");
    } else {
        addTableRow("column", id, name, true);
        configs.column[name] = dom;
        configs.crawl.id++;
    }
    return false;
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
                    rule: columns[key]
                });
            }
        }
    }

    var data = {
        seeds: seed_array,
        allowDomains: domain_array,
        crawlUrlPrefixes: crawl_array,
        columns: column_array
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
