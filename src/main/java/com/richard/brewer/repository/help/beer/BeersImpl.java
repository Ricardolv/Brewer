package com.richard.brewer.repository.help.beer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.filter.BeerFilter;

public class BeersImpl implements BeersQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public List<Beer> beerFilter(BeerFilter beerFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
		
		criteria.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		criteria.setMaxResults(pageable.getPageSize());

		if (null != beerFilter) {
			
			if (!StringUtils.isEmpty(beerFilter.getSku())) {
				criteria.add(Restrictions.eq("sku", beerFilter.getSku()));
			}
			
			if (!StringUtils.isEmpty(beerFilter.getName())) {
				criteria.add(Restrictions.ilike("name", beerFilter.getName(), MatchMode.ANYWHERE));
			}

			if (isStylePresent(beerFilter)) {
				criteria.add(Restrictions.eq("style", beerFilter.getStyle()));
			}

			if (beerFilter.getFlavor() != null) {
				criteria.add(Restrictions.eq("flavor", beerFilter.getFlavor()));
			}

			if (beerFilter.getOrigin() != null) {
				criteria.add(Restrictions.eq("origin", beerFilter.getOrigin()));
			}

			if (beerFilter.getPriceOf() != null) {
				criteria.add(Restrictions.ge("value", beerFilter.getPriceOf()));
			}

			if (beerFilter.getPriceUntil() != null) {
				criteria.add(Restrictions.le("value", beerFilter.getPriceUntil()));
			}
			
		}
		
		return criteria.list();
	}
	
	private boolean isStylePresent(BeerFilter filter) {
		return filter.getStyle() != null && filter.getStyle().getCode() != null;
	}

}
