package com.app.server.service.appinsight.health;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appinsight.health.TestARepository;
import com.app.shared.appinsight.health.TestA;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import org.springframework.web.context.request.RequestContextHolder;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.Assert;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class TestATestCase extends EntityTestCriteria {

    /**
     * TestARepository Variable
     */
    @Autowired
    private TestARepository<TestA> testaRepository;

    /**
     * RuntimeLogInfoHelper Variable
     */
    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * EntityValidator Variable
     */
    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    /**
     * RandomValueGenerator Variable
     */
    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    /**
     * List of EntityCriteria for negative Testing
     */
    private static List<EntityTestCriteria> entityConstraint;

    /**
     *  Variable to calculate health status
     */
    @Autowired
    private ArtMethodCallStack methodCallStack;

    /**
     * MockHttpSession Variable
     */
    protected MockHttpSession session;

    /**
     * MockHttpServletRequest Variable
     */
    protected MockHttpServletRequest request;

    /**
     * MockHttpServletResponse Variable
     */
    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        final MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            final String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
        RequestContextHolder.resetRequestAttributes();
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tenant.id", String.valueOf("appCreateValue"));
        runtimeLogInfoHelper.setMultitenantFields(map);
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private TestA createTestA(Boolean isSave) throws Exception {
        TestA testa = new TestA();
        testa.settDateTime(new java.sql.Timestamp(1474437822434l));
        testa.setTnm("Hello World");
        testa.settDate(new java.sql.Date(123456789));
        testa.setEntityValidator(entityValidator);
        return testa;
    }

    @Test
    public void test1Save() {
        try {
            TestA testa = createTestA(true);
            testa.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            testa.isValid();
            testaRepository.save(testa);
            map.put("TestAPrimaryKey", testa._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("TestAPrimaryKey"));
            TestA testa = testaRepository.findById((java.lang.String) map.get("TestAPrimaryKey"));
            testa.setVersionId(1);
            testa.settDateTime(new java.sql.Timestamp(1474437822455l));
            testa.setTnm("Hello World");
            testa.settDate(new java.sql.Date(123456789));
            testa.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            testaRepository.update(testa);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("TestAPrimaryKey"));
            testaRepository.findById((java.lang.String) map.get("TestAPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("TestAPrimaryKey"));
            testaRepository.delete((java.lang.String) map.get("TestAPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateTestA(EntityTestCriteria contraints, TestA testa) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            testa.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            testa.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            testa.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            testaRepository.save(testa);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "tnm", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "tnm", "HPXlqN44uaqlOr5zXrP1lNYNiMtiRQHUy6kUbDqFsF6mXW4evr2GpzdIU4HW0lQlE4hiKZ3mZD1VveB8vYdpe09lND6PJakVM8So21DuW2ZMM5sbntnWwvi44s7igSkkqz1Y7xMNWtdG6G5BggMNv2q32J7EEXhQ2E0nGZw5H5RwWicAnzwtqfwHS8yCxROxxbOfvg62aoxfifDClDOdjVGS0tQHSI5yXYVHJUa5kZklQZfezXyij4Ox5G5EyGmbt"));
        entityConstraints.add(new EntityTestCriteria(REGEX, 3, "tnm", "123"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "tDate", null));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "tDateTime", null));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                TestA testa = createTestA(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = testa.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(testa, null);
                        validateTestA(contraints, testa);
                        failureCount++;
                        break;
                    case 2:
                        testa.setTnm(contraints.getNegativeValue().toString());
                        validateTestA(contraints, testa);
                        failureCount++;
                        break;
                    case 3:
                        testa.setTnm(contraints.getNegativeValue().toString());
                        validateTestA(contraints, testa);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(testa, null);
                        validateTestA(contraints, testa);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(testa, null);
                        validateTestA(contraints, testa);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            Assert.fail();
        }
    }
}
