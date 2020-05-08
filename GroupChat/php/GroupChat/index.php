<?php
include "conn.php";

$uid = isset($_REQUEST["uid"]) ? $_REQUEST["uid"] : 0;

$re = $conn->query("select tg.id,tg.gname,tg.gimg,tg.gdesc,tug.muted from  t_group tg inner join t_user_group tug on tg.id = tug.gid where tug.uid=" . $uid);

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
