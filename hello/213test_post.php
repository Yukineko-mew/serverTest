<?php

$link = mysql_connect('localhost', 'b1012213', 'b1012213');

if(!$link) {
        die('�ڑ����s�ł��B'.mysql_error());
}

$db_selected = mysql_select_db('213test', $link);
if(!$db_selected) {
        die('�f�[�^�x�[�X�I�����s�ł��B'.mysql_error());
}

$json = '{"cj":[';

$result = mysql_query('SELECT rating,detail FROM review');
if(!$result) {
        die('�N�G�������s���܂����B'.mysql_error());
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
