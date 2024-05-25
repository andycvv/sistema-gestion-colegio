const nivelSelect = document.getElementById('idNivel-mat');
const gradoSelect = document.getElementById('idGrado-mat');

const gradosPorNivel = {
	1: ['primero', 'segundo', 'tercero', 'cuarto', 'quinto', 'sexto'],
	2: ['primero', 'segundo', 'tercero', 'cuarto', 'quinto']
};

function actualizarGrados() {
	const nivelSeleccionado = nivelSelect.value;
	// Limpiar opciones actuales
	gradoSelect.innerHTML = '';
	// Obtener los grados correspondientes al nivel seleccionado
	const grados = gradosPorNivel[nivelSeleccionado];
	// Crear y agregar las opciones al select de grado
	grados.forEach((grado, index) => {
		const option = document.createElement('option');
		option.text = grado;
		option.value = index+1;
		gradoSelect.add(option);
	});
}

nivelSelect.addEventListener('change', actualizarGrados);

actualizarGrados();