<?php

$link = mysql_connect('localhost', 'b1012213', 'b1012213');

if(!$link) {
        die('接続失敗です。'.mysql_error());
}

$db_selected = mysql_select_db('213test', $link);
if(!$db_selected) {
        die('データベース選択失敗です。'.mysql_error());
}

$json = '{"cj":[';

$result = mysql_query('SELECT rating,detail FROM review');
if(!$result) {
        die('クエリが失敗しました。'.mysql_error());
}

while($row = mysql_fetch_assoc($result)) {
        $json .= '{"rating":"'.$row['rating'].'",';
        $json .= '"detail":"'.$row['detail'].'"},';
}

$json = substr($json, 0, -1);
$json .= ']}';

mysql_close($link);

print($json);
?>
