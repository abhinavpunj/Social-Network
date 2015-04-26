$(document).ready(function(){
	$contentLoadTriggered = false;
	$(window).scroll(function(){
		if($(window).scrollTop() >= ($(document).height() - $(window).height()) && $contentLoadTriggered == false)
		{
			$contentLoadTriggered = true;
			$.get("infinite", function(data){
				$("#searchArea").append(data);
				$contentLoadTriggered = false;
			});
		}
	});

});