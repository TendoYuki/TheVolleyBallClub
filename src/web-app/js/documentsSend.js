const fileForms = document.querySelectorAll(".file-form");

fileForms.forEach(fileForm => {
    const documentInputs = fileForm.querySelectorAll(".btn.upload");
    documentInputs.forEach(documentInput => {
        const input = documentInput.querySelector("input");
        const label = documentInput.querySelector("label");
        console.log(input)

        input.addEventListener("change", e => {
            console.log(documentInput)
            let sendBtn = fileForm.querySelector(".send-btn");
            let cancelBtn = fileForm.querySelector(".cancel-btn");

            if(sendBtn == null) {
                sendBtn = document.createElement("button");
                sendBtn.classList.add("btn", "filled", "send-btn");
                sendBtn.innerText="Envoyer";
                fileForm.appendChild(sendBtn);
            }
            if(cancelBtn == null) {
                cancelBtn = document.createElement("a");
                cancelBtn.classList.add("btn", "outline", "cancel-btn");
                cancelBtn.innerText="Annuler";
                cancelBtn.setAttribute("href", window.location.href);
                fileForm.appendChild(cancelBtn);
            }

            label.innerText = "Changer";
        })
    });
});