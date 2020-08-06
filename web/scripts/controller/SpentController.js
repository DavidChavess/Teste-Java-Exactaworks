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

    findById(event){
        event.preventDefault();
        this._request.get(`spents/${this._spentId.value}`)
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

    findAll(){
        this._request.get(`spents`)
        .then(response => {   
            this._spentViewNoTags.update(response.map(spent => new Spent(spent.id, spent.person, spent.description,spent.value, spent.datetime)))
        })
        .catch(response => {
            console.log(response)
        })
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