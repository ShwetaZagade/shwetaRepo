Ext.define('Testpro.testpro.shared.com.model.appinsight.health.TestAModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "tid",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "tnm",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "tDate",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "tDateTime",
          "type": "auto",
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