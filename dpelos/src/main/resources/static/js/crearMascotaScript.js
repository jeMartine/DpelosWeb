function verificarDueno() {
    const cedula = document.getElementById("cedulaDueno").value;

    console.log(cedula);

    fetch(`/dueno/verificar/${cedula}`)
    .then(response => response.json())
    .then(data => {
      if (data.existe === "true") {
        console.log("El dueno existe");
        document.getElementById("nombreDueno").value = data.nombre;
        document.getElementById("nombreDuenoContainer").style.display = "block";
        document.getElementById("agregarDuenoContainer").style.display = "none";
        registrarMascotaBtn.disabled = false; // Habilitar el botón
      } else {
        console.log("El No existe");
        document.getElementById("nombreDuenoContainer").style.display = "none";
        document.getElementById("agregarDuenoContainer").style.display = "block";
        registrarMascotaBtn.disabled = true; // Deshabilitar el botón
        alert("Debe asignar un dueño válido");  

      }
    });
  }
  