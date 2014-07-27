package action;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import model.MType;
import service.impl.TypeImpl;
import service.interf.IType;

import com.opensymphony.xwork2.ModelDriven;

public class TypeAction extends BaseAction<MType> implements ModelDriven<MType>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IType type = new TypeImpl(); 
	
	
	public TypeAction() {
		super(MType.class);
	}
	public MType getModel() {
		return model;
	}

	
	
	//获得具体信息
	public void getData() throws Exception {
		String x = null;

		JSONObject jsonObject = type.searchById(model);
		x = jsonObject.toString();
		// 写回
		servletHandle.writeToClient(ServletActionContext.getResponse(), x);

	}
}
