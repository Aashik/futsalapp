 
<?php include('includes/header.php'); ?>

<section class="content-header">
     <ul class="nav nav-pills">
       <li class="active"><a data-toggle="pill" href="#list"><b>List</b></a></li>
       <li><a data-toggle="pill" href="#add"><b>Add</b></a></li>
     </ul>
     
     <div class="tab-content">
       <div id="list" class="tab-pane fade in active">
         <div class="box-header">
             <h3 class="box-title"><b>Employee List</b></h3>
             <div class="box-tools">
               <div class="input-group input-group-sm" style="width: 150px;">
                 <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">
                 <div class="input-group-btn">
                   <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                 </div>
               </div>
             </div>
           <!-- /.box-header -->
           <div class="box-body table-responsive no-padding">
             <table class="table table-hover" id="listEmployee">
               <tr>
                 <th>Id</th>
                 <th>Name</th>
                 <th>Username</th>
                 <th>Email</th>
                 <th>Contact No</th>
               </tr>
             </table>
           </div>
         </div>
       </div>

       <div id="add" class="tab-pane fade">
         <section class="content">
           <div class="row">
             <div class="col-md-12">
               <!-- general form elements disabled -->
               <div class="box box-warning">
                 <div class="box-header with-border">
                   <h3 class="box-title">Add Employee Detail</h3>
                 </div>
                 <!-- /.box-header -->
                 <div class="box-body">
                     <div class='row'>
                       <div class='col-md-6'>
                         <div class="form-group">
                           <label>Username</label>
                           <input type="text" id="username" class="form-control" placeholder="Enter ...">
                         </div>
                       </div>
                       <div class='col-md-6'>
                         <div class="form-group">
                           <label>Full Name</label>
                           <input type="text" id="fullname" class="form-control" placeholder="Enter ...">
                         </div>
                       </div>
                     </div>
                     <div class='row'>
                       <div class='col-md-6'>
                         <div class="form-group">
                           <label>Email</label>
                           <input type="text" id="email" class="form-control" placeholder="Enter ...">
                         </div>
                       </div>
                       <div class='col-md-6'>
                         <div class="form-group">
                           <label>Contact Number</label>
                           <input type="text" id="contactnumber" class="form-control" placeholder="Enter ...">
                         </div>
                       </div>
                     </div>
                     <div class='row'>
                       <div class='col-md-6'>
                         <div class="form-group">
                           <label>Password</label>
                           <input type="password" id="password" class="form-control" placeholder="Enter ...">
                           <input type="hidden" id="futsal_id" value="<?php echo $_SESSION["futsal_id"]; ?>" >
                         </div>
                       </div>
                       <div class='col-md-6'>
                        <div class="form-group">
                            <label>Retype Password</label>
                            <input type="password" id="repassword" class="form-control" placeholder="Enter ...">
                        </div>
                       </div>
                     </div>
                     <button class="btn btn-primary" id="submit">Submit</button>
                   </form>
                 </div>
                 <!-- /.box-body -->
               </div>
               <!-- /.box -->
             </div>
           </div>
         </section>
       </div>
     </div>

     <ol class="breadcrumb">
       <li><a href="index.php"><i class="fa fa-dashboard"></i> Home</a></li>
       <li><a href="ground.php">Setup</a></li>
       <li class="active">Ground</li>
     </ol>
   </section>

<script>
  var count = 1;
  $(document).ready(function(){
    debugger;
    var futsal_id = $("#futsal_id").val() 
    $.ajax({
      url: 'http://localhost:8090/api/admin/getAllEmployee?futsal_id='+futsal_id,
      type:'GET',
      success: function(response){
        debugger;
        response.data.forEach(listData)
      },
      error: function(response){
        debugger
        alert("error")
      }
    })    
  })

  function listData(item)
  {
    var table = "<tr>" + 
                  "<td>"+ count +"</td>" +
                  "<td>"+ item.fullName +"</td>" +
                  "<td>"+ item.userName +"</td>" +
                  "<td>"+ item.email +"</td>" +
                  "<td>"+ item.contactNo +"</td>" +
                "</tr>";
              
    $("#listEmployee").append(table)
    count++ 
  }

  $("#submit").on('click',function(){
   
    var futsal_id = $("#futsal_id").val();

    if(!$("#username").val()){
       alert("Username Empty")
       return false;
    }

    if(!$("#fullname").val()){
       alert("Full Name Empty")
       return false;
    }
    if(!$("#email").val()){
       alert("Email Empty")
       return false;
    }

    if(!$("#contactnumber").val()){
       alert("Contact Number Empty")
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
  
   $.ajax({
         url: 'http://localhost:8090/api/admin/addEmployee',
         type: 'POST',
         dataType: 'json',
         data: JSON.stringify({ 
            "userName": $("#username").val(),
            "fullName":$("#fullname").val(),
            "email":$("#email").val(),
            "contactNo":$("#contactnumber").val(),
            "password":$("#password").val(),
            "futsal_id":$("#futsal_id").val()
         }),
         contentType: 'application/json; charset=utf-8',
         success: function(response){
           
             if(response.status === "SUCCESS"){
               alert("Employee Successfully added")
               window.location.replace('http://localhost/futsal_ui/employee.php')
             }else{
               alert(response.messsage)
             }
         },
         error: function(result){
             alert("Something wrong with the system! Please contact the adminstrator!!")
         }
     })
  })
</script>

<?php include('includes/footer.php'); ?>