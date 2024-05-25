package utils;

public class Alertas {
	public static String success(String text) {
	    return "<script>"
	    		+ "Swal.fire({\r\n"
	    		+ "	title: \"Good job!\",\r\n"
	    		+ "	text: \""+ text +"\",\r\n"
	    		+ "	icon: \"success\"\r\n"
	    		+ "});"
	    		+ "</script>";
	}
	
	public static String successSession(String text) {
	    return "<script>"
	    		+ "const Toast = Swal.mixin({\r\n"
	    		+ "  toast: true,\r\n"
	    		+ "  position: \"top-end\",\r\n"
	    		+ "  showConfirmButton: false,\r\n"
	    		+ "  timer: 3000,\r\n"
	    		+ "  timerProgressBar: true,\r\n"
	    		+ "  didOpen: (toast) => {\r\n"
	    		+ "    toast.onmouseenter = Swal.stopTimer;\r\n"
	    		+ "    toast.onmouseleave = Swal.resumeTimer;\r\n"
	    		+ "  }\r\n"
	    		+ "});\r\n"
	    		+ "Toast.fire({\r\n"
	    		+ "  icon: \"success\",\r\n"
	    		+ "  title: \""+text+"\"\r\n"
	    		+ "});"
	    		+ "</script>";
	}
	
	public static String question(String title, String text) {
	    return "<script>"
	    		+ "Swal.fire({\r\n"
	    		+ "  title: \""+title+"\",\r\n"
	    		+ "  text: \""+text+"\",\r\n"
	    		+ "  icon: \"question\"\r\n"
	    		+ "});"
	    		+ "</script>";
	}
	
	public static String error(String title, String text) {
	    return "<script>"
	            + "Swal.fire({\r\n"
	            + "  icon: \"error\",\r\n"
	            + "  title: \"" + title +"\",\r\n"
	            + "  text: \"" + text + "\",\r\n"
	            + "});"
	            + "</script>";
	}
	
	public static String animated(String text) {
	    return "<script>"
	    		+ "Swal.fire({\r\n"
	    		+ "  title: \""+text+"\",\r\n"
	    		+ "  width: 600,\r\n"
	    		+ "  padding: \"3em\",\r\n"
	    		+ "  color: \"#716add\",\r\n"
	    		+ "  background: \"#fff url(/images/trees.png)\",\r\n"
	    		+ "  backdrop: `\r\n"
	    		+ "    rgba(0,0,123,0.4)\r\n"
	    		+ "    url(\"../imgs/nyan-cat-nyan.gif\")\r\n"
	    		+ "    left top\r\n"
	    		+ "    no-repeat\r\n"
	    		+ "  `\r\n"
	    		+ "});"
	    		+ "</script>";
	}
}
