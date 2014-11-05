<?php

$jsonInput = $_POST('jsonQuery');
$json = json_decode($jsonInput);

$link = mysql_connect('localhost', 'b1012213', 'b1012213');

if(!$link) {
        die('�ڑ����s�ł��B'.mysql_error());
}

$db_selected = mysql_select_db('213test', $link);
if(!$db_selected) {
        die('�f�[�^�x�[�X�I�����s�ł��B'.mysql_error());
}

$result = mysql_query('SELECT rating,detail FROM review');
if(!$result) {
        die('�N�G�������s���܂����B'.mysql_error());
}

$rating = $json->{'rating'};
$detail = "'".$json->{'detail'}."'";

print($rating);
print($detail);

$insert_flag = mysql_query("INSERT INTO review(rating, detail) VALUES($rating, $detail)");

if(!$insert_flag) {
        die('INSERT�N�G�����s�B'.mysql_error());
}

mysql_close($link);

print("finish");

?>
