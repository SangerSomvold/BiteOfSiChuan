package action;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import model.MComment;
import service.impl.CommentImpl;
import service.interf.IComment;

import com.opensymphony.xwork2.ModelDriven;

public class CommentAction extends BaseAction<MComment> implements ModelDriven<MComment>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IComment comment = new CommentImpl(); 
	
	
	public CommentAction() {
		super(MComment.class);
	}
	public MComment getModel() {
		return model;
	}
	

	public void add(){
		String x = null;
		try{
			if(model.getUserId() == null || model.getComment() == null|| model.getShopId()==0)
				throw new Exception("userId=? comment=? shopId=?");
			Boolean check = comment.add(model);
			x = getMsg(check);
		}catch (Exception e) {
			x = getError(e);
		}
		servletHandle.writeToClient(ServletActionContext.getResponse(), x);
	}
	
	//添加评论图片
	public void upImg()
	{
		JSONObject jsonObject=new JSONObject();
		try {
			if (model.getUserId() == null || model.getImageData() == null|| model.getShopId()==0 || model.getDatetime()==null) {
				throw new Exception("参数错误（shopId=? userId=? datetime=? imgData=?）");
			} else {
				comment.upImg(model);
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
	
	//获得具体信息
	public void getData() {
		String x = null;
		try {
			if (model.getShopId()== -1  || model.getPageNumber()<1) {
				throw new Exception("参数错误（shopId=?  pageNumber=?）");
			} else {
				JSONObject jsonObject = comment.searchById(model);
				x = jsonObject.toString();
			}

		} catch (Exception e) {
			x = getError(e);
		}
		// 写回
		servletHandle.writeToClient(ServletActionContext.getResponse(), x);

	}
	

}
