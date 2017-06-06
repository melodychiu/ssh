package cn.mydoll.ssh.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.mydoll.ssh.dao.IStandardDao;
import cn.mydoll.ssh.entity.Standard;

public class StandardDaoImpl extends HibernateDaoSupport implements IStandardDao{
	@Override
	public void save(Standard standard) throws Exception {
		super.getHibernateTemplate().save(standard);	
	}
	@Override
	public void update(Standard standard) throws Exception {
		super.getHibernateTemplate().update(standard);
	}
	@Override
	public void delete(Standard standard) throws Exception {
		super.getHibernateTemplate().delete(standard);
	}
	@Override
	public Standard getById(Serializable id) throws Exception {
		return super.getHibernateTemplate().get(Standard.class, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Standard> getStandardList(String condition, int pageIndex, int pageSize) throws Exception {
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria=session.createCriteria(Standard.class);
		if(null!=condition && !"".equals(condition)){
			criteria.add(Restrictions.or(Restrictions.ilike("stdnum", condition,MatchMode.ANYWHERE),
				Restrictions.ilike("zhname", condition,MatchMode.ANYWHERE)));			
		}
		criteria.addOrder(Order.asc("id"));
		criteria.setFirstResult((pageIndex-1)*pageSize);
		criteria.setMaxResults(pageSize);
		return criteria.list();
	}

	public int getStandardListCount(String condition){
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria=session.createCriteria(Standard.class);
		if(null!=condition && !"".equals(condition)){
			criteria.add(Restrictions.or(Restrictions.ilike("stdnum", condition,MatchMode.ANYWHERE),
				Restrictions.ilike("zhname", condition,MatchMode.ANYWHERE)));			
		}
		Integer	totalNum = (Integer)criteria.setProjection(Projections.rowCount()).uniqueResult();	
		return totalNum.intValue();
	}
	@Override
	public Standard haveStandard(Standard standard) throws Exception {
		String hql="from Standard where stdnum=:stdnum";
		Session session=super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(hql.toString());
		query.setProperties(standard);
		Standard _std=(Standard)query.uniqueResult();
		return _std;
	}
}