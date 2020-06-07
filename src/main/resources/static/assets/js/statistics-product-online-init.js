const url_api = 'http://localhost:8080/admin/api/v1/top-order-online';
const xlabels = [];
const product_quantity = [];
chartTopOnlineSellInit();

async function chartTopOnlineSellInit() {
    await getOnlineSellData();
    const ctx = document.getElementById('myChart').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: xlabels,
            datasets: [{
                label: '# of Votes',
                data: product_quantity,
                fill: false,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(27, 156, 252,1.0)',
                    'rgba(88, 177, 159,1.0)',
                    'rgba(154, 236, 219,1.0)',
                    'rgba(189, 197, 129,1.0)',
                    'rgba(254, 211, 48,1.0)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)'
                ],
                borderWidth: 1
            }]
        }
        ,
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
}
async function getOnlineSellData() {
    const response = await fetch(url_api);
    const data = await response.json();
    data.object.forEach(row => {
        xlabels.push(row.productDTO.name);
        product_quantity.push(row.quantity);
    });
}