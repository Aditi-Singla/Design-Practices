<?php

session_start();
$response = array();
$jsonarray = array();
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

$inputarray = array('complaint_id','comment');
if (verify($inputarray))
{
	$username = $_SESSION["username"];
	$complaint_id = $_POST["complaint_id"];
	$comment = $_POST["comment"];
	$stmt = $conn->prepare("INSERT INTO comments(complaint_id,comment,username) VALUES (?,?,?)");
	$stmt->bind_param("iss",$complaint_id,$comment,$username);
	$result = $stmt->execute();
	$errmessage = $conn->error;
	$stmt->close();
	if($result)
	{
		$response["success"] = 1;
		$response["message"] = "Comment added successfully";
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