document.querySelectorAll("ul.navbar").forEach(navbar => {
    const navbarMenu = navbar.querySelector(".navbar-menu");
    navbar.querySelector(".navbar-menu-opener").addEventListener("click", () => {
        if(navbarMenu.classList.contains("active")) {
            navbarMenu.classList.remove("active");
        } else {
            navbarMenu.classList.add("active");
        }
    })
});
