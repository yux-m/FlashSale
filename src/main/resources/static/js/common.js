//loading view
function g_showLoading() {
    var idx = layer.msg('Loading...', {
        icon: 16,
        shade: [0.5, '#f5f5f5'],
        scrollbar: false,
        offset: '0px',
        time: 100000
    });
    return idx;
}

//salt, same as in MD5utils
var g_passsword_salt = "4eilo8trk5"

//get url parameters
function g_getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substring(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
};
//format date/time with new Date().format("yyyy-MM-dd HH:mm:ss");
Date.prototype.format = function (format) {
    var args = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var i in args) {
        var n = args[i];
        if (new RegExp("(" + i + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length === 1 ? n : ("00" + n).substring(("" + n).length));
    }
    return format;
};
