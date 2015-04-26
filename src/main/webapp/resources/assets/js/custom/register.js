$(document).ready(function() {

	$(".container").on("submit", ".register", function() {

		$.post("register", $(this).serialize(), function(data) {
			if(data == "false")
			{
				$("#myAccountModal").modal('show');
			}
			else if(data == "true")
			{
				window.location.href = "index";
			}
		});
	});
});