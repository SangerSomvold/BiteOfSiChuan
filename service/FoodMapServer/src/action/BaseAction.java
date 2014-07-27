package action;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;
import common.impl.ServletHandleImpl;
import common.interf.IServletHandle;

@SuppressWarnings("unchecked")
public class BaseAction<T> extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected IServletHandle servletHandle = new ServletHandleImpl();
	protected final Log log = LogFactory.getLog(getClass());
	protected T model;
	
	public BaseAction(Class clazz){
		if(clazz==null)
			
			return;
		else{
			try {
				this.model = (T)clazz.newInstance();
			} catch (Exception e) {
				model = (T)this;
			}
		}
		
	}

	public String getMsg(Object o ){
		JSONObject jobj = new JSONObject();
		jobj.put("success", true);
		if(o == null)
			jobj.put("result", "");
		else
			jobj.put("result", o.toString());
		return jobj.toString();
	}
	
	/**
	 * 获取异常的信息
	 * @param e
	 * @return
	 * @throws Exception
	 */
	public String getError(Exception e){
		JSONObject jobj = new JSONObject();
		jobj.put("success", false);
		jobj.put("result", e.getMessage());
		return jobj.toString();
	}
}
