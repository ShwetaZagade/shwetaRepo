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
import org.eclipse.persistence.config.CacheIsolationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
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

@Table(name = "ast_City_M")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "3", comments = "City", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "City.findAll", query = " select u from City u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "City.findByCountryId", query = "select e from City e where e.systemInfo.activeStatus=1 and e.countryId=:countryId"), @javax.persistence.NamedQuery(name = "City.findByStateId", query = "select e from City e where e.systemInfo.activeStatus=1 and e.stateId=:stateId"), @javax.persistence.NamedQuery(name = "City.findById", query = "select e from City e where e.systemInfo.activeStatus=1 and e.cityId =:cityId") })
public class City implements Serializable, CommonEntityInterface, Comparator<City> {

    private static final long serialVersionUID = 1474275690823L;

    @Column(name = "cityName")
    @JsonProperty("cityName")
    @NotNull
    @Size(min = 0, max = 256)
    private String cityName;

    @Column(name = "cityCodeChar2")
    @JsonProperty("cityCodeChar2")
    @NotNull
    @Size(min = 0, max = 32)
    private String cityCodeChar2;

    @Column(name = "cityCode")
    @JsonProperty("cityCode")
    @NotNull
    @Min(0)
    @Max(3)
    private Integer cityCode;

    @Column(name = "cityDescription")
    @JsonProperty("cityDescription")
    @Size(max = 128)
    private String cityDescription;

    @Column(name = "cityFlag")
    @JsonProperty("cityFlag")
    @Size(max = 128)
    private String cityFlag;

    @Column(name = "cityLatitude")
    @JsonProperty("cityLatitude")
    @Max(11)
    private Integer cityLatitude;

    @Column(name = "cityLongitude")
    @JsonProperty("cityLongitude")
    @Max(11)
    private Integer cityLongitude;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "cityId")
    @JsonProperty("cityId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String cityId;

    @Column(name = "countryId")
    @JsonProperty("countryId")
    private String countryId;

    @Column(name = "stateId")
    @JsonProperty("stateId")
    private String stateId;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String _cityName) {
        if (_cityName != null) {
            this.cityName = _cityName;
        }
    }

    public String getCityCodeChar2() {
        return cityCodeChar2;
    }

    public void setCityCodeChar2(String _cityCodeChar2) {
        if (_cityCodeChar2 != null) {
            this.cityCodeChar2 = _cityCodeChar2;
        }
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer _cityCode) {
        if (_cityCode != null) {
            this.cityCode = _cityCode;
        }
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setCityDescription(String _cityDescription) {
        this.cityDescription = _cityDescription;
    }

    public String getCityFlag() {
        return cityFlag;
    }

    public void setCityFlag(String _cityFlag) {
        this.cityFlag = _cityFlag;
    }

    public Integer getCityLatitude() {
        return cityLatitude;
    }

    public void setCityLatitude(Integer _cityLatitude) {
        this.cityLatitude = _cityLatitude;
    }

    public Integer getCityLongitude() {
        return cityLongitude;
    }

    public void setCityLongitude(Integer _cityLongitude) {
        this.cityLongitude = _cityLongitude;
    }

    public String getPrimaryKey() {
        return cityId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return cityId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String _cityId) {
        this.cityId = _cityId;
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

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
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
     * @param City
     * @param City
     */
    @Override
    public int compare(City object1, City object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((cityName == null ? " " : cityName) + ",").append((cityCodeChar2 == null ? " " : cityCodeChar2) + ",").append((cityCode == null ? " " : cityCode));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (cityId == null) {
            return super.hashCode();
        } else {
            return cityId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.organization.locationmanagement.City other = (com.app.shared.organization.locationmanagement.City) obj;
            if (cityId == null) {
                return false;
            } else if (!cityId.equals(other.cityId)) {
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
