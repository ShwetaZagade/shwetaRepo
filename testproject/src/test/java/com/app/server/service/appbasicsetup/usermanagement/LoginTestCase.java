package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    /**
     * LoginRepository Variable
     */
    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1474275703481l));
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        user.setMultiFactorAuthEnabled(1);
        user.setIsDeleted(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1474275703481l));
        user.setGenTempOneTimePassword(1);
        user.setIsLocked(1);
        user.setSessionTimeout(2349);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainIcon("KvSMNqS1yVLjl0LbZHTxfnWNg2ZABkoABIAeqCsUBK9ArhhLXE");
        useraccessdomain.setDomainHelp("MDDqHdB5rnLKl0ADnfS9GHLmS4jNV6kNKtHsC5n8ddd8mcwpsh");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainName("2HtdcdgZAaJPpG2HoDLuLJPYJuGbfq1aHB1WHtKsVa6rKrI5Mc");
        useraccessdomain.setDomainDescription("gxmx9yUJaz6uKaknn1tuLHFIz5ZwIDOw4358pRtygZFkKt5fF7");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("AfpAaEOrjwI75JYbaMQ5wMdUPVLF0zBTJTNmBVRQtnSVj8mXw2");
        useraccesslevel.setLevelHelp("1L7RIsfg70l6qr46amJLxmpZjc1Y03bQymafKgd6wC4zBQptuc");
        useraccesslevel.setLevelName("yg2eRaSBZMxlDnqjrYJpY6YGwptV24LtfgyHgJh36NJiPoRqas");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("JXAkH7kMeO2uV7VILJETWe2KNPJdz1jqwKnZkHtvTJtAAF9BXC");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1474275703501l));
        user.setAllowMultipleLogin(1);
        user.setChangePasswordNextLogin(1);
        user.setMultiFactorAuthEnabled(1);
        user.setIsDeleted(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1474275703501l));
        user.setGenTempOneTimePassword(1);
        user.setIsLocked(1);
        user.setSessionTimeout(415);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordAlgo("Y0it5jIO2ZBlJpiLzCqUjhDtgJFXn7UHRb96b3dz1bMLEZ39ci");
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessCode(51529);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionDetails("faoa0Ef2VlVRUaPMBfLhmJV51250wzzdgp47vVnLbNqZ1lKivr");
        question.setQuestion("J3V9hXNOG0I41h5aMMlFp2BHGGuMHxVYi7dPCbdMAketF62Xwc");
        question.setQuestionIcon("7ZTUOjlqnkYZFGk7rZCF3Si6JVake4rQS5u2rVXHLqTiSq8VfW");
        question.setLevelid(4);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUser(user);
        passrecovery.setAnswer("Ko1w2wTr5xrFhjxNeiYvRMXRfrYjgtqfOccYFN69ucBSonKSbW");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordExpiry(5);
        userdata.setPassword("TMaoRyCotd5mJDkhXiWmFwU2CL18uYT1iLyfw2aNoGrsIz2gF0");
        userdata.setOneTimePasswordExpiry(8);
        userdata.setPassword("YV8SzvzMm8yaOWDU5VMeperQXS2KcCcFQjZl8pYjQ0O1lljmbm");
        userdata.setUser(user);
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1474275703728l));
        userdata.setOneTimePassword("s3l9CPmd2d55LZVh6aTnz6XB1qIsjaD4");
        userdata.setLast5Passwords("dS8m2a49ksrHJForld7fhTy041LDCYOhsEbXN8I7XVePQhjwOI");
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setMiddleName("BFKkqxr0a3ydGsvsQdhPNwG1sBau1kJe1NsrdCJEbTLDRLfkRB");
        corecontacts.setNativeLastName("4yeOTIJODElU1GftK80hZW81FcArNRBsxOe1XfOnPdJL5xM7Sc");
        corecontacts.setPhoneNumber("eOe56V9H5btjlVmBmQt6");
        Timezone timezone = new Timezone();
        timezone.setCountry("lkkBsbBYwHZWU6UVC7Kb3Hj4AGKaxfcT6RKpqEK8J9IpxrOXxS");
        timezone.setTimeZoneLabel("8yCelJqndWtxOnXbcMArq1mYxQdgZR92KxEnG7czDQTrYGTA2b");
        timezone.setUtcdifference(10);
        timezone.setCities("tEnI2o6BFQVrzlMJ1ejTcph26n0jBPvG2bAsKndB51F8t4vMWf");
        timezone.setGmtLabel("XBcgRJ77Clmyt5KBZsT2r0dkGE949Its4aRofrMqpvT9gDUV2t");
        Title title = new Title();
        title.setTitles("hJSjmFSpYjlemQ3VgYOS1lX2FXA42nGdocUuxsDxJqLvJhuLKp");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("Y982Asbnx9Irf364FIgAEy07fCn5q8alhjE0WelBfPA6JxNIN5");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha3("9rg");
        language.setLanguageDescription("r3hhSQ7gQBBErBwcx5OlHZ0jcSMrrBgtG6q3NhBgj8kYzMIquu");
        language.setLanguageType("9M1FhAjkCdFLk7j4zfUeLA71ZZYA6npp");
        language.setLanguage("zeTrvD7ss8LbdT3dQJJ84RdJ0ZmKz9rmDwNLpJB3refhtGupWf");
        language.setLanguageIcon("q7XTGkMWXb2qXRdbo6dyp5FyBrqtjNz0Ix2sNGxxjG5Kw7BHVT");
        language.setAlpha2("Sz");
        language.setAlpha4("cuUM");
        language.setAlpha4parentid(10);
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        corecontacts.setMiddleName("lcHAsMoaFrSL2PSuTaMYZr0QhxzeYwBogfc5iWNW6LRY7pvfdW");
        corecontacts.setNativeLastName("lGXcLnW4rZbLI5VUcveKiWCxlEuePaij8dt5NeEhsaGiTY0qGQ");
        corecontacts.setPhoneNumber("mHlwP3SD0jHOUQ55aS8B");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1474275703817l));
        corecontacts.setNativeFirstName("ZLAgH0w8iXjcY9TU5hpugbQ98vl0RSoi1IisUHci8vbN6K90ka");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeMiddleName("QPg2pdxP8yNoXOaIXgcQDpVRGAmWGr0dRkWAo1tu39PmeApDB2");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1474275703875l));
        corecontacts.setNativeTitle("P01uuK9hdU39VVQVwqRhqP5M7AAxnRVIIEyouohGnA6e104XQQ");
        corecontacts.setAge(125);
        corecontacts.setLastName("cgztLDuldrNVkVlRAqjYqwoWSDg4wRmKDsBcbZqGwNWw3c8Vud");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("hrT6U2ZWoUR5lxSjaMXRBEI5wOh9B9zOLm38jWO7Qni7BcHUYV");
        corecontacts.setEmailId("sZH7rQQQYaDyfrohzYRAam9JOF1ZogBMoY7V6e3rtjXcHBuY3h");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("MOPD7S4fBqM");
        address.setAddress2("5PzCa2HlVWTLlAiKGwVJtsx2YmA7a0pPLrSF9Kwe9hMjgF3ZfA");
        State state = new State();
        state.setStateCodeChar3("mn1j1FuiR847GS913HewsKwIgpaqP4ix");
        state.setStateCapital("nDRt2yKAP8ZuLKpUDIuhpql2yG998NXu5Yb4K4953GldlxHUkc");
        state.setStateFlag("2mMI74zpLoEVj0yMa1k7siZWKHJXlETD0e9w4tOvCkLav3fUEw");
        state.setStateDescription("vCfRe8KXT0oDzu6xLLnxL9Ue9X1q9kBPxTru69FgOLjnu3Qw6o");
        state.setStateName("pYUpFO8ZSn8WZVB1wlZJdXhCR3aaiHLwjmV3ZVSgqISMMVdvzI");
        Country country = new Country();
        country.setCapitalLongitude(4);
        country.setCountryName("Y3pIucZ8Tot6tGwhr3Fs8JuCQoPziUDHTmxnM4eUuHvziKqpTa");
        country.setCapitalLatitude(7);
        country.setIsoNumeric(106);
        country.setCurrencyName("29cUo2v893sqE5rf93aFXH4VBO5pkZdYCqq8sQ3EPcAT5J3IiN");
        country.setCurrencySymbol("aY7fKB7dZnlzxIZWge96aSDcYoE94TUo");
        country.setCapital("oK4ko3p5TCWp2yadX2JdqfR9Rp4PKl0n");
        country.setCountryCode2("ciB");
        country.setCurrencyCode("Mk5");
        country.setCountryFlag("9jVj8tMopIiaGfIW1726UIdanZQcr24HTxqMUpW3a6lp1jaNRz");
        country.setCountryCode1("lVN");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar3("0sdfahLvVsEeX7obrX04NRIjiCsnZQwd");
        state.setStateCapital("TTdHYgWjGt8KgsVoZOosaXYqbmHWxTCsVoKTfS7E8d2oUEp0JK");
        state.setStateFlag("O0IlX67ov7PKvkpdUWLYslJQioe7KRx3CS9mEKU1u7tPkNjdDv");
        state.setStateDescription("i1eubZ6JPazAq0tPjA4ED8bNSNK5fE8oMzjcl3ko320gpJ4TEP");
        state.setStateName("sBcoxiVbAqXchKvdaXeTAmrSW9469MQ8xAZEE4XCDcKgpznOLY");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(11);
        state.setStateCodeChar2("p46rKeXtg7ObcFH4sunHsfhQmqc9co2i");
        state.setStateCapitalLongitude(3);
        state.setStateCode(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("IRfjZTKlNS4Gq2CwGJvFKGM5RBK79Hdz");
        city.setCityLongitude(1);
        city.setCityFlag("Gn133GWNwFDgTestYaOZvdtfF8lWoa3N1IocsY2AJRCuX9BxMd");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(1);
        city.setCityLatitude(9);
        city.setCityDescription("YHCBaE2zfhaBiNcaNb9egtpgI16TDLbgG85rBbAs4rfDCxrkH8");
        city.setCityName("YOQW0LQFJwjr203dzWUAObHhUxFWz2S8QWvgr3jBwt1At0hwwU");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("VD67eCSZbwGrfXKsVhT3n20n0kofOb85W8uiCW79HrpM3vpYq9");
        addresstype.setAddressTypeIcon("XAv2rO5EcxUG3QNzy7L8IWuEWqXaW7dwnVRZYwX9CLCU4ZZ0Er");
        addresstype.setAddressType("dk1coz5E9QZ1FnmABLOvFP7oqZIsc5OLE7sUWZUIAmVGbqhmD1");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddressLabel("d5UIhZnZU7h");
        address.setAddress2("kweltj6rWKIMLyoxtsO84MJIFLcfM4kwiIg3Ge7Bf2g3Sk6pB9");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("KGZi5zScWs9LNegFee14N6sutlPN6W2CYPR8orNVmrl7X5moJl");
        address.setAddress1("3HlAGcxvGmjuuswb1tvrNdZraIvjkKb5P2NkvKsimNoSgDYIxx");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("mYxDZW");
        address.setLatitude("5TCBzV013lx1tRreBinLhytj7kNK1avA3oVLFG92xa3OQH0FFs");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("ZS9v8uqWckdslBjbRHB6rcHlwGLC9haF9ZBcnsAzntZf0qfWSK");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        login.setServerAuthText("c60eKQO38uoSWvS4");
        login.setIsAuthenticated(true);
        user.setUserId(null);
        login.setUser(user);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setFailedLoginAttempts(1);
        login.setLoginId("8Uyp4Zce9x7AXSadJqwbCBTyMwcJxZijVRzEsXoutGxuWL0Q7T");
        login.setServerAuthImage("XHXh1oYpYqbKiucDsfhhKDeTYImeGbEt");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthText("4b5yV1b369FoQ2Uw");
            login.setVersionId(1);
            login.setFailedLoginAttempts(1);
            login.setLoginId("OOWrbHQebrQrBjmCLMDRXBvVnCtWHvWmKL2KtsQFeTuLAaUTfx");
            login.setServerAuthImage("oS6xTgySKN3p7AM7v8qzGYruC1mq6czs");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "h7on66wCHiEazI7LECcAYQPvLQKSWI88gjNh02lHNwRV2xBzOm51Z5PEMgIsbgVKAfEk5xIRXkeZf03jNzjgzQXqyKvct2sgGcLVxRysVtnHcTRGAhZVtPRKMxl90IoaeHxIEPWNUYUpLSLlgZlkjffYca4ovQqDlWTHJ7baBCJmtFKIzBFQfWWDZ3JDmchmw2hLJXVAy"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "6kHMiiOkQBFvCDrEynSjY2ZgJpwl3Zi6P"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "3hobzMC17fD96tJaq"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 15));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
