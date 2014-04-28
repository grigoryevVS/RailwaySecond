
$(document).ready(function () {
    $("#station").validate({
        rules: {
            name: {
                required: true,
                minlength: 2,
                maxlength: 20
            }
        },
        messages: {
            name: {
                required: "can't be empty",
                minlength: "Min 2 character",
                maxlength: "Max 20 characters"
            }
        }
    });
});
