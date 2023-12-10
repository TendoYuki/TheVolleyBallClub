const avatarField = document.querySelector("#avatar-field");
const avatarSelector = document.querySelector(".avatar-selector");

const passwordField = document.querySelector("#password-field");
const emailField = document.querySelector("#email-field");
const groupField = document.querySelector("#group-field");
const birthdateField = document.querySelector("#birthdate-field");
const surnameField = document.querySelector("#surname-field");
const nameField = document.querySelector("#name-field");
const genderField = document.querySelector("#gender-field");

const cancelBtn = document.querySelector("#cancel-btn");
const signupBtn = document.querySelector("#sign-up-btn");

const specialSymbols = ` !"#$%&'()*+,-./:;<=>?@[\\]^_\`{|}~`;
const uppercaseSymbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

/**
 * Checks whether or not the str contains any of the chars inside of the list
 * @param {*} str Str to check
 * @param {*} list List of chars to check
 */
const has = (str, list) => {
    for(let i = 0; i < list.length; i++) if(str.includes(list[i]))return true;
    return false;
}

/**
 * Checks whether or not the password is secured enough
 * @param {*} password 
 * @returns 
 */
const verifyPassword = (password) => {
    if(password.length < 12) return {valid: false, err: "Le mot de passe doit faire au moins 12 caractères"}
    if(!has(password, specialSymbols)) return {valid: false, err: `Le mot de passe doit contenir un ou plusieurs caractères spéciaux : ${specialSymbols}`}
    if(!has(password, uppercaseSymbols)) return {valid: false, err: `Le mot de passe doit contenir une ou plusieurs majuscules`}
}

/**
 * Checks whether or not the email is valid
 * @param {*} email 
 * @returns 
 */
const verifyEmail = (email) => {
    return email.match(
        /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );
}

/**
 * Handles avatar change event
 */
avatarField.addEventListener("change", (event) => {
    avatarSelector.style.backgroundImage = `url("${URL.createObjectURL(event.target.files[0])}")`;
})

/**  
 * Handles the cancel event by redirecting to login page
 */
cancelBtn.addEventListener("click", (event) => {
    event.preventDefault();
    event.stopPropagation();
    window.location="/pages/connection/sign-in";
});

// /**  
//  * Handles the signup event by checking the data model validity
//  */
// signupBtn.addEventListener("click", (event) => {
//     event.preventDefault();
//     event.stopPropagation();

//     // Checks if gender is valid
//     if(!(genderField.value != 1 && genderField != 0)) {

//     } else
//     {

//     }

//     // Checks if the email is valid
//     if(verifyEmail(emailField.value)) {

//     } else {
        
//     }

//     // Checks if password is valid
//     const passValidity = verifyPassword(emailField.value);
//     if(passValidity.valid) {

//     } else {
        
//     }


// });