package com.app.server.service.multitenant.customers;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.multitenant.customers.AppCustomerCategoryRepository;
import com.app.shared.multitenant.customers.AppCustomerCategory;
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
import com.app.shared.multitenant.customers.AppCustomerType;
import com.app.server.repository.multitenant.customers.AppCustomerTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AppCustomerCategoryTestCase extends EntityTestCriteria {

    /**
     * AppCustomerCategoryRepository Variable
     */
    @Autowired
    private AppCustomerCategoryRepository<AppCustomerCategory> appcustomercategoryRepository;

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

    private AppCustomerCategory createAppCustomerCategory(Boolean isSave) throws Exception {
        AppCustomerType appcustomertype = new AppCustomerType();
        appcustomertype.setAppCustomerTypeId("u3ocy3gFa2JAJUzQNCuShW93HdzhKkvTug6S2ek5MJI33vR0wm");
        AppCustomerType AppCustomerTypeTest = new AppCustomerType();
        if (isSave) {
            AppCustomerTypeTest = appcustomertypeRepository.save(appcustomertype);
            map.put("AppCustomerTypePrimaryKey", appcustomertype._getPrimarykey());
        }
        AppCustomerCategory appcustomercategory = new AppCustomerCategory();
        appcustomercategory.setAppCustTypeId((java.lang.String) AppCustomerTypeTest._getPrimarykey());
        appcustomercategory.setCustomerCategory("h1IO0I7Tyx1CfGlhakGEVGAIZcccDYkQDX9fh7qbZYDTFLDylJ");
        appcustomercategory.setEntityValidator(entityValidator);
        return appcustomercategory;
    }

    @Test
    public void test1Save() {
        try {
            AppCustomerCategory appcustomercategory = createAppCustomerCategory(true);
            appcustomercategory.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appcustomercategory.isValid();
            appcustomercategoryRepository.save(appcustomercategory);
            map.put("AppCustomerCategoryPrimaryKey", appcustomercategory._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppCustomerTypeRepository<AppCustomerType> appcustomertypeRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AppCustomerCategoryPrimaryKey"));
            AppCustomerCategory appcustomercategory = appcustomercategoryRepository.findById((java.lang.String) map.get("AppCustomerCategoryPrimaryKey"));
            appcustomercategory.setVersionId(1);
            appcustomercategory.setCustomerCategory("3srD6bIrMWR2uTjeEFGVA4gHMvit1BrwfZ18RYjdTxZFPf4HEQ");
            appcustomercategory.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appcustomercategoryRepository.update(appcustomercategory);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AppCustomerCategoryPrimaryKey"));
            appcustomercategoryRepository.findById((java.lang.String) map.get("AppCustomerCategoryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByappCustTypeId() {
        try {
            java.util.List<AppCustomerCategory> listofappCustTypeId = appcustomercategoryRepository.findByAppCustTypeId((java.lang.String) map.get("AppCustomerTypePrimaryKey"));
            if (listofappCustTypeId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AppCustomerCategoryPrimaryKey"));
            appcustomercategoryRepository.delete((java.lang.String) map.get("AppCustomerCategoryPrimaryKey")); /* Deleting refrenced data */
            appcustomertypeRepository.delete((java.lang.String) map.get("AppCustomerTypePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAppCustomerCategory(EntityTestCriteria contraints, AppCustomerCategory appcustomercategory) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appcustomercategory.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appcustomercategory.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appcustomercategory.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appcustomercategoryRepository.save(appcustomercategory);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "customerCategory", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "customerCategory", "8pnPVTxCD28Wb6hMz2cWUVeKZdTKJsbq0BUZmHCnFPOpocHQXsxYzqB8uztNkapppLrUoX38h4WKsPr7mmVwKIOqNUWrEzHQBhDY0kadh9aMZR83AYwrN3Vqx6BCUugOyrvh347mrkx0xaC5x0XsBmcm24ND0ooe4bjTXCfhcPTkgboPYs8j3NSiileVPIqVi30eM3cuEENsDCHDjLJ4R47QBpbuI3KytSMzTY6b4PMod52442L8WjTtLKTCPyA3m"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AppCustomerCategory appcustomercategory = createAppCustomerCategory(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appcustomercategory.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appcustomercategory, null);
                        validateAppCustomerCategory(contraints, appcustomercategory);
                        failureCount++;
                        break;
                    case 2:
                        appcustomercategory.setCustomerCategory(contraints.getNegativeValue().toString());
                        validateAppCustomerCategory(contraints, appcustomercategory);
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
