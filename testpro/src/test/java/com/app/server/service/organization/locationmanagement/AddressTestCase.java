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
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tenant.id", String.valueOf("appCreateValue"));
        runtimeLogInfoHelper.setMultitenantFields(map);
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private Address createAddress(Boolean isSave) throws Exception {
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("Xs7MYUPjNeOhHfsTsFod3pYYFlfQyiuzVk1fxDr4dm4FJ5cFio");
        addresstype.setAddressType("c2x7roHVcCJyvk36OYvLu8C44o4x8iOPhJJg3gfJx58rjIuT2Z");
        addresstype.setAddressTypeIcon("egouy5wy8Hag5Knr5aNiJhGgZgpl8UISwbIrNQ9LSuttRSZ2fO");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLatitude(4);
        city.setCityDescription("o0hJMuqjG7uksZ8JaOFTYTL1Nk4LTT8QUTK9VPEnKZz7ZJRPVr");
        city.setCityName("pFOC9HaML8venUUGQwZ8kC0x7hnJKwlsPYl7frhTm2rvGLzscW");
        city.setCityCode(1);
        State state = new State();
        state.setStateName("yvMSVzEKMSJFJo0lEGuTyfzKifbOBnaHJHbMdOQEISt8ek8jCq");
        Country country = new Country();
        country.setCapitalLatitude(6);
        country.setCountryCode1("phc");
        country.setIsoNumeric(292);
        country.setCurrencySymbol("Ru9eweH77BTyyMipPPN1E24UgoEHEomZ");
        country.setCountryName("uxZqIMBLzXPHAWUbtEBiDAyU0SSP7uAZlmVHChlCYmqSnoRX24");
        country.setCurrencyCode("eg7");
        country.setCountryCode2("goU");
        country.setCapitalLongitude(9);
        country.setCapital("hpdlHOHJhUNEE7BOtQlvvNKK44HRzWHt");
        country.setCurrencyName("dgsPXseGn9OBNXOFc8Zk1ChMsK9Ao1RAtSdYWqp3vdQDytgK2B");
        country.setCountryFlag("qOSTJ46hHO8BQiFJZGkpX4N2uOVhbpwjyLwGMvA4ifjey6tPzx");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("EY0eZ4d29tWDVVj7MMD6hl4K9YlOk53m9hA5wvNDgG6U0WCKF0");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateDescription("Hx00XdQOuvJnOVq5xutp4dzZKtVFwRy5pgdwn0Cl46G98K74YH");
        state.setStateCapitalLatitude(5);
        state.setStateCodeChar3("5hGXXt54nYDm6KxYmDlvDQKpIQrs4mj2");
        state.setStateCode(1);
        state.setStateCodeChar2("U0OjADGRIBpb92D9zkKPVxUNDLCKl3uX");
        state.setStateCapital("N5ooXWiH8R6f45KCKQ8Zs7qkNSdPPKq4DPAQo6QCj5886yvk7j");
        state.setStateFlag("2Xd7Uo6yzAo92amvuSSr48xlixAjlleSzIF4tpG6t9Xi8Pg4Tr");
        state.setStateCapitalLongitude(3);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLatitude(3);
        city.setCityDescription("oPV4BprzkUu76IEGXciGbjqQ2CK4qr7MXGeE1mVTYWzoUY8nBh");
        city.setCityName("JrtAf6Yb2cJUDLGz81dDIX9ZekpBx4qMC0CV3ogsHpMDGnNF2r");
        city.setCityCode(1);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("nfOk2UDTHILJVafRWEmGGhxG4X23W2jN");
        city.setCityLongitude(1);
        city.setCityFlag("uxfU19OOVQocuZkrmOtqCshDyEBiNc3XWmF0m7F1UncaLOxdyI");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("efA52KfNRASSuHLC994Cn0Q5AzxqnZla6Q8IJVPWAD82zDbfSJ");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("MivPIhwSFh7");
        address.setZipcode("HQ2wkg");
        address.setLongitude("70h8v1WHuUFNPKq4Ct57irr1kguM24ZLPL8gut5k6Qy6GwMQYr");
        address.setLatitude("S4ugNuRYczuSZZIQvkFhQ4wv8nCP462VseMMAY30bg3RYAPZ0U");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setAddress2("NLxvVKebYn7EWy1uriKtwUaq5SvQ01Ecg0th7HG0drBgpHJyo7");
        address.setAddress3("0b65QXpBycFlQD31Kwj3o79rIbsQ5gsIOlLlo6CqT4U5JGFRFr");
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
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddress1("voayXLaIolhfxyw1erLSVwQRtnSVu68rlVHrIS15TkEYsQg58H");
            address.setVersionId(1);
            address.setAddressLabel("9dgEBpblBtr");
            address.setZipcode("i3I5fc");
            address.setLongitude("CTQ0a99FXd5cGG71r2nfuNZxjn4hAXgX1ihN8WU4FHwFvSpgx0");
            address.setLatitude("By9rP596mCwG6SEgokVzx2uN5NnEnnEGPunDlK99o6lBdOU4CZ");
            address.setAddress2("hDkagrxanWZ9edVHwTenKvOnDojtbsmM9xh1RjeQHdPWe0UvaB");
            address.setAddress3("bSxzE9iA8buTPtw1N2dFK7VMTfvZ6DcoEIejhkee8BuczKWlJQ");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
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
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey"));
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "mVCvbiV1Jvmr"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "uKCsmwFEoFNNxXbGHh5un4m3dl1bIrjhxtOZulCInljOEjJ5kIGUzTyNI"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "JpWdzu3FEuESybgdClImBsrdA235BvRsSLkpoGxiPVXEY5wKVqej1DXRb"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "DAjGCRIwESQno5vRzt1iNEk0IFjMf1z8VO7FYX4gUgNmzIkkmbGyVeIX2"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "FVDCLMV"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "3U6NkuqlEUAc6AGCc2ntWr9G05SIDX34rWBOPO5npU3yS1UML1T0H9vrb7kQRxH7q"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "hQpkpVqZswC8o6axn0zXi9pZl6aNzRnwozF5hBKvhIjDoIQYljL1cHzfZAFoT6xBz"));
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
