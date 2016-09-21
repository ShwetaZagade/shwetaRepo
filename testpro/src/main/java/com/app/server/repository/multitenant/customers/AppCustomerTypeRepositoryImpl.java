package com.app.server.repository.multitenant.customers;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.multitenant.customers.AppCustomerType;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Repository for AppCustomerType Master table Entity", complexity = Complexity.LOW)
public class AppCustomerTypeRepositoryImpl extends SearchInterfaceImpl implements AppCustomerTypeRepository<AppCustomerType> {

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
    public List<AppCustomerType> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<AppCustomerType> query = emanager.createNamedQuery("AppCustomerType.findAll").getResultList();
        Log.out.println("MBCCU324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerTypeRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <AppCustomerType> object.
     * @return AppCustomerType
     * @Params Object of AppCustomerType
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public AppCustomerType save(AppCustomerType entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("MBCCU322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerTypeRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <AppCustomerType> object.
     * @return java.util.List<AppCustomerType>
     * @Params list of AppCustomerType
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<AppCustomerType> save(List<AppCustomerType> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            AppCustomerType obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("MBCCU322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerTypeRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <AppCustomerType> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        AppCustomerType object = emanager.find(com.app.shared.multitenant.customers.AppCustomerType.class, id);
        emanager.remove(object);
        Log.out.println("MBCCU328900200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerTypeRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <AppCustomerType> object.
     * @Params Object of AppCustomerType
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(AppCustomerType entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("MBCCU321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerTypeRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <AppCustomerType> object.
     * @Params list of AppCustomerType
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<AppCustomerType> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            AppCustomerType obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("MBCCU321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerTypeRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
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
        Log.out.println("MBCCU324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerTypeRepositoryImpl", "search", "Total Records Fetched = " + list.size());
        return list;
    }

    /**
     * Return AppCustomerType object by filtering on refernce key <appcustTypeId>
     * @return AppCustomerType
     * @Params appcustTypeId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public AppCustomerType findById(String appcustTypeId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("AppCustomerType.findById");
        query.setParameter("appcustTypeId", appcustTypeId);
        AppCustomerType listOfAppCustomerType = (AppCustomerType) query.getSingleResult();
        Log.out.println("MBCCU324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "AppCustomerTypeRepositoryImpl", "findById", "Total Records Fetched = " + listOfAppCustomerType);
        return listOfAppCustomerType;
    }
}
