Ext.define('Testpro.testpro.shared.com.model.appinsight.alarms.BCEntityModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "bcentityPkey",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "bid",
          "reference": "BEntity",
          "defaultValue": ""
     }, {
          "name": "cid",
          "reference": "CEntity",
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