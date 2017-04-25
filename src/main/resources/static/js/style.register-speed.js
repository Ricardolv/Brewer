$(function() {
	
	var modalStyle = $('#modalRegisterSpeedStyle');
	var buttonSave = $('.js-modal-register-style-save-btn');
	var form = modalStyle.find('form');
	form.on('submit', function(event) { event.preventDefault() });
	var url = form.attr('action');
	var inputNameStyle = $('#nameStyle');
	var containerMessageError = $('.js-message-register-speed-style');
	
	modalStyle.on('shown.bs.modal', onModalShow);
	modalStyle.on('hide.bs.modal', onModalClose);
	buttonSave.on('click', onButtonSaveClick)
	
	function onModalShow() {
		inputNameStyle.focus();
	}
	
	function onModalClose() {
		inputNameStyle.val('');
		containerMessageError.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}
	
	function onButtonSaveClick() {
		var nameStyle = inputNameStyle.val().trim(); 
		$.ajax({
			url : url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ name: nameStyle }),
			error: onErrorSaveStyle,
			success: onSaveStyle
		});
	}
	
	function onErrorSaveStyle(obj) {
		var messageError = obj.responseText;
		containerMessageError.removeClass('hidden');
		containerMessageError.html('<span>' + messageError + '</span>');
		form.find('.form-group').addClass('has-error');
		
	}
	
	function onSaveStyle(style) {
		var inputStyle = $('#input-style');
		inputStyle.append('<option value=' + style.code + '>' + style.name + '</option>');
		inputStyle.val(style.code);
		modalStyle.modal('hide');
	}
	
	
});