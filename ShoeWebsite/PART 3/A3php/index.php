#!/usr/bin/php-cgi
<?php
// LOGIN PAGE REFERS TO CODE FROM https://www.tutorialspoint.com/php/php_login_example.htm
if(isset($_COOKIE["cookie"])) {
    $cookie = true;
}else{
	$cookie = false;
} 
?> 
<!-- Ajay Patel / 5660055 / ap14jl / https://www.cosc.brocku.ca/~ap14jl/A3/index.php-->
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>The Shoe Store</title>
	<link rel="stylesheet" type="text/css" href="theme.css"/>
	<h1> The Shoe Store </h1><!--image source : https://www.sccpre.cat/maxp/ioRhxb/-->
	<img src="ShoeLogo.jpg" alt="ShoeLogo" style="width:100px;height:50px;"/>

	<?php if (!$cookie): ?>
	<right><a class = "thumb" href = "userlogin.php">Log in</a><right>
	<?php else: ?>
	<right><a class = "thumb" href = "userlogout.php">Log out</a><right>
	<?php endif; ?>
</head>
<body>
<header>
	<nav>
		<a id=""/>
			<ul>
				<li></li>
				<a class = href="index.php">Home</a>
				<a class = "thumb" href="PO.php">Products</a>
				<a class = "thumb" href="Wishlist.php">Wishlist</a>
			</ul>
	</nav>
	
</header>
    <h2><b>Welcome to the #1 Ranked Shoe Store in the Universe</b></h2>
	<!--CUSTOM FONT--https://www.fontsquirrel.com/fonts/ostrich-sans-->
	<p	id = "custom"> <strong>Here we have one of our designers laying out prototype models of future footwears </strong></p>
	<img src="yeezy.png" alt="Yeezy" style="width:600px;height:350px;"/><!--https://i.redd.it/r4d5pfnp9a931.png-->
</body>

<footer>
	<small>Ajays Shoe Store</small>
</footer> 
</html>