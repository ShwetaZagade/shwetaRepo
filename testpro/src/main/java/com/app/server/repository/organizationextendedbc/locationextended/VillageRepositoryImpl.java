package com.app.server.repository.organizationextendedbc.locationextended;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.organizationextendedbc.locationextended.Village;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Repository for Village Master table Entity", complexity = Complexity.LOW)
public class VillageRepositoryImpl extends SearchInterfaceImpl implements VillageRepository<Village> {

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
    public List<Village> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Village> query = emanager.createNamedQuery("Village.findAll").getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Village> object.
     * @return Village
     * @Params Object of Village
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Village save(Village entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("OEBLE322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Village> object.
     * @return java.util.List<Village>
     * @Params list of Village
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Village> save(List<Village> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            Village obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("OEBLE322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Village> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Village object = emanager.find(com.app.shared.organizationextendedbc.locationextended.Village.class, id);
        emanager.remove(object);
        Log.out.println("OEBLE328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Village> object.
     * @Params Object of Village
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Village entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("OEBLE321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Village> object.
     * @Params list of Village
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Village> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            Village obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("OEBLE321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of Village objects by filtering on refernce key <countryId>
     * @return List<Village>
     * @Params countryId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Village> findByCountryId(String countryId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Village.findByCountryId");
        query.setParameter("countryId", countryId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.Village> listOfVillage = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findByCountryId", "Total Records Fetched = " + listOfVillage.size());
        return listOfVillage;
    }

    /**
     * Return list of Village objects by filtering on refernce key <stateId>
     * @return List<Village>
     * @Params stateId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Village> findByStateId(String stateId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Village.findByStateId");
        query.setParameter("stateId", stateId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.Village> listOfVillage = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findByStateId", "Total Records Fetched = " + listOfVillage.size());
        return listOfVillage;
    }

    /**
     * Return list of Village objects by filtering on refernce key <regionId>
     * @return List<Village>
     * @Params regionId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Village> findByRegionId(String regionId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Village.findByRegionId");
        query.setParameter("regionId", regionId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.Village> listOfVillage = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findByRegionId", "Total Records Fetched = " + listOfVillage.size());
        return listOfVillage;
    }

    /**
     * Return list of Village objects by filtering on refernce key <districtId>
     * @return List<Village>
     * @Params districtId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Village> findByDistrictId(String districtId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Village.findByDistrictId");
        query.setParameter("districtId", districtId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.Village> listOfVillage = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findByDistrictId", "Total Records Fetched = " + listOfVillage.size());
        return listOfVillage;
    }

    /**
     * Return list of Village objects by filtering on refernce key <talukaaId>
     * @return List<Village>
     * @Params talukaaId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Village> findByTalukaaId(String talukaaId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Village.findByTalukaaId");
        query.setParameter("talukaaId", talukaaId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.Village> listOfVillage = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findByTalukaaId", "Total Records Fetched = " + listOfVillage.size());
        return listOfVillage;
    }

    /**
     * Return Village object by filtering on refernce key <villageId>
     * @return Village
     * @Params villageId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Village findById(String villageId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Village.findById");
        query.setParameter("villageId", villageId);
        Village listOfVillage = (Village) query.getSingleResult();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "VillageRepositoryImpl", "findById", "Total Records Fetched = " + listOfVillage);
        return listOfVillage;
    }
}
