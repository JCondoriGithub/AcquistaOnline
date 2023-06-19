import { sendPost } from "./requests.js";

document.getElementById('btnCreateClient').addEventListener('click', createClient);

async function createClient() {

    const name = document.getElementById('inputName').value;
    const surname = document.getElementById('inputSurname').value;
    const code = document.getElementById('inputCode').value;
    const email = document.getElementById('inputEmail').value;

    class Client {

        constructor(name, surname, code, email) {
            this.name = name;
            this.surname = surname;
            this.code = code;
            this.email = email;
        }
    }

    const newClient = new Client(name, surname, code, email);
    const jsonClient = JSON.stringify(newClient);

    let response = await sendPost('api/clients', jsonClient);

    if(response.status == 201) {

        alert('ora sei registrato!');
        window.location.href = "/";
    }
}