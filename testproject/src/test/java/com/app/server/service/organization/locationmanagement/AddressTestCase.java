package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
public class AddressTestCase extends EntityTestCriteria {

    /**
     * AddressRepository Variable
     */
    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateCodeChar3("drIrdCJbHiU6MblKdW59rK6BMBEvqPtU");
        state.setStateCapital("cuLlqE2nmYSmMDGokrjVF8gjWpxte5DYWssIr1v0mxhpVSF2RE");
        state.setStateFlag("J9uWVG5rcW1ypWQyDIIcjQf2FTl3BTLga3E2BpYSJEdyCS2X8E");
        state.setStateDescription("f9rxCfKHKcUi5Ja03UD96T1kqyp04vzwOKCwmffDRuvRbqnDG4");
        state.setStateName("DS97r3zGOgXkMf2BbJNwW1o7tp3g7oTx2N29tZuStxb5n7JcuT");
        Country country = new Country();
        country.setCapitalLongitude(9);
        country.setCountryName("H4IUsvM82FmXtARgznezqnKjgcivv7ibGxStpDo2ptcYWwMlXU");
        country.setCapitalLatitude(7);
        country.setIsoNumeric(839);
        country.setCurrencyName("nZ59hU73mHACbru8wk93rnBaEcLASfKuDKCHNMVMkSiMW8b5Nk");
        country.setCurrencySymbol("y2UsqvqjbjUsrGfR9porjOOpK29c1pX8");
        country.setCapital("PYEYxr0mBdn9d9zIPgdURFMl6scUUqN9");
        country.setCountryCode2("O3Q");
        country.setCurrencyCode("0t3");
        country.setCountryFlag("hEPPOPKZw4gW3BAkNI0xS5mZlmf1i9yZR9398kYAMGEHMoHnHo");
        country.setCountryCode1("xtf");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar3("C4XepmHjwCg540TSvHZe9mUCGmu8VViG");
        state.setStateCapital("OOfpNawOGN1TJK8VJEeW3AgdSrSdNg69ZRec0Xy1Nou63aglN8");
        state.setStateFlag("2SuKyMMpDZfxjBoEm7Edght0HOIOTlZOrRnwsptEFWBemA60zN");
        state.setStateDescription("AAvNckGocOz124VxQqMeGLJc4mxt00NGiCe7WJs7yKUeXTuUz4");
        state.setStateName("nF3QpK6cZAcb4NmKYNHQHZuz2RrVwtZAJB5xS63ahAxLSMnNwZ");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(1);
        state.setStateCodeChar2("lIAJ61yl6PHWV4sa4TTVGOvceiLs9lRu");
        state.setStateCapitalLongitude(3);
        state.setStateCode(1);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("ShZ9zzt6dUr4L4HJ2zjADVOxgvrjZ6eb");
        city.setCityLongitude(11);
        city.setCityFlag("nqdadE9tyhrgvYMLv014apsC58sGJKhGHa3GP0m0QldaoTy37O");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCode(2);
        city.setCityLatitude(2);
        city.setCityDescription("AI3XNm5Jo6JV9uEknmNLRyD0dQeOkCMWK4nQkjtcZiR03piwjm");
        city.setCityName("UIKab3Gl0Qk6Tib2YUcF90IHqJflQuqi2qXRQOG8Ys4HoI6PRY");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("QtCgJ7Qi5JX5jcKIR7H8jispKympH6TL8y2kIMxbbESSmx8MuN");
        addresstype.setAddressTypeIcon("99TuWWQM7o2liQ1Wc5u32qP07HBPgkXy5zpdvvuOT3duqSXwD7");
        addresstype.setAddressType("prs1PMqjC6BqtQzhf5E23YHrP0dlpUoSbFLCiJSCMFXBo5RvS7");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setAddressLabel("jTIIS26Roan");
        address.setAddress2("FuqPsdvI67r57vhsRuGOW8HU60AjhSZuHIOf8Xv1NsKEkCxH1W");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("Vic7SmCcWNEQAKMxqu0kiuIwyUFeMJ74yEzcrgfumv6Fh0pxZF");
        address.setAddress1("vH2aySVOOO0vNgavGM2uPgqHRL7GfbP67IplTnHp3iBrhsj4Kd");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("5M4Mux");
        address.setLatitude("szUS1iWgyHMhPw0LPJtlFHEIETxMIJ57NP8XkkTmnWpOqtv798");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress3("Ef6cFz7ewsdRSJGxZirTYg4DlvbjOBazbosfL72pyELpocvW8d");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

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
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddressLabel("atP4KJMEEvP");
            address.setAddress2("6lA6yhH82hbMfr3MjnM5ETokP0xVAyNkuaHEKpRIjIDCOvL2cB");
            address.setLongitude("7Km1qZDPL1LCzhnKczyVWq6P5CYimTFtbpmZnF2BX0qzdIR4xi");
            address.setAddress1("JgoQ3kOyd2Bd5ljZpUJaiAYgnghjMCRfRgjN3vrsZGDQimrzKA");
            address.setZipcode("mACYzo");
            address.setLatitude("rsgYVbRtcIhXjmw8Qy2pYbQNL5qLFDgzjrk0oaPNVo6ZiaE52r");
            address.setAddress3("VglSdAGEKYjHtLNH3vR1ORm9OUABKztZYPJ6LPRaQYQ2HRQ3XL");
            address.setVersionId(1);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "nxabVcIiXi2D"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "Pddvvlv6byAzgzKxdj6iEIMMyyE7jLMoEaaLxM4hwoYjR9lAu1SGkXKb1"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "CQ7g6BWctanSzubMLajIuRngoBRA2CFM9v3JiVAR9X1C3wj8pFbjTmmyK"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "UiMOJwrYl8zFjV0TcEl41vvJENWOJcfmXb0b1KyKAe2AkPHSiZTGdquvZ"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "eAjPNT9"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "uoHJyOOQ4NEvCaEmuscTw8g4BLbf5HyUymBQ3HZDPV7zIXMWFJPz3izLB25W87eFR"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "CuM99RA1vxI87icVoJmCpAnB31hJ60OWwVLse7vykDeyGcVlisaELeXIl7Ejc8CWA"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
