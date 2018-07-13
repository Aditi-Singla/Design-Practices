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

$inputarray = array('level');

if (verify($inputarray))
{
	$username = $_SESSION['username'];
	$level = $_POST['level'];
	$usertype;
	$hostel;

	$stmt = $conn->prepare("SELECT usertype,hostel FROM users where username = ?");
	$stmt->bind_param("s",$username);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($usertype,$hostel);
	$stmt->fetch();
	$stmt->free_result();
	$stmt->close();

	switch($level)
	{
		////////// INDIVIDUAL /////////////
		case "Individual" :
		{
			switch($usertype)
			{
				case "Dean Academics" :
				{
					$indi = "Individual Academics";
					$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where (username = ? AND level = ?) OR (type = ?)";
					$stmt = $conn->prepare($query);
					$stmt->bind_param("sss",$username,$level,$indi);
					$stmt->execute();
					$result = $stmt->get_result();
					$stmt->close();
					$response["success"] = 1;
					$response["allcomplaints"] = array();
					while($complaint = $result->fetch_assoc())
					{
						$tmp = array();
						$tmp["complaint_id"] = $complaint["complaint_id"];
						$tmp["title"] = $complaint["title"];
						$tmp["type"] = $complaint["type"];
						$tmp["createdby"] = $complaint["username"];
						$tmp["createdat"] = $complaint["createdat"];
						$tmp["resolved"] = $complaint["resolved"];
						array_push($response["allcomplaints"], $tmp);
					}
					header('Content-Type: application/json');
					echo json_encode($response);

					break;
				}
				case "Electrician" :
				{
					$indi = "Individual Electrical";
					$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where (username = ? AND level = ?) OR (type = ?)";
					$stmt = $conn->prepare($query);
					$stmt->bind_param("sss",$username,$level,$indi);
					$stmt->execute();
					$result = $stmt->get_result();
					$stmt->close();
					$response["success"] = 1;
					$response["allcomplaints"] = array();
					while($complaint = $result->fetch_assoc())
					{
						$tmp = array();
						$tmp["complaint_id"] = $complaint["complaint_id"];
						$tmp["title"] = $complaint["title"];
						$tmp["type"] = $complaint["type"];
						$tmp["createdby"] = $complaint["username"];
						$tmp["createdat"] = $complaint["createdat"];
						$tmp["resolved"] = $complaint["resolved"];
						array_push($response["allcomplaints"], $tmp);
					}
					header('Content-Type: application/json');
					echo json_encode($response);

					break;
				}
				case "Plumber" :
				{
					$indi = "Individual Plumbing";
					$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where (username = ? AND level = ?) OR (type = ?)";
					$stmt = $conn->prepare($query);
					$stmt->bind_param("sss",$username,$level,$indi);
					$stmt->execute();
					$result = $stmt->get_result();
					$stmt->close();
					$response["success"] = 1;
					$response["allcomplaints"] = array();
					while($complaint = $result->fetch_assoc())
					{
						$tmp = array();
						$tmp["complaint_id"] = $complaint["complaint_id"];
						$tmp["title"] = $complaint["title"];
						$tmp["type"] = $complaint["type"];
						$tmp["createdby"] = $complaint["username"];
						$tmp["createdat"] = $complaint["createdat"];
						$tmp["resolved"] = $complaint["resolved"];
						array_push($response["allcomplaints"], $tmp);
					}
					header('Content-Type: application/json');
					echo json_encode($response);

					break;
				}
				case "Security" :
				{
					$indi = "Individual Security";
					$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where (username = ? AND level = ?) OR (type = ?)";
					$stmt = $conn->prepare($query);
					$stmt->bind_param("sss",$username,$level,$indi);
					$stmt->execute();
					$result = $stmt->get_result();
					$stmt->close();
					$response["success"] = 1;
					$response["allcomplaints"] = array();
					while($complaint = $result->fetch_assoc())
					{
						$tmp = array();
						$tmp["complaint_id"] = $complaint["complaint_id"];
						$tmp["title"] = $complaint["title"];
						$tmp["type"] = $complaint["type"];
						$tmp["createdby"] = $complaint["username"];
						$tmp["createdat"] = $complaint["createdat"];
						$tmp["resolved"] = $complaint["resolved"];
						array_push($response["allcomplaints"], $tmp);
					}
					header('Content-Type: application/json');
					echo json_encode($response);

					break;
				}
				default :
				{
					$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where (username = ? AND level = ?)";
					$stmt = $conn->prepare($query);
					$stmt->bind_param("ss",$username,$level);
					$stmt->execute();
					$result = $stmt->get_result();
					$stmt->close();
					$response["success"] = 1;
					$response["allcomplaints"] = array();
					while($complaint = $result->fetch_assoc())
					{
						$tmp = array();
						$tmp["complaint_id"] = $complaint["complaint_id"];
						$tmp["title"] = $complaint["title"];
						$tmp["type"] = $complaint["type"];
						$tmp["createdby"] = $complaint["username"];
						$tmp["createdat"] = $complaint["createdat"];
						$tmp["resolved"] = $complaint["resolved"];
						array_push($response["allcomplaints"], $tmp);
					}
					header('Content-Type: application/json');
					echo json_encode($response);
				}
			}
			break;
		}

		////////// Hostel /////////////
		case "Hostel" :
		{
			if ($usertype=="Dean Academics" || $usertype=="Electrician" || $usertype=="Plumber" || $usertype=="Security" || $usertype=="Normal Faculty")
			{
				$response["success"] = 1;
				$response["allcomplaints"] = $jsonarray;
			}
			else
			{
				$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where (level = ? AND hostel = ?)";
				$stmt = $conn->prepare($query);
				$stmt->bind_param("ss",$level,$hostel);
				$stmt->execute();
				$result = $stmt->get_result();
				$stmt->close();
				$response["success"] = 1;
				$response["allcomplaints"] = array();
				while($complaint = $result->fetch_assoc())
				{
					$tmp = array();
					$tmp["complaint_id"] = $complaint["complaint_id"];
					$tmp["title"] = $complaint["title"];
					$tmp["type"] = $complaint["type"];
					$tmp["createdby"] = $complaint["username"];
					$tmp["createdat"] = $complaint["createdat"];
					$tmp["resolved"] = $complaint["resolved"];
					array_push($response["allcomplaints"], $tmp);
				}	
			}
			header('Content-Type: application/json');
			echo json_encode($response);
			break;
		}

		////////// Institute /////////////
		case "Institute" :
		{
			$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where (level = ?)";
			$stmt = $conn->prepare($query);
			$stmt->bind_param("s",$level);
			$stmt->execute();
			$result = $stmt->get_result();
			$stmt->close();
			$response["success"] = 1;
			$response["allcomplaints"] = array();
			while($complaint = $result->fetch_assoc())
			{
				$tmp = array();
				$tmp["complaint_id"] = $complaint["complaint_id"];
				$tmp["title"] = $complaint["title"];
				$tmp["type"] = $complaint["type"];
				$tmp["createdby"] = $complaint["username"];
				$tmp["createdat"] = $complaint["createdat"];
				$tmp["resolved"] = $complaint["resolved"];
				array_push($response["allcomplaints"], $tmp);
			}
			header('Content-Type: application/json');
			echo json_encode($response);
			break;
		}


		default :
		{
			$response["success"] = 0;
			$response["allcomplaints"] = $jsonarray;
			header('Content-Type: application/json');
			echo json_encode($response);
		}
	}
}
else ////////////required fields missing ------> empty array
{
	$response["success"] = 0;
	$response["allcomplaints"] = $jsonarray;
	header('Content-Type: application/json');
	echo json_encode($response);
}

?>