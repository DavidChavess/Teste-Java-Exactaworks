class SpentViewNoTags extends View{
    template(spents){
        return`<table>
            <thead>
                <tr>
                    <th>Id do gasto</th>
                    <th>Nome do cliente</th>
                    <th>Descrição</th>
                    <th>Data/Hora</th>
                    <th>Valor</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                ${spents.map(spent => {
                    return `<tr>
                        <td>${spent.getId()}</td>
                        <td>${spent.getPerson()}</td>
                        <td>${spent.getDescription()}</td>
                        <td>${spent.getDate()}</td>
                        <td>${spent.getValue()}</td>
                        <td><form class='iptu'><input type='hidden' value = ${spent.getId()} /><button>ver</button></form></td>
                    </tr>`
                }).join('')}
            </tbody>
        </table>`;
    }
}