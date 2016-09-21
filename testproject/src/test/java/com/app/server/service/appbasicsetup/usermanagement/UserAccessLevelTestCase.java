package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    /**
     * UserAccessLevelRepository Variable
     */
    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("t8Q9ZUJbmzNhMBJkmytVfdtFvgmTmHEO5kgXiI2OHrhjk7dRgB");
        useraccesslevel.setLevelHelp("9k2wPFBk2WA6mvGlVB3nHyGXEtzjY3jpYJPbLkuE4AKbeI0SJr");
        useraccesslevel.setLevelName("AxHo35NygnPqo7YrcsmQdJHeBtFYY0C5Y3as7yleQcWt2M7QjA");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("FsCBbcdVMwZeigpEt5ZRYHbr2PjgImkz91KflPUF8gGfWDzIpm");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelIcon("XwzHoMGA8sV5ZeOIYD0BSgKoAKKEb4to1tIaSBbt8Asg3eDOet");
            useraccesslevel.setLevelHelp("SYlZkVPtdNLCo0oFgEbe2iho90PvvtO2dBqw9UjE29TmOzWFbX");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelName("TrBc0zeDGkH7z022DXG1xy82ywuY9AKm7ktwkq8bSQUJjJNAtU");
            useraccesslevel.setUserAccessLevel(86219);
            useraccesslevel.setLevelDescription("Fo3t7piOd8VEcJAvD4vsSNXj0fQ8IagXTetrsGbG4CtYx0d6Go");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 130905));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "kAs9dBwnk2VSKfTiLDhHLaSVKyXcT5RD6Jr1OwvFl2YI8jArGXmJT2mAVNuxVR2260n93WA6FBroi7hdpw9BpUypKZa96jmfMKbHhHcuqvTRK0BwZjF5VmBbMf82yfYbtcJHZdDfYXEZmp1nwb8D2GQzm6A83KzPQIGlpzYxpDEgiOHLP5NS6crZMJ6xMgGPj7H8rWXHhqBqromPPgUaVKQ1bjU7fylbdFyHPi8Y2xjTmhvxQDvrmSYMCb7QhgDO4"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "zJjMMflc3n7lhKD1tjmrNBhiA90e37dfFjVxqqe1E1hCQQmJP1MSH6hPZSPSLAWWaeithAtt1xrrobE4wZfY3WoFFIVQYpWX8qOsBh1brRVojMuLFYhbujc4t5hPwhRiy9I6TWpVLpgOJ7tfNblnqM6N0efk5NT8SL6rj0kVQNVN04btfap0mlRawSW8ffC4xaCVldQ6PMYtsMDlbmlrpEJ9gFcieskZc5BJhOd1XaBpLjlVI1STHGfbwmXDSt1DQ"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "utXL3gHXFnk3B0h8fwReZXRJkgvk94cHY5pTdIcaloDU825bXmW43WmwRvL8TjwHJMHUy869MavsFTjShtg3BHsDYWH5UOc0ZOPZS8xZRfWehiwABNxEDQtFosHVwtVx8mqBuFKErFNoANBwDLCuPJA6AD2EVwpYmKq8bGXBri4N4nfshBAfGNyKeE4wHhiqmACrPcSLKjiIPetLHHfmTE4bTGewekjLkDElB2U8PNQWNTECeqEZ1OVntwkCp9k4M"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
