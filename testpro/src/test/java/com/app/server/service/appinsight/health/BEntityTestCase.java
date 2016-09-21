package com.app.server.service.appinsight.health;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appinsight.health.BEntityRepository;
import com.app.shared.appinsight.health.BEntity;
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
public class BEntityTestCase extends EntityTestCriteria {

    /**
     * BEntityRepository Variable
     */
    @Autowired
    private BEntityRepository<BEntity> bentityRepository;

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

    private BEntity createBEntity(Boolean isSave) throws Exception {
        BEntity bentity = new BEntity();
        bentity.setbNm(valueGenerator.randomValueGenerate("String", 256, 1));
        bentity.setcDate(new java.sql.Timestamp(1474438240460l));
        bentity.setBno(valueGenerator.getRandomInteger(2147483647, -2147483648));
        bentity.setEntityValidator(entityValidator);
        return bentity;
    }

    @Test
    public void test1Save() {
        try {
            BEntity bentity = createBEntity(true);
            bentity.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            bentity.isValid();
            bentityRepository.save(bentity);
            map.put("BEntityPrimaryKey", bentity._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("BEntityPrimaryKey"));
            BEntity bentity = bentityRepository.findById((java.lang.String) map.get("BEntityPrimaryKey"));
            bentity.setbNm("LMUsyCiwN26jyKdJBv5ewUYDaNmruXUnMIHvApWcg7RSll8zgB");
            bentity.setVersionId(1);
            bentity.setcDate(new java.sql.Timestamp(1474438240484l));
            bentity.setBno(2147483647);
            bentity.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            bentityRepository.update(bentity);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("BEntityPrimaryKey"));
            bentityRepository.findById((java.lang.String) map.get("BEntityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("BEntityPrimaryKey"));
            bentityRepository.delete((java.lang.String) map.get("BEntityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateBEntity(EntityTestCriteria contraints, BEntity bentity) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            bentity.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            bentity.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            bentity.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            bentityRepository.save(bentity);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "bNm", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "bNm", "MceZbJarXH7PCyU02mSNODBriuJvQwAYbCHqQ9ayLzvv0pZoIpGOHyPKmbYdh5CY016FZQy6ljoAX1u8bdDDdNz2GnpbcxwJr9BncabtkiBd54DwmaONY4zbWUX0RS4DlRFCxhvpbLeHHsUUpZy5md1NNsik6OyADFndKuYfxIHFXNsSalX6Zz3wu9b698KEZxCs1BBJzMU8FAv8Cce3mTCOogLiQtK1gHJs6lSWXbuL6Wxsi4Cvqrsca4x58rm7X"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "bno", null));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "cDate", null));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 5, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        BEntity bentityUnique = bentityRepository.findById((java.lang.String) map.get("BEntityPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                BEntity bentity = createBEntity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = bentity.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(bentity, null);
                        validateBEntity(contraints, bentity);
                        failureCount++;
                        break;
                    case 2:
                        bentity.setbNm(contraints.getNegativeValue().toString());
                        validateBEntity(contraints, bentity);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(bentity, null);
                        validateBEntity(contraints, bentity);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(bentity, null);
                        validateBEntity(contraints, bentity);
                        failureCount++;
                        break;
                    case 5:
                        bentity.setbNm(bentityUnique.getbNm());
                        bentity.setBno(bentityUnique.getBno());
                        validateBEntity(contraints, bentity);
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
