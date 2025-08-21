<?php
require 'db.php';
$sql = "SELECT regno, fullname, program, DATE_FORMAT(date,'%Y-%m-%d') AS date, college, gender FROM students ORDER BY date DESC";
$res = $mysqli->query($sql);
$out = [];
while ($row = $res->fetch_assoc()) $out[] = $row;
header('Content-Type: application/json; charset=utf-8');
echo json_encode($out);
