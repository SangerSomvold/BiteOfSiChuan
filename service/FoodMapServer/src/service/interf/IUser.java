package service.interf;

import model.MUser;

public interface IUser {
	public Boolean check(MUser model) throws Exception;

	public Boolean add(MUser model) throws Exception;
	
	public Boolean change(MUser model) throws Exception;
}
