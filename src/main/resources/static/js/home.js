const loggedUser = sessionStorage.getItem('logged-user');
const user = JSON.parse(loggedUser);

document.getElementById('userGreeting').appendChild(document.createTextNode(user.name + ' ' + user.surname));

fetch('api/clients/' + user.code + '/orders/products')
.then(res => res.json())
.then(totPrice => {

    document.getElementById('totPriceText').appendChild(document.createTextNode(totPrice + ' €'));
})

fetch('api/clients/' + user.code + '/orders')
.then(res => res.json())
.then(orders => {

    let newOrder = ``;

    for(let i = 0; i < orders.length; i++) {

        newOrder += `              
    <tr>
        <td>${orders[i].product.name}</td>
        <td>${orders[i].qtyProduct}</td>
        <td>${orders[i].paymentType}</td>
        <td>${orders[i].product.price} €</td>
      </tr>
    `;
    }

    document.getElementById('tblBody').innerHTML = newOrder;
    document.getElementById('totQtyText').appendChild(document.createTextNode(orders.length));
})

document.getElementById('btnLogout').addEventListener('click', function() {
    sessionStorage.removeItem('logged-user');
    window.location.href = "/";
})

document.getElementById('modalBody').innerHTML = `
<b>Nome: </b><span>${user.name}</span><br>
<b>Cognome: </b><span>${user.surname}</span><br>
<b>Email: </b><span>${user.email}</span>
`;