package com.app.server.repository.airlineboundedcontext.reservedomain;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.airlineboundedcontext.reservedomain.Passanger;
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
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "5", comments = "Repository for Passanger Transaction table", complexity = Complexity.MEDIUM)
public class PassangerRepositoryImpl extends SearchInterfaceImpl implements PassangerRepository<Passanger> {

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
    public List<Passanger> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        List<Passanger> query = emanager.createNamedQuery("Passanger.findAll").getResultList();
        Log.out.println("ABCHU324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PassangerRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Passanger> object.
     * @return Passanger
     * @Params Object of Passanger
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Passanger save(Passanger entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("ABCHU322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PassangerRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Passanger> object.
     * @return java.util.List<Passanger>
     * @Params list of Passanger
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Passanger> save(List<Passanger> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            Passanger obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("ABCHU322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PassangerRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Passanger> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Passanger object = emanager.find(com.app.shared.airlineboundedcontext.reservedomain.Passanger.class, id);
        emanager.remove(object);
        Log.out.println("ABCHU328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PassangerRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Passanger> object.
     * @Params Object of Passanger
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Passanger entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("ABCHU321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PassangerRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Passanger> object.
     * @Params list of Passanger
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Passanger> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            Passanger obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("ABCHU321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PassangerRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return list of Passanger objects by filtering on refernce key <passangerAddr>
     * @return List<Passanger>
     * @Params passangerAddr of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public List<Passanger> findByPassangerAddr(String passangerAddr) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Passanger.findByPassangerAddr");
        query.setParameter("passangerAddr", passangerAddr);
        java.util.List<com.app.shared.airlineboundedcontext.reservedomain.Passanger> listOfPassanger = query.getResultList();
        Log.out.println("ABCHU324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PassangerRepositoryImpl", "findByPassangerAddr", "Total Records Fetched = " + listOfPassanger.size());
        return listOfPassanger;
    }

    /**
     * Return Passanger object by filtering on refernce key <passangerId>
     * @return Passanger
     * @Params passangerId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Passanger findById(String passangerId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Passanger.findById");
        query.setParameter("passangerId", passangerId);
        Passanger listOfPassanger = (Passanger) query.getSingleResult();
        Log.out.println("ABCHU324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "PassangerRepositoryImpl", "findById", "Total Records Fetched = " + listOfPassanger);
        return listOfPassanger;
    }
}
