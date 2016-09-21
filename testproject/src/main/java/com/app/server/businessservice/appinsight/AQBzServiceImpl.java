package com.app.server.businessservice.appinsight;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.shared.appinsight.AQRM;
import java.lang.Override;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "shubhangi.mane@algorhythm.co.in", updatedBy = "shubhangi.mane@algorhythm.co.in", versionNumber = "1", comments = "AQBzServiceImpl", complexity = Complexity.HIGH)
@Component
public class AQBzServiceImpl implements AQBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<AQRM> executeQueryAQ(Integer empNo1) throws Exception {
        java.util.List<com.app.shared.appinsight.AQRM> listDtoInterface = new java.util.ArrayList<com.app.shared.appinsight.AQRM>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "5C7F03EF-68AE-482F-8A02-02F68D398F91");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            atg.taglib.json.util.JSONObject jsonObjectEmpNo1 = new atg.taglib.json.util.JSONObject();
            jsonObjectEmpNo1.put("name", "empNo");
            jsonObjectEmpNo1.put("value", empNo1);
            jsonObjectEmpNo1.put("datatype", "INT");
            jsonObjectEmpNo1.put("index", 1);
            jsonArray.add(jsonObjectEmpNo1);
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.app.shared.appinsight.AQRM", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}
