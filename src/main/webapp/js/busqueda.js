function busqueda() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("buscar");
    filter = input.value.toUpperCase();
    table = document.getElementsByTagName("table");
    tr = table[0].getElementsByTagName("tr");
    for (i =1; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}