package com.app.server.businessservice.appinsight;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.appinsight.AQRM;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "1", comments = "AQBzService", complexity = Complexity.HIGH)
public interface AQBzService {

    public List<AQRM> executeQueryAQ(Integer empNo1) throws Exception;
}
