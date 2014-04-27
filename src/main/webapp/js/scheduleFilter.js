$(document).ready(function () {

    $('#stationFromName').autocomplete({
        serviceUrl: '/RailWay/scheduleView/getStations',
        paramName: "stationName",
        delimiter: ",",
        transformResult: function (response) {

            return {
                //must convert json to javascript object before process
                suggestions: $.map($.parseJSON(response), function (item) {
                    return { value: item.name, data: item.stationId };
                })
            };
        }
    });
});

$(document).ready(function () {

    $('#stationToName').autocomplete({
        serviceUrl: '/RailWay/scheduleView/getStations',
        paramName: "stationName",
        delimiter: ",",
        transformResult: function (response) {

            return {
                //must convert json to javascript object before process
                suggestions: $.map($.parseJSON(response), function (item) {

                    return { value: item.name, data: item.stationId };
                })
            };
        }
    });
});

