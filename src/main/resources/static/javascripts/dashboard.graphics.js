var Brewer = Brewer || {};

Brewer.GraphicSaleByMonths = (function() {
	
	function GraphicSaleByMonths() {
		this.ctx = $('#graphicSaleByMonths')[0].getContext('2d');
	}
	
	GraphicSaleByMonths.prototype.init = function() {
		$.ajax({
			url:'sales/totalByMonth',
			method: 'GET',
			success: onDataReceived.bind(this)
		});
		
		
	}
	
	function onDataReceived(salesMonth) {
		var month = [];
		var values = [];
		
		salesMonth.forEach(function(obj) {
			month.unshift(obj.month);
			values.unshift(obj.total);
		});
		
		var graphicSaleByMonths = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: month,
		    	datasets: [{
		    		label: 'Vendas por mês',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                data: values
		    	}]
		    },
		});
	}
	
	return GraphicSaleByMonths;
	
}());

$(function() {
	
	var graphicSaleByMonths = new Brewer.GraphicSaleByMonths();
	graphicSaleByMonths.init();
	
});