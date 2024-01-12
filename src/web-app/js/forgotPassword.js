const resetBtn = document.querySelector("#reset-btn");
const cancelBtn = document.querySelector("#cancel-btn");

const resetForm = document.querySelector("#reset-password-form");

resetBtn.addEventListener('click', event => {
    event.preventDefault();
    event.stopPropagation();
    resetForm.submit();
})


cancelBtn.addEventListener('click', event => {
    event.preventDefault();
    event.stopPropagation();
    window.location="/sign-in";
})