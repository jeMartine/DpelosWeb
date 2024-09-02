function verificarDueno() {
    const cedulaDueno = document.getElementById("cedulaDueno").value;
  
    if (cedulaDueno) {
      fetch(`/dueno/verificar/${cedulaDueno}`)
        .then(response => response.json())
        .then(data => {
          const btnAgregarDueno = document.getElementById("btnAgregarDueno");
          if (data.existe) {
            btnAgregarDueno.style.display = "none";
          } else {
            btnAgregarDueno.style.display = "block";
          }
        })
        .catch(error => console.error("Error verificando dueño:", error));
    }
  }
  