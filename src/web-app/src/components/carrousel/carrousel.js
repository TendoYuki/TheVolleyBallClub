const CARROUSEL_INTERVAL = 4000; // Interval in ms

document.querySelectorAll(".carrousel-wrapper").forEach(carrousel => {
    const prevControl = carrousel.querySelector(".prev");
    const nextControl = carrousel.querySelector(".next");
    const selectors = carrousel.querySelector(".selectors");
    const selectorsArray = Array.from(selectors.children);
    const imagesContainer = carrousel.querySelector(".images-container");
    const imagesArray = Array.from(imagesContainer.children);
    let updateInterval;

    // Index of the active image
    let selectedImage = 0;

    // Default styling
    imagesContainer.style.left = '0px';

    /**
     * Gets the width of the carrousel
     * @returns Width of the carrousel in pixel as an integer
     */
    const getCarrouselWidth = () => {
        return carrousel.getBoundingClientRect().width;
    }

    /**
     * Gets the left offset of the images container
     * @returns Left offset of the container in pixel as an integer
     */
    const getImageContainerLeftOffset = () => {
        return Number.parseInt(imagesContainer.style.left.replace(/px/g,''));
    }

    /**
     * Updates the carrousel's currently displayed image to be centered in the view
     * and updates the selectors
     */
    const updateView = () => {
        imagesContainer.style.left = `${-(selectedImage * getCarrouselWidth())}px`;

        // Deactivates active selector
        let active = selectors.querySelector(".active");
        if(active) active.classList.remove("active");

        // Activates the newly selected image's selector
        selectorsArray[selectedImage].classList.add("active");
    }

    /**
     * Changes the current image to next one
     * N.B cannot overflow and will go back to the start of the array.
     */
    const nextImage = () => {
        if(selectedImage >= imagesArray.length-1) {
            selectedImage = 0;
        } else {
            selectedImage++;
        }
        updateView();
    }

    /**
     * Changes the current image to previous one
     * N.B cannot underflow and will go back to the end of the array.
     */
    const previousImage = () => {
        if(selectedImage <= 0) {
            selectedImage = imagesArray.length-1;
        } else {
            selectedImage--;
        }
        updateView();
    }

    /**
     * Resets the interval to 0 before change occurs
     */
    const resetInterval = () => {
        clearInterval(updateInterval);
        updateInterval = setInterval(nextImage,CARROUSEL_INTERVAL);
    }

    prevControl.addEventListener("click", () => {
        resetInterval();
        previousImage();
    });

    nextControl.addEventListener("click", () => {
        resetInterval();
        nextImage();
    });

    selectorsArray.forEach(selector => {
        const selectorIndex = selectorsArray.indexOf(selector);
        selector.addEventListener("click", () => {
            resetInterval();
            selectedImage = selectorIndex;
            updateView();
        })
    })

    // Manages page resize
    window.addEventListener("resize", () => {
        imagesContainer.style.left = `${-(selectedImage * getCarrouselWidth())}px`;
    });

    // Sets up the auto swipe
    updateInterval = setInterval(nextImage,CARROUSEL_INTERVAL);

    // Initiating
    updateView();
})