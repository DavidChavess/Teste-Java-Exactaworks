class SpentController {
    constructor(){      
        const $ = document.querySelector.bind(document);
        this._person = $('#person');
        this._description = $('#description');
        this._value = $('#value');
        this._tag = $('#tag');

        this._spentId = $('#spentId'); 
        this._spentView = new SpentView('#spent');
        this._spentViewNoTags = new SpentViewNoTags('#spent');
        this._request = new SpentRequest();
    }

    insert(event){
        event.preventDefault();
        this._request.post('spents', this._newSpent())
        .then(response => {
            const spent = new Spent(response.id, response.person, response.description,response.value, response.datetime);
            spent.addAllTags(response.tags);
            this._spentView.update(spent); 
        })
    }
    
    findById(event){
        event.preventDefault();
        this._getById(this._spentId.value);
    }

    findAll(){
        this._request.get(`spents`)
        .then(response => {   
            this._spentViewNoTags.update(response.map(spent => new Spent(spent.id, spent.person, spent.description,spent.value, spent.datetime)))
            this._eventSpentDetails();
        })
        .catch(response => {
            console.log(response)
        })
    }
    
    _eventSpentDetails(){
        const formsDetails = this._spentViewNoTags.details();
        for(let i=0; i < formsDetails.length; i++){
            formsDetails[i].onsubmit = (event) => {
                event.preventDefault();
                this._getById(formsDetails[i].elements[0].value);                
            }
        }
    }

    _getById(id){
        this._request.get(`spents/${id}`)
        .then(response => {
            if(response.status === 404) throw(response.error)

            const spent = new Spent(response.id, response.person, response.description,response.value, response.datetime);
            console.log(spent);
            spent.addAllTags(response.tags);
            this._spentView.update(spent); 
        })
        .catch(response => {
            alert(response);
        })
    }

    _newSpent(){
        return {
            id : null,
            person : this._person.value,
            description : this._description.value,
            value : this._value.value,
            datetime : new Date(),
            tags : this._tag.value.split(';')
        };
    }
}