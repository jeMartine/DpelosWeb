document.addEventListener('DOMContentLoaded', function () {
    // Obtener los datos del archivo JSON
    fetch('/data/testimonios.json')
        .then(response => response.json())
        .then(data => {
            // Crear elementos HTML para cada testimonio
            const slider = document.getElementById('testimonial-slider');
            data.forEach(testimonial => {
                const testimonialElement = document.createElement('div');
                testimonialElement.className = 'testimonial';

                // Crear elementos HTML para las estrellas
                let starsHtml = '';
                for (let i = 1; i <= 5; i++) {
                    if (i <= testimonial.rating) {
                        starsHtml += '<i class="fa fa-star"></i>';
                    } else {
                        starsHtml += '<i class="fa fa-star-o"></i>';
                    }
                }

                // Crear el elemento HTML para el testimonio
                testimonialElement.innerHTML = `
                    <div class="testimonial-header">
                        <img src="${testimonial.foto}" alt="User Photo">
                        <div>
                            <div class="pet-name">${testimonial.nombre_mascota} <i class="fa fa-paw paw-icon"></i></div>
                            <div class="owner-name">${testimonial.nombre_dueño}</div>
                        </div>
                    </div>
                    <div class="separator"></div>
                    <div class="stars-date">
                        <div class="stars">
                            ${starsHtml}
                        </div>
                        <div class="date">${testimonial.fecha_comentario}</div>
                    </div>
                    <div class="description">
                        ${testimonial.comentario}
                    </div>
                    <div class="separator"></div>
                    <div class="reactions">
                        <div>
                            <i class="fa fa-heart heart"></i> <span class="like-count">${testimonial.likes}</span>
                        </div>
                        <i class="fa fa-flag flag"></i>
                    </div>
                `;
                // Agregar el elemento HTML al slider
                slider.appendChild(testimonialElement);

                // Agregar el evento de clic a los me gusta
                testimonialElement.querySelector('.heart').addEventListener('click', function () {
                    this.classList.toggle('liked');
                    let count = parseInt(this.nextElementSibling.textContent);
                    if (this.classList.contains('liked')) {
                        count++;
                    } 
                    this.nextElementSibling.textContent = count;
                });
            });

            // Iniciar el carrusel
            $("#testimonial-slider").owlCarousel({
                items: 3,
                itemsDesktop: [1000, 3],
                itemsDesktopSmall: [980, 2],
                itemsTablet: [768, 2],
                itemsMobile: [650, 1],
                pagination: true,
                navigation: false,
                slideSpeed: 1000,
                autoPlay: true
            });
        })
        .catch(error => console.error('Error al cargar los testimonios:', error));
});
