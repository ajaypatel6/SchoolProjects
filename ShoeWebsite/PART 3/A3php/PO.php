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
<!-- Ajay Patel / 5660055 / ap14jl / https://www.cosc.brocku.ca/~ap14jl/A1/index.html -->

<head>
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
 var adidas= 0;
 var nike= 0;
 var vans= 0;



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
	<h2> Product Overview </h2>

	<center><h1> add or remove a shoe from wishlist </h1></center>
	
<?php
function adidas(){
	include 'adidas.html'; 
}

if (isset($_GET['adidas'])) {
    adidas();
}
?>
	<a href = 'PO.php?adidas=1'><p id = "custom"><strong>Adidas</strong></p></a>
	<!--https://cdn2.bigcommerce.com/server1500/ac84d/products/1204/images/2688/Adidas_Logo_Flower__83153.1337144903.380.380.jpg?c=2-->
	<a href = 'PO.php?adidas=1'><img src="adidas.jpg" alt="ShoeLogo" style="width:200px;height:100px;"/></a>
	</a>
<?php

function nike(){
	include 'nike.html'; 
}

if (isset($_GET['nike'])) {
    nike();
}


?>
	</br>
	<a href = 'PO.php?nike=2'><p id = "custom"><strong>Nike</strong></p></a>
	<!--https://cdn2.bigcommerce.com/server1500/ac84d/products/1269/images/2876/Nike_Logo__97043.1340418202.380.380.jpg?c=2-->
	<a href = 'PO.php?nike=2'><img src="nike.jpg" alt="n" style="width:200px;height:100px;"/></a>
	</a>
	</br>
	
<?php
function vans(){
	include 'vans.html'; 
}

if (isset($_GET['vans'])) {
    vans();
}
?>
	<a href = 'PO.php?vans=3'><p id = "custom"><strong>Vans</strong></p></a>
	<!--https://cdn2.bigcommerce.com/server1500/ac84d/products/919/images/1976/Vans_-_Name_Logo__43477.1325313570.380.380.jpg?c=2-->
	<a href = 'PO.php?vans=3'><img src="vans.jpg" alt="v" style="width:200px;height:100px;"/></a>
	</a>
	</br>

</body>

<footer>
	<small>Ajays Shoe Store</small><br/>
</footer> 
</html>