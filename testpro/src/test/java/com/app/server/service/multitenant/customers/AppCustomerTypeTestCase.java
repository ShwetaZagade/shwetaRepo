package com.app.server.service.multitenant.customers;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.multitenant.customers.AppCustomerTypeRepository;
import com.app.shared.multitenant.customers.AppCustomerType;
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
public class AppCustomerTypeTestCase extends EntityTestCriteria {

    /**
     * AppCustomerTypeRepository Variable
     */
    @Autowired
    private AppCustomerTypeRepository<AppCustomerType> appcustomertypeRepository;

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

    private AppCustomerType createAppCustomerType(Boolean isSave) throws Exception {
        AppCustomerType appcustomertype = new AppCustomerType();
        appcustomertype.setAppCustomerTypeId("nyl2no3MonaAVnyuEzN6jY40CT51W3vv2YewD8Y7xe2kC8U7Nw");
        appcustomertype.setEntityValidator(entityValidator);
        return appcustomertype;
    }

    @Test
    public void test1Save() {
        try {
            AppCustomerType appcustomertype = createAppCustomerType(true);
            appcustomertype.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appcustomertype.isValid();
            appcustomertypeRepository.save(appcustomertype);
            map.put("AppCustomerTypePrimaryKey", appcustomertype._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AppCustomerTypePrimaryKey"));
            AppCustomerType appcustomertype = appcustomertypeRepository.findById((java.lang.String) map.get("AppCustomerTypePrimaryKey"));
            appcustomertype.setAppCustomerTypeId("g6NcIz3yusQKWyYIIGjWOjloD95F6hrKWoWJzTEEf7CCatbGqu");
            appcustomertype.setVersionId(1);
            appcustomertype.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appcustomertypeRepository.update(appcustomertype);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AppCustomerTypePrimaryKey"));
            appcustomertypeRepository.findById((java.lang.String) map.get("AppCustomerTypePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AppCustomerTypePrimaryKey"));
            appcustomertypeRepository.delete((java.lang.String) map.get("AppCustomerTypePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAppCustomerType(EntityTestCriteria contraints, AppCustomerType appcustomertype) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appcustomertype.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appcustomertype.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appcustomertype.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appcustomertypeRepository.save(appcustomertype);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "appCustomerTypeId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "appCustomerTypeId", "wEMzfhld9QSvbvS10PKbCC7CZrVlI86vgSd732yWE4ynw6G3XVcB4XrfdlxbAFkdxloRW8EMqHX3ImoafTQAYNs2YX3HVseAQpR8DSs2zbNkRAGoZiLJDrkefjoJQKGDF7xZavSt6i1VqMWML8XwKT4kQciA8EBRMh3r4k7i6V86mBDj3wrx6Ley9excDDps2eYKMyDo04TlGExFI2ftR8yqc1Zs4G4uxwXUNB88CyAtjGqpeKJJ0sObArEHMFISf"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AppCustomerType appcustomertype = createAppCustomerType(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appcustomertype.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appcustomertype, null);
                        validateAppCustomerType(contraints, appcustomertype);
                        failureCount++;
                        break;
                    case 2:
                        appcustomertype.setAppCustomerTypeId(contraints.getNegativeValue().toString());
                        validateAppCustomerType(contraints, appcustomertype);
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
