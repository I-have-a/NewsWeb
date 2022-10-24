<%@ page import="com.news.model.Comment" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.news.Tool.CommentUtil" %>
<%@ page import="com.news.Tool.TimeFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/23
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    Long commentId = Long.valueOf(request.getParameter("commentId"));
    Comment comment = ((Map<Long, Comment>) request.getAttribute("commentMap")).get(commentId);
    request.setAttribute("commentTemp", comment);
%>
<div class="media">
    <div class="media-left media-top" style="min-width: 42px">
        <img src="img/photos/${commentTemp.creator.photo}" class="img-circle" style="width:32px">
    </div>
    <div class="media-body">
        <p><strong>${commentTemp.creator.nickname}</strong>
            <em>${commentTemp.ipAddress}</em>
            <span class="pull-right">
                <button onclick="like(${commentTemp.id})" class="btn btn-link">
                    <span id="like-num-${commentTemp.id}">${commentTemp.likeNum > 0 ? commentTemp.likeNum : '赞'}</span> <span id="like-icon-${commentTemp.id}" class="glyphicon glyphicon-thumbs-up <c:if test="${not empty cookie['comment-like-'.concat(commentTemp.id)]}">alert-danger</c:if>"></span>
                </button>
                <button onclick="dislike(${commentTemp.id})" class="btn btn-link">
                    <span id="dislike-num-${commentTemp.id}">${commentTemp.disLikeNum > 0 ? commentTemp.disLikeNum : '踩'}</span> <span id="dislike-icon-${commentTemp.id}" class="glyphicon glyphicon-thumbs-down <c:if test="${not empty cookie['comment-dislike-'.concat(commentTemp.id)]}">alert-danger</c:if>"></span>
                </button>
            </span>
        </p>
        <p>
            ${commentTemp.content}
        </p>
        <p id="comment-${commentTemp.id}">
           <span class="text-info"><%=TimeFormat.getInterval(comment.getPubDate())%></span>
            <span class="pull-right">
                <button class="btn-link btn-reply" onclick="reply(${commentTemp.id})" id="btn-${commentTemp.id}">回复</button>
            </span>
        </p>
        <hr>
        <c:forEach items="${commentTemp.replies}" var="reply">
            <jsp:include page="comment.jsp">
                <jsp:param name="commentId" value="${reply.id}"/>
            </jsp:include>
        </c:forEach>
    </div>
</div>
<script>
    var replyQuill; //定义全局变量

    function setReplyContent() {
        if(replyQuill && replyQuill.getLength() > 1){
            $("#reply-content").val(replyQuill.root.innerHTML);
            return true;
        } else {
            alert("内容不能为空！")
            return false;
        }
    }

    function reply(commentId) {
        $(".editor-form").remove(); //清除编辑表单
        $(".btn-reply").removeAttr("disabled"); // 恢复按钮功能

        $("#btn-"+commentId).attr("disabled", "disabled");  //停止重复点击
        $("#comment-"+commentId).append("<form action='${pageContext.request.contextPath}/Comment/putComment?newsId=${param.id}&replyForId="+commentId
            +"' class='editor-form' method='post' onsubmit='return setReplyContent()'><div id='ql-editor'></div><input type='hidden' name='content' id='reply-content'><button class='btn btn-default'"
            <c:if test="${empty sessionScope.user}">+ ' disabled'</c:if> + ">回复</button>"
            <c:if test="${empty sessionScope.user}">+ '您还未登陆，请先<a href="login.jsp">登陆</a>'</c:if> + "</form>");
        replyQuill = new Quill('#ql-editor', {
            modules: {
                "toolbar": toolbarOptions,
                "emoji-toolbar": true,
                "emoji-shortname": true,
                "emoji-textarea": true
            },
            theme: 'snow'
        });
    }
</script>
