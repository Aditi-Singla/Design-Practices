<?php

session_start();
$response = array();
$conn;
require_once __DIR__ . '/include/db_connect.php';
$db = new DB_CONNECT();
$conn = $db->connect();

$inputarray = array('tags');

$tags = $_POST["tags"];
print_r($tags);	



?>