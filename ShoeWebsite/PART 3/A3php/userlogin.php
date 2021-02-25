#!/usr/bin/php-cgi
<!doctype html>
<?php
// LOGIN PAGE REFERS TO CODE FROM https://www.tutorialspoint.com/php/php_login_example.htm
//https://www.w3schools.com/php/func_network_setcookie.asp
   ob_start();
   session_start();
   $cookie = false;
?>
 
         <?php
            $msg = '';
            
            if (isset($_POST['login']) && !empty($_POST['username']) 
               && !empty($_POST['password'])) {
				
               if ($_POST['username'] == 'username' && 
                  $_POST['password'] == 'password') {
                  $_SESSION['valid'] = true;
                  $_SESSION['timeout'] = time();
                  $_SESSION['username'] = 'username';
                  				  
				// $cookie = true;
				  header("Location:index.php");
				  setcookie("cookie","true", time() + (10000), "/"); 
				  
               }else {
                  $msg = 'Not valid';
               }
            }
         ?>


<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title> Log in </title>
    <link rel = "stylesheet"
     type = "text/css"
     href = "theme.css" />
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
</head>
	<body>


    <h2><b>Welcome to the #1 Ranked Shoe Store in the Universe</b></h2>
	
	<!--CUSTOM FONT--https://www.fontsquirrel.com/fonts/ostrich-sans-->
	<p	id = "custom"> <strong>Here we have one of our designers laying out prototype models of future footwears </strong></p>
	
	<img src="yeezy.png" alt="Yeezy" style="width:600px;height:350px;"/><!--https://i.redd.it/r4d5pfnp9a931.png-->
      <h2>Enter Username and Password</h2> 
      <div class = "container form-signin">
        
      </div> <!-- /container -->
      <div class = "container">
      
         <form class = "form-signin" role = "form" 
            action = "<?php echo htmlspecialchars($_SERVER['PHP_SELF']); 
            ?>" method = "post">
            <h4 class = "form-signin-heading"><?php echo $msg; ?></h4>
            <input type = "text" class = "form-control" 
               name = "username" placeholder = "username = username" 
               required autofocus></br>
            <input type = "password" class = "form-control"
               name = "password" placeholder = "password = password" required>
            <button class = "btn btn-lg btn-primary btn-block" type = "submit" 
               name = "login">Login</button>
         </form>
			         
         Click here to clean <a href = "userlogout.php" tite = "Logout">Session.
      </div> 
      
   </body>
<footer>
	<small>Ajays Shoe Store</small>
</footer> 
</html>
</html>