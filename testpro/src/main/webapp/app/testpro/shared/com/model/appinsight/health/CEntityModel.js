Ext.define('Testpro.testpro.shared.com.model.appinsight.health.CEntityModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "cid",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "cNm",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "cNo",
          "type": "int",
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