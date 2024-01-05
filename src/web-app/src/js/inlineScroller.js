const scrollers = document.querySelectorAll(".scroller-wrapper");

/**
 * Clamps a number to the be between the min and max value
 * @param num Number to clamp
 * @param min Minimum value
 * @param max Maximum value
 * @returns Clamped value
 */
const clamp = (num, min, max) => Math.min(Math.max(num, min), max);


scrollers.forEach(scroller => {
    const scrollerFade = scroller.querySelector(".scroller-fade");
    const scrollerLeftController = scroller.querySelector(".left-control");
    const scrollerRightController = scroller.querySelector(".right-control");
    const scrollBox = scroller.querySelector(".inline-scroller");
    const scrollBoxElementCount = scrollBox.children.length;
    const scrollDirectionReversed = scroller.classList.contains("rtl");

    /** Used to compare if the scroll position is considered as Zeroed or not */
    const EPSILON = 3;

    /**
     * Calculates the size of the box containing all the elements of the scroller
     * @returns size of the box containing all the elements of the scroller
     */
    const getScrollerBoxElementSize = () => {
        const scrollerBoxElementsWidthElement = scroller.querySelector(".inline-scroller :first-child");
        const scrollerBoxElementsWidth = scrollerBoxElementsWidthElement.getBoundingClientRect().width;
        const scrollerBoxElementsStyle = getComputedStyle(scrollerBoxElementsWidthElement)
        const scrollerBoxElementsMargin = parseInt(scrollerBoxElementsStyle.marginLeft) + parseInt(scrollerBoxElementsStyle.marginRight);
        return scrollerBoxElementsWidth + scrollerBoxElementsMargin;
    }


    /**
     * Resets the scroll position given its orientation rtl or ltr
     */
    const resetScroll = () => {
        if(scrollDirectionReversed) {
            scrollerFade.scrollTo(100000,0);
        } else {
            scrollerFade.scrollTo(0,0);
        }
        scroller.setAttribute("data-index", 1);
    }

    /**
     * Displays the control to go to the left
     */
    const displayLeftScrollControl = () => {
        scrollerLeftController.classList.add("visible");
        scrollerFade.classList.add("fade-left");
    }

    /**
     * Hides the control to go to the left
     */
    const hideLeftScrollControl = () => {
        scrollerLeftController.classList.remove("visible");
        scrollerFade.classList.remove("fade-left");
    }

    /**
     * Displays the control to go to the right
     */
    const displayRightScrollControl = () => {
        scrollerRightController.classList.add("visible");
        scrollerFade.classList.add("fade-right");
    }
    
    /**
     * Hides the control to go to the right
     */
    const hideRightScrollControl = () => {
        scrollerRightController.classList.remove("visible");
        scrollerFade.classList.remove("fade-right");
    }

    /**
     * Gets the v of the opacity fade effect on the left or the right of the scroller
     * @returns width of the opacity fade effect on the left or the right of the scroller
     */
    const getFadeEffectSize = () => {
        return scrollerFade.getBoundingClientRect().width * (parseInt(getComputedStyle(scrollerFade).getPropertyValue('--mask-size'))/100)
    }

    /**
     * Updates the view to display the current state of the scroller
     * Or to adapt to the scroller's new size if modified
     */
    const updateScrollView = () => {
        let scrollIndex = scroller.getAttribute("data-index");
        scrollIndex = parseInt(scrollIndex);
        scroller.setAttribute("data-index", scrollIndex);

        let scrollerElementWidth = scroller.getBoundingClientRect().width;
        const scrollBoxWidth = scrollBox.getBoundingClientRect().width;
        let scrollWidth = scrollerFade.scrollWidth;
        let maxScroll = scrollWidth - scrollerElementWidth;

        // Setting the scroll position given the current index
        if(scrollIndex == 1) {
            resetScroll();
        }
        else {
            if(scrollDirectionReversed) {
                scrollerFade.scrollTo(
                    scrollBoxWidth - 
                    (getScrollerBoxElementSize()*(scrollIndex-1) - getFadeEffectSize()*2) - 
                    scrollerElementWidth
                    , 0
                );
            } else {
                scrollerFade.scrollTo(
                    getScrollerBoxElementSize()*(scrollIndex-1) - getFadeEffectSize()*2,
                    0
                );
            }
        }
        
        let scrollPosition = scrollerFade.scrollLeft;
        scrollPosition = clamp(scrollPosition, 0, maxScroll);

        // Displays the rights controllers according to the scroll position and the width

        if(scrollWidth > scrollerElementWidth && scrollPosition != 0) {
            displayLeftScrollControl();
            displayRightScrollControl();
        }

        else if(scrollWidth > scrollerElementWidth) {
            hideLeftScrollControl();
            displayRightScrollControl();
        }
        
        else{
            hideLeftScrollControl();
            hideRightScrollControl();
        }

        if(scrollPosition < EPSILON) {
            hideLeftScrollControl();
        }

        if(Math.abs(scrollWidth - (scrollPosition + scrollerElementWidth)) < EPSILON ) {
            hideRightScrollControl();
        }
    };

    /**
     * Gets the previous index of the scroller
     * @param index current index
     * @returns newIndex
     */
    const prevIndex = (index) => {
        let newIndex = index - 1;
        return newIndex <= 1 ? 1 : newIndex;
    }

    /**
     * Gets the next index of the scroller
     * @param index current index
     * @returns newIndex
     */
    const nextIndex = (index) => {
        let newIndex = index + 1;
        return newIndex >= scrollBoxElementCount ? scrollBoxElementCount : newIndex;
    }

    /**
     * Scrolls to the left of the scroller box
     */
    const scrollLeft = () => {
        if(!scrollerLeftController.classList.contains("visible")) return;    
        let scrollIndex = scroller.getAttribute("data-index");
        scrollIndex = parseInt(scrollIndex);
        
        scroller.setAttribute(
            "data-index",
            scrollDirectionReversed ? nextIndex(scrollIndex) :  prevIndex(scrollIndex)
        );
        updateScrollView();
    } 

    /**
     * Scrolls to the right of the scroller box
     */
    const scrollRight = () => {
        if(!scrollerRightController.classList.contains("visible")) return;
        let scrollIndex = scroller.getAttribute("data-index");
        scrollIndex = parseInt(scrollIndex);

        scroller.setAttribute(
            "data-index",
            scrollDirectionReversed ? prevIndex(scrollIndex) : nextIndex(scrollIndex)
        );
        updateScrollView();
    } 

    // Default setup
    resetScroll();
    updateScrollView();

    // Event setups
    scrollerRightController.addEventListener("click", scrollRight);
    scrollerLeftController.addEventListener("click", scrollLeft);
    window.addEventListener("resize", () => {resetScroll(); updateScrollView()});
    scrollerFade.addEventListener("scroll", updateScrollView);
});