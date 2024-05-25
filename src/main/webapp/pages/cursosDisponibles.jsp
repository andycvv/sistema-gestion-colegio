<%@page import="entidad.Curso"%>
<%@page import="entidad.CursoInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.CursoModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
    <!-- Estilos -->
  <link rel="stylesheet" href="../styles/sidebar.css">
  <link rel="stylesheet" href="../styles/mantenimiento.css">
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
		<%
		int idNivel = Integer.parseInt((String)request.getSession().getAttribute("nivel"));
		%>
		<div class="container-fluid p-4">
			<div class="title-option d-flex align-items-center gap-4">
				<a class="btn-regresar" href="./registrarMatricula.jsp"><i class="bi bi-arrow-left-circle h3"></i></a>
				<h3 class="col-12 m-0">Cursos disponibles</h3>
			</div>
			<div class="tabla col-12">
				<form action="../AgregarServlet" method="POST">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th scope="col">ID Curso</th>
								<th scope="col">Nombre</th>
								<th scope="col">Profesor</th>
								<th scope="col">Horario</th>
								<th scope="col">Aula</th>
								<th scope="col">Precio</th>
								<th scope="col" class="text-center">Acciones</th>
							</tr>
						</thead>
						<tbody class="table-group-divider">
							<%
							CursoModel model = new CursoModel();
							ArrayList<CursoInfo> lista = model.listadoCursoInfoPorNivel(idNivel);
							ArrayList<Curso> carro = (ArrayList<Curso>)request.getSession().getAttribute("carro");
							
							for (CursoInfo ci : lista) {
								boolean isRepeat = false;
								for(Curso c : carro){
									if(ci.getIdCurso() == c.getIdCurso()){
										isRepeat = true;
									}
								}
								if(!isRepeat){
									%>
									<tr>
										<td><%=ci.getIdCurso()%></td>
										<td><%=ci.getNombre()%></td>
										<td><%=ci.getProfesor()%></td>
										<td><%=ci.getHorario()%></td>
										<td><%=ci.getCodAula()%></td>
										<td><%=ci.getPrecio()%></td>
										<td class="td-boton text-center">
										  <button class="btn btn-primary" name="idCurso" value="<%= ci.getIdCurso() %>">
										    <i class="icono bi bi-plus-square"></i>
										  </button>
										</td>
									</tr>
								<% }%>
							<%}%>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</main>
  </div>
</body>
</html>