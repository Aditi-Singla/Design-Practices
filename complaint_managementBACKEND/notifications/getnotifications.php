<?php

session_start();
$response = array();
$jsonarray = array();
$conn;
require_once __DIR__ . '/../include/db_connect.php';
$db = new DB_CONNECT();
$conn = $db->connect();

$username = $_SESSION["username"];

$response["success"] = 1;
$response["allnotifications"] = array();

$query = "SELECT complaint_id,comment,username,createdat FROM comments WHERE ( (complaint_id IN (SELECT complaints.complaint_id FROM complaints WHERE complaints.username = ?)) AND username != ?)";
$stmt = $conn->prepare($query);
$stmt->bind_param("ss",$username,$username);
$stmt->execute();
$result = $stmt->get_result();
$stmt->close();
while($notification = $result->fetch_assoc())
{
	$tmp = array();
	$tmp["complaint_id"] = $notification["complaint_id"];
	$tmp["comment"] = $notification["comment"];
	$tmp["createdby"] = $notification["username"];
	$tmp["createdat"] = $notification["createdat"];
	array_push($response["allnotifications"],$tmp);
}
header('Content-Type: application/json');
echo json_encode($response);
?>