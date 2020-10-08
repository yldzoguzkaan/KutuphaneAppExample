<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kutuphane App</title>
</head>
<body>
	<h1>Hello World!</h1>
	<div id="login">
		<form>
			<p>
				USERNAME : <input type="text" id="username" name="username"
					size="45" />
			</p>
			<p>
				PASSWORD : <input type="password" id="password" name="password"
					size="45" />
			</p>
			<button type="button" onclick="loadDoc()">Login</button>
		</form>
	</div>
	<div id="ret">....</div>
	<script>
		function loadDoc() {

			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;

			if (username == "root" && password == "root") {
				location.replace("http://localhost:8080/Kutuphane/admin.jsp");
			}
			
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var user_id = this.responseText;
					setCookie("userid", user_id, 30);
					if (user_id == "150") {
						window.alert("username or password incorrect!");
					} else {
						location.replace("http://localhost:8080/Kutuphane/user.jsp");			
					}
				}
			};
			xhttp.open("POST",
					"http://localhost:8080/Kutuphane/Service/l/login", true);
			xhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			var s = "username=" + username + "&password=" + password;
			xhttp.send(s);
		}
		function setCookie(cname, cvalue, exdays) {
			var d = new Date();
			d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
			var expires = "expires=" + d.toUTCString();
			document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
		}
		function getCookie(cname) {
			var name = cname + "=";
			var decodedCookie = decodeURIComponent(document.cookie);
			var ca = decodedCookie.split(';');
			for (var i = 0; i < ca.length; i++) {
				var c = ca[i];
				while (c.charAt(0) == ' ') {
					c = c.substring(1);
				}
				if (c.indexOf(name) == 0) {
					return c.substring(name.length, c.length);
				}
			}
			return "";
		}
	</script>
</body>
</html>