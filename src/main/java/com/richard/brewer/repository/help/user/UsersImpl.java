package com.richard.brewer.repository.help.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.richard.brewer.model.Group;
import com.richard.brewer.model.User;
import com.richard.brewer.model.UserGroup;
import com.richard.brewer.repository.filter.UserFilter;

public class UsersImpl implements UsersQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Optional<User> findByEmailAndActive(String email) {
		return manager.createQuery(
				"from User where lower(email) = lower(:email) and active = true ", User.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}

	@Override
	public List<String> permissions(User user) {
		return manager.createQuery(
				"select distinct p.name from User u inner join u.groups g inner join g.permissions p where u = :user", String.class)
				.setParameter("user", user)
				.getResultList();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public List<User> filter(UserFilter filter) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(User.class);
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		addFilter(filter, criteria);
		
		return criteria.list();
	}

	private void addFilter(UserFilter filter, Criteria criteria) {

		if (null!= filter) {
			
			if (!StringUtils.isEmpty(filter.getName())) {
				criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filter.getEmail())) {
				criteria.add(Restrictions.ilike("email", filter.getEmail(), MatchMode.START));
			}
			
			criteria.createAlias("groups", "g", JoinType.LEFT_OUTER_JOIN);
			if (null != filter.getGroups() && !filter.getGroups().isEmpty()) {
				
				List<Criterion> subqueries = new ArrayList<>();
				for(Long codeGroup : filter.getGroups().stream().mapToLong(Group::getCode).toArray()) {
					DetachedCriteria dc = DetachedCriteria.forClass(UserGroup.class);
					dc.add(Restrictions.eq("id.group.code", codeGroup));
					dc.setProjection(Projections.property("id.user"));
					
					subqueries.add(Subqueries.propertyIn("code", dc));
				}
				
				Criterion[] criterions = new Criterion[subqueries.size()];
				criteria.add(Restrictions.and(subqueries.toArray(criterions)));
			}
			
		}
	}

}
