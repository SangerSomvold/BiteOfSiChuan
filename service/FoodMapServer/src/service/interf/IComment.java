package service.interf;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import dao.pojo.Comment;
import dao.pojo.Shop;
import model.MComment;
import model.MShop;

public interface IComment {
	public Boolean check(MComment model) throws Exception;

	public Boolean add(MComment model) throws Exception;
	
	public JSONObject getCommentData(Comment comment) throws Exception;


	public JSONObject searchById(MComment model) throws Exception;
	
	public Boolean upImg(MComment model)throws Exception;
	
	
}
