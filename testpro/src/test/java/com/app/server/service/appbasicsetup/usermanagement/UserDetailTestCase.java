package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserDetailRepository;
import com.app.shared.appbasicsetup.usermanagement.UserDetail;
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
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserDetailTestCase extends EntityTestCriteria {

    /**
     * UserDetailRepository Variable
     */
    @Autowired
    private UserDetailRepository<UserDetail> userdetailRepository;

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

    private UserDetail createUserDetail(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("VkzvFY3IgUZxm4xT55t33y1m18zzYLGW0iiwpbgV6wVzZSNTiv");
        useraccessdomain.setDomainName("Y7eymgjXIehwUBa387VGVxQXULxJn962ougKql0OlLDedfxNSS");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("hizkvg1T0xEeIsTFBo8tGiS1UrbJ6bui90yUsGBweCYslGkkuG");
        useraccessdomain.setDomainDescription("oWmKIHO03106wNYlU5yWjSJJcdBlFJJ4oggFYYwmtH6ideYygR");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("42NRLt0tFPQVUVH1DR1LW9fR654ILpAiAStCn8KxKuAyfE99kS");
        useraccesslevel.setLevelName("LnZj9Jx0S6P3sop1L5LQNg6Bg5WbogX5ZRM89h51LPMDUZkbmV");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("3UJf2HDWF6R9X3E4RVOlQHwHfS7rg3QAXTqPsqdsQRkCCncpK1");
        useraccesslevel.setLevelIcon("cxEFyOGI7bemL471yk2vPfEBdacHdTKegnUr19IhQTca8A2oum");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserDetail userdetail = new UserDetail();
        userdetail.setPasswordAlgo("BSMP0axQC19kluEOO2qMBNrmq6kYsL2fDXeaLUBBRvsFDA2XkC");
        userdetail.setGenTempOneTimePassword(1);
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setChangePasswordNextLogin(1);
        userdetail.setPasswordExpiryDate(new java.sql.Timestamp(1474436556989l));
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setLastPasswordChangeDate(new java.sql.Timestamp(1474436557036l));
        userdetail.setAllowMultipleLogin(1);
        userdetail.setUserAccessCode(10247);
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setIsDeleted(1);
        userdetail.setSessionTimeout(1569);
        userdetail.setIsLocked(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("w3gmBfetMpStHm0XyqMAsZtw7saiuRkJA2ggdVsgbs9lvEyGww");
        Question question = new Question();
        question.setLevelid(8);
        question.setQuestionDetails("aUrrhyloKDByoXCChWjokLJgUz9zB9SecJlNu9Ku7SY2PjqBFW");
        question.setQuestion("4kkIOrlFm2MgLmYAZVnD86qsiBIMxcuVimZcJe8hi7L3pIyxiV");
        question.setQuestionIcon("rllZveOrZzgK85onBNxwxVumlCmj4fMDbPVY4b6Bp12rr582UH");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("4jfywWY2SeHkAyjr4CvaKP95IOMBZ8lctFgBtgYEVd5BvW5Cxb");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setUserDetail(userdetail);
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordExpiry(3);
        userdata.setOneTimePasswordExpiry(4);
        userdata.setUserDetail(userdetail);
        userdata.setPassword("lxqkJmEuj1lG9vn81sbfsEL4Smtbovc6A9vymdfyj5Zs3J25Ob");
        userdata.setLast5Passwords("4mlSThaapS0gNK0BNIvU7EgHLYCh96O5KHjtLFhcJVPy8T4Zqe");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1474436557224l));
        userdata.setOneTimePassword("el3cOwSPfJxZ6fv5OjTkQfne58xupY6a");
        userdetail.setUserData(userdata);
        userdetail.setEntityValidator(entityValidator);
        return userdetail;
    }

    @Test
    public void test1Save() {
        try {
            UserDetail userdetail = createUserDetail(true);
            userdetail.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            userdetail.isValid();
            userdetailRepository.save(userdetail);
            map.put("UserDetailPrimaryKey", userdetail._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2findByuserAccessDomainId() {
        try {
            java.util.List<UserDetail> listofuserAccessDomainId = userdetailRepository.findByUserAccessDomainId((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            if (listofuserAccessDomainId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2findByuserAccessLevelId() {
        try {
            java.util.List<UserDetail> listofuserAccessLevelId = userdetailRepository.findByUserAccessLevelId((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            if (listofuserAccessLevelId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserDetailPrimaryKey"));
            userdetailRepository.delete((java.lang.String) map.get("UserDetailPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserDetail(EntityTestCriteria contraints, UserDetail userdetail) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            userdetail.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            userdetail.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            userdetail.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            userdetailRepository.save(userdetail);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "userAccessCode", 78125));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "multiFactorAuthEnabled", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "genTempOneTimePassword", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "allowMultipleLogin", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "isLocked", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isDeleted", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "changePasswordNextLogin", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "passwordAlgo", "pAu0jwFXLdV3tUpSKCuCBM3Zsa3rzepHZ1LulQXsu479sQY48W7kfgc6h1ZzMUVzx"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "sessionTimeout", 4674));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserDetail userdetail = createUserDetail(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = userdetail.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        userdetail.setUserAccessCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 2:
                        userdetail.setMultiFactorAuthEnabled(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 3:
                        userdetail.setGenTempOneTimePassword(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 4:
                        userdetail.setAllowMultipleLogin(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 5:
                        userdetail.setIsLocked(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 6:
                        userdetail.setIsDeleted(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 7:
                        userdetail.setChangePasswordNextLogin(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 8:
                        userdetail.setPasswordAlgo(contraints.getNegativeValue().toString());
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 9:
                        userdetail.setSessionTimeout(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
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
