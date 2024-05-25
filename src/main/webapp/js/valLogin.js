$(function() {
	$("#formLogin").validate({
		rules: {
			'dni-log': {
				required: true,
				digits: true,
				minlength: 8,
				maxlength: 8
			},
			'clave-log': {
				required: true,
				maxlength: 8
			}
		},
		messages: {
			'dni-log': {
				required: "Este campo es obligatorio",
				digits: "El DNI solo acepta dígitos",
				minlength: "El DNI debe tener 8 dígitos",
				maxlength: "El DNI debe tener 8 dígitos"
			},
			'clave-log': {
				required: "Este campo es obligatorio",
				maxlength: "La clave es de máximo de 8 caracteres"
			}
		},
		highlight: function(e) {
			$(e).removeClass("es-valido");
			$(e).addClass("es-invalido");
		},
		unhighlight: function(e) {
			$(e).removeClass("es-invalido");
			$(e).addClass("es-valido");
		},
		errorPlacement: function(error, element) {
			$(element).next().append(error).show();
		}
	});
})