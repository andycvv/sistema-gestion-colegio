<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <!-- Bootstrap Icons -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <!-- Bootstrap -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <!-- Style -->
  <link rel="stylesheet" href="./styles/index.css">
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
        <form action="UsuarioServlet" action="POST" id="formLogin">
          <div class="item-form">
            <label class="label-elemento" for="dni-log">DNI</label>
            <input type="text" name="dni-log" id="dni-log">
            <div class="invalid-feedback"></div>
          </div>
          <div class="item-form">
            <label class="label-elemento" for="clave-log">Clave</label>
            <input type="password" name="clave-log" id="clave-log">
            <div class="invalid-feedback"></div>
            <p>No tienes cuenta? <a href="./pages/register.jsp">Regístrate</a></p>
          </div>
          <button class="btn-ingresar" name="tipo" value="login">Ingresar</button>
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
  <!-- JQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <!-- JQuery Validate -->
  <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
  <!-- Validation -->
  <script src="./js/valLogin.js"></script>
</body>
</html>