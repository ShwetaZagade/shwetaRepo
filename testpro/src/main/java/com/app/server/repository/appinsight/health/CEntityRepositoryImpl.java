package com.app.server.repository.appinsight.health;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.appinsight.health.CEntity;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "Repository for CEntity Transaction table", complexity = Complexity.MEDIUM)
public class CEntityRepositoryImpl extends SearchInterfaceImpl implements CEntityRepository<CEntity> {

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
    public List<CEntity> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        List<CEntity> query = emanager.createNamedQuery("CEntity.findAll").getResultList();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "CEntityRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <CEntity> object.
     * @return CEntity
     * @Params Object of CEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public CEntity save(CEntity entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "CEntityRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <CEntity> object.
     * @return java.util.List<CEntity>
     * @Params list of CEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<CEntity> save(List<CEntity> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            CEntity obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "CEntityRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <CEntity> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        CEntity object = emanager.find(com.app.shared.appinsight.health.CEntity.class, id);
        emanager.remove(object);
        Log.out.println("AISHI328900200", runtimeLogInfoHelper.getRequestHeaderBean(), "CEntityRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <CEntity> object.
     * @Params Object of CEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(CEntity entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "CEntityRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <CEntity> object.
     * @Params list of CEntity
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<CEntity> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            CEntity obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "CEntityRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return CEntity object by filtering on refernce key <cid>
     * @return CEntity
     * @Params cid of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public CEntity findById(String cid) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("CEntity.findById");
        query.setParameter("cid", cid);
        CEntity listOfCEntity = (CEntity) query.getSingleResult();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "CEntityRepositoryImpl", "findById", "Total Records Fetched = " + listOfCEntity);
        return listOfCEntity;
    }
}
