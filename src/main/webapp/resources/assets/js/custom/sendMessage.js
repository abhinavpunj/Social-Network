$(document).ready(function() {

	$("#send").click(function() {
			

			var div = $(this).parent().find("#message");
			var message = $(this).parent().find("#message").val();
			var chatArea = $(this).parent().parent().find("#chatArea");
			var personId = $(this).parent().find("#personId").val();
				$.ajax(
						{
							type : "GET",
							data : "personId=" + personId + "&message=" + message,
							url : "sendMessage",
							success : function(data) {
										$(chatArea).append(data);
							},
							error : function(xhr, ajaxOptions, thrownError) {
		                        alert(xhr.status);
		                        alert(thrownError);
								}
					});

	});
});