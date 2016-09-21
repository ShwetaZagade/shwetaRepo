package com.app.server.service.appbasicsetup.aaa;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.aaa.JwtConfigRepository;
import com.app.shared.appbasicsetup.aaa.JwtConfig;
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
public class JwtConfigTestCase extends EntityTestCriteria {

    /**
     * JwtConfigRepository Variable
     */
    @Autowired
    private JwtConfigRepository<JwtConfig> jwtconfigRepository;

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

    private JwtConfig createJwtConfig(Boolean isSave) throws Exception {
        JwtConfig jwtconfig = new JwtConfig();
        jwtconfig.setTokenKey("PWnMk1w10tAK2wIFJwKtcqR5sUjg7BzPgq3n4GtdqgW00mmW6Q");
        jwtconfig.setExpiration(new java.sql.Timestamp(1474436572436l));
        jwtconfig.setJwtAlgorithm("rqGuXBpmyiWyPM1cer940F9WTIgxV4KIaS6LpZpPQ64nmhFBvK");
        jwtconfig.setEntityValidator(entityValidator);
        return jwtconfig;
    }

    @Test
    public void test1Save() {
        try {
            JwtConfig jwtconfig = createJwtConfig(true);
            jwtconfig.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            jwtconfig.isValid();
            jwtconfigRepository.save(jwtconfig);
            map.put("JwtConfigPrimaryKey", jwtconfig._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("JwtConfigPrimaryKey"));
            JwtConfig jwtconfig = jwtconfigRepository.findById((java.lang.String) map.get("JwtConfigPrimaryKey"));
            jwtconfig.setTokenKey("zm9PSi5JmN3mfa8aCa6bKEYWVQguThezidUMET54PdaLYhLJKf");
            jwtconfig.setVersionId(1);
            jwtconfig.setExpiration(new java.sql.Timestamp(1474436572454l));
            jwtconfig.setJwtAlgorithm("7oxF1H3SyNK2qqsXnr4DUwvqifmdZCQ2V3ZKHJi7gM640ebSMs");
            jwtconfig.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            jwtconfigRepository.update(jwtconfig);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("JwtConfigPrimaryKey"));
            jwtconfigRepository.findById((java.lang.String) map.get("JwtConfigPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("JwtConfigPrimaryKey"));
            jwtconfigRepository.delete((java.lang.String) map.get("JwtConfigPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateJwtConfig(EntityTestCriteria contraints, JwtConfig jwtconfig) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            jwtconfig.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            jwtconfig.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            jwtconfig.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            jwtconfigRepository.save(jwtconfig);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "jwtAlgorithm", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "jwtAlgorithm", "HLmWSGS2OhyOoZAWpSw14JQxeMCJKLQgBZ8HMINtf4QMRXZUwmRDr8FITDZefWyWpiJyiYkLYm3ZFGdormoaupUMUM8mKNPMA8YcKcDmniz5KXUqWoXl7peDsjVyZtHZ8iAG2hwbhrceAh8Rkj6bNU2smm5Le5yr175OOQdRpXS1xLhf02N3rhJZyTLmjfSWC7x5idDUv4Lik2Pew1IUKS4YX2NifH6KxfZhVUr3zJ0udTbunf9LSB1M41OEeAV6L"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "expiration", null));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "tokenKey", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "tokenKey", "TcXFQ6YYhIo4S87PHkHVlQhWr1rKP66vBZvXP4SGwnGrh23Lnkphq6cCU62lOJThkL2Q5zpMYkjW7KEV9rJIPgYE5AuL969GxgdfNduTkfRNBJanGVT6pCNhHwj1kxWVnRDVa8vmLD9XXwfQf5Oj6LRsiDClKsVz0xxcKhKv0hoQpRP2GwaYauk8JYjFq6cnWaQlvj5bTszQvWwgagExzWy2IhvhsA8w8wpULsSq2KDQyPyYu8zWi3TBVq2F2mZRz"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                JwtConfig jwtconfig = createJwtConfig(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = jwtconfig.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(jwtconfig, null);
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 2:
                        jwtconfig.setJwtAlgorithm(contraints.getNegativeValue().toString());
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(jwtconfig, null);
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(jwtconfig, null);
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 5:
                        jwtconfig.setTokenKey(contraints.getNegativeValue().toString());
                        validateJwtConfig(contraints, jwtconfig);
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
