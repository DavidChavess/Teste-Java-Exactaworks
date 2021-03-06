const btnShow = document.getElementById('show');
const btnFindAll = document.getElementById('findAll');
const form = document.forms[0];
const formSearch = document.forms[1];
let flagShow = false;

btnShow.addEventListener('click', ()=>{
   if(flagShow){
       form.style.display = 'flex';
       formSearch.style.display = 'none';
       flagShow = false;
   }else{
        form.style.display = 'none';
        formSearch.style.display = 'block';
        flagShow = true;
   }
})

const controller = new SpentController();
form.addEventListener('submit', controller.insert.bind(controller));
formSearch.addEventListener('submit', controller.findById.bind(controller))
btnFindAll.addEventListener('click',  controller.findAll.bind(controller));

