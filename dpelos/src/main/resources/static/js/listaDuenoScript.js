document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('search-input');
    const searchButton = document.getElementById('search-button');
    const tableBody = document.getElementById('tableBody');
    const errorMessage = document.getElementById('error-message');

    function filterTable() {
        const query = searchInput.value.toLowerCase();
        const rows = tableBody.querySelectorAll('tr');
        let found = false;

        rows.forEach(row => {
            const cells = row.querySelectorAll('td');
            const cedula = cells[1].innerText.toLowerCase();
            const nombreCompleto = cells[2].innerText.toLowerCase();

            if (cedula.startsWith(query) || nombreCompleto.includes(query)) {
                row.style.display = '';
                found = true;
            } else {
                row.style.display = 'none';
            }
        });

        if (!found) {
            errorMessage.style.display = 'block';
            errorMessage.innerText = 'No se encontraron resultados para tu búsqueda.';
        } else {
            errorMessage.style.display = 'none';
        }
    }

    searchButton.addEventListener('click', filterTable);
    searchInput.addEventListener('input', filterTable);
});