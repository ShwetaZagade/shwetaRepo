package com.app.server.businessservice.appinsight;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.appinsight.AQRM;
import java.util.List;
import com.app.shared.appinsight.AAQRM;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "AQBzService", complexity = Complexity.HIGH)
public interface AQBzService {

    public List<AQRM> executeQueryAQ() throws Exception;

    public List<AAQRM> executeQueryAAQ() throws Exception;
}
