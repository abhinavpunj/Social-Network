function validateEmail(){
	var mail = document.getElementById('email').value;
	var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	
	var valid = filter.test(mail);
	var validEmail = document.getElementById('validEmail');
	
	 var goodColor = "#66cc66";
	    var badColor = "#ff6666";
	   
	    var showenable = 0;
	if(!valid) {
		validEmail.style.color = badColor;
		validEmail.innerHTML = "Email is not valid";
	   	document.getElementById("save").disabled = true;

        return false;
    } else {
    	validEmail.style.color = goodColor;
    	validEmail.innerHTML = "Email is valid";
       	document.getElementById("save").disabled = showenable;
    	
    	return true;
    }};/**
 * 
 */