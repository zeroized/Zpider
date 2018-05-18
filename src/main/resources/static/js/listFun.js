$().ready(function () {
    $.get("/monitor/all", function (data) {
        var list = data.message.data;
        // alert(JSON.stringify(data.message.data));
        var tbody = $("#crawl-info");
        var length = list.length;
        // alert(typeof length);
        // alert(length);
        if (length !== 0) {
            list.forEach(function (item) {
                var options = item.options.split(";");
                var opt_string = "";
                options.forEach(function (e) {
                    var opt = e.split(",");
                    opt_string += "<a class='btn btn-default btn-sm' onclick='operate(\"" + opt[1] + "\",\"" + item.uuid + "\")'>" + opt[0] + "</a>"
                });
                tbody.append("<tr class='crawler-status-group' onclick='showConfig(event,\"" + item.uuid + "\")'>" +
                    "<td>" + item.uuid + "</td>" +
                    "<td>" + item.status + "</td>" +
                    "<td>" + opt_string + "</td>" +
                    "</tr>");
            });
        } else {
            // alert("length==0");
            tbody.html("<tr>" +
                "<td><p>当前没有爬虫</p></td>" +
                "<td><a class='btn btn-primary' href='/create'>创建爬虫</a></td>" +
                "<td></td>" +
                "</tr>");
        }
    });
});

function operate(url_tail, uuid) {
    // alert(url_tail+";"+uuid);
    if (url_tail === 'show') {
        selectPanel('#crawler-result');
        loadResult(uuid);
    } else {
        $.get("/monitor/opt/" + url_tail + "?uuid=" + uuid, function (data) {
            alert(JSON.stringify(data));
            if (data.status === 1) {
                window.location.href="/list";
            }
        });
    }
}

function showConfig(event, uuid) {
    if (event.target.tagName !== 'A') {
        $.get("/monitor/show/config?uuid=" + uuid, function (data) {
            var config = data.message.data;
            // alert(JSON.stringify(config));
            $("#status-uuid").text(uuid);
            $("#status-config-info").show();
            $("#status-name").text(config.name);
            $("#status-seed-list>li").remove();
            var seed_ele = $("#status-seed-list");
            config.seeds.forEach(function (e) {
                seed_ele.append("<li class='list-group-item'>" + e + "</li>");
            });
            $("#status-seed-size").text(config.seeds.length + "条目");

            $("#status-domain-list>tr").remove();
            var domain_ele = $("#status-domain-list");
            config.allowDomains.forEach(function (e) {
                domain_ele.append("<li class='list-group-item'>" + e + "</li>");
            });
            $("#status-domain-size").text(config.allowDomains.length + "条目");

            $("#status-crawl-list>tr").remove();
            var crawl_ele = $("#status-crawl-list");
            config.crawlUrlPrefixes.forEach(function (e) {
                crawl_ele.append("<li class='list-group-item'>" + e + "</li>");
            });
            $("#status-crawl-size").text(config.crawlUrlPrefixes.length + "条目");

            $("#status-column-list>tr").remove();
            var column_ele = $("#status-column-list");
            config.columns.forEach(function (e) {
                column_ele.append("<tr>" +
                    "<td>" + e.column + "</td>" +
                    "<td>" + e.type + "</td>" +
                    "<td class='table-column-rule'>" + e.rule + "</td>" +
                    "</tr>");
            });
            $("#status-column-size").text(config.columns.length + "条目");

            $("#status-workers").text(config.advancedOpt.workers);
            $("#status-max-depth").text(config.advancedOpt.maxDepth);
            $("#status-max-page").text(config.advancedOpt.maxPage);
            $("#status-polite-wait").text(config.advancedOpt.politeWait + "ms");
        });
    }
}

function loadSelect() {
    $("#crawl-result-select>option").remove();
    var select=$("#crawl-result-select");
    // select.append("<option value='food'>food</option>");
    $.get("/monitor/show/name", function (data) {
        var list = data.message.data;
        list.forEach(function (e) {
            select.append("<option value='" + e + "'>" + e + "</option>");
        })
    })
}

function loadResult(uuid) {
    if (!uuid) {
        uuid = $("#crawl-result-select").val();
    }
    $("#crawl-result-table>thead>tr>td").remove();
    $("#crawl-result-table>tbody>tr").remove();
    $.get("/monitor/opt/show?uuid=" + uuid, function (data) {
        var list = data.message.data;
        // alert(JSON.stringify(list));
        var thead=$("#crawl-result-table>thead>tr");
        thead.append("<td>序号</td>");
        for (var keyName in list[0].data) {
            // if (list[0].hasOwnProperty(keyName)) {
                thead.append("<td>" + keyName + "</td>");
            // }
        }
        list.forEach(function (e,step) {
            // alert(JSON.stringify(e));
            var piece=e.data;
            var tr = "<tr>" +
                "<td>"+step+"</td>";
            for (var key in piece) {
                // if (piece.data.hasOwnProperty(key)) {
                    tr = tr + "<td>" + piece[key] + "</td>";
                // }
            }
            tr = tr + "</tr>";
            $("#crawl-result-table>tbody").append(tr);
        });
    });
}