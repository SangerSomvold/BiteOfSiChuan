package common.interf;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public interface IServletHandle {
	public int writeToClient(HttpServletResponse res,String msgs);  
}
