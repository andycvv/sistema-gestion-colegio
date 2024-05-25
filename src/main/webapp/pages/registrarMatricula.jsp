<%@page import="entidad.CursoInfo"%>
<%@page import="entidad.Curso"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.CursoModel"%>
<%@page import="entidad.Grado"%>
<%@page import="modelo.GradoModel"%>
<%@page import="modelo.NivelModel"%>
<%@page import="entidad.Nivel"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="entidad.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="modelo.UsuarioModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Registrar Matrícula</title>
  <!-- Estilos -->
  <link rel="stylesheet" href="../styles/sidebar.css">
  <link rel="stylesheet" href="../styles/registrarMatricula.css">
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
  		<div class="main-contenido">
          <form action="../MatriculaServlet">
            <div class="title-option">
              <h2 class="text-center mb-4">Registrar Matrícula</h2>
            </div>
			  <div class="row mb-3">
			    <div class="col-md-3">
			      <label for="idEstudiante-mat" class="form-label">Estudiante</label>
			      <select class="form-select" aria-label="Default select example" name="idEstudiante-mat">
		              <%
					  ArrayList<Curso> carro = (ArrayList<Curso>)request.getSession().getAttribute("carro");
		              UsuarioModel usuarioModel = new UsuarioModel();
		              List<Usuario> listaEstudiante = usuarioModel.listadoEstudiante();
		              for(Usuario u : listaEstudiante){
		            	  if(usuarioModel.buscarEstudianteMatriculadoAnioActual(u.getId()) == null){
				          %>
		            	 	<option value="<%= u.getId() %>" <%= (carro.size()>=1) ? "selected": "" %>><%= u.getNombres() %> (<%= u.getDni() %>)</option>
		            	  <%}%>	  
		              <%}%>
				  </select>
			    </div>
			    <div class="col-md-2">
			      <label for="fecha-mat" class="form-label">Fecha</label>
			      <% 
			      	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			      	Date fecha = new Date(); 
			      %>
			      <input type="text" class="form-control" id="fecha-mat" name="fecha-mat" value="<%= sdf.format(fecha) %>" readonly>
			    </div>
					<div class="col-md-2">
					    <label for="idNivel-mat" class="form-label">Nivel</label>
					    <% 
					    if(carro.size() >= 1) {
					        int lastSelectedValue = carro.get(carro.size() - 1).getIdNivel();
					    %>
					        <select class="form-select" id="idNivel-mat" name="idNivel-mat" disabled>
					            <option value="1" <%= (lastSelectedValue==1) ? "selected" : "" %>>Primaria</option>
					            <option value="2" <%= (lastSelectedValue==2) ? "selected" : "" %>>Secundaria</option>
					        </select>
					        <input type="hidden" name="idNivel-mat" value="<%= lastSelectedValue %>">
					    <% } else { %>
					        <select class="form-select" id="idNivel-mat" name="idNivel-mat">
					            <option value="1">Primaria</option>
					            <option value="2">Secundaria</option>
					        </select>
					    <% } %>
					</div>
			    <div class="col-md-2">
			      <label for="idGrado-mat" class="form-label">Grado</label>
			      <select class="form-select" id="idGrado-mat" name="idGrado-mat">
				  </select>
			    </div>
			    <div class="col-md-3">
			      <button type="submit" class="btn btn-success w-100 mb-2" name="tipo" value="register">Registrar Matrícula</button>
                  <button type="submit" class="btn btn-success w-100" name="tipo" value="mostrarCursos">Cursos Disponibles</button>
			    </div>
			  </div>
          </form>
          <div class="container-fluid">
			<div class="row">
            	<h3 class="col-12">Cursos Seleccionados</h3>
             	<div class="tabla col-12">
		          <table class="table table-bordered table-hover">
		            <thead>
		              <tr>
		                <th scope="col">ID Curso</th>
		                <th scope="col">Nombre</th>
		                <th scope="col">Profesor</th>
		                <th scope="col">Horario</th>
		                <th scope="col">Categoría</th>
		                <th scope="col">Nivel</th>
		                <th scope="col">Precio</th>
		                <th scope="col" colspan="2" class="text-center">Acciones</th>
		              </tr>
		            </thead>
		            <tbody class="table-group-divider">
						<%
			    		for(Curso c : carro){
			    		%>
			               <tr>
			                <td><%= c.getIdCurso() %></td>
			                <td><%= c.getNombre() %></td>
			                <td><%= c.getIdProfesor() %></td>
			                <td><%= c.getIdHorario() %></td>
			                <td><%= c.getIdCategoria() %></td>
			                <td><%= c.getIdNivel() %></td>
			                <td><%= c.getPrecio() %></td>
			                <td class="td-boton text-center">
			                  <a class="btn btn-danger" href="../MatriculaServlet?tipo=removeCurso&idCurso=<%= c.getIdCurso() %>">
			                    <i class="icono bi bi-trash"></i>
			                    <span>Eliminar</span>
			                  </a>
			                </td>
			              </tr>
			    		<%}%>
		            </tbody>
		          </table>
              </div>
              <div class="row mt-3">
	              <p class="col-md-6 texto-info texto-cantidad">Cantidad de cursos: ${sessionScope.cantCursos}</p>
	              <p class="col-md-6 texto-info texto-subtotal">Subtotal: S/.${sessionScope.subtotalVenta}</p>
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
  <script src="../js/sidebar.js"></script>
  <script src="../js/regMatricula.js"></script>
</body>
</html>