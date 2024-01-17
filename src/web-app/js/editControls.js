const deleteBtn = document.querySelector("#delete-btn");
const actionField = document.querySelector("#action-field");

deleteBtn.addEventListener("click", e => {
    if(confirm("Voulez vous vraiment supprimer ce membre ?"))
        actionField.value = "delete";
    else 
        e.preventDefault();
}); 