package service.interf;


import dao.pojo.Collection;
import net.sf.json.JSONObject;
import model.MCollection;

public interface ICollection {
	
	//获取商户详细信息
	public JSONObject searchById(MCollection model) throws Exception;
	
	public JSONObject getCollectionData(Collection collection) throws Exception ;

	public Boolean add(MCollection model) throws Exception;
	
	public Boolean delete(MCollection model) throws Exception;
	public boolean isCollection(MCollection model)throws Exception;
}
