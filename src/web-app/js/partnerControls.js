const partners = document.querySelectorAll(".partner-display");

partners.forEach(partner => {
    const id = partner.querySelector(".partner-id");
    partner.addEventListener("click", event => {
        window.location = `/dashboard/partners/view?partner=${id.innerHTML}`;
    });
});