package com.korea.dbapp.util;

public class Script {

	public static String href(String uri, String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("location.href = '"+uri+"';");
		sb.append("</script>");

		return sb.toString();
	}
	
	synchronized public static String href(String uri) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("location.href = '"+uri+"';");
		sb.append("</script>");

		return sb.toString();
	}
	
	public static String back(String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");

		return sb.toString();
	}
}
