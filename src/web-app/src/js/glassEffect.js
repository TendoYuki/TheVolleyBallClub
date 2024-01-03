const glassyElements = document.querySelectorAll(".glassy");

glassyElements.forEach(glassyElement => {
    // Try getting container and creates it if it does not exists
    let glassEffectContainer = glassyElement.querySelector(".glass-effect-container");
    if(glassEffectContainer == undefined) {
        glassEffectContainer = document.createElement("div");
        glassEffectContainer.classList.add("glass-effect-container");
        glassyElement.appendChild(glassEffectContainer);
    }

    // Try getting effect and creates it if it does not exists
    let glassEffect = glassEffectContainer.querySelector(".glass-effect");
    if(glassEffect == undefined) {
        glassEffect = document.createElement("span");
        glassEffect.classList.add("glass-effect");
        glassEffect.classList.add("hidden");
        glassEffectContainer.appendChild(glassEffect);
    }

    window.addEventListener("mousemove", event => {
        const bounds = glassEffectContainer.getBoundingClientRect();
        const glassEffectBounds = glassEffect.getBoundingClientRect();

        // Checks if inside the container
        if((event.pageX > bounds.x && (bounds.x + bounds.width) > event.pageX) && (event.pageY > bounds.y && (bounds.y + bounds.height) > event.pageY)) {
            glassEffect.classList.remove("hidden");
        }
        else {
            glassEffect.classList.add("hidden");
            return;
        }

        // Calculates the x and y coordinate relative to the container
        const x = event.pageX - (bounds.x + glassEffectBounds.width/2);
        const y = event.pageY - (bounds.y + glassEffectBounds.height/2);

        // Applies the top and left as y and x 
        glassEffect.style.top = y + "px";
        glassEffect.style.left = x + "px";
    })
})