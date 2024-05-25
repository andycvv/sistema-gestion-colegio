$(function(){
	$.validator.addMethod("lettersonly", function(value, element) {
        return this.optional(element) || /^[a-zA-ZáéíóúüÁÉÍÓÚÜ\s]+$/.test(value);
    }, "Este campo solo acepta letras");
    
	$("#form-reg").validate({
		rules: {
			"nombre-reg": {
				required: true,
				lettersonly: true,
				minlength: 3,
				maxlength: 30,
			},
			"precio-reg": {
				required:true,
                number: true, 
                min: 0.01, 
			},
		},
		messages: {
			"nombre-reg": {
				required: "Este campo es obligatorio",
				minlength: "Ingresar como mínimo 3 caracteres",
				maxlength: "Ingresar como máximo 30 caracteres",
			},
			"precio-reg": {
                required: "Este campo es obligatorio",
                number: "Por favor ingrese un número válido",
                min: "El precio debe ser mayor que 0",
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
			"nombre-edit": {
				required: true,
				lettersonly: true,
				minlength: 3,
				maxlength: 30,
			},
			"precio-edit": {
				required:true,
                number: true, 
                min: 0.01, 
			},
		},
		messages: {
			"nombre-edit": {
				required: "Este campo es obligatorio",
				minlength: "Ingresar como mínimo 3 caracteres",
				maxlength: "Ingresar como máximo 30 caracteres",
			},
			"precio-edit": {
                required: "Este campo es obligatorio",
                number: "Por favor ingrese un número válido",
                min: "El precio debe ser mayor que 0",
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
});