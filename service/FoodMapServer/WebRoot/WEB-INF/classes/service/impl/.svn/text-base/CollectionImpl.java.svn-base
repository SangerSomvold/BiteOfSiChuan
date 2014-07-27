package service.impl;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import dao.impl.CollectionDAO;
import dao.impl.PictureDAO;
import dao.impl.ShopDao;
import dao.impl.TypeDAO;
import dao.impl.UserDAO;
import dao.interf.ICollectionDAO;
import dao.interf.IPictureDAO;
import dao.interf.IShopDAO;
import dao.interf.ITypeDAO;
import dao.interf.IUserDAO;
import dao.pojo.Collection;
import dao.pojo.Comment;
import dao.pojo.Picture;
import dao.pojo.Shop;
import dao.pojo.Type;
import dao.pojo.User;
import model.MCollection;
import model.MComment;
import model.MShop;
import model.MType;
import service.interf.ICollection;
import service.interf.IShop;
import service.interf.IType;
import utils.StorageUtil;

public class CollectionImpl implements ICollection {
	private ICollectionDAO cDAO = new CollectionDAO();



	public JSONObject searchById(MCollection model) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonObject= new JSONObject();
	
		
		
		String rule="model.user.userId =" + "'" + model.getUserId() + "'";
		List<Collection> collectionList = cDAO.findAll(rule);
		if(collectionList==null)
		{
			jsonObject.put("isExist", false);
		}
		else
		{
			jsonObject.put("isExist", true);
	
			jsonObject.element("typeList", getJSonArray(collectionList));
		
		}
		return jsonObject;
	}

	// 将获得的shop列表组装成带有详细信息的列表
	protected JSONArray getJSonArray(List<Collection> list) throws Exception {
		JSONArray jsonArray = new JSONArray();
		for (Collection collection : list) {
			JSONObject collectionItem = getCollectionData(collection);
			jsonArray.add(collectionItem);
		}
		return jsonArray;
	}
	
	public JSONObject getCollectionData(Collection collection) throws Exception {

		JSONObject jsonData = new JSONObject();
			
	
		jsonData.put("collection_id", collection.getCollectionId());
		jsonData.put("user_name", collection.getUser().getUserId());
		jsonData.put("shop_id", collection.getShop().getShopId());
		String tmp=collection.getShop().getShopName();
		jsonData.put("shop_name", tmp);

		
		return jsonData;
	}

	public Boolean add(MCollection model) throws Exception {

		Collection collection = new Collection();
	 
		
		//String c = model.getComment();
		//c = URLEncoder.encode(c, "utf-8");
		collection.setUser(new User(model.getUserId()));
		collection.setShop(new Shop(model.getShopId()));

		String rule = "model.shop.shopId = " + "'" + model.getShopId() + "'" + "AND " +
		"model.user.userId = " + "'" + model.getUserId() + "'";
	
		List<Collection> collectionList = cDAO.findAll(rule);
		if(collectionList.size() != 0) {
			return false;
		}



		cDAO.save(collection);
		

		
		return true;
	}

	
	public Boolean delete(MCollection model) throws Exception {
	
		String rule = "model.shop.shopId = " + "'" + model.getShopId() + "'" + "AND " +
		"model.user.userId = " + "'" + model.getUserId() + "'";
	
		List<Collection> collectionList = cDAO.findAll(rule);
		if(collectionList.size() == 0) {
			return false;
		}
		for(Collection c: collectionList) {
			cDAO.delete(c);
		}
		
		return true;
		
	}
	//是否被收藏
	public boolean isCollection(MCollection model)
	{
	
		boolean res=false;
		String rule = "model.shop.shopId = " + "'" + model.getShopId() + "'" + "AND " +
		"model.user.userId = " + "'" + model.getUserId() + "'";
	
		List<Collection> collectionList = cDAO.findAll(rule);
		if(collectionList.size()>0)
		{
			res=true;
		}
		
		return res;
	}

}
