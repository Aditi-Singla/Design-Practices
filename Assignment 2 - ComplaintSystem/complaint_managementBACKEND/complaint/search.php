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

$inputarray = array('level','input');

if (verify($inputarray))
{
	$username = $_SESSION['username'];
	$level = $_POST['level'];
	$usertype;
	$hostel;
	$stringtags = $_POST['input'];
	$words = explode(" ",$stringtags);

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
					$response["success"] = 1;
					$response["allcomplaints"] = array();
					foreach ($words as $word) 
					{
						$wordlike = "%".$word."%";
						$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where( ((username = ? AND level = ?) OR (type = ?)) AND ( description like ? ) ) ";
						$stmt = $conn->prepare($query);
						$stmt->bind_param("ssss",$username,$level,$indi,$wordlike);
						$stmt->execute();
						$result = $stmt->get_result();
						$stmt->close();
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
				case "Electrician" :
				{
					$indi = "Individual Electrical";
					$response["success"] = 1;
					$response["allcomplaints"] = array();
					foreach ($words as $word) 
					{
						$wordlike = "%".$word."%";
						$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where ( ((username = ? AND level = ?) OR (type = ?)) AND (description like ?) )";
					$stmt = $conn->prepare($query);
					$stmt->bind_param("ssss",$username,$level,$indi,$wordlike);
					$stmt->execute();
					$result = $stmt->get_result();
					$stmt->close();
					
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
				case "Plumber" :
				{
					$indi = "Individual Plumbing";
					$response["success"] = 1;
					$response["allcomplaints"] = array();
					foreach ($words as $word) 
					{
						$wordlike = "%".$word."%";
						$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where ( ((username = ? AND level = ?) OR (type = ?)) AND (description like ?) )";
					$stmt = $conn->prepare($query);
					$stmt->bind_param("ssss",$username,$level,$indi,$wordlike);
					$stmt->execute();
					$result = $stmt->get_result();
					$stmt->close();
					
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
				case "Security" :
				{
					$indi = "Individual Security";
					$response["success"] = 1;
					$response["allcomplaints"] = array();
					foreach ($words as $word) 
					{
						$wordlike = "%".$word."%";
						$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where ( ((username = ? AND level = ?) OR (type = ?)) AND (description like ?) )";
					$stmt = $conn->prepare($query);
					$stmt->bind_param("ssss",$username,$level,$indi,$wordlike);
					$stmt->execute();
					$result = $stmt->get_result();
					$stmt->close();
					
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
				default :
				{
					$response["success"] = 1;
					$response["allcomplaints"] = array();
					foreach ($words as $word) 
					{
						$wordlike = "%".$word."%";
						$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where ( ( (username = ? AND level = ?) ) AND (description like ?) )";
					$stmt = $conn->prepare($query);
					$stmt->bind_param("sss",$username,$level,$wordlike);
					$stmt->execute();
					$result = $stmt->get_result();
					$stmt->close();
					
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
				$response["success"] = 1;
					$response["allcomplaints"] = array();
					foreach ($words as $word) 
					{
						$wordlike = "%".$word."%";
						$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where ( (level = ? AND hostel = ?) AND (description like ?) )";
					$stmt = $conn->prepare($query);
					$stmt->bind_param("sss",$level,$hostel,$wordlike);
					$stmt->execute();
					$result = $stmt->get_result();
					$stmt->close();
					
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
			}
			header('Content-Type: application/json');
			echo json_encode($response);
			break;
		}

		////////// Institute /////////////
		case "Institute" :
		{
			$response["success"] = 1;
					$response["allcomplaints"] = array();
					foreach ($words as $word) 
					{
						$wordlike = "%".$word."%";
						$query = "SELECT title,createdat,username,resolved,type,complaint_id FROM complaints where (level = ? AND description like ?)";
					$stmt = $conn->prepare($query);
					$stmt->bind_param("ss",$level,$wordlike);
					$stmt->execute();
					$result = $stmt->get_result();
					$stmt->close();
					
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