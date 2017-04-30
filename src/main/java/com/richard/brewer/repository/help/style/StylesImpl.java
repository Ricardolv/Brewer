package com.richard.brewer.repository.help.style;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.richard.brewer.model.Beer;
import com.richard.brewer.model.Style;
import com.richard.brewer.repository.filter.StyleFilter;

public class StylesImpl implements StylesQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public Page<Style> styleFilter(StyleFilter styleFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
		
		criteria.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		criteria.setMaxResults(pageable.getPageSize());
		
		addOrderBy(pageable, criteria);
		
		addFilter(styleFilter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(styleFilter));
	}

	
	@SuppressWarnings("deprecation")
	private Long total(StyleFilter styleFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
		addFilter(styleFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void addOrderBy(Pageable pageable, Criteria criteria) {
		Sort sort = pageable.getSort();
		if (null != sort) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));
		}
	}
	
	private void addFilter(StyleFilter styleFilter, Criteria criteria) {
		if (!StringUtils.isEmpty(styleFilter.getName())) {
			criteria.add(Restrictions.ilike("name", styleFilter.getName(), MatchMode.ANYWHERE));
		}
	}
}
