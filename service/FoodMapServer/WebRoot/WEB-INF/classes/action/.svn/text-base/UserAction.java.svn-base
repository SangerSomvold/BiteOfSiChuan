package action;

import org.apache.struts2.ServletActionContext;

import model.MUser;
import service.impl.UserImpl;
import service.interf.IUser;

import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends BaseAction<MUser> implements ModelDriven<MUser>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IUser user = new UserImpl(); 
	
	
	public UserAction() {
		super(MUser.class);
	}
	public MUser getModel() {
		return model;
	}
	
	public void check(){
		String x = null;
		String path=ServletActionContext.getServletContext().getRealPath("/");
		System.out.println("----------------->"+path);
		try{
			if(model.getUserId() == null )
				throw new Exception("参数不能为空");
			Boolean check = user.check(model);
			x = getMsg(check);
		}catch (Exception e) {
			x = getError(e);
		}
		servletHandle.writeToClient(ServletActionContext.getResponse(), x);
	}

	public void add(){
		String x = null;
		try{
			if(model.getUserId() == null && model.getPassword() == null )
				throw new Exception("参数不能为空");
			Boolean check = user.add(model);
			x = getMsg(check);
		}catch (Exception e) {
			x = getError(e);
		}
		servletHandle.writeToClient(ServletActionContext.getResponse(), x);
	}
	
	public void reset() {
		String x = null;
		try{
			if(model.getUserId() == null || model.getPassword() == null || model.getNewPassword() == null)
				throw new Exception("参数不能为空");
			Boolean check = user.change(model);
			x = getMsg(check);
		}catch (Exception e) {
			x = getError(e);
		}
		servletHandle.writeToClient(ServletActionContext.getResponse(), x);
		
	}
}
