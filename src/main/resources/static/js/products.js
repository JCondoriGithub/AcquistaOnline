fetch('api/products')
.then(res => res.json())
.then(arrProducts => {
console.log(arrProducts)
    let products = ``;

    for(let i = 0; i < arrProducts.length; i++) {

        products += `
        <tr>
            <td>${arrProducts[i].name}</td>
            <td>${arrProducts[i].price}</td>
            <td>${arrProducts[i].qtyAvailable}</td>
            <td>${arrProducts[i].description}</td>
        </tr>`;
    }

    document.getElementById('tblBody').innerHTML = products;
})