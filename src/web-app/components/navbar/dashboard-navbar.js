// Prevents script to be loaded multiple times 
if(!dashboard_navbar_loaded_once) {
    var dashboard_navbar_loaded_once = true;

    const dashboardNavbars = document.querySelectorAll(".dashboard-navbar");
    dashboardNavbars.forEach(navbar => {
        const destinations = navbar.querySelectorAll("li");
        destinations.forEach(destination => {
            destination.addEventListener("click", () => {
                const anchor = destination.querySelector("a");
                window.location = anchor.href;
            });
        })
    })
}

