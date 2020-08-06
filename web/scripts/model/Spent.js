class Spent{
    constructor(_id, _personName, _description, _value, _date){
        Object.assign(this, { _id, _personName, _description, _value })
        this._date = new Date(_date);
        this._tags = [];

        Object.freeze(this);
    }

    getId(){
        return this._id;
    }

    getPersonName(){
        return this._personName;
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

    addTag(tag){
        this._tags.push(tag);
    }
    
    addAllTags(tags){
        tags.forEach(tag => {
            this._tags.push(tag);
        })
    }
}