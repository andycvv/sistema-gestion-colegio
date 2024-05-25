$(function(){
	$.validator.addMethod("lettersonly", function(value, element) {
        return this.optional(element) || /^[a-zA-ZáéíóúüÁÉÍÓÚÜ\s]+$/.test(value);
    }, "Este campo solo acepta letras");
    
     $.validator.addMethod("celularValido", function(value, element) {
        return this.optional(element) || /^9\d{8}$/.test(value);
    }, "El número de celular debe empezar por 9 y tener 9 dígitos");
    
	$("#form-reg").validate({
		rules: {
			"nombres-reg": {
				required: true,
				lettersonly: true,
				minlength: 3,
				maxlength: 30,
			},
			"apePaterno-reg": {
				required: true,
				lettersonly: true,
				minlength: 3,
				maxlength: 15,
			},
			"apeMaterno-reg": {
				required: true,
				lettersonly: true,
				minlength: 3,
				maxlength: 15,
			},
			"celular-reg": {
				required: true,
				digits: true,
				celularValido: true,
			},
			"dni-reg": {
				required: true,
				digits: true,
				minlength: 8,
				maxlength: 8,
			},			
		},
		messages: {
			"nombres-reg": {
				required: "Este campo es obligatorio",
				minlength: "Ingresar como mínimo 3 caracteres",
				maxlength: "Ingresar como máximo 30 caracteres",
			},
			"apePaterno-reg": {
				required: "Este campo es obligatorio",
				minlength: "Ingresar como mínimo 3 caracteres",
				maxlength: "Ingresar como máximo 15 caracteres",
			},
			"apeMaterno-reg": {
				required: "Este campo es obligatorio",
				minlength: "Ingresar como mínimo 3 caracteres",
				maxlength: "Ingresar como máximo 15 caracteres",
			},
			"celular-reg": {
				required: "Este campo es obligatorio",
				digits: "Este campo solo acepta dígitos",
			},
			"dni-reg": {
				required: "Este campo es obligatorio",
				digits: "Este campo solo acepta dígitos",
				minlength: "El DNI debe ser de 8 dígitos",
				maxlength: "El DNI debe ser de 8 dígitos",	
			},			
		},
		highlight: function(e){
			$(e).removeClass("is-valid");
			$(e).addClass("is-invalid");
		},
		unhighlight: function(e){
			$(e).removeClass("is-invalid");
			$(e).addClass("is-valid");
		},
		errorPlacement: function(error, element){
			$(element).next().append(error).show();
		},
	});
	
	$("#form-edit").validate({
		rules: {
			"nombres-edit": {
				required: true,
				lettersonly: true,
				minlength: 3,
				maxlength: 30,
			},
			"apePaterno-edit": {
				required: true,
				lettersonly: true,
				minlength: 3,
				maxlength: 15,
			},
			"apeMaterno-edit": {
				required: true,
				lettersonly: true,
				minlength: 3,
				maxlength: 15,
			},
			"celular-edit": {
				required: true,
				digits: true,
				celularValido: true,
			},
			"dni-edit": {
				required: true,
				digits: true,
				minlength: 8,
				maxlength: 8,
			},
			"clave-edit": {
				required: true,
				digits: true,
				minlength: 8,
				maxlength: 8,	
			},		
		},
		messages: {
			"nombres-edit": {
				required: "Este campo es obligatorio",
				minlength: "Ingresar como mínimo 3 caracteres",
				maxlength: "Ingresar como máximo 30 caracteres",
			},
			"apePaterno-edit": {
				required: "Este campo es obligatorio",
				minlength: "Ingresar como mínimo 3 caracteres",
				maxlength: "Ingresar como máximo 15 caracteres",
			},
			"apeMaterno-edit": {
				required: "Este campo es obligatorio",
				minlength: "Ingresar como mínimo 3 caracteres",
				maxlength: "Ingresar como máximo 15 caracteres",
			},
			"celular-edit": {
				required: "Este campo es obligatorio",
				digits: "Este campo solo acepta dígitos",
			},
			"dni-edit": {
				required: "Este campo es obligatorio",
				digits: "Este campo solo acepta dígitos",
				minlength: "El DNI debe ser de 8 dígitos",
				maxlength: "El DNI debe ser de 8 dígitos",
			},	
			"clave-edit": {
				required: "Este campo es obligatorio",
				digits: "Este campo solo acepta dígitos",
				minlength: "La clave debe ser de 8 dígitos",
				maxlength: "La clave debe ser de 8 dígitos",
			},				
		},
		highlight: function(e){
			$(e).removeClass("is-valid");
			$(e).addClass("is-invalid");
		},
		unhighlight: function(e){
			$(e).removeClass("is-invalid");
			$(e).addClass("is-valid");
		},
		errorPlacement: function(error, element){
			$(element).next().append(error).show();
		}
	});
});