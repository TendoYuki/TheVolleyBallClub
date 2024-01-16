const passwordField = document.querySelector("#password-field");
const passwordFieldConfirm = document.querySelector("#confirm-password-field");
const policiesConfirm = document.querySelector("#policies-field");
const signUpForm = document.querySelector("#sign-up");

signUpForm.addEventListener("submit", e => {
    if(passwordField.value != passwordFieldConfirm.value) {
        e.preventDefault();
    }
    if(!policiesConfirm.checked) {
        e.preventDefault();
    }
})
