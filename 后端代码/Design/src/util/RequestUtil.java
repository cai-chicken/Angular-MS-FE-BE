package util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	// 根据传递的request对象、key值，返回对应的value
	public String parameter(HttpServletRequest req, String key ) {
		// 使用三元表达式判断，如果指定key对应的value存在时，才返回对应值，否则返回空字符串
		// 防止直接使用req.getParameter(key)抛出NullPointException
		return (req.getParameter(key) != null ? req.getParameter(key) : "");
	}
}
