export async function sendPost(url, jsonData) {

    let response = await fetch(url, {
        method: 'POST', headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }, body: jsonData
    });

    return response;
}

export async function sendGet(url) {

    let response = await fetch(url);

    return response;
}

export async function sendDelete(url) {

    let response = await fetch(url, { method: 'DELETE' });

    return response;
}