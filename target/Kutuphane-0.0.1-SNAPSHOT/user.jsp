<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id=menu>
		<div id="searchbook">
			<h1>Kitap Arama Ekrani</h1>
			<h3>Kitap adi veya isbn numarasi girin:</h3>
			<h4>Format: kitap_adi</h4>
			<form action="Service/book/searchBook" method="POST">
				<input type="text" id="book" name="book" />
				<p>
					<button type="button" onclick="searchBook()">Kitap Ara</button>
				</p>
			</form>
		</div>
		<div id="sb">
			...
		
		</div>


		<div id="takeBook">
			<h1>Kitap Alma Ekrani</h1>
			<p>Tum Kitaplar</p>
			<button onclick="listBooks()">Kitaplari Getir</button>
			<div id="rett"></div>
			<p>
				<input type="text" id="bookisbn" name="bookisbn" />
			</p>
			<p>
				<button onclick="takeBook()">Kitap Al</button>
			</p>
		</div>
		<div id="giveBook">
			<h1>Kitap Verme Ekrani</h1>
			<form action="Service/book/giveBook" method="post"
				enctype="multipart/form-data">
				<p>
					Select a file : <input type="file" name="file" size="45" />
				</p>
				<button onclick="giveBook()">Kitap Ver</button>
			</form>
		</div>
	</div>
	<script>
		function searchBook() {

			var book = document.getElementById("book").value;
			

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("sb").innerHTML = this.responseText;
				}
			};
			xhttp.open("POST",
					"http://localhost:8080/Kutuphane/Service/book/searchBook", true);
			xhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			var s = "searchBook=" + book;
			xhttp.send(s);
		}
		
		function listBooks() {
			var xhttp = new XMLHttpRequest();
			//user login
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("rett").innerHTML = this.responseText;
				}
			};
			xhttp.open("GET","http://localhost:8080/Kutuphane/Service/book/listBooksGet",true);
			//xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send();
		}
		function takeBook() {
			var user_id = getCookie("userid");
			var bookisbn = document.getElementById("bookisbn").value;
			
			var xhttp = new XMLHttpRequest();
			//user login
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("rett").innerHTML = this.responseText;
				}
			};
			xhttp.open("POST",
					"http://localhost:8080/Kutuphane/Service/book/takeBook",
					true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			var s = "takeBookIsbn=" + bookisbn + "&takeBookUserId=" + user_id; 
			xhttp.send(s);
		}
		function giveBook() {
			var xhttp = new XMLHttpRequest();
			//user login
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("rett").innerHTML = this.responseText;
				}
			};
			xhttp.open("POST",
					"http://localhost:8080/Kutuphane/Service/book/giveBook",
					true);
			xhttp.setRequestHeader("Content-type", "multipart/form-data");
			xhttp.send(s);
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