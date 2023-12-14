const searchBtn = document.querySelector("#search-btn");
const searchField = document.querySelector("#search-field");
const urlParams = new URLSearchParams(window.location.search);

searchBtn.addEventListener("click", event => {
    urlParams.set("search", searchField.value);
    urlParams.set("page", 1);
    window.location.search = urlParams;
})