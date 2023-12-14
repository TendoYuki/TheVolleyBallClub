const pageControls = document.querySelectorAll(".page-control");

pageControls.forEach(pageControl => {
    const prev = pageControl.querySelector(".prev");
    const next = pageControl.querySelector(".next");
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const page = urlParams.get("page");

    if(prev)
        prev.addEventListener("click", e => {
            if(!page) {
                urlParams.append("page", "1");
            } else {
                urlParams.set("page", Number.parseInt(urlParams.get("page"))-1);
            }
            window.location.search = urlParams;
        });
    if(next)
        next.addEventListener("click", e => {
            if(!page) {
                urlParams.append("page", "2");
            } else {
                urlParams.set("page", Number.parseInt(urlParams.get("page"))+1);
            }
            window.location.search = urlParams;
        });
})