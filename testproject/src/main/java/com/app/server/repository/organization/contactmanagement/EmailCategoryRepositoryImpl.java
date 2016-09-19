package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.organization.contactmanagement.EmailCategory;
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
@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "3", comments = "Repository for EmailCategory Master table Entity", complexity = Complexity.LOW)
public class EmailCategoryRepositoryImpl extends SearchInterfaceImpl implements EmailCategoryRepository<EmailCategory> {

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
    public List<EmailCategory> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<EmailCategory> query = emanager.createNamedQuery("EmailCategory.findAll").getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCategoryRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <EmailCategory> object.
     * @return EmailCategory
     * @Params Object of EmailCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public EmailCategory save(EmailCategory entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCategoryRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <EmailCategory> object.
     * @return java.util.List<EmailCategory>
     * @Params list of EmailCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<EmailCategory> save(List<EmailCategory> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            EmailCategory obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCategoryRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <EmailCategory> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        EmailCategory object = emanager.find(com.app.shared.organization.contactmanagement.EmailCategory.class, id);
        emanager.remove(object);
        Log.out.println("ORGCM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCategoryRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <EmailCategory> object.
     * @Params Object of EmailCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(EmailCategory entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCategoryRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <EmailCategory> object.
     * @Params list of EmailCategory
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<EmailCategory> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            EmailCategory obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCategoryRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return EmailCategory object by filtering on refernce key <emailCatId>
     * @return EmailCategory
     * @Params emailCatId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public EmailCategory findById(String emailCatId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("EmailCategory.findById");
        query.setParameter("emailCatId", emailCatId);
        EmailCategory listOfEmailCategory = (EmailCategory) query.getSingleResult();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCategoryRepositoryImpl", "findById", "Total Records Fetched = " + listOfEmailCategory);
        return listOfEmailCategory;
    }
}
