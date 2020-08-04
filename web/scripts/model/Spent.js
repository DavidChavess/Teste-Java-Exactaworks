class Spent{
    constructor(_id, _person, _description, _value, _date, _tags){
        Object.assign(this, { _id, _person, _description, _value })
        this._date = new Date(_date);
        this._tags = [];

        _tags.forEach(tag => {
            this._tags.push(tag);
        })
        Object.freeze(this);
    }

    getId(){
        return this._id;
    }

    getPerson(){
        return this._person;
    }

    getDescription(){
        return this._description;
    }

    getDate(){
        return new Date(this._date.getTime());
    }

    getValue(){
        return this._value;
    }
    
    getTags(){
        return [].concat(this._tags);
    }
}