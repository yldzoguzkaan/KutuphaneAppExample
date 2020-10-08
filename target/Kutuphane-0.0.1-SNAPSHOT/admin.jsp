<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kutuphane App --> Admin Page</title>
</head>
<body>
	<div id="menu">
		<div id="kitapEkle">
			<h1>Kitap Ekleme Ekrani</h1>
			<form action="Service/book/bookUpload" method="post" enctype="multipart/form-data">
				<p>
					Select a file : <input type="file" name="file" size="45" />
				</p>
				<button onclick="insertBook()">Ekle</button>
			</form>
		</div>
		<div id="zamanAtlat">
			<h1>Zaman Atlatma Ekrani</h1>
			<h3>Zaman Formati : gg.aa.yyyy</h3>
			<form action="Service/time/skipTime" method="post">
				<p>
					Atlatilacak Gun Sayisi : <input type="text" name="time" />
					<button onclick="skipTime()">Ekle</button>
				</p>
			</form>
		</div>
		<div id="kullaniciListele">
			<h1>Kullanici Listeleme Ekrani</h1>
			<button onclick="listUsers()">Listele</button>
		</div>
	</div>
	<div id="content">
		<p>Tüm içerikler burada gözükecek</p>
	</div>
	<script>
		/** aktif işler **/
		function insertBook() {
			var xhttp = new XMLHttpRequest();
			//user login
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("content").innerHTML = this.responseText;
				}
			};
			xhttp.open("POST",
					"http://localhost:8080/Kutuphane/Service/book/bookUpload",
					true);
			xhttp.setRequestHeader("Content-type", "multipart/form-data");
			xhttp.send(s);
		}
		function skipTime() {
			
			var time = document.getElementById("time").value;
			var xhttp = new XMLHttpRequest();
			//user login
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("content").innerHTML = this.responseText;
				}
			};
			xhttp.open("POST",
					"http://localhost:8080/Kutuphane/Service/time/skipTime",
					true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			var s = "time=" + time; 
			xhttp.send(s);
		}
		function listUsers() {
			var xhttp = new XMLHttpRequest();
			//user login
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("content").innerHTML = this.responseText;
				}
			};
			xhttp.open("GET","http://localhost:8080/Kutuphane/Service/book/listUsersGet",true);
			//xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send();
		}
	</script>
</body>
</html>