fetch('api/products')
.then(res => res.json())
.then(arrProducts => {

    for(let i = 0; i < arrProducts.length; i++) {

        const tr = document.createElement('tr');

        const td1 = document.createElement('td');
        td1.appendChild(document.createTextNode(arrProducts[i].name));
        tr.appendChild(td1);

        const td2 = document.createElement('td');
        td2.appendChild(document.createTextNode(`${arrProducts[i].price} €`));
        tr.appendChild(td2);

        const td3 = document.createElement('td');
        td3.appendChild(document.createTextNode(`${arrProducts[i].qtyAvailable} unità`));
        tr.appendChild(td3);

        const td4 = document.createElement('td');
        td4.appendChild(document.createTextNode(arrProducts[i].description));
        tr.appendChild(td4);

        const btn = document.createElement('button');
        btn.type = 'button';
        btn.className = 'btn btn-primary';
        btn.appendChild(document.createTextNode('Ordina'));
        btn.addEventListener('click', function() {
            order(arrProducts[i]);
        });
        tr.appendChild(btn);

        document.getElementById('tblBody').appendChild(tr);
    }
})

function order(product) {

    const StrProduct = JSON.stringify(product);
    sessionStorage.setItem('product-order', StrProduct);

    window.location.href = "order";
}