package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.organization.contactmanagement.SocialCategory;
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
@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "3", comments = "Repository for SocialCategory Master table Entity", complexity = Complexity.LOW)
public class SocialCategoryRepositoryImpl extends SearchInterfaceImpl implements SocialCategoryRepository<SocialCategory> {

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
    public List<SocialCategory> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<SocialCategory> query = emanager.createNamedQuery("SocialCategory.findAll").getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <SocialCategory> object.
     * @return SocialCategory
     * @Params Object of SocialCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public SocialCategory save(SocialCategory entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <SocialCategory> object.
     * @return java.util.List<SocialCategory>
     * @Params list of SocialCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<SocialCategory> save(List<SocialCategory> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            SocialCategory obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <SocialCategory> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        SocialCategory object = emanager.find(com.app.shared.organization.contactmanagement.SocialCategory.class, id);
        emanager.remove(object);
        Log.out.println("ORGCM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <SocialCategory> object.
     * @Params Object of SocialCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(SocialCategory entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <SocialCategory> object.
     * @Params list of SocialCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<SocialCategory> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            SocialCategory obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return SocialCategory object by filtering on refernce key <socialCatId>
     * @return SocialCategory
     * @Params socialCatId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public SocialCategory findById(String socialCatId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("SocialCategory.findById");
        query.setParameter("socialCatId", socialCatId);
        SocialCategory listOfSocialCategory = (SocialCategory) query.getSingleResult();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "SocialCategoryRepositoryImpl", "findById", "Total Records Fetched = " + listOfSocialCategory);
        return listOfSocialCategory;
    }
}
