package com.ramlabs.hms.dao.hibernate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.ramlabs.hms.dao.VisitDao;
import com.ramlabs.hms.entity.Visit;
import com.ramlabs.hms.entity.enums.PaymentStatus;
import com.ramlabs.hms.entity.enums.ServiceType;
import com.ramlabs.hms.entity.enums.VisitCategory;
import com.ramlabs.hms.entity.enums.VisitType;
import com.ramlabs.hms.exception.HMSDaoException;
import com.ramlabs.hms.exception.HMSErrorCode;
import com.ramlabs.hms.vo.PaymentStatusVO;
import com.ramlabs.hms.vo.ServiceTypeVO;
import com.ramlabs.hms.vo.VisitByServiceSearchResultVO;
import com.ramlabs.hms.vo.VisitByServiceSearchVO;
import com.ramlabs.hms.vo.VisitCategoryVO;
import com.ramlabs.hms.vo.VisitSearchVO;
import com.ramlabs.hms.vo.VisitServiceLineItemVO;
import com.ramlabs.hms.vo.VisitTypeVO;

public class VisitHibernateDao extends BaseHibernateDao<Visit, Long> implements
		VisitDao {
	private static final String GET_VISITS_BY_SERVICE = "select v.id, v.visitDate, v.category, v.visitType, "
			+ "s.type, s.serviceReference, s.description, s.actualRate, s.quantity, s.amount, "
			+ "b.billNumber, b.id, p.firstName, p.lastName from Visit v, Service s, Billing b, Patient p "
			+ "where {0} s.visit.id = v.id and b.visit.id = v.id and p.id = v.patient.id and "
			+ "b.id = (select max(bl.id) from Billing bl where bl.visit.id = b.visit.id) order by s.type ASC, s.serviceReference ASC, v.visitDate DESC ";

	@SuppressWarnings("unchecked")
	@Override
	public List<Visit> find(VisitSearchVO visitSearchVO) throws HMSDaoException {
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);

			if (notNullAndNotEmpty(visitSearchVO.getHospitalNumber())) {
				DetachedCriteria patientCriteria = criteria
						.createCriteria("patient");
				addLikeCriteria(patientCriteria, "hospitalNumber",
						visitSearchVO.getHospitalNumber());
			}

			if (visitSearchVO.getCategory() != VisitCategoryVO.ALL) {
				VisitCategory category = Enum.valueOf(VisitCategory.class,
						visitSearchVO.getCategory().name());
				addEqualCriteria(criteria, "category", category);
			}

			if (visitSearchVO.getVisitType() != VisitTypeVO.ALL) {
				VisitType visitType = Enum.valueOf(VisitType.class,
						visitSearchVO.getVisitType().name());
				addEqualCriteria(criteria, "visitType", visitType);
			}
			if (visitSearchVO.getPaymentStatus() != PaymentStatusVO.ALL) {
				PaymentStatus paymentStatus = Enum.valueOf(PaymentStatus.class,
						visitSearchVO.getPaymentStatus().name());
				addEqualCriteria(criteria, "paymentStatus", paymentStatus);
			}

			if ((visitSearchVO.getVisitFromDate() != null)
					&& (visitSearchVO.getVisitToDate() != null)) {
				addRangeCriteria(criteria, "visitDate",
						visitSearchVO.getVisitFromDate(),
						visitSearchVO.getVisitToDate());
			} else if (visitSearchVO.getVisitFromDate() != null) {
				addGreaterThanEqualCriteria(criteria, "visitDate",
						visitSearchVO.getVisitFromDate());
			} else if (visitSearchVO.getVisitToDate() != null) {
				addLessThanEqualCriteria(criteria, "visitDate",
						visitSearchVO.getVisitToDate());
			}

			return (List<Visit>) getHibernateTemplate()
					.findByCriteria(criteria);

		} catch (Exception e) {
			throw new HMSDaoException(
					HMSErrorCode.DATA_ACCESS_EXCEPTION_CREATE, e.getMessage(),
					e);
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public VisitByServiceSearchResultVO getVisitsByService(
			VisitByServiceSearchVO visitByServiceSearchVO)
			throws HMSDaoException {
		try {
			String criteria = "";

			List<String> paramNames = new ArrayList<String>();
			List<Object> paramValues = new ArrayList<Object>();

			// category
			if (visitByServiceSearchVO.getCategory() != VisitCategoryVO.ALL) {
				VisitCategory category = Enum.valueOf(VisitCategory.class,
						visitByServiceSearchVO.getCategory().name());
				paramNames.add("category");
				paramValues.add(category);
				criteria += "(v.category = :category ) AND ";
			}

			// visit type
			if (visitByServiceSearchVO.getVisitType() != VisitTypeVO.ALL) {
				VisitType visitType = Enum.valueOf(VisitType.class,
						visitByServiceSearchVO.getVisitType().name());
				paramNames.add("visitType");
				paramValues.add(visitType);
				criteria += "(v.visitType = :visitType ) AND ";
			}

			// visit from date
			if (visitByServiceSearchVO.getVisitFromDate() != null) {
				paramNames.add("fromDate");
				paramValues.add(visitByServiceSearchVO.getVisitFromDate());
				criteria += "(v.visitDate >= :fromDate ) AND ";
			}

			// visit to date
			if (visitByServiceSearchVO.getVisitToDate() != null) {
				paramNames.add("toDate");
				paramValues.add(visitByServiceSearchVO.getVisitToDate());
				criteria += "(v.visitDate <= :toDate ) AND ";
			}

			// service type
			if (visitByServiceSearchVO.getServiceType() != ServiceTypeVO.ALL) {
				ServiceType serviceType = Enum.valueOf(ServiceType.class,
						visitByServiceSearchVO.getServiceType().name());
				paramNames.add("serviceType");
				paramValues.add(serviceType);
				criteria += "(s.type = :serviceType ) AND ";
			}

			String queryString = MessageFormat.format(GET_VISITS_BY_SERVICE,
					criteria);
			List list = getHibernateTemplate().findByNamedParam(queryString,
					paramNames.toArray(new String[0]), paramValues.toArray());
			if ((list != null) && (list.size() > 0)) {
				VisitByServiceSearchResultVO results = new VisitByServiceSearchResultVO();
				Map<String, List<VisitServiceLineItemVO>> groupLineItems = new LinkedHashMap<String, List<VisitServiceLineItemVO>>();
				Map<String, Double> groupAmountSummary = new HashMap<String, Double>();
				results.setVisitServiceGroup(groupLineItems);
				results.setServiceAmountSum(groupAmountSummary);

				for (int i = 0; i < list.size(); i++) {
					Object[] rowVector = (Object[]) list.get(i);
					VisitServiceLineItemVO vo = new VisitServiceLineItemVO();
					vo.setVisitId((Long) rowVector[0]);
					vo.setVisitDate((Date) rowVector[1]);
					vo.setCategory(((VisitCategory) rowVector[2]).name());
					vo.setVisitType(((VisitType) rowVector[3]).name());
					vo.setServiceType((ServiceType) rowVector[4]);
					vo.setServiceReference((Long) rowVector[5]);
					vo.setServiceDescription((String) rowVector[6]);
					vo.setActualRate((Float) rowVector[7]);
					vo.setQuantity((Float) rowVector[8]);
					vo.setAmount((Float) rowVector[9]);
					vo.setBillNumber((String) rowVector[10]);
					vo.setBillingId((Long) rowVector[11]);
					vo.setPatientName(getNotNullString((String) rowVector[12])
							+ " " + getNotNullString((String) rowVector[13]));

					if (groupLineItems.containsKey(vo.getServiceDescription())) {
						List<VisitServiceLineItemVO> lineItems = groupLineItems
								.get(vo.getServiceDescription());
						lineItems.add(vo);

						Double amountSummary = groupAmountSummary.get(vo
								.getServiceDescription()) + vo.getAmount();
						groupAmountSummary.put(vo.getServiceDescription(),
								amountSummary);
					} else {
						List<VisitServiceLineItemVO> lineItems = new ArrayList<VisitServiceLineItemVO>();
						lineItems.add(vo);
						groupLineItems.put(vo.getServiceDescription(),
								lineItems);

						groupAmountSummary.put(vo.getServiceDescription(),
								Double.valueOf(vo.getAmount()));
					}
				}
				return results;
			}
		} catch (Exception e) {
			throw new HMSDaoException(
					HMSErrorCode.ERROR_FETCHING_VISITS_BY_SERVICE,
					e.getMessage(), e);
		}
		return null;
	}

	private String getNotNullString(String str) {
		return (str != null) ? str : "";
	}
}
