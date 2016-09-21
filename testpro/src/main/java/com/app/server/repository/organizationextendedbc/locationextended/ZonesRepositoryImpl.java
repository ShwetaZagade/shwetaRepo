package com.app.server.repository.organizationextendedbc.locationextended;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.organizationextendedbc.locationextended.Zones;
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

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Repository for Zones Master table Entity", complexity = Complexity.LOW)
public class ZonesRepositoryImpl extends SearchInterfaceImpl implements ZonesRepository<Zones> {

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
    public List<Zones> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Zones> query = emanager.createNamedQuery("Zones.findAll").getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ZonesRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Zones> object.
     * @return Zones
     * @Params Object of Zones
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Zones save(Zones entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("OEBLE322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ZonesRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Zones> object.
     * @return java.util.List<Zones>
     * @Params list of Zones
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Zones> save(List<Zones> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            Zones obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("OEBLE322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ZonesRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Zones> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Zones object = emanager.find(com.app.shared.organizationextendedbc.locationextended.Zones.class, id);
        emanager.remove(object);
        Log.out.println("OEBLE328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ZonesRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Zones> object.
     * @Params Object of Zones
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Zones entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("OEBLE321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ZonesRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Zones> object.
     * @Params list of Zones
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Zones> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            Zones obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("OEBLE321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ZonesRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of Zones objects by filtering on refernce key <stateId>
     * @return List<Zones>
     * @Params stateId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Zones> findByStateId(String stateId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Zones.findByStateId");
        query.setParameter("stateId", stateId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.Zones> listOfZones = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ZonesRepositoryImpl", "findByStateId", "Total Records Fetched = " + listOfZones.size());
        return listOfZones;
    }

    /**
     * Return list of Zones objects by filtering on refernce key <countryId>
     * @return List<Zones>
     * @Params countryId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Zones> findByCountryId(String countryId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Zones.findByCountryId");
        query.setParameter("countryId", countryId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.Zones> listOfZones = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ZonesRepositoryImpl", "findByCountryId", "Total Records Fetched = " + listOfZones.size());
        return listOfZones;
    }

    /**
     * Return Zones object by filtering on refernce key <zoneId>
     * @return Zones
     * @Params zoneId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Zones findById(String zoneId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Zones.findById");
        query.setParameter("zoneId", zoneId);
        Zones listOfZones = (Zones) query.getSingleResult();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "ZonesRepositoryImpl", "findById", "Total Records Fetched = " + listOfZones);
        return listOfZones;
    }
}
