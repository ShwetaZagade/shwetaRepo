package com.app.server.repository.organization.contactmanagement;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.organization.contactmanagement.EmailCommunication;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Repository for EmailCommunication Transaction table", complexity = Complexity.MEDIUM)
public class EmailCommunicationRepositoryImpl extends SearchInterfaceImpl implements EmailCommunicationRepository<EmailCommunication> {

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
    public List<EmailCommunication> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        List<EmailCommunication> query = emanager.createNamedQuery("EmailCommunication.findAll").getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <EmailCommunication> object.
     * @return EmailCommunication
     * @Params Object of EmailCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public EmailCommunication save(EmailCommunication entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <EmailCommunication> object.
     * @return java.util.List<EmailCommunication>
     * @Params list of EmailCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<EmailCommunication> save(List<EmailCommunication> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            EmailCommunication obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ORGCM322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <EmailCommunication> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        EmailCommunication object = emanager.find(com.app.shared.organization.contactmanagement.EmailCommunication.class, id);
        emanager.remove(object);
        Log.out.println("ORGCM328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <EmailCommunication> object.
     * @Params Object of EmailCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(EmailCommunication entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <EmailCommunication> object.
     * @Params list of EmailCommunication
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<EmailCommunication> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            EmailCommunication obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ORGCM321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of EmailCommunication objects by filtering on refernce key <commType>
     * @return List<EmailCommunication>
     * @Params commType of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<EmailCommunication> findByCommType(String commType) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("EmailCommunication.findByCommType");
        query.setParameter("commType", commType);
        java.util.List<com.app.shared.organization.contactmanagement.EmailCommunication> listOfEmailCommunication = query.getResultList();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "findByCommType", "Total Records Fetched = " + listOfEmailCommunication.size());
        return listOfEmailCommunication;
    }

    /**
     * Return EmailCommunication object by filtering on refernce key <emailCommId>
     * @return EmailCommunication
     * @Params emailCommId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public EmailCommunication findById(String emailCommId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("EmailCommunication.findById");
        query.setParameter("emailCommId", emailCommId);
        EmailCommunication listOfEmailCommunication = (EmailCommunication) query.getSingleResult();
        Log.out.println("ORGCM324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmailCommunicationRepositoryImpl", "findById", "Total Records Fetched = " + listOfEmailCommunication);
        return listOfEmailCommunication;
    }
}
