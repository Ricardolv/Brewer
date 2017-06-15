var Brewer = Brewer || {};

Brewer.GraphicSaleByMonths = (function() {
	
	function GraphicSaleByMonths() {
		this.ctx = $('#graphicSaleByMonths')[0].getContext('2d');
	}
	
	GraphicSaleByMonths.prototype.init = function() {
		
		var graphicSaleByMonths = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun'],
		    	datasets: [{
		    		label: 'Vendas por mês',
		    		backgroundColor: "rgba(26,179,148,0.5)",
	                pointBorderColor: "rgba(26,179,148,1)",
	                pointBackgroundColor: "#fff",
	                data: [10, 5, 7, 2, 9]
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