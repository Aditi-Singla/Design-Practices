<?php
session_start();
$response = array();
$user = $_SESSION["username"];
session_destroy();
$response["success"] = 1;
$response["message"] = "Logout successful " . $user;
header('Content-Type: application/json');
echo json_encode($response);
?>