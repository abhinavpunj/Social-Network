$(document).ready(function() {

		$('.messages').click(function() {
			var badge = $(this).find(".badge");
				$.ajax(
						{

							type : "GET",
							url : "markMessageRead",
							success : function(data) {
								$(badge).html(data);
							},
							error : function(xhr, ajaxOptions, thrownError) {
	                        alert(xhr.status);
	                        alert(thrownError);
							}
					});
		});
});