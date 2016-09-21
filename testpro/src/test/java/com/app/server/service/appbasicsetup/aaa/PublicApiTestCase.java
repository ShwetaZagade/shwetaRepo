package com.app.server.service.appbasicsetup.aaa;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.aaa.PublicApiRepository;
import com.app.shared.appbasicsetup.aaa.PublicApi;
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
public class PublicApiTestCase extends EntityTestCriteria {

    /**
     * PublicApiRepository Variable
     */
    @Autowired
    private PublicApiRepository<PublicApi> publicapiRepository;

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

    private PublicApi createPublicApi(Boolean isSave) throws Exception {
        PublicApi publicapi = new PublicApi();
        publicapi.setApiData("gmo2rWZvyU1AAQYF3uGGeBAfTCJ94IHfX0dm1WiEkt9xRnogdF");
        publicapi.setSchedulerDetails("22a9IJPQS8pYPfPSk3p3D0dCuBbUxu6ErcMb0m5slf1OfkSNO1");
        publicapi.setEntityValidator(entityValidator);
        return publicapi;
    }

    @Test
    public void test1Save() {
        try {
            PublicApi publicapi = createPublicApi(true);
            publicapi.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            publicapi.isValid();
            publicapiRepository.save(publicapi);
            map.put("PublicApiPrimaryKey", publicapi._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            PublicApi publicapi = publicapiRepository.findById((java.lang.String) map.get("PublicApiPrimaryKey"));
            publicapi.setApiData("WLl5Pd2IDyGPB19OVh89DTyhayQlvhH9ArJUiaXHssGRijMDdl");
            publicapi.setSchedulerDetails("dqvINWiY8gdYwYqGZv8WXAZCVvN1uZdxOn8RKuKf3eXdBaLPgT");
            publicapi.setVersionId(1);
            publicapi.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            publicapiRepository.update(publicapi);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            publicapiRepository.findById((java.lang.String) map.get("PublicApiPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            publicapiRepository.delete((java.lang.String) map.get("PublicApiPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validatePublicApi(EntityTestCriteria contraints, PublicApi publicapi) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            publicapiRepository.save(publicapi);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "apiData", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "apiData", "ql4Kpmy2XrWhvZTCEQYSQmGzlwkOpshqclVdr58nUv3rTvLnxwT6ZL0q6Lv2jF9fQDhXz0bZNkROzCTvVBrVGnOZeSecouWLU0C6eTwcWBO2n86p0X3zdSNR1bNvVVW7wcniPmqMrCGyouEuxwf8vj9jrWG7LOdLZXJtNe90QlPDKRc5BQsu93fmarcjLGSRhCdlzCqSqXkZoBT7sT0hk0tLhwGt8blWxh7i1eWx7W56XFbGvTEaVcq7MRCveC49q"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "schedulerDetails", "H9SwjUrQES0EzdjPynX3IXNJSM3QcYZjUxXTyO99G6DX1lJaCs31Nq089TZjtQ7N0wIdrRFk1dHhRw1fJzgf1jT3HEnGbt7iQornUC04xvmarT3CZmq2nGEnsHzdlMhbNdxPMznb83RKRNGelSUnl5xAypuLAPcUlQqmcvLr4jPNYTO6NXMOAKfgYpqgikXxUXd8MOSqwHUmrDk9ldt2Ll4jTw000AXftC81b9wgFwN5h0VFOrg3gAw3Zu6zxY6m4"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                PublicApi publicapi = createPublicApi(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = publicapi.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(publicapi, null);
                        validatePublicApi(contraints, publicapi);
                        failureCount++;
                        break;
                    case 2:
                        publicapi.setApiData(contraints.getNegativeValue().toString());
                        validatePublicApi(contraints, publicapi);
                        failureCount++;
                        break;
                    case 3:
                        publicapi.setSchedulerDetails(contraints.getNegativeValue().toString());
                        validatePublicApi(contraints, publicapi);
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
