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
		if (!$field=="tags")
		{
			if (!isset($request_params[$field]) || strlen(trim($request_params[$field]))<=0)
			{
				$verify = false;
			}
		}
		else
		{
			if (!isset($request_params[$field]))
			{
				$verify = false;
			}
		}
	}
	return $verify;
}

$inputarray = array('level','title','description','type','tags');

if (verify($inputarray))
{
	$username = $_SESSION['username'];
	$level = $_POST['level'];
	$title = $_POST['title'];
	$description = $_POST['description'];
	$type = $_POST['type'];
	$stringtags = $_POST['tags'];
	if (strlen(trim($stringtags))<=0)
		$tags = array();
	else
		$tags = explode(" ",$stringtags);
	$hostel;
	$errmessage = "";

	$hostelstmt = $conn->prepare("SELECT hostel FROM users where username = ?");
	$hostelstmt->bind_param("s",$username);
	$hostelstmt->execute();
	$hostelstmt->store_result();
	$hostelstmt->bind_result($hostel);
	$hostelstmt->fetch();
	$hostelstmt->free_result();
	$hostelstmt->close();

	$stmt = $conn->prepare("INSERT INTO complaints(level,title,description,type,username,hostel) VALUES (?,?,?,?,?,?)");
	$stmt->bind_param("ssssss",$level,$title,$description,$type,$username,$hostel);
	$result = $stmt->execute();
	$errmessage = $conn->error;
	$stmt->close();
	if($result)
	{
		$id = $conn->insert_id;
		foreach ($tags as $item) {
			$stmt = $conn->prepare("INSERT INTO tags(complaint_id,tag) VALUES (?,?)");
			$stmt->bind_param("is",$id,$item);
			$stmt->execute();
			$error = $conn->error;
			$stmt->close();
		}
		$response["success"] = 1;
		$response["message"] = "Complaint added successfully";
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