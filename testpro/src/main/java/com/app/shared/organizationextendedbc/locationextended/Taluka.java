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

@Table(name = "Taluka")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Taluka", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Taluka.findAll", query = " select u from Taluka u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Taluka.DefaultFinders", query = "select e from Taluka e where e.systemInfo.activeStatus=1 and e.countryId LIKE :countryId and e.stateId LIKE :stateId and e.regionId LIKE :regionId and e.districtId LIKE :districtId and e.talukaName LIKE :talukaName and e.talukaCode LIKE :talukaCode"), @javax.persistence.NamedQuery(name = "Taluka.findByCountryId", query = "select e from Taluka e where e.systemInfo.activeStatus=1 and e.countryId=:countryId"), @javax.persistence.NamedQuery(name = "Taluka.findByStateId", query = "select e from Taluka e where e.systemInfo.activeStatus=1 and e.stateId=:stateId"), @javax.persistence.NamedQuery(name = "Taluka.findByRegionId", query = "select e from Taluka e where e.systemInfo.activeStatus=1 and e.regionId=:regionId"), @javax.persistence.NamedQuery(name = "Taluka.findByDistrictId", query = "select e from Taluka e where e.systemInfo.activeStatus=1 and e.districtId=:districtId"), @javax.persistence.NamedQuery(name = "Taluka.findById", query = "select e from Taluka e where e.systemInfo.activeStatus=1 and e.talukaId =:talukaId") })
public class Taluka implements Serializable, CommonEntityInterface, Comparator<Taluka> {

    private static final long serialVersionUID = 1474436568200L;

    @Column(name = "talukaName")
    @JsonProperty("talukaName")
    @NotNull
    @Size(min = 0, max = 256)
    private String talukaName;

    @Column(name = "talukaCode")
    @JsonProperty("talukaCode")
    @NotNull
    @Size(min = 0, max = 32)
    private String talukaCode;

    @Column(name = "talukaDescription")
    @JsonProperty("talukaDescription")
    @Size(max = 128)
    private String talukaDescription;

    @Column(name = "talukaFlag")
    @JsonProperty("talukaFlag")
    @Size(max = 128)
    private String talukaFlag;

    @Column(name = "talukaLatitude")
    @JsonProperty("talukaLatitude")
    @Max(11)
    private Integer talukaLatitude;

    @Column(name = "talukaLongitude")
    @JsonProperty("talukaLongitude")
    @Max(11)
    private Integer talukaLongitude;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "talukaId")
    @JsonProperty("talukaId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String talukaId;

    @Column(name = "countryId")
    @JsonProperty("countryId")
    private String countryId;

    @Column(name = "stateId")
    @JsonProperty("stateId")
    private String stateId;

    @Column(name = "regionId")
    @JsonProperty("regionId")
    private String regionId;

    @Column(name = "districtId")
    @JsonProperty("districtId")
    private String districtId;

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

    public String getTalukaName() {
        return talukaName;
    }

    public void setTalukaName(String _talukaName) {
        if (_talukaName != null) {
            this.talukaName = _talukaName;
        }
    }

    public String getTalukaCode() {
        return talukaCode;
    }

    public void setTalukaCode(String _talukaCode) {
        if (_talukaCode != null) {
            this.talukaCode = _talukaCode;
        }
    }

    public String getTalukaDescription() {
        return talukaDescription;
    }

    public void setTalukaDescription(String _talukaDescription) {
        this.talukaDescription = _talukaDescription;
    }

    public String getTalukaFlag() {
        return talukaFlag;
    }

    public void setTalukaFlag(String _talukaFlag) {
        this.talukaFlag = _talukaFlag;
    }

    public Integer getTalukaLatitude() {
        return talukaLatitude;
    }

    public void setTalukaLatitude(Integer _talukaLatitude) {
        this.talukaLatitude = _talukaLatitude;
    }

    public Integer getTalukaLongitude() {
        return talukaLongitude;
    }

    public void setTalukaLongitude(Integer _talukaLongitude) {
        this.talukaLongitude = _talukaLongitude;
    }

    public String getPrimaryKey() {
        return talukaId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return talukaId;
    }

    public String getTalukaId() {
        return talukaId;
    }

    public void setTalukaId(String _talukaId) {
        this.talukaId = _talukaId;
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

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String _regionId) {
        this.regionId = _regionId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String _districtId) {
        this.districtId = _districtId;
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
     * @param Taluka
     * @param Taluka
     */
    @Override
    public int compare(Taluka object1, Taluka object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((talukaName == null ? " " : talukaName) + ",").append((talukaCode == null ? " " : talukaCode));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (talukaId == null) {
            return super.hashCode();
        } else {
            return talukaId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.organizationextendedbc.locationextended.Taluka other = (com.app.shared.organizationextendedbc.locationextended.Taluka) obj;
            if (talukaId == null) {
                return false;
            } else if (!talukaId.equals(other.talukaId)) {
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
