package service.impl;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import dao.impl.PictureDAO;
import dao.impl.ShopDao;
import dao.impl.TypeDAO;
import dao.impl.UserDAO;
import dao.interf.IPictureDAO;
import dao.interf.IShopDAO;
import dao.interf.ITypeDAO;
import dao.interf.IUserDAO;
import dao.pojo.Comment;
import dao.pojo.Picture;
import dao.pojo.Shop;
import dao.pojo.Type;
import dao.pojo.User;
import model.MComment;
import model.MShop;
import model.MType;
import service.interf.IShop;
import service.interf.IType;
import utils.StorageUtil;

public class TypeImpl implements IType {
	private ITypeDAO tDAO = new TypeDAO();


	public TypeImpl() {

	}


	public JSONObject searchById(MType model) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonObject= new JSONObject();
	
		
		
		String rule = "model.typeId != "  + "'" + -1 + "'";
		List<Type> typeList = tDAO.findAll(rule);
		if(typeList==null)
		{
			jsonObject.put("isExist", false);
		}
		else
		{
			jsonObject.put("isExist", true);
	
			jsonObject.element("typeList", getJSonArray(typeList));
		
		}
		return jsonObject;
	}

	// 将获得的shop列表组装成带有详细信息的列表
	protected JSONArray getJSonArray(List<Type> list) throws Exception {
		JSONArray jsonArray = new JSONArray();
		for (Type type : list) {
			JSONObject typeItem = getTypeData(type);
			jsonArray.add(typeItem);
		}
		return jsonArray;
	}
	
	public JSONObject getTypeData(Type type) throws Exception {
		// TODO Auto-generated method stub
		JSONObject jsonData = new JSONObject();
			
	
		jsonData.put("type_id", type.getTypeId());
		jsonData.put("type_name", type.getTypeName());
		

		
		return jsonData;
	}



}
