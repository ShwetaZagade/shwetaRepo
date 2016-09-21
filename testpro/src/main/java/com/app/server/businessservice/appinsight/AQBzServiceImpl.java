package com.app.server.businessservice.appinsight;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import org.springframework.stereotype.Component;
import com.athena.server.dataengine.bizService.QueryExecuterService;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.shared.appinsight.AQRM;
import java.lang.Override;
import java.util.List;
import com.app.shared.appinsight.AAQRM;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "deepali.arvind@algorhythm.co.in", versionNumber = "1", comments = "AQBzServiceImpl", complexity = Complexity.HIGH)
@Component
public class AQBzServiceImpl implements AQBzService {

    @Autowired
    private QueryExecuterService queryExecuterService;

    @Override
    public List<AQRM> executeQueryAQ() throws Exception {
        java.util.List<com.app.shared.appinsight.AQRM> listDtoInterface = new java.util.ArrayList<com.app.shared.appinsight.AQRM>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "62E5A6CE-1721-4764-81AE-6205FB86BBBC");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.app.shared.appinsight.AQRM", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }

    @Override
    public List<AAQRM> executeQueryAAQ() throws Exception {
        java.util.List<com.app.shared.appinsight.AAQRM> listDtoInterface = new java.util.ArrayList<com.app.shared.appinsight.AAQRM>();
        try {
            atg.taglib.json.util.JSONObject queryParams = new atg.taglib.json.util.JSONObject();
            queryParams.put("queryId", "2CF42188-8E36-4817-9620-A0BC1D083813");
            atg.taglib.json.util.JSONArray jsonArray = new atg.taglib.json.util.JSONArray();
            queryParams.put("queryCriteria", jsonArray);
            listDtoInterface = queryExecuterService.getAllQueryData("com.app.shared.appinsight.AAQRM", queryParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDtoInterface;
    }
}
