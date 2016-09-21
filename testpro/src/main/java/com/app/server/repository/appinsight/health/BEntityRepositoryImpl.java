package com.app.server.repository.appinsight.health;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.appinsight.health.BEntity;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "Repository for BEntity Transaction table", complexity = Complexity.MEDIUM)
public class BEntityRepositoryImpl extends SearchInterfaceImpl implements BEntityRepository<BEntity> {

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
    public List<BEntity> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        List<BEntity> query = emanager.createNamedQuery("BEntity.findAll").getResultList();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BEntityRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <BEntity> object.
     * @return BEntity
     * @Params Object of BEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public BEntity save(BEntity entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BEntityRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <BEntity> object.
     * @return java.util.List<BEntity>
     * @Params list of BEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<BEntity> save(List<BEntity> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            BEntity obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BEntityRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <BEntity> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        BEntity object = emanager.find(com.app.shared.appinsight.health.BEntity.class, id);
        emanager.remove(object);
        Log.out.println("AISHI328900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BEntityRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <BEntity> object.
     * @Params Object of BEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(BEntity entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BEntityRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <BEntity> object.
     * @Params list of BEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<BEntity> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            BEntity obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BEntityRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return BEntity object by filtering on refernce key <bid>
     * @return BEntity
     * @Params bid of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public BEntity findById(String bid) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("BEntity.findById");
        query.setParameter("bid", bid);
        BEntity listOfBEntity = (BEntity) query.getSingleResult();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "BEntityRepositoryImpl", "findById", "Total Records Fetched = " + listOfBEntity);
        return listOfBEntity;
    }
}
