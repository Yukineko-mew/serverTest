<?php

$jsonInput = file_get_contents('php://input');
$json = json_decode($jsonInput);

$link = mysql_connect('localhost', 'b1012213', 'b1012213');

if(!$link) {
        die('接続失敗です。'.mysql_error());
}

$db_selected = mysql_select_db('213test', $link);
if(!$db_selected) {
        die('データベース選択失敗です。'.mysql_error());
}

$result = mysql_query('SELECT rating,detail FROM review');
if(!$result) {
        die('クエリが失敗しました。'.mysql_error());
}

$rating = $json->{'rating'};
$detail = "'".$json->{'detail'}."'";

print($rating);
print($detail);

$insert_flag = mysql_query("INSERT INTO review(rating, detail) VALUES($rating, $detail)");

if(!$insert_flag) {
        die('INSERTクエリ失敗。'.mysql_error());
}

mysql_close($link);

print("finish");

?>
