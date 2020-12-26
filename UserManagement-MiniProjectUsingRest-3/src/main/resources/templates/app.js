/**
 * 
 */
$(document).ready(function() {
	$.ajax({
		url : 'registerPage',
		cache : false,
		dataType : 'json',
		success : function(result){
			$.each(result, function(key, value){
				$('<option>').val(key).text(value).appendTo("#country");
			});
		}
	});
});
