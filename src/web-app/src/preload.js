/**
 * Removes the preload class,
 * Used to prevent unwanted start transition of elements
 */
window.addEventListener("load", () => {
    document.querySelector(".preload").classList.remove("preload");
});