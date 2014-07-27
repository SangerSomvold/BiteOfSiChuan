package dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.interf.IBaseDAO;

@SuppressWarnings("unchecked")
public class BaseDAO<T> implements IBaseDAO<T>{
	private String TBLNAME = "";
	private String POJOCLASS = "";
	private Class clazz;
	
	
	public BaseDAO(){
		Type genType = getClass().getGenericSuperclass();//获取泛型的类型
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		if(params==null||params.length==0)
			return;
		POJOCLASS = params[0].toString();
		POJOCLASS = POJOCLASS.substring(POJOCLASS.indexOf(" ")+1);
		int x = POJOCLASS.lastIndexOf(".");
		if(x >= 0)
			TBLNAME = POJOCLASS.substring(x +1);
		else 
			TBLNAME = POJOCLASS;
	}

	/**
	 * 保存单条记录
	 * @param entity 该记录对应的实体
	 * @return
	 * @throws RuntimeException
	 */
	public T save(T entity) throws RuntimeException{
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
		return entity;
	}
	
	/**
	 * 更新单条记录
	 * @param entity 该记录对应的实体
	 * @return
	 * @throws RuntimeException
	 */
	public T update(T entity) throws RuntimeException{
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
		return entity;
	}
	
	/**
	 * 删除单条记录
	 * @param entity该记录的实体
	 * @return
	 * @throws RuntimeException
	 */
	public boolean delete(T entity) throws RuntimeException{
		if(null != entity){
			EntityManager em = EntityManagerHelper.getEntityManager();
			em.getTransaction().begin();
			entity = em.merge(entity);
			em.remove(entity);
			em.getTransaction().commit();
			EntityManagerHelper.closeEntityManager();
			return true;
		}
		return false;
	}
	
	/**
	 * 根据主键删除单条数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteById(Object id) throws Exception{
		EntityManager em = EntityManagerHelper.getEntityManager();
		T x = (T) em.find(Class.forName(POJOCLASS), id);
		return delete(x);
	}

	/**
	 * 查找所有记录
	 * @param rule 更多限制条件，即where后的限制条件，为sql语句
	 * @return
	 * @throws RuntimeException
	 */
	public List<T> findAll(final String rule) throws RuntimeException{
		EntityManager em = EntityManagerHelper.getEntityManager();
		String queryString = "select model from "+TBLNAME +" model ";
		if(rule != null)
			queryString += "where "+rule; 
		Query query = em.createQuery(queryString);
		List<T> lst = query.getResultList();
		EntityManagerHelper.closeEntityManager();
		return lst;
	}
	
	/**
	 * 查找所有记录
	 * @param rule 更多限制条件，即where后的限制条件，为sql语句
	 * @return
	 * @throws RuntimeException
	 */
	public List<T> findAll(final String rule, final String sortfield) throws RuntimeException{
		EntityManager em = EntityManagerHelper.getEntityManager();
		String queryString = "select model from "+TBLNAME +" model ";
		if(rule != null)
			queryString += "where "+rule; 
		if(sortfield != null)
			queryString += " order by "+sortfield;
		Query query = em.createQuery(queryString);
		List<T> lst = query.getResultList();
		EntityManagerHelper.closeEntityManager();
		return lst;
	}
	
	/**
	 * 查找所有记录，带分页功能
	 * @param start 开始条数
	 * @param limit 最多条数
	 * @param rule 更多限制条件，即where后的限制条件，为sql语句
	 * @return
	 * @throws RuntimeException
	 */
	public List<T> findAll(final int start,final int limit,final String rule,final String sortfield)
		throws RuntimeException{
		EntityManager em = EntityManagerHelper.getEntityManager();
		String queryString = "select model from "+TBLNAME +" model ";
		if(rule != null)
			queryString += "where "+rule; 
		if(sortfield != null)
			queryString += " order by "+sortfield;
		Query query = em.createQuery(queryString);
		if(start >=0 && limit >0){
			query.setFirstResult(start);
			query.setMaxResults(limit);	
		}
		List<T> lst = query.getResultList();
		EntityManagerHelper.closeEntityManager();
	
		return lst;
	}
	
