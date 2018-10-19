	function PreviewImage() {
		var oFReader = new FileReader();
		oFReader.readAsDataURL(document.getElementById("avatarFile").files[0]);

		oFReader.onload = function (oFREvent) {
			document.getElementById("avatar").src = oFREvent.target.result;
		};
	};
