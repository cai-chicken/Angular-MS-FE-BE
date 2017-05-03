package util;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	public void initResponseInfo(HttpServletResponse response) {
		// 当前端指定请求凭证 => withCredentials: true;时，表示请求凭证的"include"模式
		// 该模式下，不允许使用通配符表示允许的域，需要指定具体的域，如http://localhost:81
		// 允许远程所有地址跨域
		// response.setHeader("Access-Control-Allow-Origin", "*");
		
		
		// 设置响应文本格式和字符编码
		response.setContentType(Properties.RES_CONTENT_TYPE);
		// 允许客户端发送cookie
		response.setHeader("Access-Control-Allow-Credentials", Properties.RES_ALLOW_CREDENTIALS);
		// 允许远程地址跨域
		response.setHeader("Access-Control-Allow-Origin", Properties.RES_ALLOW_ORIGIN);
		// 允许跨域的方式get/post		
		response.setHeader("Access-Control-Allow-Methods", Properties.RES_ALLOW_METHODS);
	}
}
