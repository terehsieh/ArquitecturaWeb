document.addEventListener("DOMContentLoaded", init);

function init() {
    "use strict";
    let data = null;
    getData();
    async function getData() {
        let r = await fetch("rest/estudiantes/listaEstudiantes");
        let json =	 await r.json();
        data = await json;
        showData(data);
    }
    async function showData(data){
		$('#myTable').DataTable( {
     	data : data,
	    columns: [
	        { data: 'legajo' },
	        { data: 'nombre' },
	        { data: 'apellido' },
	        { data: 'edad' },
	        { data: 'genero' },
	        { data: 'dni' },
	        { data: 'ciudad_residencia' }
		]
		});
    }
}