
$(document).ready(function () {
    $("#train").validate({
        rules: {
            name: {
                required: true,
                minlength: 2,
                maxlength: 20
            },
            numberOfSeats: {
                required: true,
                minlength: 1,
                maxlength: 400

            }
        },
        messages: {
            name: {
                required: "can't be empty",
                minlength: "Min 2 character",
                maxlength: "Max 20 characters"
            },
            numberOfSeats: {
                required: "can't be empty",
                minlength: "Min 1 character",
                maxlength: "Max 400 characters"
            }
        }
    });
});

