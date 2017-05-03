package util;

public class Properties {
	// 响应内容格式与编码
	public static final String RES_CONTENT_TYPE = "text/html;charset=utf-8;";
	// 允许客户端发送cookie，为保持跨域间session的联系
	public static final String RES_ALLOW_CREDENTIALS = "true";
	// 指定允许跨域的域
	public static final String RES_ALLOW_ORIGIN = "http://127.0.0.1:8080";// 改地址为前端项目部署地址
	// 允许跨域的方式
	public static final String RES_ALLOW_METHODS = "get, post, delete, put, options";
	
	
	// 日期格式
	public static final String LONG_DATE = "yyyy-MM-dd";
	public static final String FULL_DATE = "yyyy-MM-dd HH:mm:ss";
	public static final String MEDIUM_TIME = "HH:mm:ss";
	public static final String SHORT_TIME = "HH:mm";
}
