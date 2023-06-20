import { sendDelete, sendGet } from "./requests.js";

const loggedUser = sessionStorage.getItem('logged-user');
const user = JSON.parse(loggedUser);

document.getElementById('userGreeting').appendChild(document.createTextNode(user.name + ' ' + user.surname));

document.getElementById('btnDeleteAccount').addEventListener('click', deleteUser);

async function deleteUser() {

    let response = await sendDelete('api/clients/' + user.code);

    if(response.status == 204) {

        alert('ora non sei più registrato a questo sito');
        sessionStorage.removeItem('logged-user');
        window.location.href = "/";
    }
}

async function getTotPriceOrders() {

    let response = await sendGet('api/clients/' + user.code + '/orders/products');

    if(response.status != 200) {
        document.getElementById('totPriceText').appendChild(document.createTextNode('0 €'));
    } else {
        let totPrice = await response.json();
        document.getElementById('totPriceText').appendChild(document.createTextNode(totPrice + ' €'));
    }
}

getTotPriceOrders();

async function createOrdersTbl() {

    let response = await sendGet('api/clients/' + user.code + '/orders');
    let orders = await response.json();

    for(let i = 0; i < orders.length; i++) {        
        
        const tr = document.createElement('tr');

        const td1 = document.createElement('td');
        td1.appendChild(document.createTextNode(orders[i].product.name));
        tr.appendChild(td1);

        const td2 = document.createElement('td');
        td2.appendChild(document.createTextNode(orders[i].qtyProduct + ' unità'));
        tr.appendChild(td2);

        const td3 = document.createElement('td');
        td3.appendChild(document.createTextNode(orders[i].paymentType));
        tr.appendChild(td3);

        const td4 = document.createElement('td');
        td4.appendChild(document.createTextNode(orders[i].product.price + ' €'));
        tr.appendChild(td4);

        const btnDelete = document.createElement('button');
        btnDelete.type = 'button';
        btnDelete.className = 'btn btn-danger';
        btnDelete.appendChild(document.createTextNode('Elimina'));
        btnDelete.addEventListener('click', function() {
            deleteOrder(orders[i]);
        });
        tr.appendChild(btnDelete);

        document.getElementById('tblBody').appendChild(tr);
    }

    document.getElementById('totQtyText').appendChild(document.createTextNode(orders.length));
}

createOrdersTbl();


async function deleteOrder(order) {

    let response = await sendDelete('api/orders/' + order.code);

    if(response.status == 204) {

        alert('ordine eliminato!');
        window.location.href = "home";
    }
}

document.getElementById('btnLogout').addEventListener('click', function() {
    sessionStorage.removeItem('logged-user');
    window.location.href = "/";
})

document.getElementById('modalBody').innerHTML = `
<b>Codice: </b><span>${user.code}</span><br>
<b>Nome: </b><span>${user.name}</span><br>
<b>Cognome: </b><span>${user.surname}</span><br>
<b>Email: </b><span>${user.email}</span>
`;