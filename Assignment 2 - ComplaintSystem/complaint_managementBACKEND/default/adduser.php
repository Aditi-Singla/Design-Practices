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

$inputarr = array('username','password','logintype','usertype','name');
if (verify($inputarr))
{
	$username = $_POST['username'];
	$password = $_POST['password'];
	$logintype = $_POST['logintype'];
	$usertype = $_POST['usertype'];
	$name = $_POST['name'];
	$entryno;
	$hostel;
	$errmessage = "";
	if (isset($_POST['entryno']))
		$entryno = $_POST['entryno'];
	if (isset($_POST['hostel']))
		$hostel = $_POST['hostel'];
	$query = "INSERT INTO users(username,password,logintype,usertype,name,entryno,hostel) VALUES(?,?,?,?,?,?,?)";
	$stmt = $conn->prepare($query);
	$stmt->bind_param("sssssss",$username,$password,$logintype,$usertype,$name,$entryno,$hostel);
	$result = $stmt->execute();
	$errmessage = $conn->error;
	$stmt->close();
	if($result)
	{
		$response["success"] = 1;
		$response["message"] = "User added successfully";
		header('Content-Type: application/json');
		echo json_encode($response);
	}
	else
	{
		$response["success"] = 0;
   		$response["message"] = $errmessage;
   		header('Content-Type: application/json');
		echo json_encode($response);
	}
}
else
{
	$response["success"] = 0;
	$response["message"] = "Required field(s) missing";
	header('Content-Type: application/json');
	echo json_encode($response);
}

?>