!function (d) {
    "use strict";
    var r = function () {
    };
    r.prototype.respChart = function (r, a, o, t) {
        Chart.defaults.global.defaultFontColor = "#6c7897", Chart.defaults.scale.gridLines.color = "rgba(108, 120, 151, 0.1)";
        var e = r.get(0).getContext("2d"), n = d(r).parent();

        function i() {
            r.attr("width", d(n).width());
            switch (a) {
                case"Line":
                    new Chart(e, {type: "line", data: o, options: t});
                    break;
                case"Doughnut":
                    new Chart(e, {type: "doughnut", data: o, options: t});
                    break;
                case"Pie":
                    new Chart(e, {type: "pie", data: o, options: t});
                    break;
                case"Bar":
                    new Chart(e, {type: "bar", data: o, options: t});
                    break;
                case"Radar":
                    new Chart(e, {type: "radar", data: o, options: t});
                    break;
                case"PolarArea":
                    new Chart(e, {type: "polarArea", data: o, options: t})
            }
        }

        d(window).resize(i), i()
    }, r.prototype.init = function () {
        var r = {legend: {display: !1}};
        this.respChart(d("#lineChart"), "Line", {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                backgroundColor: "rgba(220,220,220, 0.75)",
                borderColor: "rgba(220,220,220, 0.75)",
                pointColor: "rgba(220,220,220,1)",
                pointStrokeColor: "#fff",
                data: [33, 52, 63, 92, 50, 53, 46]
            }, {
                backgroundColor: "rgba(59,192,195,0.4)",
                borderColor: "rgba(59,192,195,0.4)",
                pointColor: "rgba(59,192,195,1)",
                pointStrokeColor: "#fff",
                data: [22, 20, 30, 60, 29, 25, 12]
            }, {
                backgroundColor: "rgba(97, 92, 168, 0.4)",
                borderColor: "rgba(97, 92, 168, 0.4)",
                pointColor: "rgba(97, 92, 168, 0.4)",
                pointStrokeColor: "#fff",
                data: [14, 16, 12, 5, 32, 9, 33]
            }]
        }, r);
        this.respChart(d("#doughnut"), "Doughnut", {
            datasets: [{
                data: [30, 50, 100, 40, 120],
                backgroundColor: ["#E67A77", "#D9DD81", "#79D1CF", "#95D7BB", "#4D5360"]
            }]
        });
        this.respChart(d("#pie"), "Pie", {
            datasets: [{
                data: [40, 80, 70],
                backgroundColor: ["#dcdcdc", "#3bc0c3", "#1a2942"]
            }]
        });
        r = {legend: {display: !1}};
        this.respChart(d("#bar"), "Bar", {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                backgroundColor: "#1a2942",
                borderColor: "#1a2942",
                data: [65, 59, 90, 81, 56, 55, 40]
            }, {backgroundColor: "#3bc0c3", borderColor: "#3bc0c3", data: [28, 48, 40, 19, 96, 27, 100]}]
        }, r);
        this.respChart(d("#radar"), "Radar", {
            labels: ["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Partying", "Running"],
            datasets: [{
                label: "Desktops",
                backgroundColor: "rgba(59,192,195, 0.5)",
                borderColor: "rgba(59,192,195, 0.75)",
                pointBackgroundColor: "rgba(59,192,195, 1)",
                pointBorderColor: "#fff",
                pointHoverBackgroundColor: "#fff",
                data: [65, 59, 90, 81, 56, 55, 40]
            }, {
                label: "Tablets",
                backgroundColor: "rgba(26,41,66, 0.5)",
                borderColor: "rgba(26,41,66, 0.75)",
                pointBackgroundColor: "rgba(26,41,66,1)",
                pointBorderColor: "#fff",
                pointHoverBackgroundColor: "#fff",
                pointHoverBorderColor: "rgba(255,99,132,1)",
                data: [28, 48, 40, 19, 96, 27, 100]
            }]
        });
        this.respChart(d("#polarArea"), "PolarArea", {
            datasets: [{
                data: [30, 90, 24, 58, 82, 8],
                backgroundColor: ["#ebc142", "#3bc0c3", "#1a2942", "#f13c6e", "#615ca8", "#1ca8dd"]
            }], labels: ["Series 1", "Series 2", "Series 3", "Series 4", "Series 5", "Series 6"]
        })
    }, d.ChartJs = new r, d.ChartJs.Constructor = r
}(window.jQuery), function (r) {
    "use strict";
    window.jQuery.ChartJs.init()
}();