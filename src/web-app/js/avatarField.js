const avatarSelectors = document.querySelectorAll(".avatar-selector");

avatarSelectors.forEach(avatarSelector => {
    /**
     * Handles avatar change event
     */
    avatarSelector.addEventListener("change", (event) => {
        avatarSelector.querySelector("img.display").src=URL.createObjectURL(event.target.files[0]);
    })
})

