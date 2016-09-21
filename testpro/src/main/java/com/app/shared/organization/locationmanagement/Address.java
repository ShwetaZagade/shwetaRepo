package com.app.shared.organization.locationmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.eclipse.persistence.config.CacheIsolationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "Address")
@Entity
@Multitenant(MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "multiTenantId", contextProperty = "tenant.id")
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Address", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Address.findAll", query = " select u from Address u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Address.DefaultFinders", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.addressTypeId LIKE :addressTypeId"), @javax.persistence.NamedQuery(name = "Address.findByAddressTypeId", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.addressTypeId=:addressTypeId"), @javax.persistence.NamedQuery(name = "Address.findByCountryId", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.countryId=:countryId"), @javax.persistence.NamedQuery(name = "Address.findByStateId", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.stateId=:stateId"), @javax.persistence.NamedQuery(name = "Address.findByCityId", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.cityId=:cityId"), @javax.persistence.NamedQuery(name = "Address.findById", query = "select e from Address e where e.systemInfo.activeStatus=1 and e.addressId =:addressId") })
public class Address implements Serializable, CommonEntityInterface, Comparator<Address> {

    private static final long serialVersionUID = 1474436546811L;

    @Column(name = "addressLabel")
    @JsonProperty("addressLabel")
    @Size(max = 11)
    private String addressLabel;

    @Column(name = "address1")
    @JsonProperty("address1")
    @Size(max = 56)
    private String address1;

    @Column(name = "address2")
    @JsonProperty("address2")
    @Size(max = 56)
    private String address2;

    @Column(name = "address3")
    @JsonProperty("address3")
    @Size(max = 56)
    private String address3;

    @Column(name = "zipcode")
    @JsonProperty("zipcode")
    @NotNull
    @Size(min = 0, max = 6)
    private String zipcode;

    @Column(name = "latitude")
    @JsonProperty("latitude")
    @Size(max = 64)
    private String latitude;

    @Column(name = "longitude")
    @JsonProperty("longitude")
    @Size(max = 64)
    private String longitude;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "addressId")
    @JsonProperty("addressId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String addressId;

    @Column(name = "addressTypeId")
    @JsonProperty("addressTypeId")
    private String addressTypeId;

    @Column(name = "countryId")
    @JsonProperty("countryId")
    private String countryId;

    @Column(name = "stateId")
    @JsonProperty("stateId")
    private String stateId;

    @Column(name = "cityId")
    @JsonProperty("cityId")
    private String cityId;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Column(name = "multiTenantId", insertable = false, updatable = false)
    @JsonProperty("multiTenantId")
    private String multiTenantId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public String getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(String _addressLabel) {
        this.addressLabel = _addressLabel;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String _address1) {
        this.address1 = _address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String _address2) {
        this.address2 = _address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String _address3) {
        this.address3 = _address3;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String _zipcode) {
        if (_zipcode != null) {
            this.zipcode = _zipcode;
        }
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String _latitude) {
        this.latitude = _latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String _longitude) {
        this.longitude = _longitude;
    }

    public String getPrimaryKey() {
        return addressId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return addressId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String _addressId) {
        this.addressId = _addressId;
    }

    public String getAddressTypeId() {
        return addressTypeId;
    }

    public void setAddressTypeId(String _addressTypeId) {
        this.addressTypeId = _addressTypeId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String _countryId) {
        this.countryId = _countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String _stateId) {
        this.stateId = _stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String _cityId) {
        this.cityId = _cityId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
    }

    public String getMultiTenantId() {
        return multiTenantId;
    }

    public void setMultiTenantId(String _multiTenantId) {
        this.multiTenantId = _multiTenantId;
    }

    public void setPrimaryDisplay(String _primaryDisplay) {
        this.primaryDisplay = _primaryDisplay;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo _systemInfo) {
        this.systemInfo = _systemInfo;
    }

    /**
     * Returns boolean value if System information's active status =-1
     * @return boolean
     */
    @JsonIgnore
    public boolean isHardDelete() {
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validates the entity fields based on java.validation.constraints annotaions and sets isEntityValided value in System information object
     * @return boolean
     * @throws java.lang.SecurityException
     */
    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new java.lang.SecurityException();
        }
        return isValid;
    }

    /**
     * Sets EntityValidator object
     * @param _validateFactory
     */
    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.entityValidator = _validateFactory;
    }

    /**
     * Creates a new entity audit object and  if primarykey fields are null then sets created by, updated by, active status values in the entity audit field.
     * @param customerId
     * @param userId
     */
    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
    }

    /**
     * Creates a new entity audit object and System Information object and based on @Params RECORD_TYPE sets created by and updated by values in the entity audit field.
     * @param CustomerId
     * @param userId
     * @param RECORD_TYPE
     */
    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
    }

    /**
     * Returns Logged in user informatio.
     * @return auditInfo
     */
    @JsonIgnore
    public String getLoggedInUserInfo() {
        String auditInfo = "";
        if (this.entityAudit != null) {
            auditInfo = entityAudit.toString();
        }
        return auditInfo;
    }

    /**
     * Creates new System Information object.
     * @param RECORD_TYPE
     */
    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(0);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    /**
     * Sets active status in System Information object.
     * @param activeStatus
     */
    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    /**
     * Returns system information object.
     * @return systemInfo
     */
    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    /**
     * Creates System information obect if null and sets transaction access code in that object.
     * @param transactionAccessCode
     */
    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        this.systemInfo.setTxnAccessCode(transactionAccessCode);
    }

    /**
     * Compares 2 objects and returns integer value
     * @param Address
     * @param Address
     */
    @Override
    public int compare(Address object1, Address object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((zipcode == null ? " " : zipcode));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (addressId == null) {
            return super.hashCode();
        } else {
            return addressId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.organization.locationmanagement.Address other = (com.app.shared.organization.locationmanagement.Address) obj;
            if (addressId == null) {
                return false;
            } else if (!addressId.equals(other.addressId)) {
                return false;
            }
        } catch (java.lang.Exception ignore) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return this.systemInfo.isEntityValidated();
    }
}
