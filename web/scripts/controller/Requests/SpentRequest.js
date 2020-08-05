class SpentRequest{
    constructor(){
        this._url = "http://localhost:8080/";
    }

    findById(endpoint){
        return fetch(this._url + endpoint, {
            method: 'GET',
            headers: {"Content-Type":"Application/json"}
        })
        .then(response => response.json())
    }
}