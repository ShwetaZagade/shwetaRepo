Ext.define('Testpro.testpro.web.com.view.organizationextendedbc.locationextended.AddressExtendedMain', {
     "extend": "Ext.tab.Panel",
     "xtype": "addressExtendedMainView",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "AddressExtendedMainController",
     "restURL": "/AddressExtended",
     "defaults": {
          "split": true
     },
     "requires": ["Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.AddressExtendedModel", "Testpro.testpro.web.com.controller.organizationextendedbc.locationextended.AddressExtendedMainController", "Testpro.testpro.shared.com.model.organization.locationmanagement.AddressModel", "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.VillageModel", "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.TalukaModel", "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.DistrictModel", "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.RegionModel", "Testpro.testpro.shared.com.viewmodel.organizationextendedbc.locationextended.AddressExtendedViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "displayName": "Address Extended",
               "name": "AddressExtendedTreeContainer",
               "itemId": "AddressExtendedTreeContainer",
               "margin": "5 0 5 5",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "useArrows": true,
                    "name": "entityTreePanel",
                    "title": "Browse",
                    "rootVisible": false,
                    "itemId": "AddressExtendedTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "xtype": "form",
                    "displayName": "Address Extended",
                    "name": "AddressExtended",
                    "itemId": "AddressExtendedForm",
                    "bodyPadding": 10,
                    "items": [{
                         "xtype": "form",
                         "itemId": "form0",
                         "customWidgetType": "vdCard",
                         "header": {
                              "hidden": true
                         },
                         "items": [{
                              "layout": "column",
                              "customWidgetType": "vdColumnLayout",
                              "header": {
                                   "hidden": true
                              },
                              "xtype": "panel",
                              "items": [{
                                   "name": "addExtId",
                                   "itemId": "addExtId",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "Address Extended Id",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Address Extended Id<font color='red'> *<\/font>",
                                   "fieldId": "D48FEA26-91AE-45F2-A2F2-6893D81EF3B9",
                                   "minLength": "0",
                                   "maxLength": "64",
                                   "hidden": true,
                                   "value": "",
                                   "bindable": "addExtId"
                              }, {
                                   "name": "addressId",
                                   "itemId": "addressId",
                                   "xtype": "customcombobox",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "Address Id",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "customStore": {
                                        "model": "Testpro.testpro.shared.com.model.organization.locationmanagement.AddressModel",
                                        "autoLoad": true,
                                        "autoSync": true,
                                        "sorters": [{
                                             "property": "primaryDisplay",
                                             "direction": "ASC"
                                        }],
                                        "proxy": {
                                             "type": "ajax",
                                             "url": "secure/Address/findAll",
                                             "actionMethods": {
                                                  "read": "GET"
                                             },
                                             "headers": {
                                                  "Content-Type": "application/json"
                                             },
                                             "extraParams": {},
                                             "reader": {
                                                  "type": "json",
                                                  "rootProperty": "response.data"
                                             }
                                        }
                                   },
                                   "allowBlank": false,
                                   "fieldLabel": "Address Id<font color='red'> *<\/font>",
                                   "fieldId": "67F89C0E-14B3-46B9-B1A5-FE0357BF27BE",
                                   "restURL": "Address",
                                   "bindable": "addressId",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "villageId",
                                   "itemId": "villageId",
                                   "xtype": "customcombobox",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "Village",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "fieldLabel": "Village",
                                   "fieldId": "A6469EA2-DC4B-4B26-8BDF-A1F1B6DAFC1D",
                                   "restURL": "Village",
                                   "store": {
                                        "model": "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.VillageModel"
                                   },
                                   "bindable": "villageId",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "talukaId",
                                   "itemId": "talukaId",
                                   "xtype": "customcombobox",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "talukaa",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "fieldLabel": "talukaa",
                                   "fieldId": "F615CED5-1D09-4BE0-864E-BD84D653C3DD",
                                   "restURL": "Taluka",
                                   "store": {
                                        "model": "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.TalukaModel"
                                   },
                                   "bindable": "talukaId",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "districtId",
                                   "itemId": "districtId",
                                   "xtype": "customcombobox",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "District",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "fieldLabel": "District",
                                   "fieldId": "208F7B13-9A43-4040-A105-C3C0D703AC1B",
                                   "restURL": "District",
                                   "store": {
                                        "model": "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.DistrictModel"
                                   },
                                   "bindable": "districtId",
                                   "columnWidth": 0.5,
                                   "listeners": {
                                        "change": "onDistrictIdChange"
                                   }
                              }, {
                                   "name": "regionId",
                                   "itemId": "regionId",
                                   "xtype": "customcombobox",
                                   "customWidgetType": "vdCombo",
                                   "displayName": "Region",
                                   "margin": "5 5 5 5",
                                   "valueField": "primaryKey",
                                   "displayField": "primaryDisplay",
                                   "typeAhead": true,
                                   "queryMode": "local",
                                   "minChars": 2,
                                   "customStore": {
                                        "model": "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.RegionModel",
                                        "autoLoad": true,
                                        "autoSync": true,
                                        "sorters": [{
                                             "property": "primaryDisplay",
                                             "direction": "ASC"
                                        }],
                                        "proxy": {
                                             "type": "ajax",
                                             "url": "secure/Region/findAll",
                                             "actionMethods": {
                                                  "read": "GET"
                                             },
                                             "headers": {
                                                  "Content-Type": "application/json"
                                             },
                                             "extraParams": {},
                                             "reader": {
                                                  "type": "json",
                                                  "rootProperty": "response.data"
                                             }
                                        }
                                   },
                                   "fieldLabel": "Region",
                                   "fieldId": "5BDBAE98-258E-492F-9F63-093B165C7E9F",
                                   "restURL": "Region",
                                   "bindable": "regionId",
                                   "columnWidth": 0.5,
                                   "listeners": {
                                        "change": "onRegionIdChange"
                                   }
                              }, {
                                   "name": "villageName",
                                   "itemId": "villageName",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "Village Name",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "Village Name",
                                   "fieldId": "3D04FD52-45B8-427D-8050-A32D78DBA361",
                                   "minLength": "1",
                                   "maxLength": "128",
                                   "bindable": "villageName",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "talukaName",
                                   "itemId": "talukaName",
                                   "xtype": "textfield",
                                   "customWidgetType": "vdTextfield",
                                   "displayName": "talukaa",
                                   "margin": "5 5 5 5",
                                   "fieldLabel": "talukaa",
                                   "fieldId": "1828853F-C6E7-42EC-B937-E7A898449A68",
                                   "minLength": "1",
                                   "maxLength": "128",
                                   "bindable": "talukaName",
                                   "columnWidth": 0.5
                              }, {
                                   "name": "versionId",
                                   "itemId": "versionId",
                                   "xtype": "numberfield",
                                   "customWidgetType": "vdNumberfield",
                                   "displayName": "versionId",
                                   "margin": "5 5 5 5",
                                   "value": "-1",
                                   "fieldLabel": "versionId",
                                   "fieldId": "414A32C0-0103-4D4D-92C5-A758A5D49815",
                                   "bindable": "versionId",
                                   "hidden": true
                              }]
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Get Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "layout": "card",
                    "defaults": {
                         "autoScroll": true
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill"
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "xtype": "button",
                              "customWidgetType": "vdButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center",
                    "customWidgetType": "vdCardLayout"
               }, {
                    "xtype": "grid",
                    "customWidgetType": "vdGrid",
                    "displayName": "Address Extended",
                    "title": "Details Grid",
                    "name": "AddressExtendedGrid",
                    "itemId": "AddressExtendedGrid",
                    "requires": [],
                    "columns": [{
                         "header": "Address Extended Id",
                         "dataIndex": "addExtId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "Address Id",
                         "dataIndex": "addressId",
                         "flex": 1
                    }, {
                         "header": "Village",
                         "dataIndex": "villageId",
                         "flex": 1
                    }, {
                         "header": "talukaa",
                         "dataIndex": "talukaId",
                         "flex": 1
                    }, {
                         "header": "District",
                         "dataIndex": "districtId",
                         "flex": 1
                    }, {
                         "header": "Region",
                         "dataIndex": "regionId",
                         "flex": 1
                    }, {
                         "header": "Village Name",
                         "dataIndex": "villageName",
                         "flex": 1
                    }, {
                         "header": "talukaa",
                         "dataIndex": "talukaName",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "multiTenantId",
                         "dataIndex": "multiTenantId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "listeners": {
                              "click": "onGridRefreshClick"
                         },
                         "hidden": true
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "xtype": "form",
               "displayName": "Address Extended",
               "name": "AddressExtended",
               "itemId": "AddressExtendedForm",
               "bodyPadding": 10,
               "items": [{
                    "xtype": "form",
                    "itemId": "form0",
                    "customWidgetType": "vdCard",
                    "header": {
                         "hidden": true
                    },
                    "items": [{
                         "layout": "column",
                         "customWidgetType": "vdColumnLayout",
                         "header": {
                              "hidden": true
                         },
                         "xtype": "panel",
                         "items": [{
                              "name": "addExtId",
                              "itemId": "addExtId",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "Address Extended Id",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Address Extended Id<font color='red'> *<\/font>",
                              "fieldId": "D48FEA26-91AE-45F2-A2F2-6893D81EF3B9",
                              "minLength": "0",
                              "maxLength": "64",
                              "hidden": true,
                              "value": "",
                              "bindable": "addExtId"
                         }, {
                              "name": "addressId",
                              "itemId": "addressId",
                              "xtype": "customcombobox",
                              "customWidgetType": "vdCombo",
                              "displayName": "Address Id",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "customStore": {
                                   "model": "Testpro.testpro.shared.com.model.organization.locationmanagement.AddressModel",
                                   "autoLoad": true,
                                   "autoSync": true,
                                   "sorters": [{
                                        "property": "primaryDisplay",
                                        "direction": "ASC"
                                   }],
                                   "proxy": {
                                        "type": "ajax",
                                        "url": "secure/Address/findAll",
                                        "actionMethods": {
                                             "read": "GET"
                                        },
                                        "headers": {
                                             "Content-Type": "application/json"
                                        },
                                        "extraParams": {},
                                        "reader": {
                                             "type": "json",
                                             "rootProperty": "response.data"
                                        }
                                   }
                              },
                              "allowBlank": false,
                              "fieldLabel": "Address Id<font color='red'> *<\/font>",
                              "fieldId": "67F89C0E-14B3-46B9-B1A5-FE0357BF27BE",
                              "restURL": "Address",
                              "bindable": "addressId",
                              "columnWidth": 0.5
                         }, {
                              "name": "villageId",
                              "itemId": "villageId",
                              "xtype": "customcombobox",
                              "customWidgetType": "vdCombo",
                              "displayName": "Village",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "fieldLabel": "Village",
                              "fieldId": "A6469EA2-DC4B-4B26-8BDF-A1F1B6DAFC1D",
                              "restURL": "Village",
                              "store": {
                                   "model": "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.VillageModel"
                              },
                              "bindable": "villageId",
                              "columnWidth": 0.5
                         }, {
                              "name": "talukaId",
                              "itemId": "talukaId",
                              "xtype": "customcombobox",
                              "customWidgetType": "vdCombo",
                              "displayName": "talukaa",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "fieldLabel": "talukaa",
                              "fieldId": "F615CED5-1D09-4BE0-864E-BD84D653C3DD",
                              "restURL": "Taluka",
                              "store": {
                                   "model": "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.TalukaModel"
                              },
                              "bindable": "talukaId",
                              "columnWidth": 0.5
                         }, {
                              "name": "districtId",
                              "itemId": "districtId",
                              "xtype": "customcombobox",
                              "customWidgetType": "vdCombo",
                              "displayName": "District",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "fieldLabel": "District",
                              "fieldId": "208F7B13-9A43-4040-A105-C3C0D703AC1B",
                              "restURL": "District",
                              "store": {
                                   "model": "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.DistrictModel"
                              },
                              "bindable": "districtId",
                              "columnWidth": 0.5,
                              "listeners": {
                                   "change": "onDistrictIdChange"
                              }
                         }, {
                              "name": "regionId",
                              "itemId": "regionId",
                              "xtype": "customcombobox",
                              "customWidgetType": "vdCombo",
                              "displayName": "Region",
                              "margin": "5 5 5 5",
                              "valueField": "primaryKey",
                              "displayField": "primaryDisplay",
                              "typeAhead": true,
                              "queryMode": "local",
                              "minChars": 2,
                              "customStore": {
                                   "model": "Testpro.testpro.shared.com.model.organizationextendedbc.locationextended.RegionModel",
                                   "autoLoad": true,
                                   "autoSync": true,
                                   "sorters": [{
                                        "property": "primaryDisplay",
                                        "direction": "ASC"
                                   }],
                                   "proxy": {
                                        "type": "ajax",
                                        "url": "secure/Region/findAll",
                                        "actionMethods": {
                                             "read": "GET"
                                        },
                                        "headers": {
                                             "Content-Type": "application/json"
                                        },
                                        "extraParams": {},
                                        "reader": {
                                             "type": "json",
                                             "rootProperty": "response.data"
                                        }
                                   }
                              },
                              "fieldLabel": "Region",
                              "fieldId": "5BDBAE98-258E-492F-9F63-093B165C7E9F",
                              "restURL": "Region",
                              "bindable": "regionId",
                              "columnWidth": 0.5,
                              "listeners": {
                                   "change": "onRegionIdChange"
                              }
                         }, {
                              "name": "villageName",
                              "itemId": "villageName",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "Village Name",
                              "margin": "5 5 5 5",
                              "fieldLabel": "Village Name",
                              "fieldId": "3D04FD52-45B8-427D-8050-A32D78DBA361",
                              "minLength": "1",
                              "maxLength": "128",
                              "bindable": "villageName",
                              "columnWidth": 0.5
                         }, {
                              "name": "talukaName",
                              "itemId": "talukaName",
                              "xtype": "textfield",
                              "customWidgetType": "vdTextfield",
                              "displayName": "talukaa",
                              "margin": "5 5 5 5",
                              "fieldLabel": "talukaa",
                              "fieldId": "1828853F-C6E7-42EC-B937-E7A898449A68",
                              "minLength": "1",
                              "maxLength": "128",
                              "bindable": "talukaName",
                              "columnWidth": 0.5
                         }, {
                              "name": "versionId",
                              "itemId": "versionId",
                              "xtype": "numberfield",
                              "customWidgetType": "vdNumberfield",
                              "displayName": "versionId",
                              "margin": "5 5 5 5",
                              "value": "-1",
                              "fieldLabel": "versionId",
                              "fieldId": "414A32C0-0103-4D4D-92C5-A758A5D49815",
                              "bindable": "versionId",
                              "hidden": true
                         }]
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Get Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "layout": "card",
               "defaults": {
                    "autoScroll": true
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill"
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "xtype": "button",
                         "customWidgetType": "vdButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "extend": "Ext.form.Panel",
               "region": "center",
               "customWidgetType": "vdCardLayout"
          }]
     }]
});