// Prevents script to be loaded multiple times 
if(!admin_navbar_loaded_once) {
    var admin_navbar_loaded_once = true;

    const adminNavbars = document.querySelectorAll(".admin-navbar");
    adminNavbars.forEach(navbar => {
        const destinations = navbar.querySelectorAll("li");
        destinations.forEach(destination => {
            destination.addEventListener("click", () => {
                const anchor = destination.querySelector("a");
                window.location = anchor.href;
            });
        })
    })
}

