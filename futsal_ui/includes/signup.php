<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Futsal | Registration</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="../bower_components/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="../bower_components/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="../bower_components/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="../dist/css/AdminLTE.css">
  <link rel="stylesheet" href="../plugins/iCheck/square/blue.css">

  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition register-page">

<div class="register-box">
  <div class="register-logo">
    <a><b>Admin</b>Panel</a>
  </div>
  <div class="register-box-body">
    <p class="login-box-msg"><b>Register a new membership</b></p>
      <div class='row'>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="text" class="form-control" id="futsalname" placeholder="Futsal Name">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
        </div>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="text" class="form-control" id="ownername" placeholder="Owner Name">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
        </div>
      </div>

      <div class='row'>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="email" class="form-control" id="email" placeholder="Email">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
        </div>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="text" class="form-control" id="username" placeholder="Username">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
        </div>
      </div>

      <div class='row'>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" id="password" placeholder="Password">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
        </div>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" id="repassword" placeholder="Retype password">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
          </div>
        </div>
      </div>

      <div class='row'>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="text" class="form-control" id="contactnumber" placeholder="Contact Number">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
          </div>
        </div>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="text" class="form-control" id="image" placeholder="Image">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
          </div>
        </div>
      </div>

      <div class='row'>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="Street" id="street">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
          </div>
        </div>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="text" class="form-control" id="city" placeholder="City">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
          </div>
        </div>
      </div>

      <div class='row'>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="text" class="form-control" id="district" placeholder="District">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
          </div>
        </div>
        <div class='col-md-6'>
          <div class="form-group has-feedback">
            <input type="text" class="form-control" id="province" placeholder="Province">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
          </div>
        </div>
      </div>
      <br/>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
             &nbsp;&nbsp; <input type="checkbox"> I agree to the <a href="#">terms</a>
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="submit" id="submit" class="btn btn-primary btn-block btn-flat">Register</button>
        </div>
        <!-- /.col -->
      </div>
  </div>
  <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 3 -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });

  $("#submit").on('click',function(){
    debugger;
    if(!$("#futsalname").val()){
        alert("Futsal Name Empty")
        return false;
    }

    if(!$("#ownername").val()){
        alert("Owner Name Empty")
        return false;
    }
    if(!$("#email").val()){
        alert("Email Empty")
        return false;
    }

    if(!$("#username").val()){
        alert("Username Empty")
        return false;
    }
    if(!$("#password").val()){
        alert("Password Empty")
        return false;
    }

    if(!$("#repassword").val()){
        alert("Retype Password Empty")
        return false;
    } 
    
    if(!$("#contactnumber").val()){
        alert("Contact Number Empty")
        return false;
    }
    if(!$("#image").val()){
        alert("Image Empty")
        return false;
    }

    if(!$("#street").val()){
        alert("Street Empty")
        return false;
    }
    if(!$("#city").val()){
        alert("City Empty")
        return false;
    } 
    
    if(!$("#district").val()){
        alert("District Empty")
        return false;
    }
    if(!$("#province").val()){
        alert("Province Empty")
        return false;
    }
    var data = JSON.stringify({
            "userName":$("#username").val(),
            "fullName":$("#ownername").val(),
            "email":$("#email").val(),
            "contactNo":$("#contactnumber").val(),
            "password":$("#password").val(),
            "futsalName":$("#futsalname").val(),
            "address":{
                        "street":$("#street").val(),
                        "city":$("#city").val(),
                        "state_district":$("#district").val(),
                        "country":"Nepal",
                        "zip_postal_code":12345
                      }

        });
    $.ajax({
        url: 'http://localhost:8090/api/admin/signup',
        type: 'POST',
        dataType: 'json',
        data: data,
        contentType: 'application/json; charset=utf-8',
        success: function(response){
          debugger;
            if(response.status === "SUCCESS"){
              window.location.replace('http://localhost/futsal_ui/includes/login.php')
            }else{
              alert(response.messsage)
            }
        },
        error: function(result){
           console.log(data)
           alert("Something wrong with the system! Please contact the adminstrator!!")
        }
    })

  })
</script>
</body>
</html>
