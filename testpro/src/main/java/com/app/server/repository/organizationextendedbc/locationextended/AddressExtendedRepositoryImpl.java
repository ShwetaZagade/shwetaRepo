package com.app.server.repository.organizationextendedbc.locationextended;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.organizationextendedbc.locationextended.AddressExtended;
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
import java.util.List;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import java.lang.Override;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Repository for AddressExtended Transaction table", complexity = Complexity.MEDIUM)
public class AddressExtendedRepositoryImpl extends SearchInterfaceImpl implements AddressExtendedRepository<AddressExtended> {

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
    public List<AddressExtended> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        List<AddressExtended> query = emanager.createNamedQuery("AddressExtended.findAll").getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <AddressExtended> object.
     * @return AddressExtended
     * @Params Object of AddressExtended
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public AddressExtended save(AddressExtended entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("OEBLE322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <AddressExtended> object.
     * @return java.util.List<AddressExtended>
     * @Params list of AddressExtended
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<AddressExtended> save(List<AddressExtended> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            AddressExtended obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("OEBLE322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <AddressExtended> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        AddressExtended object = emanager.find(com.app.shared.organizationextendedbc.locationextended.AddressExtended.class, id);
        emanager.remove(object);
        Log.out.println("OEBLE328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <AddressExtended> object.
     * @Params Object of AddressExtended
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(AddressExtended entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("OEBLE321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <AddressExtended> object.
     * @Params list of AddressExtended
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<AddressExtended> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            AddressExtended obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("OEBLE321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of AddressExtended objects by filtering on refernce key <addressId>
     * @return List<AddressExtended>
     * @Params addressId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<AddressExtended> findByAddressId(String addressId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("AddressExtended.findByAddressId");
        query.setParameter("addressId", addressId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.AddressExtended> listOfAddressExtended = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "findByAddressId", "Total Records Fetched = " + listOfAddressExtended.size());
        return listOfAddressExtended;
    }

    /**
     * Return list of AddressExtended objects by filtering on refernce key <villageId>
     * @return List<AddressExtended>
     * @Params villageId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<AddressExtended> findByVillageId(String villageId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("AddressExtended.findByVillageId");
        query.setParameter("villageId", villageId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.AddressExtended> listOfAddressExtended = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "findByVillageId", "Total Records Fetched = " + listOfAddressExtended.size());
        return listOfAddressExtended;
    }

    /**
     * Return list of AddressExtended objects by filtering on refernce key <talukaId>
     * @return List<AddressExtended>
     * @Params talukaId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<AddressExtended> findByTalukaId(String talukaId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("AddressExtended.findByTalukaId");
        query.setParameter("talukaId", talukaId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.AddressExtended> listOfAddressExtended = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "findByTalukaId", "Total Records Fetched = " + listOfAddressExtended.size());
        return listOfAddressExtended;
    }

    /**
     * Return list of AddressExtended objects by filtering on refernce key <districtId>
     * @return List<AddressExtended>
     * @Params districtId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<AddressExtended> findByDistrictId(String districtId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("AddressExtended.findByDistrictId");
        query.setParameter("districtId", districtId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.AddressExtended> listOfAddressExtended = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "findByDistrictId", "Total Records Fetched = " + listOfAddressExtended.size());
        return listOfAddressExtended;
    }

    /**
     * Return list of AddressExtended objects by filtering on refernce key <regionId>
     * @return List<AddressExtended>
     * @Params regionId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<AddressExtended> findByRegionId(String regionId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("AddressExtended.findByRegionId");
        query.setParameter("regionId", regionId);
        java.util.List<com.app.shared.organizationextendedbc.locationextended.AddressExtended> listOfAddressExtended = query.getResultList();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "findByRegionId", "Total Records Fetched = " + listOfAddressExtended.size());
        return listOfAddressExtended;
    }

    /**
     * Return AddressExtended object by filtering on refernce key <addExtId>
     * @return AddressExtended
     * @Params addExtId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public AddressExtended findById(String addExtId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("AddressExtended.findById");
        query.setParameter("addExtId", addExtId);
        AddressExtended listOfAddressExtended = (AddressExtended) query.getSingleResult();
        Log.out.println("OEBLE324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AddressExtendedRepositoryImpl", "findById", "Total Records Fetched = " + listOfAddressExtended);
        return listOfAddressExtended;
    }
}
