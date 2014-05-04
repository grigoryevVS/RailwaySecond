
$(document).ready(function () {
    $("#station").validate({
        rules: {
            name: {
                required: true,
                minlength: 2,
                maxlength: 10
            }
        },
        messages: {
            name: {
                required: "can't be empty",
                minlength: "Min 2 character",
                maxlength: "Max 10 characters"
            }
        }
    });
});
