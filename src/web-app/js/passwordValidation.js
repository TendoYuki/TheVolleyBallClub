const passwordField = document.querySelector("#password-field");
const passwordFieldConfirm = document.querySelector("#confirm-password-field");
const form = document.querySelector("form");

form.addEventListener("submit", e => {
    if(passwordField.value != passwordFieldConfirm.value) {
        e.preventDefault();
        alert("password does not match");
    }
})
