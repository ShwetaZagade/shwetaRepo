package com.app.server.repository.appinsight.alarms;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.appinsight.alarms.AEntity;
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
import com.app.shared.appinsight.health.TestA;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "7", comments = "Repository for AEntity Transaction table", complexity = Complexity.MEDIUM)
public class AEntityRepositoryImpl extends SearchInterfaceImpl implements AEntityRepository<AEntity> {

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
    public List<AEntity> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        List<AEntity> query = emanager.createNamedQuery("AEntity.findAll").getResultList();
        Log.out.println("AISAL324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AEntityRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <AEntity> object.
     * @return AEntity
     * @Params Object of AEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public AEntity save(AEntity entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        java.util.List<com.app.shared.appinsight.health.TestA> testa = new java.util.ArrayList<com.app.shared.appinsight.health.TestA>();
        for (java.util.Iterator iterator = entity.getTestA().iterator(); iterator.hasNext(); ) {
            com.app.shared.appinsight.health.TestA childEntity = (com.app.shared.appinsight.health.TestA) iterator.next();
            if (childEntity.getPrimaryKey() != null) {
                com.app.shared.appinsight.health.TestA ans = emanager.find(TestA.class, childEntity.getPrimaryKey());
                testa.add(ans);
            } else {
                testa.add(childEntity);
            }
        }
        entity.setTestA(testa);
        emanager.persist(entity);
        Log.out.println("AISAL322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AEntityRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <AEntity> object.
     * @return java.util.List<AEntity>
     * @Params list of AEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<AEntity> save(List<AEntity> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            AEntity obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("AISAL322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AEntityRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <AEntity> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        AEntity object = emanager.find(com.app.shared.appinsight.alarms.AEntity.class, id);
        emanager.remove(object);
        Log.out.println("AISAL328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AEntityRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void deleteTestA(List<TestA> testa) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (com.app.shared.appinsight.health.TestA _testa : testa) {
            com.app.shared.appinsight.health.TestA s = emanager.find(com.app.shared.appinsight.health.TestA.class, _testa.getTid());
            emanager.remove(s);
        }
    }

    /**
     * Updates the <AEntity> object.
     * @Params Object of AEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(AEntity entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("AISAL321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AEntityRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <AEntity> object.
     * @Params list of AEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<AEntity> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            AEntity obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("AISAL321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AEntityRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return AEntity object by filtering on refernce key <aid>
     * @return AEntity
     * @Params aid of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public AEntity findById(String aid) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("AEntity.findById");
        query.setParameter("aid", aid);
        AEntity listOfAEntity = (AEntity) query.getSingleResult();
        Log.out.println("AISAL324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "AEntityRepositoryImpl", "findById", "Total Records Fetched = " + listOfAEntity);
        return listOfAEntity;
    }
}
