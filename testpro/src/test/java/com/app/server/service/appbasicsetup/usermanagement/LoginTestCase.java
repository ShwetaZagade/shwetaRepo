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
import com.app.shared.appbasicsetup.usermanagement.UserDetail;
import com.app.server.repository.appbasicsetup.usermanagement.UserDetailRepository;
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
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tenant.id", String.valueOf("appCreateValue"));
        runtimeLogInfoHelper.setMultitenantFields(map);
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private Login createLogin(Boolean isSave) throws Exception {
        UserDetail userdetail = new UserDetail();
        userdetail.setPasswordAlgo("iDPZA3LkldX42sK6jdYdVxDlwdTSmuJ86j1V3PVlBilE0Rczav");
        userdetail.setGenTempOneTimePassword(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("gdeV0bQX0Xc4JtYSPL6sajWfN7zW1k5HhzSFazwz5XHrywEoAc");
        useraccessdomain.setDomainName("eCro9yNGJbLxE7IEBCdftpURmI3CBgbFSN1gGqucUwUb5MODzk");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("FCmT7YyFVvnzEwRXCimug7SDcyG0iEqd66KIZVrZjLIUws7ydD");
        useraccessdomain.setDomainDescription("5p73drAti8nZJpNk5hHCTsctO7lMu4FDrGNz5YCJYaZAviKhyD");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("57njnIay0arrQ0fjWWbYi26H5yACOnmBwRryx8oImkatQU2RvA");
        useraccesslevel.setLevelName("fvD6r1Nnk5eiFE7jvTYEQ8NUXQL9CGicNxcQK1jQbfGrCByF4u");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("ZTzuZFSvWBGFHGASfvAnyWL7tBJ1dZM3FZCczKCEwtFY6OW6v3");
        useraccesslevel.setLevelIcon("OmIcHN96vDUMzwXHOJVTfmBMGiHDVlhfEQ4ri0OJZ2fAdp2FQa");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        userdetail.setPasswordAlgo("pVy8aooQIQ05RZicqEu5326J4yS9VLj6AFEW8d47P4iNimzEmb");
        userdetail.setGenTempOneTimePassword(1);
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setChangePasswordNextLogin(1);
        userdetail.setPasswordExpiryDate(new java.sql.Timestamp(1474436558800l));
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setLastPasswordChangeDate(new java.sql.Timestamp(1474436558847l));
        userdetail.setAllowMultipleLogin(1);
        userdetail.setUserAccessCode(47601);
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setIsDeleted(1);
        userdetail.setSessionTimeout(2723);
        userdetail.setIsLocked(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("PUHNVYpmjjCt8KQ0tC825uJBBBnrgrIszMRILZ55yh3pzGXf9t");
        Question question = new Question();
        question.setLevelid(3);
        question.setQuestionDetails("FK14NvY6g22eVUeVr2dRj0iOJzCZd6uPTC00nGjst6OaQudIb0");
        question.setQuestion("VaGHDL7UIdACcZaihNNV70R8qRlKQ5zr3hLU6h910GI15QMa1q");
        question.setQuestionIcon("NOjlXAj6AeTKmQtNyZCkWrSlHDujmNWO8bfVc2MwJYvZlO9NHN");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("eIdGteMSeKGBkhXDPr73TUOvE8PAqUOk9Q6yxYQlP7P9jGvpL7");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUserDetail(userdetail);
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordExpiry(6);
        userdata.setOneTimePasswordExpiry(1);
        userdata.setUserDetail(userdetail);
        userdata.setPassword("cNgeHK6yRGjc8qzP71mVvymnmUejSUa2DtR80fkCCngZKOBKS1");
        userdata.setLast5Passwords("smSnKEe1Mjc4UyV8sjFjDHDi3p59yPyLtRUhdxoewVdNogQ2lm");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1474436559057l));
        userdata.setOneTimePassword("t34L1pTfAXZWvbuqBnnS4lvAsOIiaHdM");
        userdetail.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1474436559146l));
        Title title = new Title();
        title.setTitles("qWXEXVdT1TiIJqW6MVELtWoaY9vRMjm0g8PYZP6zXa189cdLdc");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageDescription("MkImSguXbqkHooNsXD0W4ueOdhAgIXSzhgRV12jH9kAIOvlUmj");
        language.setAlpha4("mAj6");
        language.setLanguageIcon("FSKFGei0DJDQpeOimlvVs00pGb3vrYenm62EgomRUjG5uCBIRa");
        language.setLanguageType("ST1AHcfVZJmNKOvaXrZBdeagVQrffSMo");
        language.setLanguage("l90Sh60sv26Ww8gyurLuVGtibfL0K7SGOcFNP6hq95L6wXQTsk");
        language.setAlpha3("bUE");
        language.setAlpha4parentid(5);
        language.setAlpha2("Lf");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("Q2liXChktM9wE5Xy05xN2qXHpdHeTr4LvjG4D7c3j9qmHlx63S");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("fvs14PlO7rWpt9IDl0t37xHVoSxV9jYk1ejR1kKhmUGpJTKIsJ");
        timezone.setCountry("jPwX1Nxv4Fs5JxaeN7KvyLC66vQR23BjbzTutibRx33SkrNQro");
        timezone.setGmtLabel("SqgYz7BGsw7nucKwmVQKPmtQKVspQ2tDtXgaUufwDC12EIruny");
        timezone.setCities("c4driNb1aB5vwLiGFe1DZ8SenUGig8fwt0x2PBW9HmSx5TgH00");
        timezone.setUtcdifference(2);
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1474436559165l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("iupNySOZXn7DfCSSy8VWcrHOOa42wp3NO7JSvTpn8r2uHVW0Nf");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1474436559237l));
        corecontacts.setNativeFirstName("Fy5JBwD72qsptTZ7LYsC3Rrjtve9MoDdjanMFr8REyQJ9kPG7F");
        corecontacts.setNativeLastName("3ojzJd4aWETF6rKgdEdpjevnpFi8wuglf5h1FCOKblje2Hcqpo");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setEmailId("eSjhkUvoDIrgB1ZmGTjdVBl72OFokGYRtjvODQOVilITGsh84x");
        corecontacts.setAge(23);
        corecontacts.setNativeMiddleName("JRU3kuhO4DL8IQKjU2jbq2yuZpV7uAM67G6D5zdRORYnIJnku5");
        corecontacts.setNativeTitle("ViopzTcgJY9pmEepA3BmVhpbDu1ljj3yLx5xx8YkBnZhzraXIA");
        corecontacts.setLastName("NRKE8bVDbuyYOkr8Qu1Ffa48vkUSkkJb42wdDs7n6qrzYQcc4e");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setPhoneNumber("ugSNY9ew4gxXpEouQRWc");
        corecontacts.setMiddleName("03SOOa8NmYwU0nOAJVbz06SBvnml4xlfYOSyAk5ST4ybscXWEm");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("TxTebCi9UUMSQLgXxNQ1J7CUZZYqTpWy9lRj4dMSeXeU3b2W4B");
        addresstype.setAddressType("SiBrqqe8k49yLlSbaVbAr7P7MHmocUzuTynvAzSYzuwONQlqXS");
        addresstype.setAddressTypeIcon("PU5wlozIxQgC3CcvdNOzsp3jzYBQdoP5fYY5x20zagsqghFlZu");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLatitude(7);
        city.setCityDescription("oywMI2WgDbRoWldVCFxXmpM3ed0hGbZ8jKuNr8r3Nw7qAJKruu");
        city.setCityName("gPbLO8iOo6RDkvDQD9uOUbyBXkwiuk2tshO9302LvqW79kLadz");
        city.setCityCode(3);
        State state = new State();
        state.setStateName("ONKSwMg8VuiBdUQLjt7FG94yVN5OrUeaXV9dtIJQAes2E6G8gQ");
        Country country = new Country();
        country.setCapitalLatitude(11);
        country.setCountryCode1("Oo3");
        country.setIsoNumeric(635);
        country.setCurrencySymbol("0xI0iU5llUE8tQ7sa8uqB9YglLqP54eb");
        country.setCountryName("ddV05N5brBIreOPbxcumg50MZsOHdlKBb6Be8cPz14rpHXMvzC");
        country.setCurrencyCode("g2V");
        country.setCountryCode2("Eqi");
        country.setCapitalLongitude(8);
        country.setCapital("FOBzU3BZZdX4vlbY6avu6xxfHlC0ICjV");
        country.setCurrencyName("erbqg6pEhZvQHG6OtkZ6TrB3YJWexFBmQExiKdjadnNthjTi9f");
        country.setCountryFlag("NOtv2rGXT5EpxDCohVM472TQajP6Asfd3dha2Is75bkK6YHIs2");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("JZXyCXtqU3aQVQtB8wvlSdhNkxVVUrgvYcHifAoVFg2LhoyOqo");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("vonD7hESeh0OxL3wrAqtlohbSW2kD6hTtT7IyyLz1iAOIZ1g3k");
        state.setStateCapitalLatitude(8);
        state.setStateCodeChar3("M2YrOezIoElgLwWYy8zsf3rhJeS4Rc1C");
        state.setStateCode(2);
        state.setStateCodeChar2("1ME5T3G9qmurC8xtmj3rgg4lecWk3KLl");
        state.setStateCapital("Injp9LitrkKO1rgYRLhsaaiSardGWX6wja1C2rKFpikOePNbl6");
        state.setStateFlag("3xGzDT34f0qLJb9sxrJqvRWSr0n6MBKJJNk889Y6n5rk6s7JV1");
        state.setStateCapitalLongitude(10);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLatitude(6);
        city.setCityDescription("88SJH9hRtKPOTOcJBJCRxHsINtuVKw7X796PvKxDT07SbJxFDR");
        city.setCityName("BFRGl8PYi2f6oV0CzebcuCkPCT1NxvMSNqD1wcq6GrrXQizLTv");
        city.setCityCode(2);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("MdunNU644sGhvsJr2OdSoFIziHhtoKxJ");
        city.setCityLongitude(2);
        city.setCityFlag("zRLPwrxiDTfxeqlqKtkDARvJPFCQx3zRaMYqOW5LnwzfUqJDSp");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("k7rd78nV0CBaCmvfIGlup81dvxTUu2uz660ITa4Paf4Z2D0G3n");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("qXf6YyBaxTZ");
        address.setZipcode("OC8Ptq");
        address.setLongitude("c7lBLkzpyMiVrrFRQdVWdwc5YKQNcvNv0rkV5eMzzv2ctrerwH");
        address.setLatitude("zIRmI6Avy9MOPeUOVwpf25kepEWK4FDbXY7RUEg1lUYS1tcBED");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddress2("yLhHzl4dbWVbz9RrUacaiFLFdrdET8TCwzjEIVZ65dT9GbDg0F");
        address.setAddress3("Rmhk2DkKzlRCpcWzedCwV2lOfYSdXttF6o2iJg6brIre7k5jKC");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        userdetail.setUserId(null);
        login.setUserDetail(userdetail);
        login.setFailedLoginAttempts(9);
        login.setLoginId("REDPKhkHKdcNvFKtNROY0IQMkgDeBHgm1jRPoNihdZQXrHZuIL");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthText("TYOs4FYaOsNjUeFF");
        login.setServerAuthImage("RkBTJLc4dpzWu7vZa3hsTaKhtGPJxumX");
        login.setIsAuthenticated(true);
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
            map.put("UserDetailPrimaryKey", login.getUserDetail()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserDetailRepository<UserDetail> userdetailRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

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
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setVersionId(1);
            login.setFailedLoginAttempts(5);
            login.setLoginId("nhMtdTgstDndYLGwL7jhMxZrVEOTCWDoGoBqDUiKlz44KdvBRD");
            login.setServerAuthText("amumbbXTuwA4pl0A");
            login.setServerAuthImage("Hv8lmswkFlrWAUIXxhXCDx8N8TTphqtK");
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
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
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
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "1lg6K0MLpDaGFc4r4FtwAr0JhmSpBn7QwGBWvm2B2tWF7QlhnJnQKUsI5UEIFRbZUz6r11Vrs5q9pCF8RadX0kodV8Q2cYHIFlsjHZjYSkroBOmyxMjYe5EzkvqYVMVYFAuSCHfCtrgjwiO20hyZG8YPWc2O42n6Z3A93104XVGWW5P5ysd95tmYHNPRlQCiNfz4zXuZc"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "pLp1jNODHTLZcoICUM26GoN6yrm38bdLP"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "UK6EeeEepJvKSGGwE"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 13));
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
