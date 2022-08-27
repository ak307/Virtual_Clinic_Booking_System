
$(document).ready(

    function searchText(){
        const date_input = document.getElementById("date");
        date_input.valueAsDate = new Date();


        const fromData = {
            date: this.valueAsDate.getDate(),
        };

        //DO POST
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : window.location + "updateDate",
            data : JSON.stringify(fromData),
            dataType : 'json',
            success : function (result) {
                alert("SUCCESS" + result);
            },
            error : function (e){
                alert("Error!")
            }
        })
    }

)