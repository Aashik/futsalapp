<?php
  session_start();
?>


<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Futsal | Log in</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="../dist/css/login.css">
  <link rel="stylesheet" href="../plugins/iCheck/square/blue.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition login-page">
    <div class="login-wrap">
        <div class="login-html">
        <form action='config.php' method='post'> 
          <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
          <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab"></label>
          
            <div class="login-form">
              <div class="sign-in-htm">
                <div class="group">
                <?php
                    if(isset($_SESSION["ERROR_MSG"]) == 'FAILED')
                    {
                  ?>
                      <label id='error_msg' style='color:yellow;margin-left:20%' >Username/Password Incorrect</label>
                      
                  <?php
                      $_SESSION["ERROR_MSG"] = '';
                    };
                  ?>
                  <label for="user" class="label">Username</label>
                  <input id="user" type="text" name='username' class="input">
                </div>
                <div class="group">
                  <label for="pass" class="label">Password</label>
                  <input id="pass" type="password" class="input" name="password" data-type="password">
                </div>
                <br/>
                <div class="group">
                  <input id="check" type="checkbox" class="check" checked>
                  <label for="check"><span class="icon"></span> Keep me Signed in</label>
                </div>
                <div class="group">
                <br/>
                  <input type="submit" class="btn btn-primary" value="Sign In" id="login">
                </div>
                <div class="hr"></div>
               
              </div>
            </div>
          
        </div>
        </form>
    </div>


<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<script>
  $("#login").on('click',function(){
    var uname = $("#user").val();
    var pwd = $("#pass").val();

    if(!uname){
        alert("Please enter username!!")
        return false;
    }

    if(!pwd){
        alert("Please enter password!!")
        return false;
    }
    return true;
    // $.ajax({
    //     url: 'http://localhost:8090/api/admin/login',
    //     type: 'POST',
    //     dataType: 'json',
    //     data: JSON.stringify({"username": uname, "password": pwd}),
    //     contentType: 'application/json; charset=utf-8',
    //     success: function(response){
    //         if(response.status === "SUCCESS"){
    //           $.post("config.php", {"username": response.data.userName});
    //           // window.location.replace('http://localhost/futsal_ui/index.php')
    //         }else{
    //           alert("Invalid Username or Password!!!")
    //         }
    //     },
    //     error: function(result){
    //         alert("Something wrong with the system! Please contact the adminstrator!!")
    //     }
    // })

  })
</script> 

<!-- <script src="dist/js/includes/login.js"></script> -->
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../plugins/iCheck/icheck.min.js"></script>



</body>

</html>
