Ext.define('Testpro.testpro.shared.com.model.appinsight.health.ATestEntityModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "atestentityPkey",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "aid",
          "reference": "AEntity",
          "defaultValue": ""
     }, {
          "name": "tid",
          "reference": "TestA",
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