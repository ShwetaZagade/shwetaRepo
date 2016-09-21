package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    /**
     * RolesRepository Variable
     */
    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleHelp("N2bZy3LUb9Zk9O1brIyAVVPb5DSBiLHHdF0qenpOaHdvpXotWy");
        roles.setRoleIcon("5fw5fM4lCgBLJwMCPXYmTGlvapo8tT8M2Pp9kkZduxedL7m8Vn");
        roles.setRoleDescription("kEjYGcuL1kIE76ubIxiVCTnuENEJ4IObg8LUN5BZtofjcg6M4R");
        roles.setRoleName("0BDuc52OL8DwX5hGP4iFVKLa2kBzu3QEySAdjYj7TmPUDRQK2t");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setExpiryDate(new java.sql.Timestamp(1474436561840l));
        appmenus.setMenuHead(true);
        appmenus.setMenuIcon("DgwjP7H4WsoYPYEXrVQ4qyFFafuAgwzr457mCTjwLL4ybtBNxT");
        appmenus.setMenuCommands("wTqAg3M30somwmUPg4KsQi8KoIfITAH48VsrirvdaZ7SEJIxIe");
        appmenus.setMenuLabel("nEEwnSQcMW6jrAM17OaMagVBkml8g2UlMRvxehV9znAzCeBUub");
        appmenus.setMenuAction("2HAeFqnXJbPfY134uZz42mgUrDqVUXSVce7OXSmhbJDuk5q32a");
        appmenus.setUiType("385");
        appmenus.setMenuTreeId("hvJ1lkB9aQF5rJNUeaCHIzCnGgU7MeZuBxT8L68Gm8r4IiMURP");
        appmenus.setStartDate(new java.sql.Timestamp(1474436561840l));
        appmenus.setAppType(1);
        appmenus.setAppId("BKGp2cEAXEr7OWhdOofB5qEFhVsdqj1GeUAyCYgXa3G0US9aFp");
        appmenus.setMenuDisplay(true);
        appmenus.setGoLiveDate(new java.sql.Timestamp(1474436561841l));
        appmenus.setMenuAccessRights(3);
        appmenus.setAutoSave(true);
        appmenus.setRefObjectId("ozkAoXsYkw9IPblD2c1bRAIycweDIVDZxkEUxuaCBuIA7dSOAZ");
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsRead(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleHelp("v3BMtqENzvVSYmhokV6Z9VdtHXaSdsPzZXVsV1MWqWZ1CBy1CB");
            roles.setRoleIcon("CSD9mUM3vruZrax6CYsiAytJozQ6DJv7rlI7wznjMFhPoC1kHd");
            roles.setRoleDescription("cmCjBpLZuwR8aeJjsIawOEY7txrncJGPHhDqsIjEi1UEk8TXgO");
            roles.setRoleName("i3S8CWulWnORbFw1SO4WKy4dwXu9YUOnqQzkLDWOloWs6NLGCB");
            roles.setVersionId(1);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "hmfMi5f3P2ZNjZfjmTellKAgGKSYmnrjyjAu6fd0KqjzobH6w1lDbN54C4lmOWHv7EkGGQP3Z7qpAgGtW7Uy5wH6NXL6UVkyknChVQFix2vzX0wuHx05r2fvDPPokM1KZWzRrHQiD1XGnM0hXN6hDvNzDoyJxXwJKGCMyGOhH7CYUqyRZZD0jXBLN2hMbEVBUCfHGuJ4WZ18MA0aeNWJBZ1hiz1aSgDxD4NvH70duN5bhdpDfbvaCPxU9xCgbds7e"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "U2LEbjjNqYTbfhQpa3KGhInePSrMHieo2lypx4VMGpN2xf4OhQZ7U3seBEuKT2sqAB9O5nrsXLNjDtVpFrMrXY7q6fRaJ8GZQaL98j9NvCHCP1ylQraQoZMLXqv67cMj5Jwd1gR2gtG7o20hU4uT1GLskOqwhHeR4HLm9ONSsRv7OfxGxELyt5uaw3X337U6Tx2XvQn6Eft26jD0Cpg3yDLtCOFjBysrMJqRUInFvJRKRnCOqnsSKkCw4StWgSUH7"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "y5x9AYYPmg1vewnm70XtyLr6hvw8DYfqhwMxGjsQpDpkPhgsWHYV0dw1t0dPkafv6"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "lMFTYxuAl2gIzGExednakhmztJkOWMrMio3iQirgP6mzohKASFaCXFLPj5p3UlpMsiaoFeXpZKRb4MY2jwJRgDO75lSvmiPyF8inMFJKMINOMMeGJknrkkUjuFKoiJnR98Bl9lzSCAq9puVLx6AadrO7XBY3bZQ61lE85RgCaqr0dX1RzA2lL94ZdVwEdg5I4fglkd9Hu2c9BcH86YowBjBPrMI3rjDPGbVF2um5dBj77yW8XoSpTbsT0qJx4Xs99"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
