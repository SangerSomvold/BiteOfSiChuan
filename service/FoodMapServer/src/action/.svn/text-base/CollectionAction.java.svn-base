package action;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import model.MCollection;
import service.impl.CollectionImpl;
import service.interf.ICollection;

import com.opensymphony.xwork2.ModelDriven;

public class CollectionAction extends BaseAction<MCollection> implements ModelDriven<MCollection>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ICollection collection = new CollectionImpl(); 
	
	
	public CollectionAction() {
		super(MCollection.class);
	}
	public MCollection getModel() {
		return model;
	}

	
	
	//获得具体信息
	public void getData() throws Exception {
		String x = null;
		try {
			if (model.getUserId()== null) {
				throw new Exception("参数null（userId=?）");
			} else {
				JSONObject jsonObject = collection.searchById(model);
				x = jsonObject.toString();
			}

		} catch (Exception e) {
			x = getError(e);
		}
		// 写回
		servletHandle.writeToClient(ServletActionContext.getResponse(), x);


	}
	
	public void add(){
		String x = null;
		try{
			if(model.getUserId() == null || model.getShopId() <= 0)
				throw new Exception("参数不能为空");
			Boolean check = collection.add(model);
			x = getMsg(check);
		}catch (Exception e) {
			x = getError(e);
		}
		servletHandle.writeToClient(ServletActionContext.getResponse(), x);
	}
	
	public void delete() {
		String x = null;
		try{
			if(model.getUserId() == null || model.getShopId() <= 0)
				throw new Exception("参数不能为空,userId=? shopId=?");
			
			Boolean check = collection.delete(model);
			x = getMsg(check);
		}catch (Exception e) {
			x = getError(e);
		}
		servletHandle.writeToClient(ServletActionContext.getResponse(), x);
	}
	//验证是否收藏
	public void isCollect()
	{
		String x=null;
		try{
			if(model.getUserId() == null || model.getShopId() <= 0)
				throw new Exception("参数不能为空,userId=? shopId=?");
			   boolean check=collection.isCollection(model);
			 
			   x=getMsg(check);
			 
		
			
		}catch (Exception e)
		{
			x = getError(e);
		}
		servletHandle.writeToClient(ServletActionContext.getResponse(), x);
	}
}
