Ext.define('Testproject.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Testproject.view.appreportui.ReportViewController',
	            'Testproject.view.appreportui.datagrid.DataGridPanel',
	            'Testproject.view.appreportui.datagrid.DataGridView',
	            'Testproject.view.appreportui.querycriteria.QueryCriteriaView',
	            'Testproject.view.appreportui.chart.ChartView',
	            'Testproject.view.appreportui.datapoint.DataPointView',
	            'Testproject.view.googlemaps.map.MapPanel',
	            'Testproject.view.appreportui.chartpoint.ChartPointView'
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
