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
		
		response.done(onUpdatededOnTheServer.bind(this));
	}
	
	function onUpdatededOnTheServer(html) {
		this.tableBeersContainer.html(html);
		$('.js-table-beer-quantity-item').on('change', onQuantityChangedItem.bind(this));
		$('.js-table-item').on('dblclick', onDoubleClick);
		$('.js-exclusion-item-btn').on('click', onExclusionItemClick.bind(this));
	}
	
	function onQuantityChangedItem(event) {
		var input = $(event.target);
		var quantity = input.val();
		var codeBeer = input.data('code-beer');
		
		var response = $.ajax({
			url: 'item/' + codeBeer, 
			method: 'PUT',
			data: {
				quantity: quantity
			}
			
		});
		
		response.done(onUpdatededOnTheServer.bind(this));
	}
	
	function onDoubleClick(event) {
		$(this).toggleClass('requesting-exclusion');
	}
	
	function onExclusionItemClick(event) {
		var codeBeer = $(event.target).data("code-beer");
		var response = $.ajax({
			url: 'item/' + codeBeer, 
			method: 'DELETE',
			
		});
		
		response.done(onUpdatededOnTheServer.bind(this));
	}
	
	return TableItems;
	
}());

$(function() {
	
	var autoComplete = new Brewer.Autocomplete();
	autoComplete.init();
	
	var tableItems = new Brewer.TableItems(autoComplete);
	tableItems.init();
	
});