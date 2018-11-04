<?php
    session_start();

    $uname		=	$_POST["username"];
    $pwd		=	$_POST["password"];

    // Check the empty validation then send back with error variable via GET method. 
    if($uname=="" || $pwd=="") {
        header("location: signupp.php");
        die();
    } else {
        $url = 'http://localhost:8090/api/admin/login';

        $context = stream_context_create(array(
            'http' => array(
                'method' => 'POST',
                'header' => 'Content-type: application/json',
                'content' => json_encode(
                    array(
                        'username' => $uname,
                        'password' => $pwd
                    )
                ),
                'timeout' => 60
            )
        ));

        $resp = file_get_contents($url, FALSE, $context);
        $data = json_decode($resp);
        if($data->{'status'} == 'SUCCESS')
        {
            $_SESSION["username"] = $data->{'data'}->{'login_isntance'}->{'userName'};  
            $_SESSION["futsal_id"] = $data->{'data'}->{'futsal_id'};
            $_SESSION["user_type"] = $data->{'data'}->{'login_user_group'};
            header("location: ../index.php");
        }else{
            $_SESSION["ERROR_MSG"] = "FAILED";
            header("location: login.php");
            die();
        }
        
    };

?>
