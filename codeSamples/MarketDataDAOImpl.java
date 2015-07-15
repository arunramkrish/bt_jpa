package com.extremefin.finstinct.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.extremefin.finstinct.domain.MarketData;

@Repository
@Transactional
public class MarketDataDAOImpl extends BaseDAOImpl<MarketData> implements
		MarketDataDAO {

	@Override
	public List<Date> getPreviousNDates(Integer entityId, Date date, int n) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Date> query = cb.createQuery(Date.class);
		Root<MarketData> from = query.from(MarketData.class);
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		Expression<Integer> entityIdExpr = from.get("marketDataId").get(
				"entityId");
		query.select(dateExpr);
		query.where(cb.equal(entityIdExpr, entityId),
				cb.lessThan(dateExpr, date));
		query.orderBy(cb.desc(dateExpr));
		TypedQuery<Date> typedQuery = getEntityManager().createQuery(query);
		typedQuery.setMaxResults(n);
		return typedQuery.getResultList();
	}

	@Override
	public List<Date> getPreviousNDates(Date date, int n) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Date> query = cb.createQuery(Date.class);
		Root<MarketData> from = query.from(MarketData.class);
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		query.select(dateExpr).distinct(true);
		query.where(cb.lessThanOrEqualTo(dateExpr, date));
		query.orderBy(cb.desc(dateExpr));
		TypedQuery<Date> typedQuery = getEntityManager().createQuery(query);
		typedQuery.setMaxResults(n);
		return typedQuery.getResultList();
	}

	@Override
	public BigDecimal[] getMovingAverage(Integer entityId, List<Date> dates) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		Root<MarketData> from = query.from(MarketData.class);
		Expression<BigDecimal> openPriceExpr = from.get("openPrice");
		Expression<BigDecimal> highPriceExpr = from.get("highPrice");
		Expression<BigDecimal> lowPriceExpr = from.get("lowPrice");
		Expression<BigDecimal> closePriceExpr = from.get("closePrice");
		Expression<Integer> entityIdExpr = from.get("marketDataId").get(
				"entityId");
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		query.multiselect(cb.sum(openPriceExpr), cb.sum(highPriceExpr),
				cb.sum(lowPriceExpr), cb.sum(closePriceExpr));
		query.where(cb.equal(entityIdExpr, entityId), dateExpr.in(dates));

		Object[] objects = (Object[]) getEntityManager().createQuery(query)
				.getSingleResult();
		BigDecimal[] maArray = new BigDecimal[objects.length];
		for (int i = 0; i < objects.length; i++) {
			maArray[i] = ((BigDecimal) objects[i]);
		}
		return maArray;
	}
	
	@Override
	public Integer getDaysBetweenDates(Date startDate, Date endDate) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Date> query = cb.createQuery(Date.class);
		Root<MarketData> from = query.from(MarketData.class);
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		query.select(dateExpr).distinct(true);
		query.where(cb.greaterThanOrEqualTo(dateExpr, startDate), cb.lessThanOrEqualTo(dateExpr, endDate));
		return getEntityManager().createQuery(query).getResultList().size();
	}	

    @Override
    public List<Date> getPastDates(Date startDate, int ndays) {
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Date> query = cb.createQuery(Date.class);
        Root<MarketData> from = query.from(MarketData.class);
        Expression<Date> dateExpr = from.get("marketDataId").get("date");
        query.select(dateExpr).distinct(true);
        query.where(cb.lessThanOrEqualTo(dateExpr, startDate)).orderBy(cb.desc(dateExpr));
        return getEntityManager().createQuery(query).setMaxResults(ndays).getResultList();
    }

	@Override
	public Map<Integer, MarketData> getMarketDataForDate(Date date) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MarketData> query = cb.createQuery(MarketData.class);
		Root<MarketData> from = query.from(MarketData.class);
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		query.where(cb.equal(dateExpr, date));
		TypedQuery<MarketData> typedQuery = getEntityManager().createQuery(
				query);
		return getEntityIdMarketDataMap(typedQuery.getResultList());
	}

	@Override
	public Map<Integer, MarketData> getMarketDataForPreviousDate(Date date) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Date> prevDtQuery = cb.createQuery(Date.class);
		Root<MarketData> prevDtFrom = prevDtQuery.from(MarketData.class);
		Expression<Date> prevDateExpr = prevDtFrom.get("marketDataId").get(
				"date");
		prevDtQuery.select(cb.greatest(prevDateExpr));
		prevDtQuery.where(cb.lessThan(prevDateExpr, date));
		prevDtQuery.orderBy(cb.desc(prevDateExpr));
		TypedQuery<Date> prevDtTypedQuery = getEntityManager().createQuery(
				prevDtQuery);
		prevDtTypedQuery.setMaxResults(1);
		Date prevDate = prevDtTypedQuery.getSingleResult();

		CriteriaQuery<MarketData> query = cb.createQuery(MarketData.class);
		Root<MarketData> from = query.from(MarketData.class);
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		query.where(cb.equal(dateExpr, prevDate));
		TypedQuery<MarketData> typedQuery = getEntityManager().createQuery(
				query);
		return getEntityIdMarketDataMap(typedQuery.getResultList());
	}

	@Override
	public MarketData getMarketDataForPreviousDate(Integer entityId, Date date) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MarketData> query = cb.createQuery(MarketData.class);
		Root<MarketData> from = query.from(MarketData.class);
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		Expression<Integer> entityIdExpr = from.get("marketDataId").get(
				"entityId");
		query.where(cb.equal(entityIdExpr, entityId),
				cb.lessThan(dateExpr, date));
		query.orderBy(cb.desc(dateExpr));
		TypedQuery<MarketData> typedQuery = getEntityManager().createQuery(
				query);
		typedQuery.setMaxResults(1);
		try {
			return typedQuery.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteMarketData(Date date) {

		Query query = getEntityManager().createQuery(
				"DELETE FROM MarketData m WHERE m.marketDataId.date = :dt");
		query.setParameter("dt", date, TemporalType.DATE);
		query.executeUpdate();
	}

	@Override
	public Map<Integer, BigDecimal[]> getAveragePivotPriceForMonth(
			Date startDate, Date endDate) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		Root<MarketData> from = query.from(MarketData.class);
		Expression<BigDecimal> openPriceExpr = from.get("openPrice");
		Expression<BigDecimal> highPriceExpr = from.get("highPrice");
		Expression<BigDecimal> lowPriceExpr = from.get("lowPrice");
		Expression<BigDecimal> closePriceExpr = from.get("closePrice");
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		Expression<Integer> entityIdExpr = from.get("marketDataId").get(
				"entityId");
		query.multiselect(entityIdExpr, cb.count(dateExpr),
				cb.avg(openPriceExpr), cb.avg(highPriceExpr),
				cb.avg(lowPriceExpr), cb.avg(closePriceExpr));
		query.where(cb.greaterThanOrEqualTo(dateExpr, startDate),
				cb.lessThanOrEqualTo(dateExpr, endDate));
		query.groupBy(entityIdExpr);

		List<Object> resultList = getEntityManager().createQuery(query)
				.getResultList();
		Map<Integer, BigDecimal[]> map = new HashMap<Integer, BigDecimal[]>();
		Object[] objArr = null;
		BigDecimal bdFour = new BigDecimal(4);
		for (Object row : resultList) {
			objArr = (Object[]) row;
			BigDecimal pivotPrice = BigDecimal.ZERO;
			for (int i = 2; i <= 5; i++) {
				pivotPrice = pivotPrice.add(new BigDecimal((Double) objArr[i]));
			}
			pivotPrice = pivotPrice.divide(bdFour);
			map.put((Integer) objArr[0], new BigDecimal[] {
					new BigDecimal((Long) objArr[1]), pivotPrice });
		}
		return map;
	}

	@Override
	public BigDecimal getAveragePivotPrice(Integer entityId, Date startDate, Date endDate, boolean includeEndDate) {

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		Root<MarketData> from = query.from(MarketData.class);
		Expression<BigDecimal> openPriceExpr = from.get("openPrice");
		Expression<BigDecimal> highPriceExpr = from.get("highPrice");
		Expression<BigDecimal> lowPriceExpr = from.get("lowPrice");
		Expression<BigDecimal> closePriceExpr = from.get("closePrice");
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		Expression<Integer> entityIdExpr = from.get("marketDataId").get("entityId");
		query.multiselect(cb.avg(openPriceExpr), cb.avg(highPriceExpr), cb.avg(lowPriceExpr), cb.avg(closePriceExpr));
		Predicate endDatePredicate = null;
		if (includeEndDate) {
			endDatePredicate = cb.lessThanOrEqualTo(dateExpr, endDate);
		} else {
			endDatePredicate = cb.lessThan(dateExpr, endDate);
		}
		query.where(cb.equal(entityIdExpr, entityId), cb.greaterThanOrEqualTo(dateExpr, startDate), endDatePredicate);
		return calculatePivotPrice(getEntityManager().createQuery(query).getResultList());
	}
	
	public MarketData getMarketDataForDate(Integer entityId, Date date) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MarketData> query = cb.createQuery(MarketData.class);
		Root<MarketData> from = query.from(MarketData.class);
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		Expression<Integer> entityIdExpr = from.get("marketDataId").get(
				"entityId");
		query.where(cb.equal(entityIdExpr, entityId), cb.equal(dateExpr, date));
		TypedQuery<MarketData> typedQuery = getEntityManager().createQuery(
				query);
		typedQuery.setMaxResults(1);
		try {
			return typedQuery.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public MarketData getLatestMarketDataForDate(Integer entityId, Date date) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MarketData> query = cb.createQuery(MarketData.class);
		Root<MarketData> from = query.from(MarketData.class);
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		Expression<Integer> entityIdExpr = from.get("marketDataId").get(
				"entityId");
		query.where(cb.equal(entityIdExpr, entityId), cb.lessThanOrEqualTo(dateExpr, date));
		query.orderBy(cb.desc(dateExpr));
		TypedQuery<MarketData> typedQuery = getEntityManager().createQuery(
				query);
		typedQuery.setMaxResults(1);
		try {
			return typedQuery.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public MarketData getLatestMarketDataForPreviousDate(Integer entityId, Date date) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MarketData> query = cb.createQuery(MarketData.class);
		Root<MarketData> from = query.from(MarketData.class);
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		Expression<Integer> entityIdExpr = from.get("marketDataId").get(
				"entityId");
		query.where(cb.equal(entityIdExpr, entityId), cb.lessThan(dateExpr, date));
		query.orderBy(cb.desc(dateExpr));
		TypedQuery<MarketData> typedQuery = getEntityManager().createQuery(
				query);
		typedQuery.setMaxResults(1);
		try {
			return typedQuery.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
    public SortedMap<Integer, NavigableMap<Date, MarketData>> getMarketData(Collection<Integer> entityIds, Date startDate, Date endDate) {
		
        SortedMap<Integer, NavigableMap<Date, MarketData>> map = new TreeMap<Integer, NavigableMap<Date, MarketData>>();

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MarketData> query = cb.createQuery(MarketData.class);
		Root<MarketData> from = query.from(MarketData.class);
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		Expression<Integer> entityIdExpr = from.get("marketDataId").get("entityId");
		query.where(entityIdExpr.in(entityIds), cb.greaterThanOrEqualTo(dateExpr, startDate),
				cb.lessThanOrEqualTo(dateExpr, endDate));
		query.orderBy(cb.asc(entityIdExpr), cb.asc(dateExpr));
		List<MarketData> resultList = getEntityManager().createQuery(query).getResultList();
        NavigableMap<Date, MarketData> smap = null;
		for (MarketData md : resultList) {
			smap = map.get(md.getMarketDataId().getEntityId());
			if (smap == null) {
				smap = new TreeMap<Date, MarketData>();
				smap.put(md.getMarketDataId().getDate(), md);
				map.put(md.getMarketDataId().getEntityId(), smap);
			} else {
				smap.put(md.getMarketDataId().getDate(), md);
			}
		}
		return map;
	}	
	
	@Override
	public Date getLastRunDate() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Date> prevDtQuery = cb.createQuery(Date.class);
		Root<MarketData> prevDtFrom = prevDtQuery.from(MarketData.class);
		Expression<Date> prevDateExpr = prevDtFrom.get("marketDataId").get(
				"date");
		prevDtQuery.select(cb.greatest(prevDateExpr));
		prevDtQuery.orderBy(cb.desc(prevDateExpr));
		TypedQuery<Date> prevDtTypedQuery = getEntityManager().createQuery(
				prevDtQuery);
		prevDtTypedQuery.setMaxResults(1);
		try {
			return prevDtTypedQuery.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Integer> getEntitiesWithMarketData(Date lastRunMarketData) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Integer> dtQuery = cb.createQuery(Integer.class);
		Root<MarketData> dtFrom = dtQuery.from(MarketData.class);
		Expression<Date> dateExpr = dtFrom.get("marketDataId").get("date");

		Expression<Integer> entityExpr = dtFrom.get("marketDataId").get(
				"entityId");

		dtQuery.select(entityExpr);
		dtQuery.where(cb.equal(dateExpr, lastRunMarketData));
		dtQuery.orderBy(cb.asc(entityExpr));
		TypedQuery<Integer> prevDtTypedQuery = getEntityManager().createQuery(
				dtQuery);
		try {
			return prevDtTypedQuery.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Date> getDatesInRange(Date startDate, Date endDate) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Date> query = cb.createQuery(Date.class);
		Root<MarketData> from = query.from(MarketData.class);
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		query.select(dateExpr);
		query.distinct(true);
		query.where(cb.greaterThanOrEqualTo(dateExpr, startDate),
				cb.lessThanOrEqualTo(dateExpr, endDate));
		query.orderBy(cb.asc(dateExpr));
		TypedQuery<Date> typedQuery = getEntityManager().createQuery(query);
		return typedQuery.getResultList();
	}
	
	@Override
	public Map<Integer, BigDecimal[]> getMaxHighMinLow(Collection<Integer> entityIds, Date startDate, Date endDate) {
		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Object> query = cb.createQuery();
		Root<MarketData> from = query.from(MarketData.class);
		Expression<BigDecimal> highPriceExpr = from.get("highPrice");
		Expression<BigDecimal> lowPriceExpr = from.get("lowPrice");
		Expression<Date> dateExpr = from.get("marketDataId").get("date");
		Expression<Integer> entityIdExpr = from.get("marketDataId").get("entityId");
		query.multiselect(entityIdExpr, cb.max(highPriceExpr), cb.min(lowPriceExpr));
		query.where(entityIdExpr.in(entityIds), cb.greaterThanOrEqualTo(dateExpr, startDate), cb.lessThanOrEqualTo(dateExpr, endDate));
		query.groupBy(entityIdExpr);

		List<Object> resultList = getEntityManager().createQuery(query).getResultList();
		Map<Integer, BigDecimal[]> map = new HashMap<Integer, BigDecimal[]>();
		Object[] objArr = null;
		for (Object row : resultList) {
			objArr = (Object[]) row;
			map.put((Integer) objArr[0], new BigDecimal[] { (BigDecimal) objArr[1], (BigDecimal) objArr[2] });
		}
		return map;
	}
	
	private BigDecimal calculatePivotPrice(List<Object> resultList) {
		
		Object[] objArr = null;
		BigDecimal pivotPrice = null;
		BigDecimal bdFour = new BigDecimal(4);
		for (Object row : resultList) {
			objArr = (Object[]) row;
			pivotPrice = BigDecimal.ZERO;
			for (int i = 0; i < 4; i++) {
				pivotPrice = pivotPrice.add(new BigDecimal((Double) objArr[i]));
			}
			pivotPrice = pivotPrice.divide(bdFour);
		}
		return pivotPrice;
	}	
	
	private Map<Integer, MarketData> getEntityIdMarketDataMap(
			List<MarketData> mdList) {

		Map<Integer, MarketData> map = new HashMap<Integer, MarketData>();
		for (MarketData md : mdList) {
			map.put(md.getMarketDataId().getEntityId(), md);
		}
		return map;
	}
}
