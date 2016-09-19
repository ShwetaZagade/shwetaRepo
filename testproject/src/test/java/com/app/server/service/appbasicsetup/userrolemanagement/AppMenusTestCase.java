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
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuAction("RuJMB6KZYkXgYwaaZ7QfVuAY5nanMqM5meaFMifbAvjdw2hw1c");
        appmenus.setGoLiveDate(new java.sql.Timestamp(1474275706896l));
        appmenus.setRefObjectId("ZbR1Q8bAGSuHuGtS2N239z0GxKpLClENyEPEXohGDmoUv5FRsk");
        appmenus.setStartDate(new java.sql.Timestamp(1474275706896l));
        appmenus.setMenuTreeId("mKHrt98V3q1vuR48lL1EAmnBes4vR5LEVRVdIOJWOffFvBoPNQ");
        appmenus.setAutoSave(true);
        appmenus.setMenuCommands("g33GXvKaVnghCV2e5EbihB1KJkxK71u3ZfbsuT3pBLYEh7Iibz");
        appmenus.setExpiryDate(new java.sql.Timestamp(1474275706896l));
        appmenus.setAppType(2);
        appmenus.setUiType("aWY");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuAccessRights(1);
        appmenus.setAppId("hQOxA2GKIYTyF4h9KCVdulAPMh1I22VtYA3YbpBcYrbSAc5DmL");
        appmenus.setMenuHead(true);
        appmenus.setMenuLabel("cVHp0YtgwwjSAtRBJfmmiMmS3rhgQeOsakyv7ISM92G2LLv3FS");
        appmenus.setMenuIcon("Hq6POTtEbygmz2AJEZdE96UzeslZjndBNI6GKeTrYVeexVZa9k");
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
            appmenus.setVersionId(1);
            appmenus.setMenuAction("vMsTJZS4Ni5bjqr0BNtrxKXO5GPPKPyTSsR2w4A6r4woZNZphx");
            appmenus.setGoLiveDate(new java.sql.Timestamp(1474275706912l));
            appmenus.setRefObjectId("6swNBtCo0rdhByaFXUyyWcq4YTAFiQFSpFoAzpD1kg8h4VCokU");
            appmenus.setStartDate(new java.sql.Timestamp(1474275706913l));
            appmenus.setMenuTreeId("Ggs1NLRXGDwJyBF853WQFN9GN7oTNwGDSl9M4F7lLnP68SKgWk");
            appmenus.setMenuCommands("VkTlazcG7HO8RFC2ORPWxkilVCQMVcUFmEFlo6MbQdBcLN2O70");
            appmenus.setExpiryDate(new java.sql.Timestamp(1474275706915l));
            appmenus.setAppType(1);
            appmenus.setUiType("iZn");
            appmenus.setMenuAccessRights(6);
            appmenus.setAppId("q3JKgvcENY4MzaNx3xMdS1Y4hsTMCggwt9QzSTJ48mxC2zWMIX");
            appmenus.setMenuLabel("wZ4dRzZFz8w7vgTsi2khbMwHG8Z8q9iX1fOXPospmzjA1HAYZy");
            appmenus.setMenuIcon("pmScNyfU2ARbALIRDoyPHHfkZFuMGPcO4SxKE1Xd1ogyyQfQF3");
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "zpcx8Sm3B7HXzLLsBb1agtK24ZScHpdwWnHxcXPVIVIlDeT3NQGvhhdbLxDL0J3kEF18hyPXoGtJE7DjW3gkjCNAFWg4mvFYioIYskC2vSwAIdVratFOmaGG14YOFxKaJyKjHaHbWJu6ZXjqv62rZOxp92qqRy7rdFiF2l8EOa8rnak8OP4ircLMrYfbaohIu4hWLs58oXace3daXGf1Ew8CKn3W6Xw8tptbSKaKVJeTG0F9pk5sOkt14yg4jjuZX"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "Jg1BHNLgnCqB8aZSmeK3AWGEyi6faUoBTMBUY2ciwpFmg09hevbLp3PQiLz031SuoWXN0WJweYFvp2YU6ejSt0ZpSnR9CqHqbTv6a4Un8Nq7A8pUBbi8hrFFVaaQYvAxCtBVFzoIkMuypBLsjEsTQOMT3ZsuOoSuqJzkDruqEcC2Piw5A7qJpyVKI8EBD0rsl3X9PkZLc7CAlB6XQQV1dYCBsD1O82BCppfQujGYSnmyYXEKp03p63cDNTAJZ6yM8"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "su6iENvZuXxbxtXNVgqPpRxC53ef05zU0w3s4LZPk1o6NSFmF8Ip6gkj3yVxriGqWliC4ykm5WFWdMUt1bSFJCTQtvh3EkMKiawpQRP0lv6PKv4AZqu2AtvfptXUxbHU0FlXZdHpG4prnpc99haKyZyGQ0ZA9yU7RQmQtuBOo5O4Y13FhQyyOatgpkVa9WF2FGMd3SkFXCdMiToR7iFVa2YDG46SfHdlPrbSox7Kroo6z9v79hPyx28ZBiVgruF9O"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "P5CkjZf9fkcpx7GC6Hoz7NUrIUTSseYyjErRXlgXZ0lsu6S8ptRmuEqJpjBnELEBf"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "aOcQwj7764c5E7duU4jDTkxhWTCcgLD9jQOZtI2nDAdHiugwg61nRARCQV4cE8hk99n2hgHO2dbsODphgovxgSBhv2CO1RmyRKdUP1CYbq8bYbAXOb9ttSC8Wj6pQiSDmS1uO1dxZNYlhdAeaW7SjSf7hMIfYLzFoEU1ezz3ya26GckM8K17locaBCdUcEUZMvFHgPmuCb49GbaABtr3cVN68MsNuwJ1yMGye94lXXLJsCge9GPSv1L7mNpYB1wb5"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "Zt8s"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "reytyUGx2hW9e4ETUKfnQBrIuiI99SgF9lM4kZHYn4F6VcTa3Af7Biuukk8DBv0n7hhMGcvJZ5qmMwkDT8Jk95KRIqOdu4KeJ6VNdcHjYaJCeVzSyXpmQ5UHPgs5HMzzMx9XhO3aPsHnYz6sJtkHMiNtZtSliNmvyI7isOn9639RbZK36eYpJJFc5Ua726BvHgfKW77EjVYoMJC2LH9WTipBX7CLggVO1LhneKMYSFKeiRP7GjcofZplbWRbxtFhw"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 16));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "5uVhJmTPodpnJDNMkXi9Vmq0Iv9YPsNhv8utOJK9IvZubVYZGosqR9mTJj54ZJqSLBDos27dDho6HohtMDFxty6Cjvr9iY9fWaG3AAbpxFNIKoJ8s5BPSNBOfnXCX2Qct14WAUv8PJ3XfYziIxTXxGCjJo2ikJAHscUnFcUtEY4zjdmADAdfC99Wr6YcZ2RlvyIwmhfQ4y93EavSEmLu4cvnnVYnpucAanjMzeTUVDq8QfJaD8phPx3N5bgnnNL26"));
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
