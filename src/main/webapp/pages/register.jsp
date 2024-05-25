<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register</title>
  <!-- BootstrapIcons -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <!-- Style -->
  <link rel="stylesheet" href="../styles/index.css">
  <!-- SweetAlert -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
  <div class="contenedor">
    <div class="login-contenedor">
      <div class="div-left">
        <div class="perfil-info">
          <i class="bi bi-person-circle"></i>
          <h1>Bienvenido a Santa Cruz!</h1>
          <p>Ingrese sus datos</p>
        </div>
        <form action="../UsuarioServlet" action="POST" id="formRegister">
          <div class="item-form">
            <label class="label-elemento" for="dni">DNI</label>
            <input type="text" name="dni-cred" id="dni-cred">
            <div class="invalid-feedback"></div>
          </div>
          <div class="item-form">
            <label class="label-elemento" for="clave">Clave</label>
            <input type="text" name="clave-cred" id="clave-cred">
            <div class="invalid-feedback"></div>
          </div>
          <div class="item-form">
            <select class="form-select" name="idRol-cred">
              <option value="2">Profesor</option>
              <option value="3" selected>Estudiante</option>
            </select>
            <p>Ya tienes cuenta? <a href="../index.jsp">Ingresar</a></p>
          </div>
          <button class="btn-ingresar" name="tipo" value="update-cred">Registrar</button>
        </form>
      </div>
      <div class="div-right"></div>
    </div>
    <%if((String)request.getSession().getAttribute("alerta") != null){
    %>
    ${sessionScope.alerta}
    <%}
    request.getSession().setAttribute("alerta", null);
    %>
  </div>
  <!-- JQUERY -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <!-- JQuery Validate -->
  <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
  <!-- Validation -->
  <script src="../js/valRegister.js"></script>
</body>
</html>