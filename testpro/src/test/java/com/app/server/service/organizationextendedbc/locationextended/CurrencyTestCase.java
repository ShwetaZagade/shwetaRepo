package com.app.server.service.organizationextendedbc.locationextended;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationextendedbc.locationextended.CurrencyRepository;
import com.app.shared.organizationextendedbc.locationextended.Currency;
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
public class CurrencyTestCase extends EntityTestCriteria {

    /**
     * CurrencyRepository Variable
     */
    @Autowired
    private CurrencyRepository<Currency> currencyRepository;

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

    private Currency createCurrency(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLatitude(3);
        country.setCountryCode1("OCX");
        country.setIsoNumeric(430);
        country.setCurrencySymbol("O6peNLobZcYbW9iCJrJ24RwvQ9Bg4gYn");
        country.setCountryName("AEpGiqY6Cljr4uUana319epVRnpioylATyyhNf8m7tNM7jwGLs");
        country.setCurrencyCode("76b");
        country.setCountryCode2("DNx");
        country.setCapitalLongitude(2);
        country.setCapital("u3qAGr4moML20RcoX9ND1MrCsgPQjGSF");
        country.setCurrencyName("B7V144JBtyzBVAaVNobQLSATfufaOnViF2KwwtPW9DXBWjJ5QR");
        country.setCountryFlag("k0LDhbMg1JrCmwA5HO9scvvqC6gPc5TEfd6fimILj2ovDCrNzg");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        Currency currency = new Currency();
        currency.setUnicodeDecimal("WJk1vfCx3gnX7qsDJULxsCzXH05kbkhppJ1gWNjDkjX4HBsIfy");
        currency.setUnicodeHex("pmkWIyaprkDmF4Y66UqndebSASUkDzSV25TAntMxmpaKDtv0uq");
        currency.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        currency.setCurrencyCode("fQ8t8DIfomSdtP0Yxv3sr3tOaWPRXyV4uGMkTwbeoIrYstYzru");
        currency.setEntityValidator(entityValidator);
        return currency;
    }

    @Test
    public void test1Save() {
        try {
            Currency currency = createCurrency(true);
            currency.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            currency.isValid();
            currencyRepository.save(currency);
            map.put("CurrencyPrimaryKey", currency._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CurrencyPrimaryKey"));
            Currency currency = currencyRepository.findById((java.lang.String) map.get("CurrencyPrimaryKey"));
            currency.setUnicodeDecimal("CPLbelRzGdKUn4SwSeHGighFzsf3sMdy2PDSqbpmqlTu0hPKm5");
            currency.setUnicodeHex("ii3CCoKcziLe59P6K8HtsVlKvzHuSAQ7vbuXZ7vqY8t7g6NUs2");
            currency.setVersionId(1);
            currency.setCurrencyCode("mG9lo8HxRmjJaCDdzMnJyk5NqHI3PpF2YX8i8vMmSNEJNeDeH6");
            currency.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            currencyRepository.update(currency);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CurrencyPrimaryKey"));
            currencyRepository.findById((java.lang.String) map.get("CurrencyPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Currency> listofcountryId = currencyRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CurrencyPrimaryKey"));
            currencyRepository.delete((java.lang.String) map.get("CurrencyPrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCurrency(EntityTestCriteria contraints, Currency currency) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            currency.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            currency.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            currency.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            currencyRepository.save(currency);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "currencyCode", "31NQzkcEzx9xP9JYJGgBtV3sRZ2sIC9exeTOlKcKjq8abZsUqYCgtV7Zy0mmoLwNXn2o0dUC6e0ECzQuntHUKfSIm1FTYNk0AxEeBhEw3utUw6KlFpOHPHVKF1D5BXSCN"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "unicodeDecimal", "6rG75JiaxEaTcYBdkdRtFr5pZ0Oi5dkQUg9u0elV9QHGGTiteSUHc0BkjR12onU0Uv1rkgYuWWWbKkdMaO76jTB09LOcD3tLdq1nO8K9L7p5N5H31KasW5kWGDEyGMPfd"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "unicodeHex", "aRJBC59KghT9JFaxCiEZIhkXxcdVST7S1LMl5WQfEzT4NWyu6VhAtBEYhkWvm5zksiws8lKXlnOcUYVvWyqvrpazPQC5LK92te4pJ61jnlTFOJsDQhkCR4osmyE7DJ7NC"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Currency currency = createCurrency(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = currency.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        currency.setCurrencyCode(contraints.getNegativeValue().toString());
                        validateCurrency(contraints, currency);
                        failureCount++;
                        break;
                    case 2:
                        currency.setUnicodeDecimal(contraints.getNegativeValue().toString());
                        validateCurrency(contraints, currency);
                        failureCount++;
                        break;
                    case 3:
                        currency.setUnicodeHex(contraints.getNegativeValue().toString());
                        validateCurrency(contraints, currency);
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
