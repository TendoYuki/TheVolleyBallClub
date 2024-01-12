const avatarField = document.querySelector("#avatar-field");
const avatarSelector = document.querySelector(".avatar-selector");

const passwordField = document.querySelector("#password-field");
const passwordConfirmField = document.querySelector("#confirm-password-field");
const emailField = document.querySelector("#email-field");
const groupField = document.querySelector("#group-field");
const birthdateField = document.querySelector("#birthdate-field");
const surnameField = document.querySelector("#surname-field");
const nameField = document.querySelector("#name-field");
const genderField = document.querySelector("#gender-field");

const cancelBtn = document.querySelector("#cancel-btn");
const signupBtn = document.querySelector("#sign-up-btn");

const signUpForm = document.querySelector("#sign-up");

const specialSymbols = ` !"#$%&'()*+,-./:;<=>?@[\\]^_\`{|}~`;
const uppercaseSymbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
const numbers = "1234567890";

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
    return {valid: true};
}

/**
 * Checks whether or not the name/surname is valid 
 * @param {*} password 
 * @returns 
 */
const verifyNotNumbers = (name,type) => {
    if(has(name,numbers)){
        if(type=="prenom"){
            return {valid: false, err: "Le prenom ne doit pas contenir de chiffres"};
        }
        else return {valid: false, err: "Le nom ne doit pas contenir de chiffres"};
    } 
    return {valid: true};
}

/**
 * Checks whether or not the user is under 15 
 * @param {*} password 
 * @returns 
 */
const verifyAge = (date) => {
    const MILIS_YEAR = 31556952000;
    const MIN_AGE = 15;
    let minDate = Date.now() - (MILIS_YEAR*MIN_AGE);
    let bd = new Date(date);
    if(!(bd.getTime() < minDate)) return {valid: false, err: "L'age minimum requis est 15 ans"};
    return {valid: true};
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
    window.location="/connection/sign-in";
});

/**  
 * Handles the signup event by checking the data model validity
 */
signupBtn.addEventListener("click", (event) => {
    event.preventDefault();
    event.stopPropagation();

    if(avatarField.value) {

    } else {
        alert("Avatar cannot be null");
        return;
    }

    if(avatarField.value) {}

    // Checks if gender is valid
    if(!(genderField.value != 1 && genderField.value != 0)) {

    } else {
        alert("invalid gender");
        return;
    }
    
    if(!(groupField.value != 1 && groupField.value != 2)) {

    } else {
        alert("invalid group");
        return;
    }

    // Checks if name is valid
    const passValidityName = verifyNotNumbers(nameField.value,"prenom");
    if(passValidityName.valid) {

    } else {
        alert(passValidityName.err);
        return;
    }

    // Checks if surname is valid
    const passValiditySurname = verifyNotNumbers(surnameField.value,"nom");
    if(passValiditySurname.valid) {

    } else {
        alert(passValiditySurname.err);
        return;
    }

    // Checks if the email is valid
    if(verifyEmail(emailField.value)) {

    } else {
        alert("email invalid");
        return;
    }

    // Checks if age is valid
    const passValidityAge =  verifyAge(birthdateField.value);
    if(passValidityAge.valid) {
        
    } else {
        alert(passValidityAge.err);
        return;
    }

    // Checks if password is valid
    const passValidityPassword = verifyPassword(passwordField.value);
    if(passValidityPassword.valid) {
        
    } else {
        alert(passValidityPassword.err);
        return;
    }

    // Checks if passwords matches
    if(passwordConfirmField.value === passwordField.value) {
        
    } else {
        alert("Les mots de passe de corréspondent pas.");
        return;
    }

    signUpForm.submit();
});