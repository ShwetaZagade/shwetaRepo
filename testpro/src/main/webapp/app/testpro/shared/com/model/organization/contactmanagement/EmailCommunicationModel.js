Ext.define('Testpro.testpro.shared.com.model.organization.contactmanagement.EmailCommunicationModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "emailCommId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "commtype",
          "reference": "EmailCategory",
          "defaultValue": ""
     }, {
          "name": "email",
          "type": "string",
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