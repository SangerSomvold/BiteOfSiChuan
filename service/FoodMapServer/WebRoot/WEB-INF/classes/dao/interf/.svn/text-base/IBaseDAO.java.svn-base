package dao.interf;

import java.util.List;

public interface IBaseDAO<T> {
	/**
	 * 保存单条记录
	 */
	public T save(T entity) throws RuntimeException;
	/**
	 * 更新单条记录
	 */
	public T update(T entity) throws RuntimeException;
	/**
	 * 删除单条记录
	 */
	public boolean delete(T entity) throws RuntimeException;
	/**
	 * 根据主键删除单条数据
	 */
	public boolean deleteById(Object id) throws Exception;
	/**
	 * 查找所有记录
	 */
	public List<T> findAll(final String rule) throws RuntimeException;
	public List<T> findAll(final String rule, final String sortfield) throws RuntimeException;
	/**
	 * 查找所有记录，带分页功能
	 */
	public List<T> findAll(final int start,final int limit,final String rule,final String sortfield)
		throws RuntimeException;
	/**
	 * 根据主键查找单条记录
	 */
	public T findById(Object id) throws Exception;
	/**
	 * 条件查找
	 */
	public List<T> findByProperties(String[] pNames,Object[] pValues)
		throws RuntimeException;
	/**
	 * 获取全部记录的条数
	 */
	public Long getAllResultsCount();
	/**
	 * 获取带查找条件记录的条数
	 */
	public Long getResultsCountRule(String  rule);
	/**
	 * 用原生的sql查询数据
	 */
	public List<Object> nativeQuery(String sql);
	
	public List<T> findByProperties(final int start,final int limit,String[] pNames,Object[] pValues,String sortString)
	throws RuntimeException;
	public void nativeQuery1(String sql);
}
