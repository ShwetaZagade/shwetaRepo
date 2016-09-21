package com.app.server.businessservice.appinsight.health;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.app.shared.appinsight.AQRM;
import com.app.shared.appinsight.health.TestDto;
import com.spartan.pluggable.exception.layers.ds.BusinessRuleUnableToProceedException;

@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "3", comments = "TestDs", complexity = Complexity.HIGH)
public interface TestDs {

    public AQRM proTestDs(TestDto ds) throws BusinessRuleUnableToProceedException, Exception;
}
