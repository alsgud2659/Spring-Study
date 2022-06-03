/*
*   reply module
* */

console.log("Reply Module......");
let userService = (function(){
    function add(user, callback, error) {
        console.log("add user....");
        $.ajax({
            url: "/user/join",
            type: "post",
            data: JSON.stringify(user),
            contentType: "application/json",
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(xhr, status, er);
                }
            }
        })
    }

    function read(uno, callback, error) {
        $.ajax({
            url: "/user/get/" + uno,
            type: "get",
            dataType: "json",
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(xhr, status, er);
                }
            }
        })
    }

    function modify(user, callback, error) {
        $.ajax({
            url: "/user/modify/" + user.uno,
            type: "post",
            data: JSON.stringify(user),
            contentType: "application/json",
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(xhr, status, er);
                }
            }
        })
    }

    function remove(uno, callback, error) {
        $.ajax({
            url: "/user/remove/" + uno,
            type: "get",
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(xhr, status, er);
                }
            }
        })
    }


    return {
        add:add,
        read:read,
        modify:modify,
        remove:remove,
    };
})();