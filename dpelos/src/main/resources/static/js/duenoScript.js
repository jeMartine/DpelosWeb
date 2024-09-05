/*import { Input, initMDB } from "mdb-ui-kit";



initMDB({ Input });

const searchButton = document.getElementById('search-button');
const searchInput = document.getElementById('search-input');
searchButton.addEventListener('click', () => {
  const inputValue = searchInput.value;
  alert(inputValue);
});*/

$(document).ready(function () {
  const $owl = $(".owl-carousel");

  $owl.owlCarousel({
    items: 3,
    margin: 10,
    loop: false,
    nav: false,
    responsive: {
      0: {
        items: 1
      },
      600: {
        items: 2
      },
      1000: {
        items: 3
      }
    }
  });

  /* Ajustar el carrusel cuando solo hay un item */
  function adjustCarouselForSingleItem() {
    const itemsCount = $owl.find(".owl-item").length;

    if (itemsCount === 1) {
      $owl.trigger('add.owl.carousel', ['<div class="item empty-item"></div>', 0]);
      $owl.trigger('refresh.owl.carousel');
      $owl.trigger('to.owl.carousel', [1, 0, true]);
    } else if (itemsCount === 2) {
      $owl.trigger('refresh.owl.carousel');
    }
  }

  adjustCarouselForSingleItem();

  /* Desplegar informacion de la mascota al hacer click en la imagen */
  $(".owl-carousel").on("click", ".img-mascota", function () {
    const card = $(this).closest(".card-mascota");
    const selected = card.hasClass("selected");

    $(".card-mascota").removeClass("selected");
    $("#info-mascota").hide();

    if (!selected) {
      card.addClass("selected");

      const nombre = $(this).data("nombre");
      const raza = $(this).data("raza");
      const fecha = $(this).data("fecha");
      const estado = $(this).data("estado");
      const enfermedad = $(this).data("enfermedad");

      $("#info-nombre").text(nombre);
      $("#info-raza").text(raza);
      $("#info-fecha").text(fecha);
      $("#info-enfermedad").text(enfermedad);

      const estadoDiv = $("#info-estado");
      estadoDiv.text(estado ? "En tratamiento" : "Inactivo");
      estadoDiv.removeClass("estado-en-tratamiento estado-inactivo");
      estadoDiv.addClass(estado ? "estado-en-tratamiento" : "estado-inactivo");

      $("#info-mascota").show();
    }
  });

  /* Buscar una mascota*/
  $("#search-button").on("click", function () {
    const searchTerm = $("#search-input").val().trim().toLowerCase();
    const images = $(".owl-carousel .img-mascota");
    let found = false;

    $("#error-message").hide();
    $(".card-mascota").removeClass("selected");
    $("#info-mascota").hide();

    images.each(function () {
      const nombre = $(this).data("nombre").toLowerCase();
      if (nombre === searchTerm) {
        $(this).closest(".card-mascota").addClass("selected");
        const info = {
          nombre: $(this).data("nombre"),
          raza: $(this).data("raza"),
          fecha: $(this).data("fecha"),
          estado: $(this).data("estado"),
          enfermedad: $(this).data("enfermedad")
        };

        $("#info-nombre").text(info.nombre);
        $("#info-raza").text(info.raza);
        $("#info-fecha").text(info.fecha);
        $("#info-enfermedad").text(info.enfermedad);

        const estadoDiv = $("#info-estado");
        estadoDiv.text(info.estado ? "En tratamiento" : "Inactivo");
        estadoDiv.removeClass("estado-en-tratamiento estado-inactivo");
        estadoDiv.addClass(info.estado ? "estado-en-tratamiento" : "estado-inactivo");

        $("#info-mascota").show();
        found = true;
      }
    });

    if (!found) {
      $("#error-message").text("No existe una mascota con el nombre '" + searchTerm + "'").show();
    }
  });

});


