<%@page import="modelo.CursoModel"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="entidad.CursoInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Consultar Curso</title>
  <!-- Estilos -->
  <link rel="stylesheet" href="../styles/sidebar.css">
  <link rel="stylesheet" href="../styles/consulta.css">
  <!-- Bootstrap Icons -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <!-- Bootstrap -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
      <div class="main-contenido">
        <h1>Consulta de Cursos</h1>
        <form action="../ConsultaCursoServlet" method="POST">
	        <div class="botones-inciales">
	          <div class="input-buscar input-group mb-3">
				<select class="form-select" id="tipoConsulta" name="tipoConsulta">
					<option value="1">Nombre</option>
					<option value="2">Profesor</option>
					<option value="3">Aula</option>
				</select>
			  </div>
	          <div class="input-buscar input-group mb-3">
	            <input type="text" class="form-control" placeholder="Buscar" aria-describedby="basic-addon1" name="inputCon">
	          </div>
	          <button class="boton-nuevo boton btn btn-warning" name="tipo" value="read">
	            <i class="bi bi-search"></i>
	            <span>Buscar</span>
	          </button>
	          <button class="boton-nuevo boton btn btn-info" name="tipo" value="clear">
	            <i class="bi bi-arrow-clockwise"></i>
	            <span>Reiniciar tabla</span>
	          </button>
	        </div>
        </form>
        <div class="tabla">
          <table class="table table-bordered table-hover">
            <thead>
              <tr>
              	<th scope="col">ID</th>
                <th scope="col">Nombre</th>
                <th scope="col">Profesor</th>
                <th scope="col">Horario</th>
                <th scope="col">Aula</th>
                <th scope="col">Precio</th>
              </tr>
            </thead>
            <tbody class="table-group-divider">
            
           <%
           ArrayList<CursoInfo> lista = (ArrayList<CursoInfo>)request.getSession().getAttribute("lista");
           if(lista != null){
        	   for(CursoInfo ci: lista){
           %>
	               <tr>
	               <td><%= ci.getIdCurso() %></td>
	               <td><%= ci.getNombre() %></td>
	               <td><%= ci.getProfesor() %></td>
	               <td><%= ci.getHorario() %></td>
	               <td><%= ci.getCodAula() %></td>
	               <td><%= ci.getPrecio() %></td>
	               </tr>
              <%}%>
         <%} else{
        	 CursoModel model = new CursoModel();
        	 for(CursoInfo ci: model.listadoCursoInfo()){ 
        	 %>	 
        	 	 <tr>
        	 	 <td><%= ci.getIdCurso() %></td>
        	 	 <td><%= ci.getNombre() %></td>
        	 	 <td><%= ci.getProfesor() %></td>
        	 	 <td><%= ci.getHorario() %></td>
        	 	 <td><%= ci.getCodAula() %></td>
        	 	 <td><%= ci.getPrecio() %></td>
        	 	 </tr>
        	 <%}%>
         <%}%>
            </tbody>
          </table>
        </div>
      </div>
    </main>
  </div>
<% request.getSession().setAttribute("lista", null); %>
  <!-- JQUERY -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
  <script src="../js/sidebar.js"></script>
</body>
</html>