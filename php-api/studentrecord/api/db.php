<?php
$mysqli = new mysqli("localhost","root","","allstudents");
$mysqli->set_charset("utf8mb4");
if ($mysqli->connect_errno) { http_response_code(500); echo "db_error"; exit; }
