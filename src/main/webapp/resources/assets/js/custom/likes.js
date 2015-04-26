$(document).ready(function() {

	$(".wrapper").on('click', '.addLike', function(){

		var link = $(this);
		var div = $(this).parent().parent();
		var postId = $(div).find("hidden").attr("value");
		var like = $(div).find("span");
		
		$.ajax(
				{
					type : "GET",
					data : "id=" + postId,
					url : "addLike",
					success : function(data) {
								$(like).html(data);
					},
					error : function(xhr, ajaxOptions, thrownError) {
	                        alert(xhr.status);
	                        alert(thrownError);
							}
			});
	});
});