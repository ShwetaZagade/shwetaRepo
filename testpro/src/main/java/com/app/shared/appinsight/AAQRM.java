package com.app.shared.appinsight;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.sql.Date;
import com.athena.config.jsonHandler.CustomSqlDateJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlDateJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.sql.Timestamp;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "AQBzService", complexity = Complexity.MEDIUM)
public class AAQRM implements DTOMapperInterface {

    private String tnm;

    @JsonSerialize(using = CustomSqlDateJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlDateJsonDeserializer.class)
    private Date tDate;

    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp tDateTime;

    public AAQRM(Object[] aryObject) {
        if (aryObject != null) {
            tnm = (aryObject[0] == null ? null : new java.lang.String(aryObject[0].toString()));
            tDate = (aryObject[1] == null ? null : aryObject[1] instanceof java.sql.Date ? (java.sql.Date) aryObject[1] : new java.sql.Date(((java.sql.Timestamp) aryObject[1]).getTime()));
            tDateTime = (aryObject[2] == null ? null : (java.sql.Timestamp) aryObject[2]);
        } else {
            tnm = null;
            tDate = null;
            tDateTime = null;
        }
    }

    public String getTnm() {
        return tnm;
    }

    public Date gettDate() {
        return tDate;
    }

    public Timestamp gettDateTime() {
        return tDateTime;
    }
}
