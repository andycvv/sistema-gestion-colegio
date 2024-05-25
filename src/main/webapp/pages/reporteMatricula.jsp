<%@page import="java.util.ArrayList"%>
<%@page import="entidad.MatriculaReporte"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Usuario"%>
<%@page import="modelo.UsuarioModel"%>
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
  <link rel="stylesheet" href="../styles/reporteMatricula.css">
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
        <h1>Reporte de matrículas</h1>
        <form action="../ReporteMatriculaServlet" method="POST">
	        <div class="botones-inciales">
	          <div class="cbos-contenedor d-flex gap-3">
		          <div class="input-buscar input-group mb-3">
					<select class="form-select cbo-opcion" id="anio" name="anio">
						<option value="2024">2024</option>
						<option value="2023">2023</option>
						<option value="2022">2022</option>
						<option value="2021">2021</option>
						<option value="2020">2020</option>
					</select>
				  </div>
				  <div class="input-buscar input-group mb-3">
					<select class="form-select cbo-opcion" id="nivel" name="nivel">
						<option value="1">Primaria</option>
						<option value="2">Secundaria</option>
					</select>
				  </div>
	          </div>
	          <div class="botones-contenedor d-flex gap-3">
		          <button class="boton-nuevo boton btn btn-warning" name="tipo" value="report">
		            <i class="bi bi-binoculars"></i>
		            <span>Reportar</span>
		          </button>
		          <button class="boton-nuevo boton btn btn-info" name="tipo" value="print">
		            <i class="bi bi-printer"></i>
		            <span>Imprimir</span>
		          </button>
	          </div>
	        </div>
        </form>
        <div class="tabla">
          <table class="table table-bordered table-hover">
            <thead>
              <tr>
              	<th scope="col">ID</th>
                <th scope="col">Estudiante</th>
                <th scope="col">DNI</th>
                <th scope="col">Fecha</th>
                <th scope="col">Nivel</th>
                <th scope="col">Grado</th>
              </tr>
            </thead>
            <tbody class="table-group-divider"> 
            <%
            List<MatriculaReporte> lista = (ArrayList<MatriculaReporte>)request.getSession().getAttribute("reporteMatricula");
			if(lista != null){
				for(MatriculaReporte mr : lista){
				%>
              		<tr>
	                  <td><%= mr.getIdMatricula() %></td>
	                  <td><%= mr.getEstudiante() %></td>
	                  <td><%= mr.getDni() %></td>
	                  <td><%= mr.getFecha() %></td>
	                  <td><%= mr.getNivel() %></td>
	                  <td><%= mr.getGrado() %></td>
                    </tr> 
				<%}%>
			<%}%>
            </tbody>
          </table>
        </div>
      </div>
    </main>
  </div>

  <!-- JQUERY -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
  <script src="../js/sidebar.js"></script>
  <script src="../js/reporteMatricula.js"></script>
</body>
</html>