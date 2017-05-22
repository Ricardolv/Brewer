Brewer.TableItems = (function() {
	
	function TableItems(autoComplete) {
		this.autoComplete = autoComplete;
		this.tableBeersContainer = $('.js-table-beers-container');
	}
	
	TableItems.prototype.init = function() {
		this.autoComplete.on('selected-item', onSelectedItem.bind(this));
	}
	
	function onSelectedItem(event, item) {
		var response = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codeBeer: item.code
			}
			
		});
		
		response.done(onAddedOnTheServer.bind(this));
	}
	
	function onAddedOnTheServer(html) {
		this.tableBeersContainer.html(html);
	}
	
	return TableItems;
	
}());

$(function() {
	
	var autoComplete = new Brewer.Autocomplete();
	autoComplete.init();
	
	var tableItems = new Brewer.TableItems(autoComplete);
	tableItems.init();
	
});