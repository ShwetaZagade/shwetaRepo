package com.app.server.repository.organizationextendedbc.locationextended;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.organizationextendedbc.locationextended.District;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import javax.persistence.EntityManager;
import java.lang.Override;
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Repository for District Master table Entity", complexity = Complexity.LOW)
public class DistrictRepositoryImpl extends SearchInterfaceImpl implements DistrictRepository<District> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    private LogManager Log = LogManagerFactory.getInstance(AppLoggerConstant.LOGGER_ID);

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * Method for fetching list of entities
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<District> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<District> query = emanager.createNamedQuery("District.findAll").getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistrictRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <District> object.
     * @return District
     * @Params Object of District
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public District save(District entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("OEBLE322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistrictRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <District> object.
     * @return java.util.List<District>
     * @Params list of District
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<District> save(List<District> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            District obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("OEBLE322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistrictRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <District> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        District object = emanager.find(com.app.shared.organizationextendedbc.locationextended.District.class, id);
        emanager.remove(object);
        Log.out.println("OEBLE328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistrictRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <District> object.
     * @Params Object of District
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(District entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("OEBLE321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistrictRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <District> object.
     * @Params list of District
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<District> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            District obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("OEBLE321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistrictRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Returns list of objects.
     * @Params findername,Map of fields,Map of fieldMetadata
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Object> search(String finderName, Map<String, Object> fields, Map<String, String> fieldMetaData) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        javax.persistence.Query query = emanager.createNamedQuery(finderName);
        java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
        Map<String, String> metaData = new java.util.HashMap<String, String>();
        metaData = fieldMetaData;
        String inputStr = "01-01-1850 00:00:00 UTC";
        java.util.Date date = setFormattedDate(inputStr);
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        for (Map.Entry<String, String> entry : metaData.entrySet()) {
            boolean matched = false;
            for (Map.Entry<String, Object> entry1 : fields.entrySet()) {
                if (entry.getKey() == entry1.getKey()) {
                    if (entry.getValue().equalsIgnoreCase("integer") || entry.getValue().equalsIgnoreCase("double") || entry.getValue().equalsIgnoreCase("float") || entry.getValue().equalsIgnoreCase("long")) {
                        map.put("min" + entry1.getKey(), entry1.getValue());
                        map.put("max" + entry1.getKey(), entry1.getValue());
                    } else if (entry.getValue().equalsIgnoreCase("String")) {
                        map.put(entry1.getKey(), "%" + entry1.getValue() + "%");
                    } else if (entry.getValue().equalsIgnoreCase("Date") || entry.getValue().equalsIgnoreCase("DateTime")) {
                        java.util.Date dateValue = setFormattedDate(entry1.getValue().toString());
                        map.put(entry1.getKey(), dateValue);
                    } else if (entry.getValue().equalsIgnoreCase("TimeStamp")) {
                        java.util.Date dateValue = setFormattedDate(entry1.getValue().toString());
                        map.put(entry1.getKey(), new java.sql.Timestamp(dateValue.getTime()));
                    } else {
                        map.put(entry1.getKey(), entry1.getValue());
                    }
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                if (entry.getValue().equalsIgnoreCase("String")) {
                    map.put(entry.getKey(), "%");
                } else if (entry.getValue().equalsIgnoreCase("integer")) {
                    map.put("min" + entry.getKey(), Integer.MIN_VALUE);
                    map.put("max" + entry.getKey(), Integer.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("double")) {
                    map.put("min" + entry.getKey(), java.lang.Double.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Double.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("long")) {
                    map.put("min" + entry.getKey(), java.lang.Long.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Long.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("float")) {
                    map.put("min" + entry.getKey(), java.lang.Float.MIN_VALUE);
                    map.put("max" + entry.getKey(), java.lang.Float.MAX_VALUE);
                } else if (entry.getValue().equalsIgnoreCase("Date") || entry.getValue().equalsIgnoreCase("DATETIME")) {
                    map.put(entry.getKey(), date);
                } else if (entry.getValue().equalsIgnoreCase("TINYINT")) {
                    map.put(entry.getKey(), 1);
                } else if (entry.getValue().equalsIgnoreCase("timestamp")) {
                    map.put(entry.getKey(), timestamp);
                } else if (entry.getValue().equalsIgnoreCase("integer_userAccesCode")) {
                    map.put(entry.getKey(), runtimeLogInfoHelper.getUserAccessCode());
                }
            }
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Object> list = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistrictRepositoryImpl", "search", "Total Records Fetched = " + list.size());
        return list;
    }

    /**
     * Return list of District objects by filtering on refernce key <countryId>
     * @return List<District>
     * @Params countryId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<District> findByCountryId(String countryId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("District.findByCountryId");
        query.setParameter("countryId", countryId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.District> listOfDistrict = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistrictRepositoryImpl", "findByCountryId", "Total Records Fetched = " + listOfDistrict.size());
        return listOfDistrict;
    }

    /**
     * Return list of District objects by filtering on refernce key <stateId>
     * @return List<District>
     * @Params stateId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<District> findByStateId(String stateId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("District.findByStateId");
        query.setParameter("stateId", stateId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.District> listOfDistrict = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistrictRepositoryImpl", "findByStateId", "Total Records Fetched = " + listOfDistrict.size());
        return listOfDistrict;
    }

    /**
     * Return list of District objects by filtering on refernce key <regionId>
     * @return List<District>
     * @Params regionId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<District> findByRegionId(String regionId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("District.findByRegionId");
        query.setParameter("regionId", regionId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.District> listOfDistrict = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistrictRepositoryImpl", "findByRegionId", "Total Records Fetched = " + listOfDistrict.size());
        return listOfDistrict;
    }

    /**
     * Return District object by filtering on refernce key <districtId>
     * @return District
     * @Params districtId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public District findById(String districtId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("District.findById");
        query.setParameter("districtId", districtId);
        District listOfDistrict = (District) query.getSingleResult();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "DistrictRepositoryImpl", "findById", "Total Records Fetched = " + listOfDistrict);
        return listOfDistrict;
    }
}
