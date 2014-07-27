package service.interf;


import dao.pojo.Type;
import net.sf.json.JSONObject;
import model.MType;

public interface IType {
	
	//获取商户详细信息
	public JSONObject searchById(MType model) throws Exception;
	
	public JSONObject getTypeData(Type type) throws Exception;

}
