package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    /**
     * StateRepository Variable
     */
    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLatitude(7);
        country.setCountryCode1("cqw");
        country.setIsoNumeric(152);
        country.setCurrencySymbol("m8EU89dnfQQc4uZAYfvpEhEk2NtxcwqN");
        country.setCountryName("AFt1iOu8ChDSRvSPrFgis4JXQdk6yBRVuqqkyxZRz3wiXi8yGN");
        country.setCurrencyCode("KEn");
        country.setCountryCode2("dU3");
        country.setCapitalLongitude(3);
        country.setCapital("kWtMLl1m6moBbeWcpcDmD54XkpLGOfnR");
        country.setCurrencyName("fAlrOa7ag19v7xGbYGNg0fn0WHAw5OzHKrcImSRtojptamIuNa");
        country.setCountryFlag("MzafQv0p15lkotX4HKqlu6tdpM6l1Pomz7Crqnm4PCl2lSOALB");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("adEqEEZhJdH9Uoxov7bcs3vrGC6fpfRN3cw1nXsQuPHpd2M56R");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateDescription("CV8dQR1lD6eJXaeBuoO8ZX20XXhAmoaSa8GcJvSSB5AGz9m20J");
        state.setStateCapitalLatitude(6);
        state.setStateCodeChar3("DA2ngacWg3B6T1tZAjIsc6f0EG1PVys2");
        state.setStateCode(2);
        state.setStateCodeChar2("0HvAP4TgPf714CIcqggcnsipo5Dw6mMI");
        state.setStateCapital("UPaddtMvCmVuH5WBzy6d3fxM7Z9Co4S8Pl1VWLHAms4J20TXYX");
        state.setStateFlag("uYEkA6z4MwxGMbynbcQAD5kNue76hLSKCFoXDXt9OP1v667Rtu");
        state.setStateCapitalLongitude(3);
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateName("ZCfRd0FRuFH22ygSa1s35CVUsIUF5PPlb1FTyjy8bAsaLUHNE7");
            state.setStateDescription("YXKBKlanHYizFTfeNvWonigIckAfCwtWgOKkELnHDWBund7He9");
            state.setStateCapitalLatitude(2);
            state.setStateCodeChar3("gOsrNUX37pDkVhtH33pjXDz6RUWlwFpj");
            state.setVersionId(1);
            state.setStateCode(2);
            state.setStateCodeChar2("MTCimL6dyN8wya2Uw9pXf6sxDxTiefDB");
            state.setStateCapital("Z8MNpzYBoLKqLyxRT0Q3SJoIQJyE0eiTf5C4qxrwgtxCig8a24");
            state.setStateFlag("nhQJdpDk3R8xKBTomkAIeJPwjKGrv4Gh5Xca4gnDHWZI8hpt7f");
            state.setStateCapitalLongitude(5);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "1gAq5iU0ocjT4liQRXowRU1DcEz7wfTLoyZjF6mHesxwfJ2FJDFwMq3QTQb7aKBARiLn8LZWrXg1UZI0z9UJ7i5rEaCTmVJr3xvndUHzqA8rHdzjZ0mYN35lp5gV4sTQs9EQsm7eVg1LfrdQp6LZTGmIYnRR1XkhdKnX0DStUTYLCfah36nbS15cJnbQcNjdlKUqP0V22tK2v6kbVFdDktMECPjZA8eICI7K41Ji4pqP94yuBoWl6Klu71KcEYfIM"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "KPiO8pfwtAdy3PpRWYQe0fOa8Ueh5x6HJ"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "osHpJr0emozTp4ZZWXxEp6gIdPCnSkDwz"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "5XNvrEDvRaaINZsro5fhxwM5gau2S9mlSZYMFXF6PbKRMdBb0rJdAqKmKiCQjyh47TfDjH6nYRH0hzwUi0j32jxCEOUhzUQBRHDo6BPmnheLxTPZOfPkrVS06Hk9I9CxFq75idoCzCdtTBMvZbSnf1Nx1N6E4XeKvYCvBQDSAJYbr8sQGjcyGzJhuZwV6Xp0WqEOIc65P1Qjz25BbgCZrYxmn5QocRSGnciWFNIxR7J71qqwhJjbPKqUnWTLaYNpD"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "Rm4AL1qe4Bk9W8dhPNo3TVrHov27q57uboniC5HjFUbenhIyNugT3QhMgI3p2SMefQRPunTzV80AZQfe6jRZ06feoe0qgiJdU9Yb9UoyLR5myWsFmMxlyJBffxlJ2VVOL"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "L7D3EW7VDtumypXcrgif9s2Ew4YKKNXPcadWrbbmzUEKbgdGgeWfOHXAzJ7EinWf6udi9xviNsN1I3JRn9hGiQGoiS5o8bK0dFrBt63CRcZDqXVN0jMT6szjjxOIbOElw"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 14));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 21));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
