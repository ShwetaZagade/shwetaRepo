package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    /**
     * UserAccessDomainRepository Variable
     */
    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("fqhFkK3c2eMkWTd7R8zROVQYgzNocqbQfXcJ2iZ6JozmIEhofl");
        useraccessdomain.setDomainName("cs1srsvyNoK6EALFUDzvKpdQQS8fpq4Fn3Z2b8T0EY752r5eaP");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("Xs8owaCa4hJF0SQVUQIYWAqFAG1rNI01Af58Zw4Br6wkO0q550");
        useraccessdomain.setDomainDescription("qqTJutrWi6KgTs168B8A1ycWAbErvzLtIHFLlqHpn6ksO9iSjV");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainHelp("LuNBAInFe1ucEX6A9wSRjAo7lzlW3OA1xWFCfHLqqw5nTR0Txq");
            useraccessdomain.setDomainName("9LUFLBeBX1QE9B0e8om1eMbt2MF5muQJNAdd1Qh4R9E4eR8aZ4");
            useraccessdomain.setUserAccessDomain(94492);
            useraccessdomain.setDomainIcon("MC7QtG63zW8SJm4XUeSeEAUlyf6St64oVpatqUHZKHh9WyeTbH");
            useraccessdomain.setDomainDescription("cq8gvMNnHrYKI9ww9LrH6x0jJ29r9Kjj1m7slq1O7cYQ9rKTGm");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 162300));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "EK9Jx140Hgq3D9tINR17BhC3cdPXIrdfn5nuQCsCbgRoMEppi29uEKKHiTACqCvKxP37C1lTVXSBDP9kOjO82ytW9bje6bw5q9rK01go3uOgWLFv73T9w14jy9adn6KFEFuXjH1GtliVaDRk3dz3zQn6LKQOtfVluTDZz8Y4N566wWdltH1qclwlCAd2wNTX01VRd3HLpCKeaQrnqFt0JVnh2dfVgShdQ1Z1VAYnhT3UmaHDbJml2V2bavhSC4zqE"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "3K5sy6FcalM7kAJcLRajU6hF5dGbaDanHTYZnf1ADYgTpUJ1iDWJ9bBkquDwZHnniKraMl3wYfkaPLgo3ZperZ2yWq9vGhGg5G7CDu60l8WKtyL10FTSDee6fwhdIb0VAiowzaPiSQwfTjcp2Cgu6wwKQTGHzMxiuYenuTkwRbPhDWVegRS1d24aO9glQUxYojsHsvjqw8tY2DeYcFRCcWmXr7UEbFfRyxPni9VjQgL6CgQExHI5mjLVzuqn1VArn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "dLEf0y69oUrOnltT4A3FdWzigWSQVSyAi79XNLhoGmKhOc9eGLy2wdVGbtjHFMZYylht6ih8m4ojQU1QgRPH9ccfWWl0kAs17GeJ5UQmzz0UE9ino8rvFHpCyCdXJOBqnIiQTZvFhOf29q8LhUV0UP2KhtdI7d5KpWw4S1l54DkNe2NqxQiWqopwxIz5EDsC8VRtl6gtzZCpPwzt42QNgkaBBLW2cG23M1axFmdEF1t9L7ksWODBRAOuebYIghBGf"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
