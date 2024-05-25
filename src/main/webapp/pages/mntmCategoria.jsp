<%@page import="entidad.Categoria"%>
<%@page import="modelo.CategoriaModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Principal</title>
  <!-- Estilos -->
  <link rel="stylesheet" href="../styles/sidebar.css">
  <link rel="stylesheet" href="../styles/mantenimiento.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/2.0.7/css/dataTables.bootstrap5.css">
  <!-- Bootstrap Icons -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <!-- Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <!-- SweetAlert -->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
  <div class="contenedor">
    <div class="sidebar">
      <div class="menu-btn">
        <i class="bi bi-chevron-left"></i>
      </div>
      <div class="head">
        <div class="user-img">
          <img src="../imgs/logo.png" alt="">
        </div>
        <div class="user-details">
          <p class="title">Usuario</p>
          <p class="name">${usuario.getNombres()}</p>
        </div>
      </div>
      <nav class="nav">
        <div class="menu">
          <p class="title">Principal</p>
          <ul>
            <li>
              <a href="./principal.jsp">
                <i class="icon bi bi-house"></i>
                <span class="text">Panel</span>
              </a>
            </li>
            <li>
              <a href="#">
                <i class="icon bi bi-gear"></i>
                <span class="text">Mantenimiento</span>
                <i class="arrow bi bi-chevron-down"></i>
              </a>
              <ul class="sub-menu">
                <li>
                  <a href="./mntmUsuario.jsp">
                    <span class="text">Usuario</span>
                  </a>
                </li>
                <li>
                  <a href="./mntmCategoria.jsp">
                    <span class="text">Categoría</span>
                  </a>
                </li>
                <li>
                  <a href="./mntmCurso.jsp">
                    <span class="text">Curso</span>
                  </a>
                </li>
              </ul>
            </li>
            <li>
              <a href="#">
                <i class="icon bi bi-question-circle"></i>
                <span class="text">Consulta</span>
                <i class="arrow bi bi-chevron-down"></i>
              </a>
              <ul class="sub-menu">
                <li>
                  <a href="./consultarEstudiante.jsp">
                    <span class="text">Estudiante</span>
                  </a>
                </li>
                <li>
                  <a href="./consultarCurso.jsp">
                    <span class="text">Curso</span>
                  </a>
                </li>
              </ul>
            </li>
            <li>
              <a href="#">
                <i class="icon bi bi-file-earmark-bar-graph"></i>
                <span class="text">Reporte</span>
                <i class="arrow bi bi-chevron-down"></i>
              </a>
              <ul class="sub-menu">
                <li>
                  <a href="./reporteMatricula.jsp">
                    <span class="text">Matrícula</span>
                  </a>
                </li>
              </ul>
            </li>
            <li>
              <a href="#">
                <i class="icon bi bi-person-vcard"></i>
                <span class="text">Transacciones</span>
                <i class="arrow bi bi-chevron-down"></i>
              </a>
              <ul class="sub-menu">
                <li>
                  <a href="./registrarMatricula.jsp">
                    <span class="text">Registrar Matrícula</span>
                  </a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </nav>
      <div class="menu">
        <p class="title">Cuenta</p>
        <ul>
          <li>
            <a href="../UsuarioServlet?tipo=salir">
              <i class="icon bi bi-box-arrow-right"></i>
              <span class="text">Cerrar Sesión</span>
            </a>
          </li>
        </ul>
      </div>
    </div>

    <main>
      <div id="form-editar" class="form-fondo">
        <div class="form-div">
          <form action="../CategoriaServlet" id="form-edit">
            <div class="title-option">
              <h2 class="text-center mb-4">Editar Categoría</h2>
              <div class="icon-close">
                <i class="bi bi-x-circle"></i>
              </div>
            </div>
            <div class="mb-3">
              <label for="id-edit" class="form-label">ID Categoría</label>
              <input type="text" class="form-control" id="dni-edit" name="id-edit" readonly>
            </div>
            <div class="mb-3">
              <label for="nombre-edit" class="form-label">Nombre de categoría</label>
              <input type="text" class="form-control" id="nombre-edit" name="nombre-edit">
              <div class="invalid-feedback"></div>
            </div>
            <button type="submit" class="btn btn-primary" name="tipo" value="edit">Editar</button>
          </form>
        </div>
      </div>
      <div id="form-registrar"class="form-fondo">
        <div class="form-div">
          <form action="../CategoriaServlet" id="form-reg">
            <div class="title-option">
              <h2 class="text-center mb-4">Registrar Categoría</h2>
              <div class="icon-close">
                <i class="bi bi-x-circle"></i>
              </div>
            </div>
            <div class="mb-3">
              <label for="nombre-reg" class="form-label">Nombre de categoría</label>
              <input type="text" class="form-control" id="nombre-reg" name="nombre-reg">
              <div class="invalid-feedback"></div>
            </div>
            <button type="submit" class="btn btn-primary" name="tipo" value="register">Registrar</button>
          </form>
        </div>
      </div>
      <div class="main-contenido">
        <div class="container-fluid">
        	<div class="row">
		        <h1 class="col-12">Detalles de categoría</h1>
		        <div class="botones-inciales">
		          <button class="boton-nuevo boton btn btn-success">
		            <i class="icono bi bi-plus-square"></i>
		            <span>Nuevo</span>
		          </button>
		        </div>
		        <div class="tabla col-12">
		          <table class="table table-bordered table-hover w-100" id="tabla-categoria">
		            <thead>
		              <tr>
		                <th scope="col">ID Categoría</th>
		                <th scope="col">Nombre</th>
		                <th scope="col" class="text-center">Acciones</th>
		              </tr>
		            </thead>
		            <tbody class="table-group-divider">
		            <%
		            CategoriaModel model = new CategoriaModel();
		            ArrayList<Categoria> lista = model.listado();
		            for(Categoria c : lista){
		            %>
		               <tr>
		                <td><%= c.getIdCategoria() %></td>
		                <td><%= c.getNombre() %></td>
		                <td class="td-boton">
		                  <a class="boton-editar btn btn-primary" href="#">
		                    <i class="icono bi bi-plus-square"></i>
		                    <span>Editar</span>
		                  </a>
		                  
		                  <a class="btn btn-danger" href="../CategoriaServlet?tipo=delete&idCategoria=<%= c.getIdCategoria() %>">
		                    <i class="icono bi bi-trash"></i>
		                    <span>Eliminar</span>
		                  </a>
		                </td>
		              </tr>
		            <%}%>
		
		            </tbody>
		          </table>
		        </div>
        	</div>
        </div>
      </div>
    </main>
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
  <script src="../js/sidebar.js"></script>
  <script src="../js/mantenimiento.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
  <script src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>
  <script src="https://cdn.datatables.net/2.0.7/js/dataTables.bootstrap5.js"></script>
  <script src="../js/valMntmCategoria.js"></script>
  <script>
  	new DataTable('#tabla-categoria');
  </script>
</body>

</html>