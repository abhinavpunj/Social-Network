$(document).ready(function() {

	$(".wrapper").on('click', '.addComment', function(){

		var link = $(this);
		var comment = $(this).parent().find(".comment").val();
		var div = $(this).parent().parent();
		var postId = $(div).find("hidden").attr("value");

		$.ajax(
				{
					type : "GET",
					data : "id=" + postId + "&comment=" + comment,
					url : "addComment",
					success : function(data) {
								$(div).find(".commentSection").append(data);
								
					},
					error : function(xhr, ajaxOptions, thrownError) {
	                        alert(xhr.status);
	                        alert(thrownError);
							}
			});
	});
});