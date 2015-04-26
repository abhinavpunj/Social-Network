$(document).ready(function() {

	$(".fa-times").hide();
	$(".fa-check").hide();

	$(".username").blur(function() {

		var username = $(this).val();
		var submit = document.getElementById("save");
		$.ajax(
				{

					type : "GET",
					data : "username=" + username,
					url : "uniqueUsername",
					success : function(data) {
						if(data == "true")
						{
							$(".fa-check").show();
							$(".fa-times").hide();
							submit.disabled = 0;
						}
						if(data == "false")
						{
							$(".fa-times").show();
							$(".fa-check").hide();
							submit.disabled = true;

						}
					}
				
			});
	})
});