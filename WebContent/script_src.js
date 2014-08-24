function getDateTime(past) {
	var dateTime = Number(new Date((new Date()).valueOf() - past));
		return dateTime;
	}
	function loadXMLDoc() {

		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var jsonobj = JSON.parse(xmlhttp.responseText);
				//document.getElementById("mydiv1").innerHTML += xmlhttp.responseText;
				parseJson(jsonobj);
				//document.getElementById("mydiv1").innerHTML += getDateTime();
			}
		};
		var currentTime = getDateTime(3000);
		var url = "http://pune876.egain.in:8080/ChatRoom/chat/getmsg?lastTime=" + currentTime; 
		xmlhttp.open("GET", url, true);
		xmlhttp.send();
	}
	function myFunction() {
		setInterval(function() {
			loadXMLDoc();
		}, 3000);
	}
	
	function parseJson(arr) {
	    for(var i in arr) {
	        document.getElementById("getmsgbox").innerHTML += "<i>"+arr[i].toString()+"</i><br />";
	    }
	}
	window.onload = myFunction();
	
	function sendTime() {
		document.getElementById('date').value = getDateTime(0);
	}
