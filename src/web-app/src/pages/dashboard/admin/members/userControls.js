const users = document.querySelectorAll(".user-display");

users.forEach(user => {
    const id = user.querySelector(".user-id");
    user.addEventListener("click", event => {
        window.location = `/dashboard/admin/members/view/?user=${id.innerHTML}`;
    });
});