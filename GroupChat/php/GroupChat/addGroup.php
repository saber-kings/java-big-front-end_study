<?php
include "conn.php";

$uid = isset($_REQUEST["uid"]) ? $_REQUEST["uid"] : 0;
$gid = isset($_REQUEST["gid"]) ? $_REQUEST["gid"] : 0;

$str = "{";

if ($uid != 0 && $gid != 0) {
    $isExist = $conn->query("select * from t_user_group where uid=" . $uid . " and gid=" . $gid);
    $num = mysqli_num_rows($isExist);
    if ($num > 0) {
        $str .= "\"msg\":\"existed\"";
    } else if ($num == 0) {
        $re = $conn->query("insert into t_user_group(uid,gid) values(" . $uid . "," . $gid . ")");

        if ($re) {
            $str .= "\"msg\":\"success\"";
        } else {
            $str .= "\"msg\":\"error\"";
        }
    }

    mysqli_free_result($isExist);
} else {
    $str .= "\"msg\":\"isNull\"";
}

$str .= "}";

echo $str;

mysqli_close($conn);
