<?php
session_start();
$_SESSION = array();
session_destroy();
unset($_SESSION);
header('location:../index.php');

