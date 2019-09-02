function comment() {
    var parent_id = $("#main-question-id").val();
    var comment_id =$("#comment-id").val();
    var content = $("#description").val();
    $.ajax(
        {
            type : 'POST' ,
            contentType : 'application/json;charset=UTF-8' ,
            url : '/comment' ,
            data : JSON.stringify({
                parent_id : parent_id ,
                comment_id : comment_id ,
                content : content ,
                type : '1',
                question_id : parent_id
            }) ,
            success : function (data) {
                if (data.code==100){
                    alert(data.message);
                } else if(data.code==101){
                    alert(data.message);
                }else if (data.code==200){
                    alert(data.message);
                    location.reload();
                }
            }
        }
    )
}

function collapseComments(data) {
    var id = data.getAttribute("data-id");
   var erji= $("#comment-"+id);
   if (erji.hasClass("in")){
       erji.removeClass("in");
       data.classList.remove("active");
   }else {
       erji.addClass("in");
       data.classList.add("active");
       $.ajax(
           {
               type : 'POST' ,
               contentType : 'application/json;charset=UTF-8' ,
               url : '/getTowComments' ,
               data : JSON.stringify({
                   parent_id : id ,
                   type : '2'
               }) ,
               success : function (comments) {
                   var splice ='';
                    for (i =0; i!=comments.length;++i){
                        splice+='<div class="media-left">\n' +
                            '    <img class="media-object img-rounded" width="35px" height="35px" src="'+comments[i].user.avatar_url+'">\n' +
                            '    </div>\n' +
                            '    <div class="media-body">\n' +
                            '    <h5 class="media-heading">'+comments[i].user.name+'</h5>\n' +
                            '    <div>'+comments[i].content+'</div>\n' +
                            '<div class="menu">\n' +
                            '    <span class="pull-right">'+new Date(comments[i].gmt_create)+'</span>\n' +
                            '</div>\n' +
                            '</div>';
                        splice+='<hr>';
                    }
                   $("#twoComments-"+id).html(splice);
               }
           }
       )
   }
}
function towComment(e) {
    var parent_id = e.getAttribute("data-id");
    var content = $("#input-"+parent_id).val();
    var comment_id =$("#comment-id").val();
    $("#comment-"+parent_id).removeClass("in");
    $.ajax(
        {
            type : 'POST' ,
            contentType : 'application/json;charset=UTF-8' ,
            url : '/comment' ,
            data : JSON.stringify({
                parent_id : parent_id ,
                comment_id : comment_id ,
                content : content ,
                type : '2'
            }) ,
            success : function (data) {
                if (data.code==100){
                    alert(data.message);
                } else if(data.code==101){
                    alert(data.message);
                }else if (data.code==200){
                    alert(data.message);
                }
            }
        }
    )
}

function selectTags(data) {
    var value =data.innerHTML;
    var previous = $("#tag").val();
    if (previous.indexOf(value)!=-1) {
    }else{
        if (previous){
            $("#tag").val(previous+','+value);
        } else {
            $("#tag").val(value );
        }
    }
}

function openSelectTags() {
    var tags =$("#tags");
    if (tags.hasClass('in')){
        tags.removeClass('in');
    } else{
        tags.addClass('in');
        $.ajax(
            {
                type : 'POST' ,
                contentType : 'application/json;charset=UTF-8' ,
                url : '/findTags' ,
                success : function (data) {
                    var tagsStr ='';
                    for (i = 0;i!=data.length;++i){
                        tagsStr+='<span class="label label-info" onclick="selectTags(this)">'+data[i].tag_name+'</span>'
                    }
                    $("#tags").html(tagsStr);
                }
            }
        )

    }
}