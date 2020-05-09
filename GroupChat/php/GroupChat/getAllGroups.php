<?php
include "conn.php";

$re = $conn->query("select * from t_group");

$num = mysqli_num_rows($re);
//$row=mysqli_fetch_assoc($re);

$str = "[";

for ($i = 0; $i < $num; $i++) {
    $row = mysqli_fetch_assoc($re);
    $myJSON = json_encode($row, JSON_UNESCAPED_UNICODE);
    $str .= $myJSON . ",";
}
if ($num > 0) {
    $str = substr($str, 0, strlen($str) - 1);
}
$str .= "]";

echo $str;

mysqli_free_result($re);
mysqli_close($conn);
