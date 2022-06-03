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
                    error(xhr, status, er);
                }
            }
        });
    }

    // 댓글 한개 정보 가져오기기
    function get(rno, callback, error) {
        console.log("get reply.....");
        $.ajax({
            url: "/reply/" + rno,
            type: "get",
            dataType: "json",
            success : function (result) {
                if (callback){
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if(error){
                    error(xhr, status, er);
                }
            }
        });
    }

    // 댓글 삭제
    function remove(rno, callback, error) {
        console.log("remove reply.......");
        $.ajax({
            url: "/reply/remove/" + rno,
            type: "delete",
            success: function (result) {
                if(callback){
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if(error){
                    error(xhr, status, er);
                }
            }
        });
    }

    //댓글 수정
    function modify(reply, callback, error) {
        console.log("modify reply.......");
        $.ajax({
            url: "/reply/" + reply.rno,
            type: "patch",
            data: JSON.stringify(reply),
            contentType: "application/json",
            success: function (result) {
                if(callback){
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if(error){
                    error(xhr, status, er);
                }
            }
        })

    }

    // 댓글 목록
    function getList(param, callback, error) {
        console.log("modify reply.......");
        $.ajax({
            url: "/reply/list/" + param.bno + "/" + param.page,
            type: "get",
            dataType: "json",
            success: function (result) {
                if(callback){
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if(error){
                    error(xhr, status, er);
                }
            }
        })
    }

    // 1번 테스트
    function test1(callback, error) {
        $.ajax({
            url: "/reply/test1",
            type: "get",
            success: function (result) {
                if(callback){
                    callback(result);
                }
            }
        });
    }

    // 2번 테스트
    function test2(str, callback, error) {
        $.ajax({
            url: "/reply/test2/" + str,
            type: "get",
            success: function (result) {
                if(callback){
                    callback(result);
                }
            }
        });
    }

    // 3번 테스트
    function test3(callback, error) {
        $.ajax({
            url: "/reply/test3",
            type: "post",
            dataType: "json",
            success: function (result) {
                if(callback){
                    callback(result);
                }
            }
        });
    }

    function test4(param, callback, error) {
        $.ajax({
            url: "/reply/test4/" + param.name + "/" + param.age + "/" + param.number,
            type: "post",
            dataType: "json",
            success: function (result) {
                if(callback){
                    callback(result);
                }
            }
        });
    }

    function test5(param, callback, error) {
        $.ajax({
            url: "/reply/test5/" + param.name + "/" + param.age + "/" + param.number,
            type: "post",
            dataType: "json",
            success: function (result) {
                if(callback){
                    callback(result);
                }
            }
        })
    }

    return {
        add: add,
        get: get,
        remove:remove,
        modify:modify,
        getList: getList,
        test1: test1,
        test2: test2,
        test3: test3,
        test4: test4,
        test5: test5,
    };
})();