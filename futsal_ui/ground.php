 
<?php include('includes/header.php'); ?>

 <section class="content-header">
      <ul class="nav nav-pills">
        <li class="active"><a data-toggle="pill" href="#list"><b>List</b></a></li>
        <li><a data-toggle="pill" href="#add"><b>Add</b></a></li>
      </ul>
      
      <div class="tab-content">
        <div id="list" class="tab-pane fade in active">
          <div class="box-header">
              <h3 class="box-title"><b>Ground List</b></h3>
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
              <table class="table table-hover"  id="listGround">
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Opening Hour</th>
                  <th>Closing Hour</th>
                  <th>Price (per hr)</th>
                  <th>Status</th>
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
                    <h3 class="box-title">Add Ground Details</h3>
                  </div>
                  <!-- /.box-header -->
                  <div class="box-body">
                      <div class='row'>
                        <div class='col-md-6'>
                          <div class="form-group">
                            <label>Ground Name</label>
                            <input type="text" id="groundname" class="form-control" placeholder="Enter ...">
                          </div>
                        </div>
                        <div class='col-md-6'>
                          <div class="form-group">
                            <label>Opening Hour</label>
                            <input type="text" id="openinghour" class="form-control" placeholder="Enter ...">
                          </div>
                        </div>
                      </div>
                      <div class='row'>
                        <div class='col-md-6'>
                          <div class="form-group">
                            <label>Closing Hour</label>
                            <input type="text" id="closinghour" class="form-control" placeholder="Enter ...">
                          </div>
                        </div>
                        <div class='col-md-6'>
                          <div class="form-group">
                            <label>Price Per Hour</label>
                            <input type="text" id="price" class="form-control" placeholder="Enter ...">
                          </div>
                        </div>
                      </div>
                      <div class='row'>
                        <div class='col-md-6'>
                          <div class="form-group">
                            <label>Image</label>
                            <input type="text" id="image" class="form-control" placeholder="Enter ...">
                            <input type="hidden" id="futsal_id" value="<?php echo $_SESSION["futsal_id"]; ?>" >
                          </div>
                        </div>
                        <div class='col-md-6'>
                          <div class="form-group">
                            <label>Status</label>
                            <select class="form-control" id="status">
                              <option value="0">---Select Status---</option>
                              <option value="Active">Active</option>
                              <option value="Inactive">Inactive</option>
                            </select>
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
    var futsal_id = $("#futsal_id").val() 
    $.ajax({
      url: 'http://localhost:8090/api/getGroundList?futsal_id='+futsal_id,
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
                  "<td>"+ item.ground_name +"</td>" +
                  "<td>"+ item.opening_hour +"</td>" +
                  "<td>"+ item.closing_hour +"</td>" +
                  "<td>"+ item.unit_hour_price +"</td>" +
                  "<td>"+ item.status +"</td>" +
                "</tr>";
              
    $("#listGround").append(table)
    count++ 
  }

   $("#submit").on('click',function(){
    debugger;
    var futsal_id = $("#futsal_id").val();

    if(!$("#groundname").val()){
        alert("Ground Name Empty")
        return false;
    }

    if(!$("#openinghour").val()){
        alert("Opening Hour Empty")
        return false;
    }
    if(!$("#closinghour").val()){
        alert("Closing Hour Empty")
        return false;
    }

    if(!$("#price").val()){
        alert("Price Per Hour Empty")
        return false;
    }

    if(!$("#image").val()){
        alert("Image Empty")
        return false;
    }

    if($("#status").val() === "0"){
        alert("Please select status")
        return false;
    }
   
    $.ajax({
          url: 'http://localhost:8090/api/addGround?futsal_id='+futsal_id,
          type: 'POST',
          dataType: 'json',
          data: JSON.stringify({
              "ground_name":$("#groundname").val(),
              "opening_hour":$("#openinghour").val(),
              "closing_hour":$("#closinghour").val(),
              "unit_hour_price":$("#price").val(),
              "image":$("#image").val(),
              "status":$("#status").val()
          }),
          contentType: 'application/json; charset=utf-8',
          success: function(response){
            debugger;
              if(response.status === "SUCCESS"){
                alert("Ground Successfully added")
                window.location.replace('http://localhost/futsal_ui/ground.php')
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
