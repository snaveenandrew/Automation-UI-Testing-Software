		(function() {
			"use strict";
		 
			var video, $output;
			var scale = 0.25;

			var initialize = function() {
				$output = $("#output");
				video = $("#video").get(0);
				$("#capture").click(captureImage);                
			};
		 
			var captureImage = function() {
				var canvas = document.createElement("canvas");
				canvas.width = video.videoWidth * scale;
				canvas.height = video.videoHeight * scale;
				canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);
				var img = document.createElement("img");
				img.src = canvas.toDataURL();
				document["output"].src = img.src;
			};
			$(initialize);            
		}());
		