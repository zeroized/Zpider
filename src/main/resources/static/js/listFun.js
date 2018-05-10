$().ready(function(){
    $.get("/monitor/all",function(data){
        var list=data.message.data;
        // alert(JSON.stringify(data.message.data));
        var tbody=$("#crawl-info");
        var length=list.length;
        // alert(typeof length);
        // alert(length);
        if (length !== 0) {
            list.forEach(function (item) {
                var options = item.options.split(";");
                var opt_string = "";
                options.forEach(function (e) {
                    var opt = e.split(",");
                    opt_string += "<a class='' onclick='operate(\"" + opt[1] + "\",\"" + item.uuid + "\")'>" + opt[0] + "</a>"
                });
                tbody.append("<tr>" +
                    "<td><p>" + item.uuid + "</p></td>" +
                    "<td><p>" + item.status + "</p></td>" +
                    "<td>" + opt_string + "</td>" +
                    "</tr>");
            });
        }else{
            // alert("length==0");
            tbody.html("<tr>" +
                "<td><p>当前没有爬虫</p></td>" +
                "</tr>");
        }
    });
});

function operate(url_tail,uuid){
    alert(url_tail+";"+uuid);
    // $.get("/monitor/opt/"+url_tail+"?uuid="+uuid,function(data){});
}

function showConfig(uuid){
    $.get("/monitor/show/config?uuid="+uuid,function (data) {
        alert(JSON.stringify(data));
    })
}