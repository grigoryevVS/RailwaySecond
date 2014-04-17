
function checkForm() {
    showError('login', 'Incorrect!');
    return false;
}

function showError(field, errorMessage) {
    var errorSpan = document.createElement("span");
    var errorMessage = document.createTextNode(errorMessage);

    errorSpan.appendChild(errorMessage);
    errorSpan.className = "errorMsg";

    var fieldLabel = document.getElementById(field).previousSibling;
    while (fieldLabel.nodeName.toLowerCase() != "label") {
        fieldLabel = fieldLabel.previousSibling;
    }
    fieldLabel.appendChild(errorSpan);
}