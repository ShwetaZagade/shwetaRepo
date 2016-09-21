package com.app.server.repository.sampleboundedcontext.sampledomain;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.sampleboundedcontext.sampledomain.Employee;
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
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "1", comments = "Repository for Employee Transaction table", complexity = Complexity.MEDIUM)
public class EmployeeRepositoryImpl extends SearchInterfaceImpl implements EmployeeRepository<Employee> {

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
    public List<Employee> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        List<Employee> query = emanager.createNamedQuery("Employee.findAll").getResultList();
        Log.out.println("YHHHH324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <Employee> object.
     * @return Employee
     * @Params Object of Employee
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public Employee save(Employee entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("YHHHH322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <Employee> object.
     * @return java.util.List<Employee>
     * @Params list of Employee
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<Employee> save(List<Employee> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            Employee obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("YHHHH322100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <Employee> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Employee object = emanager.find(com.app.shared.sampleboundedcontext.sampledomain.Employee.class, id);
        emanager.remove(object);
        Log.out.println("YHHHH328100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <Employee> object.
     * @Params Object of Employee
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(Employee entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("YHHHH321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <Employee> object.
     * @Params list of Employee
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<Employee> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            Employee obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("YHHHH321100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return Employee object by filtering on refernce key <empId>
     * @return Employee
     * @Params empId of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public Employee findById(String empId) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("Employee.findById");
        query.setParameter("empId", empId);
        Employee listOfEmployee = (Employee) query.getSingleResult();
        Log.out.println("YHHHH324100200", runtimeLogInfoHelper.getRequestHeaderBean(), "EmployeeRepositoryImpl", "findById", "Total Records Fetched = " + listOfEmployee);
        return listOfEmployee;
    }
}
