Ext.define('Testpro.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Testpro.view.appreportui.ReportViewController',
	            'Testpro.view.appreportui.datagrid.DataGridPanel',
	            'Testpro.view.appreportui.datagrid.DataGridView',
	            'Testpro.view.appreportui.querycriteria.QueryCriteriaView',
	            'Testpro.view.appreportui.chart.ChartView',
	            'Testpro.view.appreportui.datapoint.DataPointView',
	            'Testpro.view.googlemaps.map.MapPanel',
	            'Testpro.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
