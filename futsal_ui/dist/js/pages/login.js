
$("#login").on('click',function(){
        var uname = $("#user").val();
        var pwd = $("#pass").val();

        if(!uname){
            alert("Please enter username!!")
            return
        }

        if(!pwd){
            alert("Please enter password!!")
            return
        }
        debugger;
        $.ajax({
            url: 'http://localhost:8090/api/admin/login',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({	"username": uname, "password": pwd}),
            success: function(response){
                debugger;
                window.location.replace('')
            },
            error: function(jqXHR, exception){
                debugger;
                alert("Something wrong with the system! Please contact the adminstrator!!")
            }
        })
})
