package com.app.server.service.multitenant.customers;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.multitenant.customers.AppCustomerRepository;
import com.app.shared.multitenant.customers.AppCustomer;
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
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class AppCustomerTestCase extends EntityTestCriteria {

    /**
     * AppCustomerRepository Variable
     */
    @Autowired
    private AppCustomerRepository<AppCustomer> appcustomerRepository;

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

    private AppCustomer createAppCustomer(Boolean isSave) throws Exception {
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1474436565562l));
        Title title = new Title();
        title.setTitles("t4DiRfpbDAVJQOTFIluwwkWQQfF0moaKzJT2OwTJkSr2Vr7ctm");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageDescription("pC4up2rC0xlImqs1DMYSitcas3mYQCgnkBozqGb2PZ4ZvUrWoo");
        language.setAlpha4("kqLq");
        language.setLanguageIcon("IIv1Mmoj36CcKiEsKxnykuTXuH4nSgEUKGXfBCQwMw1tg1J7ux");
        language.setLanguageType("oAWKnuZ4vdORkpdetsom4eJEVM65EgiO");
        language.setLanguage("Rtbukn6dTAfQDQlDrA3jcwzdkLW9BJYXTPYtWPFGwu1zyeL9lV");
        language.setAlpha3("Bgy");
        language.setAlpha4parentid(11);
        language.setAlpha2("17");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("VBHEo7JmroDzJ5O08gcOCuDeD0kVGtBNx4pG6L6ZfBBHKAYCME");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("Wez44AKu8VFm42st4pkC7tcVvk7Mf9tD9eMBAAuieyLGMAI3My");
        timezone.setCountry("74XNDpvLUKP4lHvL6wpjAlTQVaBFScYdNxzh4qClYUTC0qZdZS");
        timezone.setGmtLabel("n0bzNBI4Yovbf4GGDu196rIdAwGNcxZyaayCxkGCNRibjEQuSs");
        timezone.setCities("fBwP8RNE7FKNzX66S0pMFahd3WTBA7McfVXHDc4F9rzm2nep03");
        timezone.setUtcdifference(4);
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1474436565576l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("pjRDuLKjU7Li1JcMhaIlehKTmSuGABkNX9CzdpIoyYIpJ2CABp");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1474436565663l));
        corecontacts.setNativeFirstName("7kKFzXGCDPpRBEP95lZYeaIYs6U194WTCCFBuAkYmLZRIzYWaG");
        corecontacts.setNativeLastName("DkAPzKDS34DNXqNgKjbQkAIdWJCa7SLZp468i5l5HhlcJErolE");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setEmailId("aJcFt0CYTME5kzqA7pMYiObPs76OkbAg3UYkQqq4gdTTBg5EMT");
        corecontacts.setAge(106);
        corecontacts.setNativeMiddleName("NA8gCZlVM5mSYoqra9JYtua4cRM5bMZGiNKzGNNQWOu70JAM9K");
        corecontacts.setNativeTitle("oLzofHYHqt82KfHIgLPjzPWOe2DNO542VjgUVIDbxFYl0Fy0US");
        corecontacts.setLastName("O95tC6fI68NNEyo92gNGDDpMz1tGcB4WEKzi0WbfDaHeGcvZYg");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setPhoneNumber("9lsVAG8zrGT5sPduoNzr");
        corecontacts.setMiddleName("wgndCX8o3n4VkdCbPnSVUjTUGpH4KNS2HVesNm1MNs9ICPpeGc");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("kkH2940vAx43lGtFObVZCqL0smOitpglMzhmZiX4VvBS97139m");
        addresstype.setAddressType("8r7vcoxgU2XGrpXhBK71RpvosqmptqQuTYemm6Mf3EVfrIEsNg");
        addresstype.setAddressTypeIcon("c89py3CHWFcZY4e6gxApoDR6zOU0NxHdA39PiPAxaPycozKFix");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLatitude(1);
        city.setCityDescription("Vfj5Wtjhou5QgpAYVgpfxHLlGlbV5jh2VWPQb7CLzDwrtFyvQT");
        city.setCityName("7O1pUEzfK4q9TBSgNfmUBYuc6Am7O3BDZu23KAx6Fpa8uVbmyA");
        city.setCityCode(2);
        State state = new State();
        state.setStateName("keADbik8q0VmqWfyO6iG0lS0PKQPnrXgBXQCtUKed1Gv2nGlqc");
        Country country = new Country();
        country.setCapitalLatitude(1);
        country.setCountryCode1("Ntv");
        country.setIsoNumeric(144);
        country.setCurrencySymbol("BRF2PEbUPLUjz7KOIEx4ryibsvKzN3P3");
        country.setCountryName("Q0L8y7ckkikASNOb6le2Sdw0TdCD8r2l9Yb7aryYEnALS2TA18");
        country.setCurrencyCode("HT8");
        country.setCountryCode2("coM");
        country.setCapitalLongitude(1);
        country.setCapital("A9KrKJulgbsDJwdQ7oLUMjiouXxYWLuU");
        country.setCurrencyName("Zlb4mnGwX8UTfk5Y0O5woFFtsc9xVrpYDZmd42kmPct0hSluuW");
        country.setCountryFlag("wBai8wtctqNeReaXjbg4Yy1NxhWhuK0in9hoi768jCNKscTGKM");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("CYQzbL0SFnjFtGjTUKDg0kjbycdyjQKfU78N1gpiu9su4WuTvN");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("R1nDHsPDYnsO2TmBelNoD0Qx4Qdw2yNcBC99lMAPssG6p5nLRI");
        state.setStateCapitalLatitude(8);
        state.setStateCodeChar3("tzWyUKe5WnJN5u07My8bniYbrkJda2HA");
        state.setStateCode(2);
        state.setStateCodeChar2("i2XS37Zl96TroxkiLgqnaXtaMEUKYGmv");
        state.setStateCapital("9rakrLIsV3GF4XjZkBzXo3nfffF12Mp3Wm6ueSgNi9H7yEFaVD");
        state.setStateFlag("WAyFxrOTpR5oAXFjZnU5rWgDqlvhuzjsD20SExXl8u1zIhTCHY");
        state.setStateCapitalLongitude(3);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLatitude(11);
        city.setCityDescription("1XQO1Jkt3FgdNSCpmOb82rRGkcgzbtg9abaAxSXu0A6CK9NGgO");
        city.setCityName("GNznIn4LxZtmSDqrhuGIWfg5eMoJjNC99BJRKjLLTxBlVebsbB");
        city.setCityCode(3);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("bhIEgfCQhnnOMzBA9rovZF58RwID8IGV");
        city.setCityLongitude(10);
        city.setCityFlag("59DlGWKLpBa5Y5YjCrPxcEHpmsC0SjvcGavBoKY7MtRATY5DR2");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("C0ABpxgkmCpRakqGcOASfMUPlyqpAVgPmwDVCB6MTXKeefXnYU");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("gNCIvqeymje");
        address.setZipcode("0iCcBq");
        address.setLongitude("xOBFhnIw52t58dDG6oLMLSd8OIFiwd9NFXsY2b6Vu1MgRtC5vE");
        address.setLatitude("aPdr8yfkVAjMXDWtJM7yaB84svjcDQYifQnn0BtLatLJMqpEvs");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("Hd6o70BGmKRIHoNS5lKNRfJV6epEIBEssWZU3B6DAR2VL7GiDG");
        address.setAddress3("zmHSNxTYBwNyUPPGWTdBBIGxKoDQWTI92vrgcbRcIFGVjx6QqI");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        CoreContacts CoreContactsTest = new CoreContacts();
        if (isSave) {
            CoreContactsTest = corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        }
        AppCustomer appcustomer = new AppCustomer();
        appcustomer.setEndDate(new java.sql.Date(123456789));
        appcustomer.setStartDate(new java.sql.Date(123456789));
        appcustomer.setCustomerName("2hKvhJtHkPJJv8IneI6Ga9vsN8Mm2LIftfjNO3Qa5dw6nPPtAt");
        appcustomer.setCustomerStatus(1);
        appcustomer.setContactId((java.lang.String) CoreContactsTest._getPrimarykey());
        appcustomer.setEntityValidator(entityValidator);
        return appcustomer;
    }

    @Test
    public void test1Save() {
        try {
            AppCustomer appcustomer = createAppCustomer(true);
            appcustomer.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appcustomer.isValid();
            appcustomerRepository.save(appcustomer);
            map.put("AppCustomerPrimaryKey", appcustomer._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            Assert.assertNotNull(map.get("AppCustomerPrimaryKey"));
            AppCustomer appcustomer = appcustomerRepository.findById((java.lang.String) map.get("AppCustomerPrimaryKey"));
            appcustomer.setVersionId(1);
            appcustomer.setEndDate(new java.sql.Date(123456789));
            appcustomer.setStartDate(new java.sql.Date(123456789));
            appcustomer.setCustomerName("GRihtNQ1NI5yhEjsiGvDymYX89ROUgEbszhno3BF3lI7Srjukq");
            appcustomer.setCustomerStatus(1);
            appcustomer.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appcustomerRepository.update(appcustomer);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AppCustomerPrimaryKey"));
            appcustomerRepository.findById((java.lang.String) map.get("AppCustomerPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycontactId() {
        try {
            java.util.List<AppCustomer> listofcontactId = appcustomerRepository.findByContactId((java.lang.String) map.get("CoreContactsPrimaryKey"));
            if (listofcontactId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AppCustomerPrimaryKey"));
            appcustomerRepository.delete((java.lang.String) map.get("AppCustomerPrimaryKey")); /* Deleting refrenced data */
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

    private void validateAppCustomer(EntityTestCriteria contraints, AppCustomer appcustomer) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appcustomer.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appcustomer.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appcustomer.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appcustomerRepository.save(appcustomer);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "customerName", "XGC7k5QcsEghNrDbO8hBExH8pxuW2CZYmliCw2CuFfA8gl2ynHwwe8EU1hUoBgiBUCZERSAIwwLy4vbgkDsXmInxmAu7yqS3sQMqwoyE8L2Dmzs1nN0BJiGhMDmdU1dtnK7eXZTSRxH0fsIKweS3rGDrFs9Hm9RNdZCWC9enSX2uYiKf3WVBfopxcGc0nZozcGqVU0xPlOsljrGutWaHn0HPxQhjwkjDkMAQXu5gxRfSfXuSCrmkQODRLnLnoThD6"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 2, "startDate", null));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "endDate", null));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "customerStatus", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "customerStatus", 2));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AppCustomer appcustomer = createAppCustomer(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appcustomer.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        appcustomer.setCustomerName(contraints.getNegativeValue().toString());
                        validateAppCustomer(contraints, appcustomer);
                        failureCount++;
                        break;
                    case 2:
                        field.setAccessible(true);
                        field.set(appcustomer, null);
                        validateAppCustomer(contraints, appcustomer);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(appcustomer, null);
                        validateAppCustomer(contraints, appcustomer);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(appcustomer, null);
                        validateAppCustomer(contraints, appcustomer);
                        failureCount++;
                        break;
                    case 5:
                        appcustomer.setCustomerStatus(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppCustomer(contraints, appcustomer);
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
