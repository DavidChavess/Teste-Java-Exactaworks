class SpentController {
    constructor(){      
        this._spentId = document.querySelector('#spentId'); 
        this._spentView = new SpentView('#spent');
        this._request = new SpentRequest();
    }

    findById(event){
        event.preventDefault();
        this._request.findById(`spents/${this._spentId.value}`)
        .then(response => {
            if(response.status === 404) throw(response.error)

            const spent = new Spent(response.id, response.person, response.description,response.value, response.datetime);
            spent.addAllTags(response.tags);
            this._spentView.update(spent); 
        })
        .catch(response => {
            console.log(response)
        })
    }
}