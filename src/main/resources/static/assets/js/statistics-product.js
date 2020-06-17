let dataChart = [];
$(document).ready(function () {

    $("#search-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submit();

    });

});
function fire_ajax_submit() {

    var search = {}
    search["name"] = $("#product_code").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/admin/api/v1/count-product-by-year",
        data: JSON.stringify(search),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: async function (data) {
            await getCustomerData(data);
            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                title: {
                    text: data.message
                },
                axisY: {
                    title: "Units Sold",
                    valueFormatString: "#",
                    suffix: "",
                    stripLines: [{
                        value: data.average,
                        label: "Average"
                    }]
                },
                data: [{
                    yValueFormatString: "# Units",
                    xValueFormatString: "YYYY-MM-DD",
                    type: "spline",
                    dataPoints: dataChart
                }]
            });
            chart.render();
        },
        error: function (e) {
            window.alert(e.responseJSON.message);
        }
    });

}

async function getCustomerData(data) {
    dataChart = [];
    data.list.forEach(row=>{
        dataChart.push({x : new Date(row.day) , y : row.quantity})
    })
}