/*import { Input, initMDB } from "mdb-ui-kit";



initMDB({ Input });

const searchButton = document.getElementById('search-button');
const searchInput = document.getElementById('search-input');
searchButton.addEventListener('click', () => {
  const inputValue = searchInput.value;
  alert(inputValue);
});*/

$(document).ready(function() {
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

  $(".owl-carousel").on("click", ".img-mascota", function() {
    $(".card-mascota").removeClass("selected");
    $("#info-mascota").hide();

    $(this).closest(".card-mascota").addClass("selected");

    const nombre = $(this).siblings(".img-caption").find("p").text();
    const fecha = $(this).data("fecha");
    const estado = $(this).data("estado");
    const enfermedad = $(this).data("enfermedad");

    $("#info-nombre").text(nombre);
    $("#info-fecha").text(fecha);
    $("#info-estado").text(estado);
    $("#info-enfermedad").text(enfermedad);

    $("#info-mascota").show();
  });

  $(window).resize(function() {
    adjustCarouselForSingleItem();
  });
});
