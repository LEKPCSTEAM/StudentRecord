<?php
require 'db.php';
$regno = $_POST['regno'] ?? '';
$fullname = $_POST['fullname'] ?? '';
$gender = $_POST['gender'] ?? '';
$program = $_POST['program'] ?? '';
$college = $_POST['college'] ?? '';
$date = $_POST['date'] ?? '';
if ($regno===''){ http_response_code(400); echo "missing_regno"; exit; }
$stmt = $mysqli->prepare("UPDATE students SET fullname=?, gender=?, program=?, college=?, date=? WHERE regno=?");
$stmt->bind_param("ssssss",$fullname,$gender,$program,$college,$date,$regno);
if($stmt->execute()) echo "updated"; else { http_response_code(500); echo "update_error"; }
