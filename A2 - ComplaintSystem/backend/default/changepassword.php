<?php

session_start();
$response = array();
$conn;
require_once __DIR__ . '/../include/db_connect.php';
$db = new DB_CONNECT();
$conn = $db->connect();

function verify($inputarray)
{
	$verify = true;
	$request_params = $_REQUEST;
	foreach ($inputarray as $field) 
	{
		if (!isset($request_params[$field]) || strlen(trim($request_params[$field]))<=0)
		{
			$verify = false;
		}
	}
	return $verify;
}

$inputarray = array('oldpassword','newpassword');
if (verify($inputarray))
{
	$oldpassword = $_POST['oldpassword'];
	$newpassword = $_POST['newpassword'];
	$username = $_SESSION["username"];

	$query = "SELECT password FROM users where username = ?";
	$stmt = $conn->prepare($query);
	$stmt->bind_param("s",$username);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($actualpassword);

	if ($stmt->num_rows > 0)
	{
		$stmt->fetch();
		if ($actualpassword==$oldpassword)
		{
			$query2 = "UPDATE users SET password = ? WHERE username = ?";
			$stmt2 = $conn->prepare($query2);
			$stmt2->bind_param("ss",$newpassword,$username);
			$stmt2->execute();
			if ($stmt2->affected_rows > 0)
			{
				$response["success"] = 1;
				$response["message"] = "Password changed";
			}
			else
			{
				$response["success"] = 0;
				$response["message"] = "You entered the same password";
			}
			$stmt2->close();
		}
		else
		{
			$response["success"] = 0;
			$response["message"] = "Incorrect password";
		}
	}
	$stmt->free_result();
	$stmt->close();
	
    header('Content-Type: application/json');
    echo json_encode($response);
}
else
{
	$response["success"] = 0;
	$response["message"] = "Required field(s) missing";
	header('Content-Type: application/json');
	echo json_encode($response);
}

?>