	function PreviewImage() {
		var oFReader = new FileReader();
		oFReader.readAsDataURL(document.getElementById("avatarFile").files[0]);

		oFReader.onload = function (oFREvent) {
			document.getElementById("avatar").src = oFREvent.target.result;
		};
	};

function checkPasswordMatch() {
		var password = $("#newPass").val();
		var confirmPassword = $("#newPassRepeat").val();
		if ((password.length === 0) && (confirmPassword.length === 0)) {
			$("#divCheckPasswordMatch").hide();
			$("#submitButton").css('background-color','#4CAF50');
		}
		else{
			$("#divCheckPasswordMatch").show();
			if (password != confirmPassword){
				$("#divCheckPasswordMatch").html("Passwords do not match!").css('color','red');
				$("#submitButton").prop("disabled", true).css('background-color','grey');
			}
			else{
				$("#divCheckPasswordMatch").html("Passwords match.").css('color','green');
				$("#submitButton").prop("disabled", false).css('background-color','#4CAF50');
			}
		}
	};