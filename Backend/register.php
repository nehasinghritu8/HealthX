<?php
require "init.php";

function executeQuery($conn,$sql)
{
    $result = mysqli_query($conn,$sql) 
    if($result)
        echo "Succeeded";
    else
        echo "Error :".mysqli_error($conn);
        
    return $result;
}

$user_name=$_POST["user_username"];
$user_pass=$_POST["user_pass"];
$user_type=$_POST["user_type"];
$user_name=$_POST["user_name"];
$image=$_POST["user_image"];
$user_address=$_POST["user_address"];
$user_description=$_POST["user_description"];

$sql_query="INSERT INTO users(username,passhash,name,type,address,description) VALUES('$user_username','$user_pass','$user_name','$user_type','$user_address','$user_description');";
executeQuery($con,$sql_query);

$sql_query="SELECT user_id FROM users WHERE username == $user_username and passhash == $user_pass ";
$user_id = executeQuery($con,$sql_query);

if($image!=NULL)
{
    $user_image = file_get_contents($image);
    $sql_query="INSERT INTO images(image,id) VALUES($user_image,$user_id)";
    executeQuery($con,$sql_query);
}
   
?>