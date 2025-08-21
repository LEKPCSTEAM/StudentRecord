<?php
require 'db.php';
$regno = $_POST['regno'] ?? '';
$fullname = $_POST['fullname'] ?? '';
$gender = $_POST['gender'] ?? '';
$program = $_POST['program'] ?? '';
$college = $_POST['college'] ?? '';
$date = $_POST['date'] ?? '';
if ($regno===''){ http_response_code(400); echo "missing_regno"; exit; }
$stmt = $mysqli->prepare("INSERT INTO students(regno,fullname,gender,program,college,date) VALUES(?,?,?,?,?,?)");
$stmt->bind_param("ssssss",$regno,$fullname,$gender,$program,$college,$date);
if($stmt->execute()) echo "created"; else { http_response_code(409); echo "exists_or_error"; }
