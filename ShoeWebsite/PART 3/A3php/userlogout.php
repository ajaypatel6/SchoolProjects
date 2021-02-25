#!/usr/bin/php-cgi
<?php
// LOGIN PAGE REFERS TO CODE FROM https://www.tutorialspoint.com/php/php_login_example.htm
   session_start();
   unset($_SESSION["username"]);
   unset($_SESSION["password"]);
   
   echo 'You have logged out';
   header('Refresh: 2; URL = index.php');
?>