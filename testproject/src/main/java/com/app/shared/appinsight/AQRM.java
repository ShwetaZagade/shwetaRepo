package com.app.shared.appinsight;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "1", comments = "AQ", complexity = Complexity.MEDIUM)
public class AQRM implements DTOMapperInterface {

    private String empNm;

    private Integer empNo;

    public AQRM(Object[] aryObject) {
        if (aryObject != null) {
            empNm = (aryObject[0] == null ? null : new java.lang.String(aryObject[0].toString()));
            empNo = (aryObject[1] == null ? null : new java.lang.Integer(aryObject[1].toString()));
        } else {
            empNm = null;
            empNo = null;
        }
    }

    public String getEmpNm() {
        return empNm;
    }

    public Integer getEmpNo() {
        return empNo;
    }
}
