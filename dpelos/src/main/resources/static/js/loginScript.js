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
    document.body.style.backgroundColor = '#9496F3'; // Color del cliente
    document.body.style.transition="all 0.6s ease-in-out";
});

/*
$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');
    const userType = urlParams.get('userType');

    if (error === 'user' && userType === 'veterinario') {
        $("#modalVeterinario").modal("show");
        container.classList.add("active"); // Asegúrate de mostrar el lado de veterinario
        document.body.style.backgroundColor = '#94F3D4'; // Color del empleado
    } else if (error === 'password' && userType === 'veterinario') {
        $("#modalVeterinario").modal("show");
        container.classList.add("active"); // Asegúrate de mostrar el lado de veterinario
        document.body.style.backgroundColor = '#94F3D4'; // Color del empleado
    } else if (error === 'true') {
        $("#modalCliente").modal("show");
        container.classList.remove("active"); // Asegúrate de mostrar el lado de cliente
        document.body.style.backgroundColor = '#9496F3'; // Color del cliente
    }

    $('#modalCliente, #modalVeterinario').on('hidden.bs.modal', function () {
        window.location.href = '/login';
    });
});*/

$(document).ready(function () {
    if (window.location.search.includes("error=true")) {
        $("#modalCliente").modal("show");
    } else if (window.location.search.includes("error=password")) {
        $("#modalVeterinario").modal("show");
    } else if (window.location.search.includes("error=user")) {
        $("#modalVeterinario").modal("show");
    }
    
    $('#modalCliente, #modalVeterinario').on('hidden.bs.modal', function () {
        window.location.href = '/login';
    });
});



