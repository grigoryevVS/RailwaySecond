/**
 * JS validation of authorization form
 * client side validation.
 */
$(document).ready(function () {
    $("#user").validate({
        rules: {
            login: {
                required: true,
                minlength: 2,
                maxlength: 50
            },
            password: {
                required: true,
                minlength: 2,
                maxlength: 50
            }
        },
        messages: {
            login: {
                required: "can't be empty",
                minlength: "Min 1 character",
                maxlength: "Max 30 characters"
            },
            password: {
                required: "can't be empty",
                minlength: "Min 3 characters",
                maxlength: "Max 50 characters"
            }
        }
    });
});
