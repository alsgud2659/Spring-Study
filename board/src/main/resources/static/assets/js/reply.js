/*
*   reply module
* */

console.log("Reply Module......");
let replyService = (function(){

    function add(reply, callback, error){
        console.log("add reply..........");
        $.ajax({
            url: "/reply/new",
            type: "post",
            data: JSON.stringify(reply),
            contentType: "application/json",
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        });
    }

    return {add: add};
})();