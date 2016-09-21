package com.app.shared.appinsight;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.sql.Timestamp;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.sql.Date;
import com.athena.config.jsonHandler.CustomSqlDateJsonSerializer;
import com.athena.config.jsonHandler.CustomSqlDateJsonDeserializer;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "AQ", complexity = Complexity.MEDIUM)
public class AQRM implements DTOMapperInterface {

    private String tnm;

    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp tDateTime;

    @JsonSerialize(using = CustomSqlDateJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlDateJsonDeserializer.class)
    private Date tDate;

    public AQRM(Object[] aryObject) {
        if (aryObject != null) {
            tnm = (aryObject[0] == null ? null : new java.lang.String(aryObject[0].toString()));
            tDateTime = (aryObject[1] == null ? null : (java.sql.Timestamp) aryObject[1]);
            tDate = (aryObject[2] == null ? null : aryObject[2] instanceof java.sql.Date ? (java.sql.Date) aryObject[2] : new java.sql.Date(((java.sql.Timestamp) aryObject[2]).getTime()));
        } else {
            tnm = null;
            tDateTime = null;
            tDate = null;
        }
    }

    public String getTnm() {
        return tnm;
    }

    public Timestamp gettDateTime() {
        return tDateTime;
    }

    public Date gettDate() {
        return tDate;
    }
}
