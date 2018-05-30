<?php
$con= mysqli_connect ("localhost", "root", "","jobportal");
$file_path = "cv/";
$file_name = $_FILES['uploaded_file']['name'];
$file_path = $file_path.basename($_FILES['uploaded_file']['name']);
echo "$file_name";
//Writes the information to the database
mysqli_query($con,"INSERT INTO jobappied(cv_mobile) VALUES ('$file_name')") ;

if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'],$file_path))
{
	echo "success";
}
else
{
	echo "error";
}
?>