package action;

import model.MShop;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import service.impl.ShopImpl;
import service.interf.IShop;

import com.opensymphony.xwork2.ModelDriven;

public class ShopAction extends BaseAction<MShop> implements ModelDriven<MShop> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IShop shop = new ShopImpl();

	public ShopAction() {
		super(MShop.class);
		// TODO Auto-generated constructor stub
	}

	public MShop getModel() {
		return model;
	}

	//获得某一商户具体信息（ID）
	public void getDataById() {
		String x = null;
		try {
			if (model.getShopId() == null) {
				throw new Exception("参数错误（shopId=?）");
			} else {
				JSONObject jsonObject = shop.searchById(model);
				x = jsonObject.toString();
			}

		} catch (Exception e) {
			x = getError(e);
		}
		// 写回
		servletHandle.writeToClient(ServletActionContext.getResponse(), x);

	}

	// 搜索关键字(名称)
	public void getDataByKey() {
		String res = null;
		try {

			if (model.getShopName() == null || model.getPageNumber()<1) {
				throw new Exception("参数错误（shopName=? pageNumber=?）");
			} else {
				JSONObject jsonObject = shop.searchByKey(model);
				res = jsonObject.toString();
			}

		} catch (Exception e) {
			res = getError(e);
		}
		// 写回
		servletHandle.writeToClient(ServletActionContext.getResponse(), res);
	}
	// 搜索类型
	public void getDataByType() {
		String res = null;
		try {

			if (model.getTypeId() ==-1|| model.getPageNumber()<1) {
				throw new Exception("参数错误（typeId=? pageNumber=?）");
			} else {
				JSONObject jsonObject = shop.searchByType( model);
				res = jsonObject.toString();
			}

		} catch (Exception e) {
			res = getError(e);
		}
		// 写回
		servletHandle.writeToClient(ServletActionContext.getResponse(), res);
	}

	// 分页获得信息
	public void getAllData() {
		String res = null;
		try {

			if (model.getPageNumber() < 1) {
				throw new Exception("参数错误（pageNumber=?）");
			} else {
				JSONObject jsonObject = shop.getAllShopData(model);
				res = jsonObject.toString();
			}

		} catch (Exception e) {
			res = getError(e);
		}
		// 写回
		servletHandle.writeToClient(ServletActionContext.getResponse(), res);
	}

	// 添加商户
	public void addShop() {
		JSONObject jsonObject=new JSONObject();
		try {
			if (model.getShopName() == null||model.getUserId()==null) {
			
				throw new Exception("参数错误（shopName=? userId=? ）");
			} else {
				shop.add(model);
				jsonObject.put("succeed", true);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jsonObject.put("succeed", false);
			jsonObject.put("error", getError(e));
		}
		// 写回
		servletHandle.writeToClient(ServletActionContext.getResponse(), jsonObject.toString());
		
	}
	//添加商户图片
	public void upImg()
	{
		JSONObject jsonObject=new JSONObject();
		try {
			if (model.getShopName() == null||model.getUserId()==null||model.getCreateDate()==null) {
				throw new Exception("参数错误（shopName=? userId=? getCreateDate=?）");
			} else {
				shop.upImg(model);
				jsonObject.put("succeed", true);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jsonObject.put("succeed", false);
			jsonObject.put("error", getError(e));
		}
		// 写回
		servletHandle.writeToClient(ServletActionContext.getResponse(), jsonObject.toString());
	}

}
