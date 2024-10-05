const apiUrl = `http://localhost:8080/eventvisualizer_war/app/api`;

const postOrPutFetch = async (methodType, entityData, entityType)=>{
    let url = `${apiUrl}/add=${entityType}`;
    return await fetch(url, {
        method: methodType,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entityData),
    }).then(response => {
        if(!response.ok) {
            console.error(`Error with response. status: ${response.status} ${response.statusText}`);
        }
        return response.json();
    }).then(data => {
        return data;
    }).catch(err => console.error(`Error posting data:`, err))
}

const getOrDeleteFetch = async (methodType, entityType)=>{
    id = 1;
    let url = `${apiUrl}/${entityType}/${id}`;
    return await fetch(url, {
        method: methodType,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if(!response.ok) {
            console.error(`Error with response. status: ${response.status} ${response.statusText}`);
        }
        return response.json();
    }).then(data => {
        return data;
    }).catch(err => console.error(`Error posting data:`, err))
}

const getFetch = async () => {
    return await fetch('http://localhost:8080/eventvisualizer_war/app/api/notebooks/1', {
        method: 'GET', // or 'GET' depending on the API
        headers: {
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            return response.json();
        }).then(data => {
            console.log(data);
            return data;
        }).catch(err => console.log(err));
}