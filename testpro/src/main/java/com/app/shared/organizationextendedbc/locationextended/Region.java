package com.app.shared.organizationextendedbc.locationextended;
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

@Table(name = "Region")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Region", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Region.findAll", query = " select u from Region u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Region.DefaultFinders", query = "select e from Region e where e.systemInfo.activeStatus=1 and e.countryId LIKE :countryId and e.stateId LIKE :stateId and e.regionName LIKE :regionName and e.regionCode1 BETWEEN :minregionCode1 AND :maxregionCode1 and e.regionCodeChar2 LIKE :regionCodeChar2"), @javax.persistence.NamedQuery(name = "Region.findByCountryId", query = "select e from Region e where e.systemInfo.activeStatus=1 and e.countryId=:countryId"), @javax.persistence.NamedQuery(name = "Region.findByStateId", query = "select e from Region e where e.systemInfo.activeStatus=1 and e.stateId=:stateId"), @javax.persistence.NamedQuery(name = "Region.findById", query = "select e from Region e where e.systemInfo.activeStatus=1 and e.regionId =:regionId") })
public class Region implements Serializable, CommonEntityInterface, Comparator<Region> {

    private static final long serialVersionUID = 1474436566839L;

    @Column(name = "regionName")
    @JsonProperty("regionName")
    @NotNull
    @Size(min = 0, max = 256)
    private String regionName;

    @Column(name = "regionCode1")
    @JsonProperty("regionCode1")
    @NotNull
    @Min(0)
    @Max(3)
    private Integer regionCode1;

    @Column(name = "regionCodeChar2")
    @JsonProperty("regionCodeChar2")
    @NotNull
    @Size(min = 0, max = 32)
    private String regionCodeChar2;

    @Column(name = "regionDescription")
    @JsonProperty("regionDescription")
    @Size(max = 256)
    private String regionDescription;

    @Column(name = "regionFlag")
    @JsonProperty("regionFlag")
    @Size(max = 128)
    private String regionFlag;

    @Column(name = "regionLatitude")
    @JsonProperty("regionLatitude")
    @Max(11)
    private Integer regionLatitude;

    @Column(name = "regionLongitude")
    @JsonProperty("regionLongitude")
    @Max(11)
    private Integer regionLongitude;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "regionId")
    @JsonProperty("regionId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String regionId;

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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String _regionName) {
        if (_regionName != null) {
            this.regionName = _regionName;
        }
    }

    public Integer getRegionCode1() {
        return regionCode1;
    }

    public void setRegionCode1(Integer _regionCode1) {
        if (_regionCode1 != null) {
            this.regionCode1 = _regionCode1;
        }
    }

    public String getRegionCodeChar2() {
        return regionCodeChar2;
    }

    public void setRegionCodeChar2(String _regionCodeChar2) {
        if (_regionCodeChar2 != null) {
            this.regionCodeChar2 = _regionCodeChar2;
        }
    }

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String _regionDescription) {
        this.regionDescription = _regionDescription;
    }

    public String getRegionFlag() {
        return regionFlag;
    }

    public void setRegionFlag(String _regionFlag) {
        this.regionFlag = _regionFlag;
    }

    public Integer getRegionLatitude() {
        return regionLatitude;
    }

    public void setRegionLatitude(Integer _regionLatitude) {
        this.regionLatitude = _regionLatitude;
    }

    public Integer getRegionLongitude() {
        return regionLongitude;
    }

    public void setRegionLongitude(Integer _regionLongitude) {
        this.regionLongitude = _regionLongitude;
    }

    public String getPrimaryKey() {
        return regionId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return regionId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String _regionId) {
        this.regionId = _regionId;
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
     * @param Region
     * @param Region
     */
    @Override
    public int compare(Region object1, Region object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((regionName == null ? " " : regionName) + ",").append((regionCode1 == null ? " " : regionCode1) + ",").append((regionCodeChar2 == null ? " " : regionCodeChar2));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (regionId == null) {
            return super.hashCode();
        } else {
            return regionId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.organizationextendedbc.locationextended.Region other = (com.app.shared.organizationextendedbc.locationextended.Region) obj;
            if (regionId == null) {
                return false;
            } else if (!regionId.equals(other.regionId)) {
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
