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
$inputarray = array('complaint_id');
if (verify($inputarray))
{
	$complaint_id = $_POST["complaint_id"];

	$stmt = $conn->prepare("SELECT resolved FROM complaints where complaint_id = ?");
	$stmt->bind_param("i",$complaint_id);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($old);
	$stmt->fetch();
	$stmt->free_result();
	$stmt->close();

	if ($old=="Resolved")
		$resolve = "Pending";
	else
		$resolve = "Resolved";

	$query2 = "UPDATE complaints SET resolved = ? WHERE complaint_id = ?";
	$stmt2 = $conn->prepare($query2);
	$stmt2->bind_param("si",$resolve,$complaint_id);
	$stmt2->execute();
	if ($stmt2->affected_rows > 0)
	{
		$response["success"] = 1;
		$response["message"] = "Resolved changed successfully";
	}
	else
	{
		$response["success"] = 0;
		$response["message"] = "Same resolve sent";
	}
	$stmt2->close();
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