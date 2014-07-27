package common.impl;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import common.interf.IServletHandle;


public class ServletHandleImpl implements IServletHandle{
	/**
	 * 写回 client（不含success）
	 * @param	res response
	 * 			msgs 要回写的消息
	 */
	public int writeToClient(HttpServletResponse res,String msgs){
		res.setContentType("text/html;charset=UTF-8");
		res.addHeader("Cache-Control", "no-cache");
		try{
	    	PrintWriter out = res.getWriter();
			out.write(msgs);
			out.flush();
			out.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    return 1;
	}
}
