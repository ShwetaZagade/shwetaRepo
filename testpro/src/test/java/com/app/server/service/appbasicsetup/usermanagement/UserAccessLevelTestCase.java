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
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tenant.id", String.valueOf("appCreateValue"));
        runtimeLogInfoHelper.setMultitenantFields(map);
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("w3vtsVfjIZF16tXQNezFnxgYdz6gvZNYyzEKPXNCq9lPdixs0t");
        useraccesslevel.setLevelName("vNg7fmRIWMKCoNCNftyU8PsfUnfZrvvt8AkU2NugNruIrvjlV9");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("kTqcKBolcO5vU8bbsdhmUtDossQl2JLhujJkUwIEfcr5gex53c");
        useraccesslevel.setLevelIcon("EeLjTk3nvc1HevWdaeXm1DTmRzxbuIEx7bs2nGbQQSsl1hwxCG");
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
            useraccesslevel.setLevelHelp("XdDtU5n1R3nX9rnrN7iGJQAjTz3pTL3QYrWrzfQbViRRQkSDns");
            useraccesslevel.setLevelName("rSwkVTTByqyW6T9Z72JM4Zfb52uSMh4WhVIk7n9XtqE76U6bQP");
            useraccesslevel.setUserAccessLevel(66061);
            useraccesslevel.setLevelDescription("5Rvl3loLIiL7E4xZ2TgNWbfaUh5JJg5HoXfCCAGCjlV2e8ZlVV");
            useraccesslevel.setLevelIcon("qzimhZq8GKJHEu8HcYy78pqoFC4hyONJX3H6wXVBKpQAk7VsFn");
            useraccesslevel.setVersionId(1);
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 107765));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "RQdKpoPH6OFmnXgbnGAyJfb9uJZVmsUOakoxt1dLIDcU0qPzhBQnhb3poEMmxVNphdomlzHcSIY84PGun3P1K3SrOTIOwKkGE2DcRFyqrlNrwNHMfnQ4wmWNiocWTUtniL4MCFEwmmY29VovWa6leouRcPgD3Gn6Tq2mrTOKPjNvRCSwcShd3mKwfwTw38ixTjM02IXaiSIG5PbtZpEQEzgDiLzRgDiraghmrCIjNesKC1tiALuonT26YlMgSJfAR"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "n7if2shOQvObLmYfusAtgXwx54ICFlOIGni86TiQ6me5gUAC8rV3BnPiZRGdVq6nFirSescEdZBiDMyGjHZDwVdIEYiloAofQJVjxFD54Rik3gUDgNFOfgbXBJKLVwg49xHbFAZH7zTkTXbRwNpbrZpjmzfcxIzItYoFi1FafhS1JSOoDDvbWjyoet2yv2csInUDVPfioI2Ua8NF85tezm8AjQbgivJungIp305ucJElEVeQXLsyObvAzRhpXXcAU"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "BihLdyPpNCxN7lQKGmbW15jb0AYlX3DM3dug4ljLDPwScLJjCXRBTpfDLKgvkPLv0hTA5Keow7fe18DcQUL3obGldfa6Ug2Q30PyER6cDgcfkqmXOPgg4A2qSpJQuRdU5wGJ1UL2Cd2xHJHU3tIJwTP9j3tKBApQWHLnxPp1yt6ZvScAB17HMULeOsdqjn2FUo8DzdWeyUTtE6rBbwb0pFSUocxYQVGawleMhtoyobe73FmiaXyCXo0qgOW41APrn"));
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
