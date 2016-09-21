package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    /**
     * AppMenusRepository Variable
     */
    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setExpiryDate(new java.sql.Timestamp(1474436562476l));
        appmenus.setMenuHead(true);
        appmenus.setMenuIcon("HQCvKbWXwYcrkqV57G0zxKOgXVy2bWMQx1kzQr6c49V7IEw0p7");
        appmenus.setMenuCommands("J1xOmpZcrD4oGYElHWabptUvuJPwOAfe7l9MUooiXgTQ2R1qM9");
        appmenus.setMenuLabel("2Y4ztU8M2z6FFNfcoccIwgEOKjJvMam8dFuiqtsQzK0RinGrC2");
        appmenus.setMenuAction("KXM5K0iroSWbPskh0l7lQA4V8IktOqxg5MttLlSoMYeD73Ny2q");
        appmenus.setUiType("BwB");
        appmenus.setMenuTreeId("TJgBaUV0TxGjWt85h55lLkqPgV3zHj4gKCiZxSpvRsigLfP1gd");
        appmenus.setStartDate(new java.sql.Timestamp(1474436562476l));
        appmenus.setAppType(2);
        appmenus.setAppId("qQNjPlTDYqFYWC7dcOhu4LOA8i8aLhl3NQOIi9Bjp34V9dGJI1");
        appmenus.setMenuDisplay(true);
        appmenus.setGoLiveDate(new java.sql.Timestamp(1474436562477l));
        appmenus.setMenuAccessRights(11);
        appmenus.setAutoSave(true);
        appmenus.setRefObjectId("wNR7Cx926N4hOTHHC4ExdA1k3hlqomAmAhck68bfTvOIn1tvxe");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setExpiryDate(new java.sql.Timestamp(1474436562500l));
            appmenus.setMenuIcon("60AD6H2i3g8QUtPzIbYfUMVXyvTjNeFvENDqDqxJMJzrNmJPyC");
            appmenus.setMenuCommands("TkP1kaZNcd5yWCZ3DZ6Iot8mgjaV8lGYxLJ7scqRbHXgSLnKHs");
            appmenus.setVersionId(1);
            appmenus.setMenuLabel("LvlaiWx17dfoIaMgK14sRbrMskkN55fJ7ob1Ixo1EJkSRmvsY9");
            appmenus.setMenuAction("l9qDJSjRcdc1wXffGqAYsVUTEcSTilT39EuQIPaDvFXSWELezn");
            appmenus.setUiType("egu");
            appmenus.setMenuTreeId("XQZ88Xv9lCZvcmrFQaqycbBckxIGZqNxquNEDJjnqyXctA5psr");
            appmenus.setStartDate(new java.sql.Timestamp(1474436562512l));
            appmenus.setAppType(1);
            appmenus.setAppId("Vj0w9stEnNJy6DcE5BmJzpcteg8CTbzFm1v0of8UD1dLgxeyso");
            appmenus.setGoLiveDate(new java.sql.Timestamp(1474436562518l));
            appmenus.setMenuAccessRights(1);
            appmenus.setRefObjectId("nTthY35QAQ7I0hp34NRLHwuEl0e8BRLt3N7NU7AOuGIhVnSfak");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "JpMuBdj0Mp98bIz6U2l2hJRIg3s6JHBhW7z4qrmVzQubdVIDbE0OWHDF8zCq1LfxpMyxP859opPAMSB6dBipZtx2TAHnPZs7JpOWfRUk192LpzhX829x5NelbEjGVhXvk76fGRVrmfbByGXbyB6ZglRSiamu9lDDttC0iAMXE2Er6vtlI3tOMrN3qigRpOC8d6be2cYfDzTXCW6UkWfgAlCeTn5XD9gBnDBF6kynN3sYyKnmOfVp1ZvXTDpRG6fY8"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "zhmkbyre3GeuIEPGqjoMrRLETjvfcKEktBtH10DILLNcyK0OSWBbi1fmg7gzuwJZJKyl2blVrj4T3DsWsNQbsi9ZdpIICNMcKXU06jP5EmfTvzgE9qdxymv1VNf6h5aEOEL2E3M2GOxaG0kykLU3goWQvKo7ljJgUPYSRYxJgy2z98MK63bTm6BCwwFSCNUJ6xa2bCE8M8mwSckuSxGdbgP65BjAo2ptfgJcJsBjlYujislEzzYzrOEwfhfQn3e8B"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "QXlFWbbVwxoDDXcgEaY5qg6nEpKjm4r0tCzZAb7fUfcl0iwWSVkoNghoFV802iDXP5wjkmj02K2W0i2qOIqanNggnFqGsJVmY3XXMEQhbpeKGWhiaUSNJr9AS8cz59yFXAeH9ltPDI3RwOt9jn2fuW1ctHV7CyQle8dLNQxZcaWkpP0DsLvePLnNA4oE0PazON3xN4b6BXczsfTHD5ZURzWNifj0crNZWmgD2zDFLfkrAPzz2keVSH1pDAwrGD1tv"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "CSN9vjxczB82gKE4uqPhpI842FiBZrgb7gP9BeV3C4GsGf9bbrSpPBTgE8hm9KI6J"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "Ubwg1I6s5MZif1xAypoQ1qMaYTVCAR4vAtrSdG95se5sz36VmfR1xzHsNz5fG4jGoxxGlAL6T7SYSCwaZSGWsLf5JvYMVP30CqE77k6R4kudepPWtDUH9FDYT2Yr75mBigLHlhvx2pbGNL7ckg7syv139jWRRmWf6s6CwHjSC6AQWrNIsahnYr9uaSxBmhWDBIetg1s4RpFxLgBii3Bgq2B8gtxzkUMImxBzp0QMZVTTsnVLUabU6DNPnMMm02QKO"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "bXSP"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "7wIQrb27EYZ6Vgk9XQ1ZDQRB2VZlAOFX1yb6OJidWBcl8UWHgK1qq8K7vrepctzo1iKqlkd3vMzt9rUrnb5Lzx7hscYQ1U85iUcnYpIkgwBVksmqmRSY2HNgMSXSzs0mPMQGBf7SHFgdxBcYIatonAV36lBE0wAUPYD7GJN8N4QvwFGf9vQttyPiJDSZcPpMD9xWgmFp2bUaHWTjSoRHoGaO4S3U9tTWGX21PPDZkTFj6j62GBGlbRfwhNdxQzjLj"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 16));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "ouhIQ0XE1lNhjJseG2jCjg8fqMMyiGqBUPkv8K0TdeJPYMsfHcrG0jNMHKjPjoukhzIzoJkO18uIPIQSU6JppJGPOChwNZTPuAUmd52s3Y39JccMUigRMx7mR2pAHafzFTiaAYP83FSyzz1CrR44abrIX0oXXK8twGSjpYjjslLGYN0Vdb7SESVVwHAGgRqtv1un5MvraEpB1wzqWg2D2SdPmCpLQZOOwnLaNNY2bQH4rtDKwgpXz2OPBHxCnhnkE"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
