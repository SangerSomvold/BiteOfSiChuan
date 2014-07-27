package service.impl;

import model.MUser;
import service.interf.IUser;
import dao.impl.UserDAO;
import dao.interf.IUserDAO;
import dao.pojo.User;

public class UserImpl implements IUser{
	private IUserDAO uDao = new UserDAO();
	

	public Boolean add(MUser model) throws Exception {
		User user = uDao.findById(model.getUserId());
		if(user != null)
			throw new Exception("该用户已存在");
		user = new User();
		user.setUserId(model.getUserId());
		user.setPassword(model.getPassword());
		
		uDao.save(user);
		return true;
	}
	
	
	public Boolean check(MUser model) throws Exception {
		User user = uDao.findById(model.getUserId());
		uDao.addxxx();
		if(user == null)
			throw new Exception("该用户不存在");
		if(! user.getPassword().equals(model.getPassword()))
			return false;
		return true;
	}
	
	public Boolean change(MUser model) throws Exception {
		User user = uDao.findById(model.getUserId());
		if(user == null)
			throw new Exception("该用户不存在");
		if(! user.getPassword().equals(model.getPassword()))
			throw new Exception("原密码错误");
		user.setPassword(model.getNewPassword());
		uDao.update(user);
		return true;
			
	}
}
