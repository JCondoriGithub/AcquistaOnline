document.getElementById('btnCreateClient').addEventListener('click', createClient);

function createClient() {

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

    fetch('api/clients', {
        method: 'POST', headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }, body: jsonClient
    }).then(res => {
        if(res.status == 201) {
            alert('ora sei registrato!');
            window.location.href = "/";
        }
    })
}