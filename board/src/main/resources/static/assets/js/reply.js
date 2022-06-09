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
            url: "/reply/" + reply.replyNumber,
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
        let page = param.page || 1;
        $.getJSON("/reply/list/" + param.bno + "/" + page, function (replyPageDTO) {
            if(callback){
                callback(replyPageDTO.total, replyPageDTO.list);
            }
        }).fail(function (xhr, status, er) {
            if(error){
                error(er);
            }
        });
        // $.ajax({
        //     url: "/reply/list/" + param.bno + "/" + page,
        //     type: "get",
        //     dataType: "json",
        //     success: function (result) {
        //         if(callback){
        //             callback(result);
        //         }
        //     },
        //     error: function (xhr, status, er) {
        //         if(error){
        //             error(xhr, status, er);
        //         }
        //     }
        // });
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

    // 댓글 작성 시간 (JavaScript)
    function getReplyDateByJavaScript(replyDate) {
        let today  = new Date();
        let rDate = new Date(replyDate);
        console.log(today);
        console.log(rDate);
        let gap = today.getTime() - rDate.getTime();
        console.log(gap);

        if(gap < 1000 * 60 * 60 * 24) {
            let h = rDate.getHours();
            let m = rDate.getMinutes();
            let s = rDate.getSeconds();

            return [(h < 10 ? '0' : '') + h, (m < 10 ? '0' : '') + m, (s < 10 ? '0' : '') + s].join(":")
        }else {
            let y = rDate.getFullYear();
            let m = rDate.getMonth() + 1;
            let d = rDate.getDate();

            return [y, (m < 10 ? '0':'') + m, (d < 10 ? '0':'') + d].join("-");
        }
    }

    // 댓글 수정 시간(JAVA)
    function getReplyDateByController(replyDate){
        let result;
        $.ajax({
            url: "/time",
            type: "get",
            data: {replyDate: replyDate},
            async: false, //아래의 콜백함수의 연산이 모두 끝나고 나서 다음 작업이 진행된다.
            success: function(time){
                result = time;
            }
        });
        return result;
    }

    return {
        add: add,
        get: get,
        remove:remove,
        modify:modify,
        getList: getList,
        getReplyDateByJavaScript: getReplyDateByJavaScript,
        getReplyDateByController: getReplyDateByController,
        test1: test1,
        test2: test2,
        test3: test3,
        test4: test4,
        test5: test5,
    };
})();