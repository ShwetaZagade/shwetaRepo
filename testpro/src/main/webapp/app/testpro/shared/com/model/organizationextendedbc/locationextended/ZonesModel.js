Ext.define('Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.ZonesModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "zoneId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "zoneName",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "stateid",
          "reference": "State",
          "defaultValue": ""
     }, {
          "name": "countryid",
          "reference": "Country",
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