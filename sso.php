<?php
//echo phpinfo();
echo auth_basic();
echo'<br> authentification is working now';


// https://ssosecapi.fontys.nl/json.ashx?app=dEGRQ7sQZUGDmc5rVIQF4A=callback

// function callback(data) {}


function auth_basic() {
$userid='GUEST';
if(isset($_SERVER['AUTH_USER'])) { $user = explode("\\", $_SERVER['AUTH_USER']); }
if(isset($user[1]) && $user[1] != '') {$userid=$user[1]; }
else { $user = explode("@", $_SERVER['AUTH_USER']); }
if(isset($user[0]) && $user[0] != '') { $userid=$user[0]; }
return($userid);
}

?>