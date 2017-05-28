package com.richard.brewer.repository.help.sale;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.richard.brewer.model.PersonType;
import com.richard.brewer.model.Sale;
import com.richard.brewer.repository.filter.SaleFilter;
import com.richard.brewer.repository.pagination.PaginationUtil;

@SuppressWarnings({"deprecation", "unchecked"})
public class SalesImpl implements SalesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginationUtil;

	@Transactional(readOnly = true)
	@Override
	public Page<Sale> filter(SaleFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Sale.class);
		paginationUtil.prepare(criteria, pageable);
		addFilter(filter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}

	private Long total(SaleFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Sale.class);
		addFilter(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void addFilter(SaleFilter filter, Criteria criteria) {
		criteria.createAlias("client", "c");
		
		if (filter != null) {
			if (!StringUtils.isEmpty(filter.getCode())) {
				criteria.add(Restrictions.eq("code", filter.getCode()));
			}
			
			if (filter.getStatus() != null) {
				criteria.add(Restrictions.eq("status", filter.getStatus()));
			}
			
			if (filter.getSince() != null) {
				LocalDateTime since = LocalDateTime.of(filter.getSince(), LocalTime.of(0, 0));
				criteria.add(Restrictions.ge("creationDate", since));
			}
			
			if (filter.getUpUntil() != null) {
				LocalDateTime upUntil = LocalDateTime.of(filter.getUpUntil(), LocalTime.of(23, 59));
				criteria.add(Restrictions.le("creationDate", upUntil));
			}
			
			if (filter.getMinimumValue() != null) {
				criteria.add(Restrictions.ge("totalValue", filter.getMinimumValue()));
			}
			
			if (filter.getMaximumValue() != null) {
				criteria.add(Restrictions.le("totalValue", filter.getMaximumValue()));
			}
			
			if (!StringUtils.isEmpty(filter.getClientName())) {
				criteria.add(Restrictions.ilike("c.name", filter.getClientName(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filter.getClientCpfCnpj())) {
				criteria.add(Restrictions.eq("c.cpfCnpj", PersonType.removeFormatting(filter.getClientCpfCnpj())));
			}
		}
	}

}
