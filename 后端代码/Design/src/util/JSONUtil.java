package util;

import java.util.List;

import entity.Students;
import net.sf.json.JSONObject;

public class JSONUtil {
	private JSONObject json = new JSONObject();
	private String jsonStr;
	
	/**
	 * 将参数对象data转化成json字符串返回
	 * @param data
	 * @return jsonStr
	 */
	public String JSONStringify(List<Students> data) {
		json.put("errno", 0);
		json.put("data",data);
		jsonStr = json.toString(1, 1);
		return jsonStr;
	}

	public String JSONStringify(Boolean data, Boolean ...booleans) {
		try {
			if (booleans.length > 0 & booleans[0] == false) {
				json.put("errno", 1);
			} else if (booleans.length > 0 & booleans[0] == true) {
				json.put("errno", 2);
			} else {
				json.put("errno", 0);
			}
		} catch (Exception e) {
			json.put("errno", 0);
		}
		json.put("data",data);
		jsonStr = json.toString(1, 1);
		return jsonStr;
	}

	public String JSONStringify(Object data, Boolean ...booleans) {
		try {
			if (booleans.length > 0 & booleans[0] == false) {
				json.put("errno", 1);
			} else if (booleans.length > 0 & booleans[0] == true) {
				json.put("errno", 2);
			} else {
				json.put("errno", 0);
			}
		} catch (Exception e) {
			json.put("errno", 0);
		}
		json.put("data",data);
		jsonStr = json.toString(1, 1);
		return jsonStr;
	}
}
