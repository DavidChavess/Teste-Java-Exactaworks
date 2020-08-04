class SpentView extends View{
    template(spent){
        return`<table>
            <thead>
                <tr>
                    <th>Id do gasto</th>
                    <th>Nome do cliente</th>
                    <th>Descrição</th>
                    <th>Data/Hora</th>
                    <th>Valor</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${spent.getId()}</td>
                    <td>${spent.getPerson()}</td>
                    <td>${spent.getDescription()}</td>
                    <td>${spent.getDate()}</td>
                    <td>${spent.getValue()}</td>
                </tr>
            </tbody>
        </table>
        <ol class='tags'>
            <h3>Tags: </h3>
            ${this.renderizaTags(spent)}
        </ol>`;
    }
    
    renderizaTags(spent){
        let html = ''
        spent.getTags().forEach(tag => html +=`<li>${tag}</li>`)
        return html;        
    }
}