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
$inputarray = array('complaint_id','vote');
if (verify($inputarray))
{
	$username = $_SESSION['username'];
	$complaint_id = (int) $_POST['complaint_id'];
	$vote = $_POST['vote'];
	$errmessage = "";

	$stmt = $conn->prepare("SELECT vote FROM votes where username = ? AND complaint_id = ?");
	$stmt->bind_param("si",$username,$complaint_id);
	$stmt->execute();
	$stmt->store_result();
	$stmt->bind_result($previousvote);
	$stmt->fetch();
	$stmt->free_result();
	$stmt->close();

	if ($previousvote===NULL)
	{
		$stmt = $conn->prepare("INSERT INTO votes(complaint_id,username,vote) VALUES (?,?,?)");
		$stmt->bind_param("iss",$complaint_id,$username,$vote);
		$result = $stmt->execute();
		$errmessage = $conn->error;
		$stmt->close();
		if($result)
		{
			switch ($vote)
			{
				case "up" :
				{
					$stmt = $conn->prepare("SELECT upvotes,difference FROM complaints where complaint_id = ?");
					$stmt->bind_param("i",$complaint_id);
					$stmt->execute();
					$stmt->store_result();
					$stmt->bind_result($upvotes,$difference);
					$stmt->fetch();
					$stmt->free_result();
					$stmt->close();

					$upvotes += 1; 
					$difference += 1; 
					$query2 = "UPDATE complaints SET upvotes = ?, difference = ? WHERE complaint_id = ?";
					$stmt2 = $conn->prepare($query2);
					$stmt2->bind_param("iii",$upvotes,$difference,$complaint_id);
					$stmt2->execute();
					if ($stmt2->affected_rows > 0)
					{
						$response["success"] = 1;
						$response["message"] = "Vote added successfully";
					}
					$stmt2->close();
					break;
				}
				case "down" :
				{
					$stmt = $conn->prepare("SELECT downvotes,difference FROM complaints where complaint_id = ?");
					$stmt->bind_param("i",$complaint_id);
					$stmt->execute();
					$stmt->store_result();
					$stmt->bind_result($downvotes,$difference);
					$stmt->fetch();
					$stmt->free_result();
					$stmt->close();

					$downvotes += 1; 
					$difference -= 1; 
					$query2 = "UPDATE complaints SET downvotes = ?, difference = ? WHERE complaint_id = ?";
					$stmt2 = $conn->prepare($query2);
					$stmt2->bind_param("iii",$downvotes,$difference,$complaint_id);
					$stmt2->execute();
					if ($stmt2->affected_rows > 0)
					{
						$response["success"] = 1;
						$response["message"] = "Vote added successfully";
					}
					$stmt2->close();
					break;
				}
				default : 
				{
					$response["success"] = 1;
					$response["message"] = "Vote added successfully";
				}
			}
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
		if ($previousvote==$vote)
		{
			switch ($vote)
			{
				case "up":
				{
					$stmt = $conn->prepare("SELECT upvotes,difference FROM complaints where complaint_id = ?");
					$stmt->bind_param("i",$complaint_id);
					$stmt->execute();
					$stmt->store_result();
					$stmt->bind_result($upvotes,$difference);
					$stmt->fetch();
					$stmt->free_result();
					$stmt->close();

					$upvotes -= 1; 
					$difference -= 1; 
					$query2 = "UPDATE complaints SET upvotes = ?, difference = ? WHERE complaint_id = ?";
					$stmt2 = $conn->prepare($query2);
					$stmt2->bind_param("iii",$upvotes,$difference,$complaint_id);
					$stmt2->execute();
					if ($stmt2->affected_rows > 0)
					{
						$response["success"] = 1;
						$response["message"] = "Vote added successfully";
					}
					$stmt2->close();
					break;
				}
				case "down":
				{
					$stmt = $conn->prepare("SELECT downvotes,difference FROM complaints where complaint_id = ?");
					$stmt->bind_param("i",$complaint_id);
					$stmt->execute();
					$stmt->store_result();
					$stmt->bind_result($downvotes,$difference);
					$stmt->fetch();
					$stmt->free_result();
					$stmt->close();

					$downvotes -= 1; 
					$difference += 1; 
					$query2 = "UPDATE complaints SET downvotes = ?, difference = ? WHERE complaint_id = ?";
					$stmt2 = $conn->prepare($query2);
					$stmt2->bind_param("iii",$downvotes,$difference,$complaint_id);
					$stmt2->execute();
					if ($stmt2->affected_rows > 0)
					{
						$response["success"] = 1;
						$response["message"] = "Vote added successfully";
					}
					$stmt2->close();
					break;
				}
				default:
				{
					$response["success"] = 1;
					$response["message"] = "Vote added successfully";
				}
			}

			$stmt = $conn->prepare("DELETE FROM votes WHERE (complaint_id = ? AND username = ?)");
			$stmt->bind_param("is",$complaint_id,$username);
			$stmt->execute();
			$stmt->close();

		}
		else
		{
			$response["success"] = 0;
			$response["message"] = "Invalid";
		}
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