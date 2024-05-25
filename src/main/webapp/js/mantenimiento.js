const botonNuevo = document.querySelector(".boton-nuevo");
const botonesEditar = document.querySelectorAll(".boton-editar");

const formRegistrar = document.querySelector("#form-registrar");
const formEditar = document.querySelector("#form-editar");

const iconsClose = document.querySelectorAll(".icon-close");

botonNuevo.addEventListener("click", (e) => {
	e.preventDefault();
	formRegistrar.classList.add("form-active");
});

botonesEditar.forEach((boton) => {
	boton.addEventListener("click", (e) => {
		e.preventDefault();
		formEditar.classList.add("form-active");

		const fila = e.target.closest("tr").querySelectorAll("td");
		const inputs = document.querySelectorAll("#form-editar input");
		const selects = document.querySelectorAll("#form-editar select");

		inputs.forEach((input, index) => {
			input.value = fila[index].textContent;
		});
		
		if(inputs.length>3){
			selects.forEach((select, index) => {
				select.value = fila[index + 6].textContent;
			});
		} else if(inputs.length==3){
			selects.forEach((select, index) => {
				select.value = fila[index + 3].textContent;
			});
		} else if(inputs.length<=2){
			selects.forEach((select, index) => {
				select.value = fila[index + 1].textContent;
			});
		}
	});
});

iconsClose.forEach((icon) => {
	icon.addEventListener("click", (e) => {
		const formToClose = e.target.closest('.form-fondo');
		if (formToClose) {
			formToClose.classList.remove('form-active');
		}
	});
});
