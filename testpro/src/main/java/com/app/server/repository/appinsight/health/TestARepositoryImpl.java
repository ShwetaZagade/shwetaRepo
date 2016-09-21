package com.app.server.repository.appinsight.health;
import com.app.server.repository.core.SearchInterfaceImpl;
import com.app.shared.appinsight.health.TestA;
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
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "Repository for TestA Transaction table", complexity = Complexity.MEDIUM)
public class TestARepositoryImpl extends SearchInterfaceImpl implements TestARepository<TestA> {

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
    public List<TestA> findAll() throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        List<TestA> query = emanager.createNamedQuery("TestA.findAll").getResultList();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestARepositoryImpl", "findAll", "Total Records Fetched = " + query.size());
        return query;
    }

    /**
     * Saves the new  <TestA> object.
     * @return TestA
     * @Params Object of TestA
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public TestA save(TestA entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.persist(entity);
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestARepositoryImpl", "save", entity);
        return entity;
    }

    /**
     * Saves the list of new <TestA> object.
     * @return java.util.List<TestA>
     * @Params list of TestA
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public List<TestA> save(List<TestA> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            TestA obj = entity.get(i);
            emanager.persist(obj);
        }
        Log.out.println("AISHI322900200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestARepositoryImpl", "saveAll", "Total Records saved = " + entity.size());
        return entity;
    }

    /**
     * Deletes the <TestA> object.
     * @Params String id
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void delete(String id) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        TestA object = emanager.find(com.app.shared.appinsight.health.TestA.class, id);
        emanager.remove(object);
        Log.out.println("AISHI328900200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestARepositoryImpl", "delete", "Record Deleted");
    }

    /**
     * Updates the <TestA> object.
     * @Params Object of TestA
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(TestA entity) throws Exception {
        javax.persistence.EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        emanager.merge(entity);
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestARepositoryImpl", "update", entity);
    }

    /**
     * Updates the list of <TestA> object.
     * @Params list of TestA
     * @throws java.lang.Exception
     */
    @Transactional
    @Override
    public void update(List<TestA> entity) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        for (int i = 0; i < entity.size(); i++) {
            TestA obj = entity.get(i);
            emanager.merge(obj);
        }
        Log.out.println("AISHI321900200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestARepositoryImpl", "updateAll", "Total Records updated = " + entity.size());
    }

    /**
     * Return TestA object by filtering on refernce key <tid>
     * @return TestA
     * @Params tid of type String
     * @throws java.lang.Exception
     */
    @Transactional
    public TestA findById(String tid) throws Exception {
        EntityManager emanager = emfResource.getResource(runtimeLogInfoHelper.getMultitenantFields());
        Query query = emanager.createNamedQuery("TestA.findById");
        query.setParameter("tid", tid);
        TestA listOfTestA = (TestA) query.getSingleResult();
        Log.out.println("AISHI324900200", runtimeLogInfoHelper.getRequestHeaderBean(), "TestARepositoryImpl", "findById", "Total Records Fetched = " + listOfTestA);
        return listOfTestA;
    }
}
