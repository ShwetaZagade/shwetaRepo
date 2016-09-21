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
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleDescription("VXkBPqlDqRNtyaR1D3aYAfFMWz9sgfoD5aWrUH9kOXPG4b1Gqb");
        roles.setRoleName("X6iKcVWILYxv29Yj1PQ3m6ntJIW2b0svEHqYqkuVukihv1864h");
        roles.setRoleIcon("YN5VLKLKt8kW2jegJFBg120NFSaHMlDqyfsXR5hJn3p4DeGq6h");
        roles.setRoleHelp("Ff82hBfSbYscPOudIcgizoaZ8bmSC6EB95dXUsILPiwrJmvWXA");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsExecute(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuAction("b3WsMV5L7AjPpNLi5S3hsU72iOmJHwTzbjB8lBIipHy3cnh4La");
        appmenus.setGoLiveDate(new java.sql.Timestamp(1474275706375l));
        appmenus.setRefObjectId("cygiDq0G8OD7vF9fqABZWq9dehHKW5kx9xarfCjlPy6NjUHDEf");
        appmenus.setStartDate(new java.sql.Timestamp(1474275706375l));
        appmenus.setMenuTreeId("zMcJuNLJWNWpNZVvlfrqNwdSswfQaCZnrl7tzlaGtgrKH5PDIZ");
        appmenus.setAutoSave(true);
        appmenus.setMenuCommands("7cd6Bh5izn5Zi55QzErvWUI9KZ1a2t8lsu2hbgCWwlv6wlJtAY");
        appmenus.setExpiryDate(new java.sql.Timestamp(1474275706376l));
        appmenus.setAppType(2);
        appmenus.setUiType("6RZ");
        appmenus.setMenuDisplay(true);
        appmenus.setMenuAccessRights(2);
        appmenus.setAppId("QJVJUjCqz0BLOLv78rOgdpb6K31wTntEd7W2Oy4gk4m889HOi4");
        appmenus.setMenuHead(true);
        appmenus.setMenuLabel("EhxS9k1YKOVhPKqUEYDpwH6rdAokElNLXo2QlJHcBS4kwJRy4x");
        appmenus.setMenuIcon("2kLDFRcaPH759qbLoETUrhgCzpZiuONbb06tiAR1fd8t9hWq4t");
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
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
            roles.setRoleDescription("ObMzRfHOdwOfHEFNj8BUdfZDjzx0tU2RIzBYNz2o0FAXyChmp2");
            roles.setRoleName("xTwoJylYG6PRWIWhrlHyv3omQ9nh1De04M7Q2e5t0Xox8Dl13o");
            roles.setRoleIcon("zz8ti8q7U1nGkNRruoWGLGSR03DGjCi9jQ19MgbNynTIHB0KtQ");
            roles.setVersionId(1);
            roles.setRoleHelp("KPdN1LWBoWfNpIEDu9TUs6lIb1pDOoX09MfR8UxhHYJIYTYFmY");
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "8HCqTnVjyicyTVDenSamzjGe5Gyjoe7ije3tVmr51xlsVbIPZEs0AOozgQvmuoeQ5H6zEPPMZeOfDugYuNVmN6ATjZiGwf554pJyem5OQ7YzCnbAzRiXGNeU3drrRUqHkV9IhRonKOQSmofCwqUnvoNMknZrarxrgBetabjAhAjmVrsHvmw1l1cbEwUSFInIva38HogPZP0wytaZ39vWpdR6k28NiwmbEnrRf6OuU55AaKv9vL6q1WpxvoIiUBHjf"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "mRW2m73LJriZCM2s82JjIwD6DQVnxG6qGcNAcMNSoISzdYveI7KhDDH7H6sv7LtLq5BeXhSQnNgjQbnHXAC07GOSf61bjFcYE9q91bvG1cFyl6fmvx7NzeTbomHu3u3DZT4bmaROMG3NA9oUkU6eFdIdk7YKe5gnMidABU1tOL1JQom9r8xR2ny80tWXGIf07ZpdufvMqDApjcuIUfBDqKYQzeJsZYv5ZXFOVRCNjxXp9jn1TrsFZaPFznx3x94xv"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "ykX6uUUODp5vc5S1BdDe768PHCI3hLMK3WptIvYa94p3vPtlCPXLtVxMnJaBcFtf0"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "mtxBvypcxlBgFcSdUVo8RvnF6gpIW2OAC7l784ERG9MdXHAXC0nJ9KOkn0ohfnhP1Z45LMmw5l8wn7S7q73iedi7WEYJYklC51UNaxXfj8dzxjrMeGqGrLMjB5ywsNP7HsFCCwgRhs4gyQvSKFmlKsMoLTeah0iOAzz0syQAV9xmwQLCCvqWG3FCKzWIj27GvWYOvXbQEwwV6XEwiXoFkIrY4sgxhUSVFseKLfMk6sSYFc0lU1mZDzV1pz0JR9FNy"));
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
