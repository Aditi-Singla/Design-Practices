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

$inputarray = array('username','password','logintype');

if (verify($inputarray))
{
	$username = $_POST['username'];
	$password = $_POST['password'];
	$logintype = $_POST['logintype'];

	$query = "SELECT password,logintype,usertype,name,hostel FROM users where username = ?";
	$stmt = $conn->prepare($query);
	$stmt->bind_param("s",$username);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($actualpassword,$actuallogintype,$usertype,$name,$hostel);

	if ($stmt->num_rows > 0)
	{
		$stmt->fetch();
		if ($actualpassword == $password && $actuallogintype == $logintype)
		{
			$response["success"] = 1;
			$response["message"] = "Login successful";
			$response["logintype"] = $logintype;
			$response["usertype"] = $usertype;
			$response["name"] = $name;
			
			$_SESSION["username"] = $username;
		}
		else
		{
			$response["success"] = 0;
			$response["message"] = "Invalid credentials";   
		}
	}
	else
	{
		$response["success"] = 0;
		$response["message"] = "Incorrect username";
	}
	$stmt->free_result();
	$stmt->close();
	header('Content-Type: application/json');
	// print_r($_POST);
	echo json_encode($response);
}
else
{
	$response["success"] = 0;
	$response["message"] = "Required field(s) missing";
	header('Content-Type: application/json');
	// print_r($_POST);
	echo json_encode($response);
}
?>