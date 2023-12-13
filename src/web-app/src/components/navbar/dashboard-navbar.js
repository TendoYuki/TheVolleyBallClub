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