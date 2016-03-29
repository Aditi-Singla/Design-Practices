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
	$complaint_id = $_POST['complaint_id'];
	$current = $_SESSION['username'];

	$stmt = $conn->prepare("SELECT usertype FROM users where username = ?");
	$stmt->bind_param("s",$current);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($usertype);
	$stmt->fetch();
	$stmt->free_result();
	$stmt->close();

	$query = "SELECT complaint_id,level,title,description,type,username,upvotes,downvotes,hostel,resolved,createdat,difference FROM complaints WHERE complaint_id = ?";
	$stmt = $conn->prepare($query);
	$stmt->bind_param("i",$complaint_id);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($complaint_id,$level,$title,$description,$type,$username,$upvotes,$downvotes,$hostel,$resolved,$createdat,$difference);
	if ($stmt->num_rows > 0)
	{
		$stmt->fetch();
		$response["success"] = 1;
		$response["message"] = "Successful";
		$response["myusername"] = $current;
		$response["myusertype"] = $usertype;
		$response["complaint_id"] = $complaint_id;
		$response["level"] = $level;
		$response["title"] = $title;
		$response["description"] = $description;
		$response["type"] = $type;
		$response["createdby"] = $username;
		$response["upvotes"] = $upvotes;
		$response["downvotes"] = $downvotes;
		$response["hostel"] = $hostel;
		$response["resolved"] = $resolved;
		$response["createdat"] = $createdat;
		$response["difference"] = $difference;

		$response["allcomments"] = array();
		$stmt2 = $conn->prepare("SELECT comment,username,createdat FROM comments where (complaint_id = ?) ORDER BY createdat ASC");
		$stmt2->bind_param("i",$complaint_id);
		$stmt2->execute();
		$result = $stmt2->get_result();
		$stmt2->close();
		while($item = $result->fetch_assoc())
		{
			$tmp = array();
			$tmp["comment"] = $item["comment"];
			$tmp["commentby"] = $item["username"];
			$tmp["commentaddedat"] = $item["createdat"];
			array_push($response["allcomments"],$tmp);
		}
	}
	else
	{
		$response["success"] = 0;
		$response["message"] = "No complaint found";
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