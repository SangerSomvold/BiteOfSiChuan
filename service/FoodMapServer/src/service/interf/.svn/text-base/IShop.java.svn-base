package service.interf;


import dao.pojo.Shop;
import net.sf.json.JSONObject;
import model.MShop;

public interface IShop {
	
	//获取商户详细信息
	public  JSONObject getShopData(Shop shop) throws Exception;
	//获取所有商户信息(分页)
	public JSONObject getAllShopData(MShop model) throws Exception;
	//根据关键字搜索信息
	public JSONObject searchByKey(MShop model)throws Exception;
	//根据类型搜索信息
	public JSONObject searchByType(MShop model) throws Exception ;
	//创建商户
	public JSONObject add(MShop model)throws Exception;
	//根据ID获取某一商户信息
	public JSONObject searchById(MShop model)throws Exception;
	//添加图片
	public JSONObject upImg(MShop model)throws Exception;
	

}
