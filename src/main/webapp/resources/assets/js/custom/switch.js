$(document).ready(function() {
            $("#divEdit").hide();

            $("#edit").click(function(){
                $("#divSave").hide(1000);
                $("#divEdit").show(1000);
    });
    $("#save").click(function(){
              $("#divEdit").hide(1000);
        	  $("#divSave").show(1000);
    });
});