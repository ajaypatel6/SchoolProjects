#!/usr/bin/php-cgi
<?php
// LOGIN PAGE REFERS TO CODE FROM https://www.tutorialspoint.com/php/php_login_example.htm
if(isset($_COOKIE["cookie"])) {
    $cookie = true;
}
else{
	$cookie = false;
} 
?>
<!-- Ajay Patel / 5660055 / ap14jl / https://www.cosc.brocku.ca/~ap14jl/A3/wishlist.php -->
<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>The Shoe Store</title>
	<link rel="stylesheet" type="text/css" href="theme.css"/>
	<h1> The Shoe Store </h1>
	<img src="ShoeLogo.jpg" alt="ShoeLogo" style="width:100px;height:50px;"/><!--TOP PICS HERE-->
	<?php if (!$cookie): ?>
	<right><a class = "thumb" href = "userlogin.php">Log in</a><right>
	<?php else: ?>
	<right><a class = "thumb" href = "userlogout.php">Log out</a><right>
	<?php endif; ?>
  <script>
  
	function addShoe(brand) {
		var output = brand+ " :Amount: "+parseInt(localStorage.getItem(brand));
		
		var e = document.createElement("LI");//e=element
		var o = document.createTextNode(output +" (X)");//o=output
		e.appendChild(o);
		
		if (output != '') {
			document.getElementById("shoeList").appendChild(e);
		}
	}

	function removeShoe(brand) {
		if(brand == "Adidas"){
			localStorage.setItem("Adidas",0);
		}
		if(brand =="Nike"){
			localStorage.setItem("Nike",0);
		}
		if(brand =="Vans"){
			localStorage.setItem("Vans",0);
		}
	}

  </script>
  
</head>

<body>
<header>
	<nav>
		<a id=""/>
			<ul>
				<li></li>
				<a class = "thumb" href="index.php">Home</a>
				<a class = "thumb" href="PO.php">Products</a>
				<a class = "thumb" href="Wishlist.php">Wishlist</a>
			</ul>
	</nav>
</header>
		<h2> Wishlist  </h2>
		<ol id="shoeList">

		</ol>
<script>
	if(parseInt(localStorage.getItem('Adidas'))!=0){
		addShoe('Adidas');
	}
	
	if(parseInt(localStorage.getItem('Nike'))!=0){
		addShoe('Nike');
	}
	
	if(parseInt(localStorage.getItem('Vans'))!=0){
		addShoe('Vans');
	}
	
	
	//removeshoes

</script>
    
</body>
<footer>
	<small>Ajays Shoe Store</small><br/>
</footer> 
</html>