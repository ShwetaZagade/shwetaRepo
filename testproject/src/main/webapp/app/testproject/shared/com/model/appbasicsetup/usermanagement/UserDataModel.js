Ext.define('Testproject.testproject.shared.com.model.appbasicsetup.usermanagement.UserDataModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "userDataId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "password",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "oneTimePassword",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "oneTimePasswordExpiry",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "oneTimePasswordGenDate",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "last5Passwords",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "User",
          "reference": "UserModel"
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});