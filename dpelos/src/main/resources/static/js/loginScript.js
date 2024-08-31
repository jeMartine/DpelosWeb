const container = document.getElementById('container');
const registerBtn = document.getElementById('register');
const loginBtn = document.getElementById('login');


registerBtn.addEventListener('click', () => {
    container.classList.add("active");
    document.body.style.backgroundColor = '#94F3D4'; // Color del empleado
    document.body.style.transition="all 0.6s ease-in-out";

});

loginBtn.addEventListener('click', () => {
    container.classList.remove("active");
    document.body.style.backgroundColor = '#fbdb5d'; // Color del cliente
    document.body.style.transition="all 0.6s ease-in-out";
});

$(document).ready(function () {
    if (window.location.search.includes("error=true")) {
        $("#modalCliente").modal("show");
    }
    $('#modalCliente').on('hidden.bs.modal', function () {
        window.location.href = '/login';
    });
});