	/**
	 * 根据主键查找单条记录
	 * @param id 主键
	 * @return
	 * @throws Exception
	 */
	public T findById(Object id) throws Exception{
		EntityManager em = null;
		try{
			em = EntityManagerHelper.getEntityManager();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		T x = (T) em.find(Class.forName(POJOCLASS), id);
		EntityManagerHelper.closeEntityManager();
		return x;
	}
	
	/**
	 * 待条件分页的查询,按照sortString排序
	 * @param start
	 * @param limit
	 * @param pNames
	 * @param pValues
	 * @param sortString
	 * @return
	 * @throws RuntimeException
	 */
	public List<T> findByProperties(final int start,final int limit,String[] pNames,Object[] pValues,String sortString)
		throws RuntimeException{
		if(pNames.length == 0||pValues.length == 0)
			return findAll(null);
		EntityManager em = EntityManagerHelper.getEntityManager();
		String queryString = "select model from "+TBLNAME+" model where " +
				"model."+pNames[0]+"= ?1";
		for(int i=1; i<pNames.length;i++){
			queryString += " and model."+pNames[i]+"= ?"+(i+1);
		}
		if(null != sortString){
			queryString += " order by "+sortString;
		}
		Query query = em.createQuery(queryString);
		for(int i=0;i<pValues.length;i++){
			query.setParameter(i+1, pValues[i]);
		}
		if(start >=0 && limit >0){
			query.setFirstResult(start);
			query.setMaxResults(limit);	
		}
		List<T> lst = query.getResultList();
		EntityManagerHelper.closeEntityManager();
		return lst;
	}
	
	/**
	 * 条件查找
	 * @param pNames 条件数组
	 * @param pValues 与条件对应的值数组
	 * @return
	 * @throws RuntimeException
	 */
	public List<T> findByProperties(String[] pNames,Object[] pValues)
		throws RuntimeException{
		if(pNames.length == 0||pValues.length == 0)
			return findAll(null);
		EntityManager em = EntityManagerHelper.getEntityManager();
		String queryString = "select model from "+TBLNAME+" model where " +
				"model."+pNames[0]+"= ?1";
		for(int i=1; i<pNames.length;i++){
			queryString += " and model."+pNames[i]+"= ?"+(i+1);
		}
		Query query = em.createQuery(queryString);
		for(int i=0;i<pValues.length;i++){
			query.setParameter(i+1, pValues[i]);
		}
		List<T> lst = query.getResultList();
		EntityManagerHelper.closeEntityManager();
		return lst;
	}
	
	/**
	 * 获取全部记录的条数
	 * @return
	 */
	public Long getAllResultsCount(){
		EntityManager em = EntityManagerHelper.getEntityManager();
		String queryString = "select count(model) from "+TBLNAME +" model ";
		Query query = em.createQuery(queryString);
		Long count = (Long) query.getSingleResult(); 
		
		EntityManagerHelper.closeEntityManager();
		return count;
	}

	/**
	 * 获取带查找条件记录的条数
	 * @return
	 */
	public Long getResultsCountRule(String  rule){
		EntityManager em = EntityManagerHelper.getEntityManager();
		String queryString = "select count(model) from "+TBLNAME +" model where "+rule;
		Query query = em.createQuery(queryString);
		Long count = (Long) query.getSingleResult(); 
		EntityManagerHelper.closeEntityManager();
		return count;
	}

	/**
	 * 用原生的sql查询数据
	 * @param sql
	 * @return
	 */
	public List<Object> nativeQuery(String sql){
		EntityManagerHelper.beginTransaction();
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query q = em.createNativeQuery(sql);
		EntityManagerHelper.commit();
		List<Object> lst = q.getResultList();
		return lst;
	}
	
	public void nativeQuery1(String sql){
		EntityManagerHelper.beginTransaction();
		EntityManager em = EntityManagerHelper.getEntityManager();
		Query q = em.createQuery(sql);
		EntityManagerHelper.commit();

	}
}
