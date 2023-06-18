const productOrder = sessionStorage.getItem('product-order');
const product = JSON.parse(productOrder);

const loggedUser = sessionStorage.getItem('logged-user');
const user = JSON.parse(loggedUser);

document.getElementById('productRecap').innerHTML = `
<h4>Prodotto da ordinare: </h4><span>${product.name}</span>
<h4>Prezzo: </h4><span>${product.price}</span>
<h4>Unità disponibili: </h4><span>${product.qtyAvailable}</span>
<h4>Descrizione: </h4><span>${product.description}</span>
`;

document.getElementById('btnOrder').addEventListener('click', createOrder);

function createOrder() {

    const paymentType = document.getElementById('inputPayment').value;
    const qty = document.getElementById('inputQty').value;

    class Order {

        constructor(paymentType, qtyProduct) {
            this.paymentType = paymentType;
            this.qtyProduct = qtyProduct;
        }
    }

    const newOrder = new Order(paymentType, qty);
    const jsonProduct = JSON.stringify(newOrder);

    fetch('api/clients/' + user.code + '/products/' + product.id + '/orders', {
        method: 'POST', headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }, body: jsonProduct
    }).then(res => {

        if(res.status == 201) {
            alert('prodotto ordinato!');
            window.location.href = "products";
        } else {
            alert('Ops! Qualcosa è andato storto...');
        }

    })
}