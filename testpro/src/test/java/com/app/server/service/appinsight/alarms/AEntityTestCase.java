package com.app.server.service.appinsight.alarms;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appinsight.alarms.AEntityRepository;
import com.app.shared.appinsight.alarms.AEntity;
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
import com.app.shared.appinsight.health.TestA;
import com.app.server.repository.appinsight.health.TestARepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AEntityTestCase extends EntityTestCriteria {

    /**
     * AEntityRepository Variable
     */
    @Autowired
    private AEntityRepository<AEntity> aentityRepository;

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

    private AEntity createAEntity(Boolean isSave) throws Exception {
        AEntity aentity = new AEntity();
        aentity.setaNm(valueGenerator.randomValueGenerate("String", 256, 1));
        aentity.setaDate(new java.sql.Timestamp(1474447889429l));
        java.util.List<TestA> listOfTestA = new java.util.ArrayList<TestA>();
        TestA testa = new TestA();
        testa.settDateTime(new java.sql.Timestamp(1474447889440l));
        testa.setTnm("Hello World");
        testa.settDate(new java.sql.Date(123456789));
        listOfTestA.add(testa);
        aentity.addAllTestA(listOfTestA);
        aentity.setEntityValidator(entityValidator);
        return aentity;
    }

    @Test
    public void test1Save() {
        try {
            AEntity aentity = createAEntity(true);
            aentity.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            aentity.isValid();
            aentityRepository.save(aentity);
            map.put("AEntityPrimaryKey", aentity._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TestARepository<TestA> testaRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AEntityPrimaryKey"));
            AEntity aentity = aentityRepository.findById((java.lang.String) map.get("AEntityPrimaryKey"));
            aentity.setaNm("pvCTGwsPriiPBmpC85Te1eV7jLei1boTnLxCmHq00L0nWTk6Yv");
            aentity.setVersionId(1);
            aentity.setaDate(new java.sql.Timestamp(1474447889525l));
            aentity.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            aentityRepository.update(aentity);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AEntityPrimaryKey"));
            aentityRepository.findById((java.lang.String) map.get("AEntityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AEntityPrimaryKey"));
            aentityRepository.delete((java.lang.String) map.get("AEntityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAEntity(EntityTestCriteria contraints, AEntity aentity) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            aentity.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            aentity.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            aentity.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            aentityRepository.save(aentity);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "aNm", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "aNm", "cUGaX8duNupfq4ULCVGknlls9A2TxMOD6rlwqAGtfL6XP154Kt8aTCjS8n4C9ZvldVTyIRauRQqGYpsXpXEVMt6K6IIt6lSz58pfbJmBzAqmue4qmHZvWQnxgr3rMM5sndzd9ff0hC75GEWPkCpuwdWreH2QyR7bKMCBViWyWBZL3Qw3xooZowJM0hycegLQzR2LySMA54e6xcXVmGYM60vNVYyM0M6eqh1HyMl1haj1UTLRqsXT3hpami2yPq98A"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "aDate", null));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 4, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        AEntity aentityUnique = aentityRepository.findById((java.lang.String) map.get("AEntityPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AEntity aentity = createAEntity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = aentity.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(aentity, null);
                        validateAEntity(contraints, aentity);
                        failureCount++;
                        break;
                    case 2:
                        aentity.setaNm(contraints.getNegativeValue().toString());
                        validateAEntity(contraints, aentity);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(aentity, null);
                        validateAEntity(contraints, aentity);
                        failureCount++;
                        break;
                    case 4:
                        aentity.setaNm(aentityUnique.getaNm());
                        validateAEntity(contraints, aentity);
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
