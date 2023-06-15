const btnLogin = document.getElementById('btnLogin');

btnLogin.addEventListener('click', checkCodClient);

async function checkCodClient() {

    const code = document.getElementById('inputCodeClient').value;
    
    let res = await fetch('api/clients/' + code);

    if(res.status == 404) {
        alert('NON SEI REGISTRATO!');
    } else {

        let json = await res.json();

        let user = JSON.stringify(json);
        sessionStorage.setItem('logged-user', user);

        window.location.href = "home";
    }
}