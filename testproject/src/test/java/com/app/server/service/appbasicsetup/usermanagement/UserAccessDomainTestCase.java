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
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("MNRTYbSVGugImrDywYXCJU1NFuY49FoNtvdiSNz9Cz1hbt6t51");
        useraccessdomain.setDomainHelp("6JR9GzR9BvLAyZy0NDNkFv5i8LIIIu9iGdFP2QsppwfNp5Jiv2");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("fj2KPmHqbO4xhGeWx5hpHkZNtWeEpmXpsuGbVgl0pobW6KDFF7");
        useraccessdomain.setDomainDescription("gL1176SXZ8RArEGKJG8vih1vmMtgbcUo6mGz4cVqF9ci9OYGyR");
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
            useraccessdomain.setDomainIcon("blOIIF0ceGg50vhwzBo69kiYpld67iGoZILQvj1VUHX5T8OKtT");
            useraccessdomain.setDomainHelp("ali5EDO3jiR37oQ3ntW9M7dQQTAQ8b2MyvcGi1X7IRcOfqA316");
            useraccessdomain.setUserAccessDomain(9115);
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainName("lO4y8HDZR00NRndVjNkPDty7QfZDsKBvI06gsSoVxcngRbeYMZ");
            useraccessdomain.setDomainDescription("LHEcRiP4xxxadcXOjCp83RAuEsoczjBoV0uAUVShEGcu7S8G3T");
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 150588));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "GWoai6v5rrYzSoEOzSSFRuptGFRJX2zQgP6VlIVLJiGT6DPCIZDcDbM9LuczVAmKEhlAfib3vQ0CH66KikeoyI8rcxxV8epl6B7zD8C454GDraunS4XWiJzxMZO8NCvpPByxGLjI25vXDrf0E8xy8a73nOjSou8oyWsbAsS3HgjQrR0DJNKOZSmYLmQttSlBZ8c7KrmTQvRRhPQ9aRdFYGcnHwmcYc5DBIuBrnYq2LT6wQHgbwRSCCyeD8zmJYDnf"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "NEzudZSTOS5WYCsTdI3KLoTremzVOgjRAECV98qCbbzPWIursQURFd3bvTPHU5RUmunuwOZKJMlH76NsBG9Cw2BXSwof1XFP0YwZ85bcB3f737guTH1C9ACDeuJF9EZ4XHnPlAKhPTxDruXHMBPw4y6hmzN5jnJsIbRlbQuhYI2zDBoHltHrU8ndZS0BZXooqMYssB725GEGKJEe3jT411cQAJQEwesJwiEmKLBthPBWoVaHHGG8BCAOZGBewVqYv"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "Ab1xzFfW9FxKW8qaZFJ9PiMocmWDv34TEO7X6jUFFxQWk7hHaHnzF1V72lKPFUC894c8mwTXAnkpn0tH4nUBRd04TMfKnYbduNGl3aFdic4PeA34BD42uUHtKdrmjUeABRDuXe3dMtovGYegXrOLTxzu1J44XjtN86Bug143c4PApWKPZimvStOxWKh4haLdEnQFEB5Ki9h8L8bSp5dD1W2BkEP09BY8Fm9BATLx9GR813IpX7rPFd6nveaTrazYd"));
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
