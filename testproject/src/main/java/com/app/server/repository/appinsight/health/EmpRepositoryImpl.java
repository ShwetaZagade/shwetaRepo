package com.app.server.repository.appinsight.health;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.appinsight.health.Emp;
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
import com.app.shared.appinsight.health.EmpDEpt;

@Repository
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "4", comments = "Repository for Emp Transaction table", complexity = Complexity.MEDIUM)
public class EmpRepositoryImpl extends SearchInterfaceImpl implements EmpRepository<Emp> {

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
    public List<Emp> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource();
        List<Emp> query = emanager.createNamedQuery("Emp.findAll").getResultList();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmpRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Emp> object.
     * @return Emp
     * @Params Object of Emp
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Emp save(Emp entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        emanager.persist(entity);
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmpRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Emp> object.
     * @return java.util.List<Emp>
     * @Params list of Emp
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Emp> save(List<Emp> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Emp obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmpRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Emp> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Emp object = emanager.find(com.app.shared.appinsight.health.Emp.class, id);
        emanager.remove(object);
        Log.out.println("AISHI328900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmpRepositoryImpl", "delete", "Record Deleted");
    }

    @Override
    @Transactional
    public void deleteEmpDEpt(List<EmpDEpt> empdept) {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        for (com.app.shared.appinsight.health.EmpDEpt _empdept : empdept) {
            com.app.shared.appinsight.health.EmpDEpt s = emanager.find(com.app.shared.appinsight.health.EmpDEpt.class, _empdept.getDeptId());
            emanager.remove(s);
        }
    }

    /**
     * Updates the <Emp> object.
     * @Params Object of Emp
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Emp entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource();
        emanager.merge(entity);
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmpRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Emp> object.
     * @Params list of Emp
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Emp> entity) throws Exception {
        EntityManager emanager = emfResource.getResource();
        for (int i = 0; i < entity.size(); i++) {
            Emp obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmpRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Emp object by filtering on refernce key <empId>
     * @return Emp
     * @Params empId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Emp findById(String empId) throws Exception {
        EntityManager emanager = emfResource.getResource();
        Query query = emanager.createNamedQuery("Emp.findById");
        query.setParameter("empId", empId);
        Emp listOfEmp = (Emp) query.getSingleResult();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmpRepositoryImpl", "findById", "Total Records Fetched = " + listOfEmp);
        return listOfEmp;
    }
}
