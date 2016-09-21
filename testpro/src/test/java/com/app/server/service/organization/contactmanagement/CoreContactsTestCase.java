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
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

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
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tenant.id", String.valueOf("appCreateValue"));
        runtimeLogInfoHelper.setMultitenantFields(map);
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Title title = new Title();
        title.setTitles("VNG0k3ESN7EYEHxkr1WDJy7PRVb8bdHvazNPBnAz4hy3O5ZNJ3");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageDescription("qu8lTeTwbUY4wwQ5D2RG7XVZgwehVDXEwkiDW2i0hnlXPfXv0I");
        language.setAlpha4("03MB");
        language.setLanguageIcon("7QfjeCS3H5hTmCbM7Exv4ipSeEn3PfTwh0nm7uKMmQ09SrZwsS");
        language.setLanguageType("X9CDmvaXxHe26v2lPI9npPJOdz5WcEsY");
        language.setLanguage("eZJlIXBiNd213hmS7JOcB4uf5BZH8SOzidzmlPXOuzvnu8Mw1X");
        language.setAlpha3("ut8");
        language.setAlpha4parentid(10);
        language.setAlpha2("eq");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("peZB8RcBGgzqEC8GBoR1rPmWyd8dccNc5QEiWdUq5gxPqJS4JJ");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("9er8FUROykxxJGZDQE2fK3K8OnTTNK1WE9VswgQwCxF8Il37T4");
        timezone.setCountry("0MWki5Ng3Ik7yAv2b8TPh8Cxua3ihNZMhwURYZuX1ifVsSEa27");
        timezone.setGmtLabel("yzNq55G45vRXVqWVRElcJMUeQfhzOj3jMBcycKdGkG7hmW5Dd3");
        timezone.setCities("DcVIkjBdZw53zD3sFCkCp8YIPHzvPq6P9dCkZi8pl3tuC7rOQw");
        timezone.setUtcdifference(10);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1474436549897l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("Klh1IN5YrK94G5coziaDsEDtQPWYA7moCOxPAQkHhOF715DQzn");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1474436550017l));
        corecontacts.setNativeFirstName("pMp69PSImShF8tNFkXhu1xx5DdHBdsLAvt5HWlBjYGDasswsez");
        corecontacts.setNativeLastName("tpmGzlslGTsXVW4w9lI0V1xPWqkUEF8NDudBm0IIQ746ogCUio");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setEmailId("eV29GrbnHk790jHLVmfaBD53mBYS5EtWaVcMWHm2SVm2WymXpz");
        corecontacts.setAge(60);
        corecontacts.setNativeMiddleName("edkQ05NhEOJmbqZhfbdNSne7wmYaa1AQ4dqKGx674xYncb9lew");
        corecontacts.setNativeTitle("1nyEd1PeQt3aNu0ZzHvRGtAe8xsEDiKc7stY1lWBrx7m1JWUgh");
        corecontacts.setLastName("PJKSMpkfBDThsD8yZMpLXEO9qYVMh7F0KPJn2pEpSlamSsuPqU");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setPhoneNumber("7qGMDp5PoWQT645R76Ft");
        corecontacts.setMiddleName("f5XDSwru0n7GSXmHNLmus9fzbdt0PygINQpHKt9m9yNmolPpza");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("62SdNLEeqEEBIqcbRjcd6FkkiU2cVkXspoX1vUjHqwbcFy2QRq");
        addresstype.setAddressType("mQrcAaCqZZuD1fsuqPjtVf31DIzdHVm6bi1iMBZW0daVbmLDdL");
        addresstype.setAddressTypeIcon("20rs57jTYTZIFYYuq4GG3Mi83lu1vxDcl0C9F1uN1HJAgagedb");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLatitude(1);
        city.setCityDescription("TqSoP25oy5EWGkrYz3koDjPfqBn6CPkCEUa6hGzgDivf71M4si");
        city.setCityName("j0V8yJuCTt7RftsYWY9S7bTWesTD4DzKAysIN1gg1cVhgM6epK");
        city.setCityCode(3);
        State state = new State();
        state.setStateName("2gf1jctXFq2LETBuEQwlvx9OqGDaPPodFiOAdU175maH1RyHoY");
        Country country = new Country();
        country.setCapitalLatitude(1);
        country.setCountryCode1("r5v");
        country.setIsoNumeric(840);
        country.setCurrencySymbol("0G4MfJKv7LBTmAHvGH2LWqOA3X1kmS9y");
        country.setCountryName("5uqqnkDucoxDM3F6IBaaTDRjg77hCOmgEoXQmi5eJzbwFDz0ax");
        country.setCurrencyCode("gLb");
        country.setCountryCode2("F30");
        country.setCapitalLongitude(5);
        country.setCapital("dlzqMJ17zK4TCsvnvtaUAXYPX0PvDF3G");
        country.setCurrencyName("nueejyFdM7sbYVyXAl9qnYgZ9GDVbJTntz7sqqLTUbpZzkHdeF");
        country.setCountryFlag("UzjEepm7VasZIQc3U2X6KBiiEgHzv1l73qyeYLO3QCPwskaqkj");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("Q0MOLJniEpPS8R3aqzNyOkmh5iShTB3dzCChnpx456dSjzqfw4");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("8NUW43loBPfJTdgYuTVoIfwo8gKem4ap8hn3eeeXIvItG8WK1E");
        state.setStateCapitalLatitude(10);
        state.setStateCodeChar3("Uhlh8zMtZbME2K2f8I3hOwO7R5oQKinZ");
        state.setStateCode(1);
        state.setStateCodeChar2("oj7LlQDjOzE6yEgXOdbB9jvRzOoXNra8");
        state.setStateCapital("Kz4e54w34NIDYxWGcVCOIn8o6KgEOaXPJrL0wbVg43dzQiZ74i");
        state.setStateFlag("DOpFcwhBKVVybT22Hn6kRjN98ezfGoBqJLAmzhgBjGSMSM8IQM");
        state.setStateCapitalLongitude(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLatitude(6);
        city.setCityDescription("F1Gy854oEJ7y4aYMjingyI7oW8r2UKUSeYelaDeOVgkaGlUzmQ");
        city.setCityName("OsGJ0L3pf9supc9cYKyQIGn5hf0vzcAKnrLJTTqZFfWs3MhuhH");
        city.setCityCode(2);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("rEVkix5vKxy1TbsXFcSXSmj4x4cKSzOl");
        city.setCityLongitude(9);
        city.setCityFlag("jMxCcHFM9FNyXdWpYcGKNNaZpfTChcl6kNiOEkyrhRh3l6CE59");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("WZPN5ZRcSAeeYbql7Ro1EJg1fmP3EHPiDSUbAAZ9BhOQuOe0Ac");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("tQO6VPxYF7s");
        address.setZipcode("DyaiLg");
        address.setLongitude("kzyYKByVT2bRCT4ddYnNfdYSWKPW7hvJp5n2hhwY3b6D30STro");
        address.setLatitude("SFtYOnjMf7f8dKYhjqLA29AVzkxXHQQlfkTcTTNk7t6clcrLsv");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddress2("NAOWcLGANoEI5UWih9bMlrPnrZB29QPBlfyxiiryRB5ovMSdMp");
        address.setAddress3("fSxPIDAafujMkiBT5YRfzPXzMEeAl3YOSwqvGLvVs84sVpZuRu");
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
    private TitleRepository<Title> titleRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setVersionId(1);
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1474436550321l));
            corecontacts.setFirstName("P5iZMEhLtzpuXfcpn7tu7R6mQU2ndk4VVBYW838m0Q69cUV2RU");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1474436550346l));
            corecontacts.setNativeFirstName("3188ccedHEovDzuZHAlZcrccsmlC41090yQfjC7tDAK5j6pehu");
            corecontacts.setNativeLastName("ZqpqAbgQob38kraDCIbVBNk6t4UZoegDNGmUTacZ6opEuHhN6V");
            corecontacts.setEmailId("VPzu1Y6GaYRyBBzJQ4vB4nPTp0qCOYQa5h91NDfnF6yuedzp2w");
            corecontacts.setAge(86);
            corecontacts.setNativeMiddleName("S5g13rHioJBJ95ri8ZlJVdc9ExvNE4kD2MYnbMwcMtWP9w1qzW");
            corecontacts.setNativeTitle("R0KcEgJVC1Ekh7CxRM1qwr9n8omJBTh8QBSCV8ft1wN7dlmdj4");
            corecontacts.setLastName("bHDqsaHcJKMknYIXmIjDt7PoOBay6J47rc66ivYXgUQkZKSU9u");
            corecontacts.setPhoneNumber("HqAgIE5fW4n7a4qPOz16");
            corecontacts.setMiddleName("tcPhEmdk7u6TzZ59awk60Rhx9gn15TMkM5VQJg0FJUQQ9ABz3A");
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
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey"));
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "gAOI2eE7XhurmnhMf9eYg3viYHbK8zVX9SOsWhPjOjcohan3esQRZFk0rtuAGsx81wR3APgPOuw1kB68TQHAvBYpRPMNwj15RnkWlzfDD75GGnf1F5YRNErOq7q5NPFE1jh04dAcYkWBxfU7pce84eFJXHDw0xtYdxkPdr198lfWoi34cYibhy0aApqMcpFZCTWlXk6ZyItugjZOYFPAajJqafX3n4sC87jJdEWPTvF2ohC2T9t1WA6dVPr38e25L"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "h5CZcD8ofQjw9uEBEvnysx99Jcn6S7657BEQ2fjwLlyTody2aItewC4ZiQdBMRKtfviL1tZ6xI9YZfRVXDLbJaibvLOyDgLDxUnW7e71ImMdLcYvnxVsdLAzEljZw8k5Fg52Zql3TuL9PYLFstENy6xbYR2d6IIeVe4Y6MMiuL14og2JsInTfamDNROD3G1Yd1LUfq8v68fzvCUpYgVpQeMOwS9lXcBHcbppTzzMjuEJCgQjFBqvpBpd7PP9J2sIe"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "JuAnxUoxBk7Oe8cnzUgJd1sJPAz4OpBMcZh4rAdWqn8LmmXrBmzuH57wz8bgflayZ8f1qHBPSl0p7VNDZvE3hRUMXxd1BkEXrMllA97IrSwdznCnstk5lnVtGCIGiIR1rw5zTx0EheyjyioNt3IVJUbZDAuI6QBVQSuIUrldxWtzefPAt5HB62ownhJIRWEamgtepn11ZAMI9dVwJFLFMn2aNLqevsvAKPdX6epHIXXzWGhEURJqn1AbPmiXRXN9F"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "U0mBl6pDehRPC6ogjml76uzmU9tROlD9T1nkO3sibfVoQEY616uwmRqN1ubpfXKXZ"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "vTHp36GD2gRqjUHceFP6fP3ugd1iMgV2IZitu5DcK5WehsbDsDzyi8pcmsBI3HfxTp6Ek3xLkocFaztzPD4WOWR7gBS7Wm0VDRvQLpjrBDDolfFo7ajAPtCJhJEcLuhUSAlM8nmZVxLNqZ3a4qTJQs9EuczFSlUORCWRYwIm9MqUTLKuS6k2U5Ry1yR2lokspBSfwFoQKwgavBnZY6h73JzTDFkcNMOZCKgqAqFD8Kv18eifVNpBf1m6RHvIxjvHn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "WunKs6bGsuIjkPKGbYCeBswG9EuxKIpDZOGaiuj5BtRCACJFVuyIZMtMwnYkBewZY48r6UZrwmYNxWv471G0P1db5E5Z04mnzvRdmK4PyjAkD8oI1tdERlFWrZjFT8z2TnRoaB9ESa4BDUT98tvtB8kofr3IWloRx7PPiO6X922lmWDvvHe1tzHmTiNGbGwsW8qctYZGErYdq7yghnzkasC7osPX0FVGkFERi4zhmtr99FeiNNWLXMvTCvQczYogc"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "f3jRuh24y31X8GBTMVqSmRB9JVcCm4652YyN7NwRUydART9NMRfUDrUE4QOpPzn0vlQpmOXkEjn7mFSGYVcyzT27Chm2tHczm5ixi7fzet4HMaifDdDeDhIWe1b9m9rObOA0MMbsHymSnTnFMTDrn0L2o2YwbWlCsSY8hxwsomudC5x04jFVVJR4kWzyqMxGM2C9B33LKO8fAtARvi9CrIxRtqupmyJJbKtalpXg4V6CLBDeeBBogY0Mhr7Y6gqMK"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 231));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "j3Z0vMwEYnfsfv2NGbSKaemJzElpLxf8YkD65wVdO2e3F65EXdo9CkEVogDbgeSehlm9q20FYCjAjfWZjclQIBGjzFobbK2f4eME5WHT4s0nBiyZ9EXwxtr9OACbSQUXNz5nrohX2zZmyIcYjXHxQRzwjBrb4V1zTLLv4OdGYwhgCYRKzeZN40tUTci6qOl33a9jCp6RSWaTGNefP72up9Ny7C6HehHD5wPiE0bMcg6GVRUVwHeJn26tnlUAlGToH"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "GsIPu3Zcq96DpEVvExtBO"));
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
