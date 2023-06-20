import { sendGet } from "./requests.js";

async function createProductTbl() {

    let response = await sendGet('api/products');
    let products = await response.json();

    for(let i = 0; i < products.length; i++) {

        const tr = document.createElement('tr');

        const td1 = document.createElement('td');
        td1.appendChild(document.createTextNode(products[i].name));
        tr.appendChild(td1);

        const td2 = document.createElement('td');
        td2.appendChild(document.createTextNode(`${products[i].price} €`));
        tr.appendChild(td2);

        const td3 = document.createElement('td');
        td3.appendChild(document.createTextNode(`${products[i].qtyAvailable} unità`));
        tr.appendChild(td3);

        const td4 = document.createElement('td');
        td4.appendChild(document.createTextNode(products[i].description));
        tr.appendChild(td4);

        const btn = document.createElement('button');
        btn.type = 'button';
        btn.className = 'btn btn-primary';
        btn.appendChild(document.createTextNode('Ordinalo ora'));
        btn.addEventListener('click', function() {
            order(products[i]);
        });
        tr.appendChild(btn);

        document.getElementById('tblBody').appendChild(tr);
    }
}

createProductTbl();

function order(product) {

    const StrProduct = JSON.stringify(product);
    sessionStorage.setItem('product-order', StrProduct);

    window.location.href = "order";
}