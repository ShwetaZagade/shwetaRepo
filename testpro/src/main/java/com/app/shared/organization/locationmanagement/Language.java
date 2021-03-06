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

@Table(name = "Language")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "3", comments = "Language", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Language.findAll", query = " select u from Language u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Language.findById", query = "select e from Language e where e.systemInfo.activeStatus=1 and e.languageId =:languageId") })
public class Language implements Serializable, CommonEntityInterface, Comparator<Language> {

    private static final long serialVersionUID = 1474436544076L;

    @Column(name = "language")
    @JsonProperty("language")
    @NotNull
    @Size(min = 0, max = 256)
    private String language;

    @Column(name = "languageType")
    @JsonProperty("languageType")
    @Size(max = 32)
    private String languageType;

    @Column(name = "languageDescription")
    @JsonProperty("languageDescription")
    @Size(max = 256)
    private String languageDescription;

    @Column(name = "languageIcon")
    @JsonProperty("languageIcon")
    @Size(max = 128)
    private String languageIcon;

    @Column(name = "alpha2")
    @JsonProperty("alpha2")
    @Size(max = 2)
    private String alpha2;

    @Column(name = "alpha3")
    @JsonProperty("alpha3")
    @Size(max = 3)
    private String alpha3;

    @Column(name = "alpha4")
    @JsonProperty("alpha4")
    @Size(max = 4)
    private String alpha4;

    @Column(name = "alpha4parentid")
    @JsonProperty("alpha4parentid")
    @Max(11)
    private Integer alpha4parentid;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "languageId")
    @JsonProperty("languageId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String languageId;

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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String _language) {
        if (_language != null) {
            this.language = _language;
        }
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String _languageType) {
        this.languageType = _languageType;
    }

    public String getLanguageDescription() {
        return languageDescription;
    }

    public void setLanguageDescription(String _languageDescription) {
        this.languageDescription = _languageDescription;
    }

    public String getLanguageIcon() {
        return languageIcon;
    }

    public void setLanguageIcon(String _languageIcon) {
        this.languageIcon = _languageIcon;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String _alpha2) {
        this.alpha2 = _alpha2;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(String _alpha3) {
        this.alpha3 = _alpha3;
    }

    public String getAlpha4() {
        return alpha4;
    }

    public void setAlpha4(String _alpha4) {
        this.alpha4 = _alpha4;
    }

    public Integer getAlpha4parentid() {
        return alpha4parentid;
    }

    public void setAlpha4parentid(Integer _alpha4parentid) {
        this.alpha4parentid = _alpha4parentid;
    }

    public String getPrimaryKey() {
        return languageId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return languageId;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String _languageId) {
        this.languageId = _languageId;
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
     * @param Language
     * @param Language
     */
    @Override
    public int compare(Language object1, Language object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((language == null ? " " : language));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (languageId == null) {
            return super.hashCode();
        } else {
            return languageId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.organization.locationmanagement.Language other = (com.app.shared.organization.locationmanagement.Language) obj;
            if (languageId == null) {
                return false;
            } else if (!languageId.equals(other.languageId)) {
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
