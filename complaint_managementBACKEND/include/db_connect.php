<?php
/*
connect to database
*/
class DB_CONNECT
{
	function _construct()
	{
		$this->connect();
	}
	function _destruct()
	{
		$this->close();
	}

	function connect()
	{
		require_once __DIR__ . '/db_config.php';
		$con = mysqli_connect(DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE) or die(mysqli_connect_error());
		return $con;
	}
	function close()
	{
		mysql_close();
	}
}

?>