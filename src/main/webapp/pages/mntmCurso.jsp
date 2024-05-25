<%@page import="modelo.NivelModel"%>
<%@page import="entidad.Nivel"%>
<%@page import="entidad.Horario"%>
<%@page import="modelo.HorarioModel"%>
<%@page import="java.util.List"%>
<%@page import="modelo.UsuarioModel"%>
<%@page import="entidad.Usuario"%>
<%@page import="modelo.CursoModel"%>
<%@page import="entidad.Curso"%>
<%@page import="modelo.CategoriaModel"%>
<%@page import="entidad.Categoria"%>
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
          <form action="../CursoServlet" id="form-edit">
            <div class="title-option">
              <h2 class="text-center mb-4">Editar Curso</h2>
              <div class="icon-close">
                <i class="bi bi-x-circle"></i>
              </div>
            </div>
            <div class="row mb-3">
           		<div class="col-md-6">
	              <label for="idCurso-edit" class="form-label">ID Curso</label>
	              <input type="text" class="form-control" id="idCurso-edit" name="idCurso-edit" readonly>
	            </div>
	            <div class="col-md-6">
	              <label for="nombre-edit" class="form-label">Nombre</label>
	              <input type="text" class="form-control" id="nombre-edit" name="nombre-edit">
	              <div class="invalid-feedback"></div>
	            </div>
            </div>
			<div class="row mb-3">
	            <div class="col-md-6">
	              <label for="precio-edit" class="form-label">Precio</label>
	              <input type="text" class="form-control" id="precio-edit" name="precio-edit">
	              <div class="invalid-feedback"></div>
	            </div>
	            <div class="col-md-6">
	              <label for="idProfesor-edit" class="form-label">Profesor</label>
	              <select class="form-select" aria-label="Default select example" name="idProfesor-edit">
	              <%
	              UsuarioModel usuarioModel = new UsuarioModel();
	              List<Usuario> listaProfesor = usuarioModel.listadoProfesor();
	              for(Usuario u : listaProfesor){
	              %>	  
	            	 <option value="<%= u.getId() %>"><%= u.getId() %> (<%= u.getNombres() %>)</option>
	              <%}%>
				  </select>
	            </div>
            </div>
			<div class="row mb-3">
	            <div class="col-md-6">
	              <label for="idHorario-edit" class="form-label">Horario</label>
	              <select class="form-select" aria-label="Default select example" name="idHorario-edit">
	              <%
	              HorarioModel horarioModel = new HorarioModel();
	              List<Horario> listaHorario = horarioModel.listadoHorarioAsignado();
	              for(Horario h : listaHorario){
	              %>	  
	            	 <option value="<%= h.getIdHorario() %>"><%= h.getHoraInicio() %> - <%= h.getHoraFin() %> / <%= h.getCodAula() %></option>
	              <%}%>
				  </select>
	            </div>
	            <div class="col-md-6">
	              <label for="idCategoria-edit" class="form-label">Categoría</label>
	              <select class="form-select" aria-label="Default select example" name="idCategoria-edit">
	              <%
	              CategoriaModel categoriaModel = new CategoriaModel();
	              List<Categoria> listaCategoria = categoriaModel.listado();
	              for(Categoria c : listaCategoria){
	              %>	  
	            	 <option value="<%= c.getIdCategoria() %>"><%= c.getIdCategoria() %> (<%= c.getNombre() %>)</option>
	              <%}%>
				  </select>
	            </div>
            </div>
            <div class="row mb-3">
	            <div class="col-md-6">
	              <label for="idNivel-edit" class="form-label">Nivel</label>
	              <select class="form-select" aria-label="Default select example" name="idNivel-edit">
	              <%
	              NivelModel nivelModel = new NivelModel();
	              List<Nivel> listaNivel = nivelModel.listado();
	              for(Nivel n : listaNivel){
	              %>	  
	            	 <option value="<%= n.getIdNivel() %>"><%= n.getNombre() %></option>
	              <%}%>
				  </select>
	            </div>
            </div>
            <button type="submit" class="btn btn-primary" name="tipo" value="edit">Editar</button>
          </form>
        </div>
      </div>
      <div id="form-registrar"class="form-fondo">
        <div class="form-div">
          <form action="../CursoServlet" id="form-reg">
            <div class="title-option">
              <h2 class="text-center mb-4">Registrar Curso</h2>
              <div class="icon-close">
                <i class="bi bi-x-circle"></i>
              </div>
            </div>
            <div class="row mb-3">
	            <div class="col-md-6">
	              <label for="nombre-reg" class="form-label">Nombre</label>
	              <input type="text" class="form-control" id="nombre-reg" name="nombre-reg">
	              <div class="invalid-feedback"></div>
	            </div>
	            <div class="col-md-6">
	              <label for="idProfesor-reg" class="form-label">Profesor</label>
	              <select class="form-select" aria-label="Default select example" name="idProfesor-reg">
	              <%
	              for(Usuario u : listaProfesor){
	              %>	  
	            	 <option value="<%= u.getId() %>"><%= u.getNombres() %> <%= u.getApePaterno()%> <%= u.getApeMaterno() %></option>
	              <%}%>
				  </select>
	            </div>
            </div>
			<div class="row mb-3">
	            <div class="col-md-6">
	              <label for="idHorario-reg" class="form-label">Horario</label>
	              <select class="form-select" aria-label="Default select example" name="idHorario-reg">
	              <%
	              for(Horario h : listaHorario){
	              %>	  
	            	 <option value="<%= h.getIdHorario() %>"><%= h.getHoraInicio() %> - <%= h.getHoraFin() %> / <%= h.getCodAula() %></option>
	              <%}%>
				  </select>
	            </div>
	            <div class="col-md-6">
	              <label for="idCategoria-reg" class="form-label">Categoría</label>
	              <select class="form-select" aria-label="Default select example" name="idCategoria-reg">
	              <%
	              for(Categoria c : listaCategoria){
	              %>	  
	            	 <option value="<%= c.getIdCategoria() %>"><%= c.getIdCategoria() %> (<%= c.getNombre() %>)</option>
	              <%}%>
				  </select>
	            </div>
            </div>
			<div class="row mb-3">
	            <div class="col-md-6">
	              <label for="idNivel-reg" class="form-label">Nivel</label>
	              <select class="form-select" aria-label="Default select example" name="idNivel-reg">
	              <%
	              for(Nivel n : listaNivel){
	              %>	  
	            	 <option value="<%= n.getIdNivel() %>"><%= n.getNombre() %></option>
	              <%}%>
				  </select>
	            </div>
	            <div class="col-md-6">
	              <label for="precio-reg" class="form-label">Precio</label>
	              <input type="text" class="form-control" id="precio-reg" name="precio-reg">
	              <div class="invalid-feedback"></div>
	            </div>
            </div>
            <button type="submit" class="btn btn-primary" name="tipo" value="register">Registrar</button>
          </form>
        </div>
      </div>
      <div class="main-contenido">
		<div class="container-fluid">
			<div class="row">
			    <h1 class="col-12">Detalles de curso</h1>
		        <div class="botones-inciales">
		          <button class="boton-nuevo boton btn btn-success">
		            <i class="icono bi bi-plus-square"></i>
		            <span>Nuevo</span>
		          </button>
		        </div>
		        <div class="tabla col-12">
		          <table class="table table-bordered table-hover w-100" id="tabla-curso">
		            <thead>
		              <tr>
		                <th scope="col">ID Curso</th>
		                <th scope="col">Nombre</th>
		                <th scope="col">Precio</th>
		                <th scope="col">ID Profesor</th>
		                <th scope="col">ID Horario</th>
		                <th scope="col">ID Categoría</th>
		                <th scope="col">ID Nivel</th>
		                <th scope="col" class="text-center">Acciones</th>
		              </tr>
		            </thead>
		            <tbody class="table-group-divider">
		            <%
		            CursoModel model = new CursoModel();
		            ArrayList<Curso> lista = model.listado();
		            for(Curso c : lista){
		            %>
		               <tr>
		                <td><%= c.getIdCurso() %></td>
		                <td><%= c.getNombre() %></td>
		                <td><%= c.getPrecio() %></td>
		                <td><%= c.getIdProfesor() %></td>
		                <td><%= c.getIdHorario() %></td>
		                <td><%= c.getIdCategoria() %></td>
		                <td><%= c.getIdNivel() %></td>
		                <td class="td-boton">
		                  <a class="boton-editar btn btn-primary" href="#">
		                    <i class="icono bi bi-plus-square"></i>
		                    <span>Editar</span>
		                  </a>
		                  
		                  <a class="btn btn-danger" href="../CursoServlet?tipo=delete&idCurso=<%= c.getIdCurso() %>">
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
  <script src="../js/mantenimiento.js"></script>  <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
  <script src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>
  <script src="https://cdn.datatables.net/2.0.7/js/dataTables.bootstrap5.js"></script>
  <script src="../js/valMntmCurso.js"></script>
  <script>
  	new DataTable('#tabla-curso');
  </script>
</body>

</html>