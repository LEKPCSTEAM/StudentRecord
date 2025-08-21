<?php
require 'db.php';
$regno = $_POST['regno'] ?? '';
if ($regno===''){ http_response_code(400); echo "missing_regno"; exit; }
$stmt = $mysqli->prepare("DELETE FROM students WHERE regno=?");
$stmt->bind_param("s",$regno);
if($stmt->execute()) echo "deleted"; else { http_response_code(500); echo "delete_error"; }
