Brewer = Brewer || {};

Brewer.Autocomplete = (function() {
	
	function Autocomplete() {
		this.skuOuNomeInput = $('.js-sku-name-beers-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-beer').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(skuOrName) {
				return '/brewer/beers?skuOrName=' + skuOrName;
			},
			getValue: 'name',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template: {
				type: 'custom',
				method: function(nome, beer) {
					beer.valueFormat = Brewer.currencyFormat(beer.value);
					return this.template(beer);
				}.bind(this)
			}
		};
		
		this.skuOuNomeInput.easyAutocomplete(options);
	}
	
	return Autocomplete
	
}());

$(function() {
	
	var autocomplete = new Brewer.Autocomplete();
	autocomplete.iniciar();
	
})
