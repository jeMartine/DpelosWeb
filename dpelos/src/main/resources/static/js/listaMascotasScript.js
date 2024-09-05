document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('search-input');
    const searchButton = document.getElementById('search-button');
    const cards = document.querySelectorAll('.card');
    const errorMessage = document.getElementById('error-message');
    const cardContainer = document.getElementById('card-container');

    function filterCards() {
        const query = searchInput.value.toLowerCase().trim();
        let found = false;

        cards.forEach(card => {
            const nombre = card.getAttribute('data-nombre').toLowerCase();
            const raza = card.getAttribute('data-raza').toLowerCase();

            if (nombre.includes(query) || raza.includes(query)) {
                card.style.display = '';
                found = true;
            } else {
                card.style.display = 'none';
            }
        });

        if (!found) {
            errorMessage.style.display = 'block';
            errorMessage.innerText = 'No se encontraron resultados para tu búsqueda.';
        } else {
            errorMessage.style.display = 'none';
        }

        cardContainer.style.display = 'flex';
        cardContainer.style.flexWrap = 'wrap';
    }

    searchButton.addEventListener('click', filterCards);
    searchInput.addEventListener('input', filterCards);
});
