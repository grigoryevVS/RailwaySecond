/**
 * JS autocomplete of station selects in the scheduleFilter
 * And ajax function for implementation filter on the same page, without redirect
 * to improve usability and performance
 */
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
function getTimetable() {
    var sta1 = $('#stationFromName').val();
    var sta2 = $('#stationToName').val();
    var date = $('#date').val();



    $.ajax({
        type: "POST",
        url: "/RailWay/scheduleView/filteredSchedule",
        data: { stationToName: sta2, stationFromName: sta1, date: date},
        success : function(data) {
            var find = $(data).find('table');
            $('#table').html(find);
        },
        error : function() {
            alert("error");
        }
    });

};

