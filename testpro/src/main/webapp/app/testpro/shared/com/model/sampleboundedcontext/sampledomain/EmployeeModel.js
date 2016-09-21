Ext.define('Testpro.testpro.shared.com.model.sampleboundedcontext.sampledomain.EmployeeModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "empId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "empName",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "empsal",
          "type": "number",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});