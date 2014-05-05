/**
 * JS validation of user form
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
                minlength: 3,
                maxlength: 50
            },

            firstName: {
                required: true,
                minlength: 2,
                maxlength: 50
            },
            lastName: {
                required: true,
                minlength: 2,
                maxlength: 50
            },
            birthDate: {
                required: true
            }
        },
        messages: {
            login: {
                required: "can't be empty",
                minlength: "Min 2 character",
                maxlength: "Max 30 characters"
            },
            password: {
                required: "can't be empty",
                minlength: "Min 3 characters",
                maxlength: "Max 50 characters"
            },
            firstName: {
                required: "can't be empty",
                minlength: "Min 2 characters",
                maxlength: "Max 30 characters"
            },
            lastName: {
                required: "can't be empty",
                minlength: "Min 2 characters",
                maxlength: "Max 30 characters"
            },
            birthDate: {
                required: "can't be empty"
            }
        }
    });
});
