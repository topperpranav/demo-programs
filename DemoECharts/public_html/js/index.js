/*
 * Demonstrates the usage of live updation using series
 * 
 * @author Pranav
 * @since 2019-05-09
 */


var option;
$(document).ready(function () {
    //This element refers to the id of the container for echarts graph
    var dom = document.getElementById("chart");
    //Initialize echarts
    var myChart = echarts.init(dom);
    //On Page load declare options for the echarts as null, populate dynamically
    option = null;

    //This will be used to store data dynamically.
    //In this implementation
    var data = [];

    option = {
        //set the tile of the graph
        title: {
            text: 'Live Updation'
        },
        //Tool tip is usful when mouse is hovered over the graph
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                animation: false
            }
        },

        //Set the x-axis type data will be provided in the series area
        xAxis: {
            type: 'category',
            splitLine: {
                show: true
            }
        },

        //Set the y-axis type data will be provided in the series area
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            }
        },

        //Set the series
        series: [{
                //Name of this series can be used to refer to this data later
                name: 'Simple Line',
                //Series type is line for this
                type: 'line',
                showSymbol: false,
                hoverAnimation: false,
                //We are not using polar cordinate system here, we are using cartesian coordinate system
                //Hence data value can be of below form
                // [x1,y1]
                // [x2,y2]
                // [x3,y3]
                // [x4,y4]
                // Where x denotes the xaxis point and y denotes the y axis point
                data: data
            }]
    };

    //Add to data at regular intervals to simulate dynamic data
    setInterval(function () {
        data.push(randomData());
        //Set the chart series.
        //Here data should have a 'value' field that will have value in the following form:
        // [x1,y1]
        // [x2,y2]
        // [x3,y3]
        // [x4,y4]
        // Where x denotes the xaxis point and y denotes the y axis point
        myChart.setOption({
            series: [{
                    data: data
                }]
        });
    }, 1000);

    //Sanity check
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }

});

/**
 * Gets random data
 * 
 * @returns object
 */
function randomData() {
    var yAxisValue = Math.random() * 20 + Math.random() * 10 + 32;
    var xAxisValue = new Date();
    return {
        //This may be used to grop data
        name: "Some Name",
        value: [
            //This represents x axis point
            Math.round(xAxisValue.getTime()),
            //This represents yaxis point
            Math.round(yAxisValue)
        ]
    };
}

