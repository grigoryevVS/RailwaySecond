/**
 * JS validation of route form
 * client side validation.
 */
$(document).ready(function () {
    $("#route").validate({
        rules: {
            title: {
                required: true,
                minlength: 2,
                maxlength: 20
            }
        },
        messages: {
            title: {
                required: "can't be empty",
                minlength: "Min 2 character",
                maxlength: "Max 20 characters"
            }
        }
    });
});