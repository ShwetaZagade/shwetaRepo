package com.app.shared.appinsight.health;
import com.athena.server.pluggable.interfaces.EntityValidatorInterface;
import java.io.Serializable;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "1", comments = "TestDto", complexity = Complexity.MEDIUM)
@XmlRootElement
public class TestDto implements EntityValidatorInterface, Serializable {

    private static final long serialVersionUID = 1474277331294L;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> dtoValidator;

    private Integer empNo;

    @Transient
    @JsonIgnore
    private boolean isDtoValidated = false;

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isDtoValidated;
    }

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer _empNo) {
        this.empNo = _empNo;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.dtoValidator = _validateFactory;
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.dtoValidator != null) {
            isValid = this.dtoValidator.validateEntity(this);
            this.isDtoValidated = true;
        } else {
            throw new SecurityException();
        }
        return isValid;
    }
}
