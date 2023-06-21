import { sendPost } from "./requests.js";

const productOrder = sessionStorage.getItem('product-order');
const product = JSON.parse(productOrder);

const loggedUser = sessionStorage.getItem('logged-user');
const user = JSON.parse(loggedUser);

document.getElementById('productRecap').innerHTML = `

    <p class="text-center fs-3 text-primary-emphasis">Prodotto da ordinare: <span class="fs-3 text-primary">${product.name}</span></p>
    <p class="text-center fs-3 text-primary-emphasis">Prezzo: <span class="fs-3 text-primary">${product.price} €</span></p>
    <p class="text-center fs-3 text-primary-emphasis">Unità disponibili: <span class="fs-3 text-primary">${product.qtyAvailable}</span></p>
    <p class="text-center fs-3 text-primary-emphasis">Descrizione: <span class="fs-3 text-primary">${product.description}</span></p>
`;

document.getElementById('btnOrder').addEventListener('click', createOrder);

async function createOrder() {

    const paymentType = document.getElementById('inputPayment').value;
    const qty = document.getElementById('inputQty').value;

    class Order {

        constructor(paymentType, qtyProduct) {
            this.paymentType = paymentType;
            this.qtyProduct = qtyProduct;
        }
    }

    const newOrder = new Order(paymentType, qty);
    const jsonOrder = JSON.stringify(newOrder);

    let response = await sendPost('api/clients/' + user.code + '/products/' + product.id + '/orders', jsonOrder);

    if(response.status == 201) {
        alert('prodotto ordinato!');
        window.location.href = "products";
    } else {
        alert('Ops! Qualcosa è andato storto...');
    }
}