class ValidationError{
    static isEmpty(value){
        if(value.length === 0)
            throw new EmptyFieldException("Os campos não podem ser vazio!");   
        return value; 
    }

    static isEmptyOrMinTen(value){
        if( !(this.isEmpty(value) instanceof EmptyFieldException) )
            if(value.length < 10)
                throw new MinTenException("Os campos não podem ser menor que 10 caracteres!");   
        return value; 
    }
}