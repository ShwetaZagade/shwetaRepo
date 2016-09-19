package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    /**
     * CoreContactsRepository Variable
     */
    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setCountry("yg3s1CtWgB6NURkUka3bgpJyDSEAGwnk78GYtMEoi0EHCz5uXv");
        timezone.setTimeZoneLabel("7Y8fPEQKp9nmklZ1lYSXZrzREWAPpXypH0zeZdRpL3YJPDj9oC");
        timezone.setUtcdifference(2);
        timezone.setCities("AyiUMEKw5y2P2NuFrZ1eOOwW6SgfRNYqYsISiCKZwyxZoPuhD3");
        timezone.setGmtLabel("x4B9VULmL2NuPmxlwc7g49aapZ57FY59c0hdW8ie9Bf7hkTpN2");
        Title title = new Title();
        title.setTitles("ynIaY92Zz8ny55qFty5FCTdlr6jQDF1vODlR250whSGagEdswU");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("JuJBeNbh8HowK4kiAEyiskqtcTlOzMsM9yVIbjA1hKNhW3hZ6t");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha3("w6L");
        language.setLanguageDescription("k50dmj5yINWzqGeJsmb8ffX514l9eK22CzyLJkePUmtcnOjFru");
        language.setLanguageType("RgGhR6NaYCvrQF4uqw6jJIjNbkbc3Z2o");
        language.setLanguage("WhfSSztztsMyEG5Mszx9cCW2hFaJ4CeWlXoN9qCt3WdVOgnRF3");
        language.setLanguageIcon("xrG9cUst2xYWWOImoLAYqIQoWIuQ1uhCRKsLepqj1h8Rly81ot");
        language.setAlpha2("ae");
        language.setAlpha4("x0Io");
        language.setAlpha4parentid(7);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setMiddleName("55uT8dHmw8wYvptYCfWadUZVYiVcpbgOFnlNiqWHkNSIBIHy7J");
        corecontacts.setNativeLastName("OlBJ5FcuYaqxSimLvRBA9qR5zFRulCXjqtS3AhREWKjpEaqaiL");
        corecontacts.setPhoneNumber("mzvU5tTFaOqRoVuSE9GP");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1474275694912l));
        corecontacts.setNativeFirstName("GeXiSx7uobLY5vpGqdZ4hYnW1p98HRNYROqreNSb1zSlIvNdIu");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("QlFSeXzEKt35Vhd9rEEOx3KpUSaOO8dogFw402QNJ0zQfE99qb");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1474275695018l));
        corecontacts.setNativeTitle("UJnfK0y3VNDZ3udKVvpZRyAuzNmmpdljX4Nuu9DCdPyIzm6Dxv");
        corecontacts.setAge(44);
        corecontacts.setLastName("UaaxBUUXczdinMkDrsgGh5XM03lIBihdzOUjJSWt4WM67LEoAz");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("TIMcygYRHqgDVS6C0CNBaEDGOiWBa9Fhavlrb9Sz8ubAUbgvEY");
        corecontacts.setEmailId("pINNNL1d8pTqgL76kewMTnoDw3Arq8nSXSG5vbRakTQjisfIHx");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("ljR5wRHacJ9");
        address.setAddress2("fRb8AwA54hGT0UsdCUVqDfZChUEieu1QcI4mnZxqgPcqqejK7j");
        State state = new State();
        state.setStateCodeChar3("gm805WhDh7eAMzEMAdCGIPVag2L1nF0x");
        state.setStateCapital("MkBH5v2ACJ8G54Lnp795JugFIFh9HTgzbs4kmlDW3el4jUGRjg");
        state.setStateFlag("G4FRRsnrgBSEamcKSv5yx8UJfjR1RQPqRNLqlXdu4zSpleY2Zr");
        state.setStateDescription("XXbQy7y0CJZ8iS6uoqxzz33cROfBLF2uU18a52EkQzbKQEBQ4I");
        state.setStateName("AyRyXCf75eN6imAEA4j4T6t4q8Nc4XQlNYgDoaiqnJ8qP7UFCC");
        Country country = new Country();
        country.setCapitalLongitude(1);
        country.setCountryName("LVpLdRE23Xaz5GIJJc9E2c5a3DvXYCfRXnMTylIm76Tqd0LQRa");
        country.setCapitalLatitude(3);
        country.setIsoNumeric(850);
        country.setCurrencyName("0OYhsnrcBMyp7UTxiIzEPNQXoULCEoloKoiJfYHjDA1j7v3nLh");
        country.setCurrencySymbol("nxi5dST2clvm7UOzSybNylV17wVeXxwz");
        country.setCapital("ctMEw5wLhkZktEiLjhyNf6Tsy3kxsacT");
        country.setCountryCode2("0Oc");
        country.setCurrencyCode("9XS");
        country.setCountryFlag("x0wAQY7D61MDPGrQLSQVpHwrjy1AFmltYSuF19LxQid9k1HOlW");
        country.setCountryCode1("ArD");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar3("3d19OpJpDZBwXYlTi2e2QWQADFvA5KwC");
        state.setStateCapital("LdiAipJQfVYsjN9fzryESpAO6ndRuYUE4l3HPW3WmmdoRC0Huh");
        state.setStateFlag("OnlrKSbSa4xwnnqbaxsrfF4ce51ijTcvhnxnsaI9CTyfzV38Qj");
        state.setStateDescription("xZA5yun1VOx7rRLfE07Jty7Sga8q2epbVaySScBvddknF1bfCf");
        state.setStateName("s38X7mShVGiPyIhd4AKcKW3qNPTrdETz8s29x3UFVbXelMlh3x");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(2);
        state.setStateCodeChar2("eoJlQmvANCrVXEMv5H8S30jaZeuxxdfB");
        state.setStateCapitalLongitude(6);
        state.setStateCode(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("StJt69P8qMeb4DtKDOxZdURaino5QUpm");
        city.setCityLongitude(1);
        city.setCityFlag("fNUiEhsgyC4j6HtCWqb478GyLfFVUrxRhjgUEWVHY7hlSmQ33I");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(3);
        city.setCityLatitude(8);
        city.setCityDescription("bff8LjJkehBWE9RIqkO3QRn77JiUDd9WJZjMMzcRwodA5mxFp4");
        city.setCityName("021DwTgaEIUDtJJP8qJ7LBL0Rn1s0oTTL5B7u20hQszJTA1y2p");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("8OS6YbectUKBPBMeLyvtvpposGkfqbRCGlSrcHcx7eFH8obj8x");
        addresstype.setAddressTypeIcon("2emjvwQ03ac1NcZ55mhUipbg47aaADBzjMROAHhZyze49cYmAw");
        addresstype.setAddressType("hQ8JVMsiadZeykV9WXk5O6g371Eg9PP0kRY16jZwEVyCJLlo7O");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddressLabel("EqDBYyhl1WG");
        address.setAddress2("hjzm52lvWuAyU62kl8Tqy6SRuRLqFGOQz8aYKjQJrpve817p2S");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("oNNYw25Y82044OWfKYMwNXE7p5mSEhW0Rj4knsL83hK3D0Xd80");
        address.setAddress1("hG1EFksl4PIsbn92zVcmOpKhyZQ9fYBz6atfSFYDn0D4E5E8yZ");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("ucRkJd");
        address.setLatitude("qibOhXNYoacjQOwBIcUTItcWhEbweSpW25hZ1RNLlOvHeGac2b");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("DSb8jBKrPlvQrZR4GvJc40eMnQgPKproQECgyIXlxDG0T5Y3W8");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setMiddleName("N6G7GVewFsK5BZ03iE10KK47qlRPCFR5iq0oTI9fhoVwhwvPSk");
            corecontacts.setNativeLastName("1TYwQg1nOr8paVdxoqnJIJhSakWCqvfdt0QBqI7M5dEuTYgi5Y");
            corecontacts.setPhoneNumber("vcOe0eEojTHB0ftSAIVZ");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1474275695561l));
            corecontacts.setNativeFirstName("L0NjPJ5sFZ4jSUPc1NrtYYgkD7CoTG5tqv3MpDCAJxr5yxXy4r");
            corecontacts.setNativeMiddleName("yH27PmWllCA6Al1impDZopQ95EOK4wlJOFthcHXUdTrhYo0Vqo");
            corecontacts.setVersionId(1);
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1474275695620l));
            corecontacts.setNativeTitle("Q4KBYFopv5a4VTAK0eG0y9NsXwjoct2nrnk4GKantnh8Cv56Ef");
            corecontacts.setAge(16);
            corecontacts.setLastName("pCKimBTMYJP0DEqP01OhOjHIkE4EtnjSR3257w6OpKoH8lBlmw");
            corecontacts.setFirstName("LCbvQ9XgYWBOe0SpYyIEwZEulmgIxGIsptLx6CUTMKwaebYjGJ");
            corecontacts.setEmailId("sKhwBHaxb0kge1EYIrw8FP0lp7AwSQ62g8K5s5SioCUa6FCVCc");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "7igWjT9AwkuWoJQVKAaQBRe8LKhzJUwTSlsbTmEwHUERHu73wIlhL5CmwbLuYltbvYBsIw3LWfUIDHyCf5VHo4VOYZYc2DHJ2ArTHfjz72sH4hcUby04pMT0wRDHVbetNYiQU7fBzg0uL1e9xkMy8P98mvuE838tZ3YX9qKAbq72uhN7uhv47QShtqvfAcGzKkeEjFfQHdTKrCs4u8ZmT3FkEcA7UpBOMhmnY1MKyNOqTlPyuDHe4ktJWCV8UgZjg"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "LUP8IcllzmscmHtvRXOkdDeXZSYeO2TVCqZ9Jc3Q95442Tw5N9MiY2GDorKYZ34IrMOwFjok4efcvPXhAw0A8igMsofJNWIwsX1kSONCU14uZ6FKtqOaxoL8aAlcooVl4cNeZZvysuV0mSP02XAxQJRDZoyOGKzJxfe2U36hBV4rk6VSt5ivw5OjbbJnzkOxU5DrrDaSggoNrAXXjg42xHTlO3ospj7NdXKEXk2AL7EON0qj6UWM0EFjHznc64kvb"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "9D5obrfILsJaHzT0fFcqvyD4VDbUkcd149E1Lb8m4AgUHGw0IphRnqwj9xDDyEECPUzWA5sQCWFnpAbErIO9tItfL1IDNu1ri3gnhuhTvX8B3KGPGTxiethC5nIUOJJpPOeqXyg8reT2xkSnTpPOqWFDrB8cF7632Hrt8YUBBn5fk8u6wGt4EFPngq8KVh9lXFMBrozsZOZc2h6DGutJLA3niHBCJoLkBD1GAIMURYdeiIf03RNV46EiZ0dhvDOwU"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "LQLzuwUtQj4oXP5hYdtAKhRUUOw4hlIyfqyNYz1isr2xZhLMCJod2HLCTzzacJQhE"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "Lr7UUbaKn1spdbAVGeQwzrNbeznsezs2DNlHu3usMiPLl7Opv6IWnuApPlSpRn58GUfYqAvVD9X1F5kJAAWQ0l9BCz46bXW0TtBAmzhelgDai0rgfNdYgJ2i17vXAB7ichGDPI23NRmRlyoaGptNG7QUB1hL58IRLizniXXWoQR6t9wrDR1iR0tLJzEpn92yvrI6rbUF7bRdAqNdNvGn94BwdXfhkdEPgx9ZEnvBUavlEjAV83HA0QkULr31Z7Qg4"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "OMHSAQjgnppItB8alvFcQUXXqaSNu7cIsKR5v7XQkMMC6F62LuEHTjllfUDLvzfgC65hTWcWqNkgVRJ3RXT1NPr8I7Swfz0kdSvWdecccgJSqLQoPD3UJf3f6GO6bRflNQRE7bLsCmcaAa5rM1DOMNwdgydzLiat4TlnNhYPLr28gR2HIwXxR98ssBQyW1CU8hNtqAtDb7YGZQnJOYJlBZt8fjy4GwZEiBCqOA5UAU84eBs74yUOtSkRXrrUan0cB"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "Ujz6QQxlQuG2NtLCGxuWi5YaPShC5ei7WCMMIEaGCCmmlOlHQ9ur1hMGWHt3UHe1KB7POwhOQDaBDuNdcIEZ5PEbrYQtyppfAFxzYz0xphme4QyZwmiVyPaBKe12ZcdfobjPQNQRWAKgc12aVCzwfvKseg19F1u524d0UoYwC6KaIwCaBfqw3ZKmq55kSpzf2Gaphr4W8q5UaEgzLPVkkguH9BC3RGSpPtXXHelvuINpXNe5cEFShzRPaqytyMbL1"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 207));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "yltOAOk5LcDaXX6VjBDWis2oHMD4DD7BcxK3lKfUDVzhr9Shd8DS3JU2bf5MaS9ojXxhbwdQVJn8ee1sl39FSWxmblftUyr9pZluflsksWyqCcFpFK0ayjUtfxizh2VHB2JDqQWUwPFfbnSxTJvOK0baXkmojuB08nYiANgXMtndMk5IoH6oUARP4M7sZKdl8XGnPGmzIDxczeltXNh1WDrPHIMXg6h1olEfkNFOGq0u8bqiOwpbzJbEENl360sOB"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "fWGxOoetYvpyHHsPrjn07"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
