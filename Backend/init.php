<?php
$db_name="hackathon";
$mysql_user="theabhinavjain";
$mysql_pass="";
$server_name="127.0.0.1";
$con=mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);

if(!$con)
{
    die("Error").mysqli_error($con);
//	echo "Connection error ".mysqli_connect_error();
}
else
{
//	echo "<h3> DB connection success ... </h3>";
}
?>