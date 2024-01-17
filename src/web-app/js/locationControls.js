const locations = document.querySelectorAll(".location-display");

locations.forEach(location => {
    const id = location.id.replace("location-id-", "");
    
    location.addEventListener("click", event => {
        window.location = `/dashboard/locations/view?location=${id}`;
    });
});