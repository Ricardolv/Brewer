var Brewer = Brewer || {};

Brewer.UploadPhoto = (function() {
	
	function UploadPhoto() {
		
		this.inputPhotoName = $('input[name=photo]');
		this.inputContentType = $('input[name=contentType]');
		
		this.htmlPhotoBeerTemplate = $('#photo-beer').html();
		this.template = Handlebars.compile(this.htmlPhotoBeerTemplate);
		
		this.containerPhotoBeer = $('.js-container-photo-beer');
		
		this.uploadDrop = $('#upload-drop');
	}
	
	UploadPhoto.prototype.start = function() {
		
		var settings = {
				type: 'json',
				filelimit: 1,
				allow: '*.(jpg|jpeg|png)',
				action: this.containerPhotoBeer.data('url-photos'),
				complete: onUploadComplete.bind(this)
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
		
	}
	
	function onUploadComplete(response) {
		
		this.inputPhotoName.val(response.name);
		this.inputContentType.val(response.contentType);
		
		this.uploadDrop.addClass('hidden');
		var htmlPhotoBeer = this.template({ photoName: response.name });
		this.containerPhotoBeer.append(htmlPhotoBeer);
		
		$('.js-remove-photo').on('click', onRemovePhoto.bind(this));
	}
	
	function onRemovePhoto() {
		$('.js-photo-beer').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputPhotoName.val('');
		this.inputContentType.val('');
	}
	
	return UploadPhoto;
	
})();

$(function() {

	var uploadPhoto = new Brewer.UploadPhoto();
	uploadPhoto.start();
	
});
