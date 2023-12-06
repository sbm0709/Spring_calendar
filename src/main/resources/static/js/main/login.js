const signUpBox = document.querySelector(".sign-up-box");

signUpBox.addEventListener("click", handleClick, false);
function handleClick(event) {
    if (signUpBox.classList.contains("active")) return;
    let str = `
            <span onclick="event.stopPropagation(); removedActive();">X</span>
            <input type="text" name="id" placeholder="ID"/>
            <input type="password" name="pw" placeholder="PASSWORD"/>
            <input type="file" name="fileData"/>
            <button onclick="handleSignUp()">회원가입</button>
    `;
    this.classList.toggle("active");
    this.innerHTML = "";
    setTimeout(() => (this.innerHTML = str), 500);
}

function handleSignUp() {
    let signUpBoxActive = document.querySelector(".active");
    let inputs = signUpBoxActive.querySelectorAll("input");
    let form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', "/user/register");
    form.setAttribute('enctype', "multipart/form-data");

    let idField = inputs[0];
    let pwField = inputs[1];
    let fileField = inputs[2];

    // if (
    //     idField.value === "" ||
    //     pwField.value === "" ||
    //     fileField.value === ""
    // ) {
    //     return;
    // }

    form.appendChild(idField);
    form.appendChild(pwField);
    form.appendChild(fileField);
    document.body.appendChild(form);
    form.encoding = "multipart/form-data";
    console.log(form.encoding)
    form.submit();
    removedActive();

}



function removedActive() {
    signUpBox.classList.remove("active");
    signUpBox.innerHTML = `<i class="fa-solid fa-pen"></i>`;
}
