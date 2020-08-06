class SpentRequest{
    constructor(){
        this._url = "http://localhost:8080/";
    }

    get(endpoint){
        return fetch(this._url + endpoint, {
            method: 'GET',
            headers: {"Content-Type":"Application/json"}
        })
        .then(response => response.json())
    }
    
    post(endpoint, obj){
        return fetch(this._url + endpoint, {
            method: 'POST',
            body: JSON.stringify(obj),
            headers: {"Content-Type":"application/json"}
        })
        .then((response) => {
            if(response.status === 201){
                return response.json();
            }
            return response;
        })
    }
}