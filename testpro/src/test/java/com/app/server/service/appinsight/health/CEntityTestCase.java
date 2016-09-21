package com.app.server.service.appinsight.health;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appinsight.health.CEntityRepository;
import com.app.shared.appinsight.health.CEntity;
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
public class CEntityTestCase extends EntityTestCriteria {

    /**
     * CEntityRepository Variable
     */
    @Autowired
    private CEntityRepository<CEntity> centityRepository;

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

    private CEntity createCEntity(Boolean isSave) throws Exception {
        CEntity centity = new CEntity();
        centity.setcNm("Ul8KIpYkkulwv86Gwq829TV9oa9oQ2ffSRjeFXxTRQb63UjSA5");
        centity.setcNo(valueGenerator.getRandomInteger(2147483647, -2147483648));
        centity.setEntityValidator(entityValidator);
        return centity;
    }

    @Test
    public void test1Save() {
        try {
            CEntity centity = createCEntity(true);
            centity.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            centity.isValid();
            centityRepository.save(centity);
            map.put("CEntityPrimaryKey", centity._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CEntityPrimaryKey"));
            CEntity centity = centityRepository.findById((java.lang.String) map.get("CEntityPrimaryKey"));
            centity.setcNm("mwp9v3BsZmUvSmA6FqonXe4kojq5VIozuApF1SIO1MXM2X9NDE");
            centity.setVersionId(1);
            centity.setcNo(2147483647);
            centity.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            centityRepository.update(centity);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CEntityPrimaryKey"));
            centityRepository.findById((java.lang.String) map.get("CEntityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CEntityPrimaryKey"));
            centityRepository.delete((java.lang.String) map.get("CEntityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCEntity(EntityTestCriteria contraints, CEntity centity) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            centity.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            centity.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            centity.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            centityRepository.save(centity);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "cNm", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "cNm", "HDRnr1HkqnCY5NK7e9CXXIWAt1gVtMGMYDuouAwOHg7lWCmWdtRM6HGGT2PaWav7HfGaMkwFVuQ7PPqV9x3rsIbTFRP482Lz0afSEEuVJNJdpJf9YShrWoH5BqfcdyG4GD6qwNiWjro87zr85baLh5ajPtTzlFfIr2RjpuZ5bMFwdW1a1o5N3WfutgZ0896NxkWXXDRBclN2EskIKkkxhp4SUVhvmpbxNp8X1NveIXG9zeF6MBMAW1BpJJlUfMccf"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "cNo", null));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 4, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        CEntity centityUnique = centityRepository.findById((java.lang.String) map.get("CEntityPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                CEntity centity = createCEntity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = centity.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(centity, null);
                        validateCEntity(contraints, centity);
                        failureCount++;
                        break;
                    case 2:
                        centity.setcNm(contraints.getNegativeValue().toString());
                        validateCEntity(contraints, centity);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(centity, null);
                        validateCEntity(contraints, centity);
                        failureCount++;
                        break;
                    case 4:
                        centity.setcNo(centityUnique.getcNo());
                        validateCEntity(contraints, centity);
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
