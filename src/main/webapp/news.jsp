<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.news.Impl.NewsDaoImpl" %>
<%@ page import="com.news.model.News" %>
<%@ page import="java.util.List" %>
<%@ page import="com.news.dao.CommentDao" %>
<%@ page import="com.news.Impl.CommentDaoImpl" %>
<%@ page import="com.news.model.Comment" %>
<%@ page import="com.news.Tool.CommentUtil" %>
<%@ page import="com.news.dao.TagDao" %>
<%@ page import="com.news.Impl.TagDaoImpl" %>
<%@ page import="com.news.model.Tag" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: 23686
  Date: 2022/9/16
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新闻详情</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/quill-emoji.css">
    <link rel="stylesheet" href="css/quill.snow.css">
</head>
<body>
<jsp:include page="common/header.jsp" flush="true"></jsp:include>
<div class="container">
    <div class="col-xs-8">
        <%
            NewsDaoImpl newsDao = new NewsDaoImpl();
            int id = Integer.parseInt(request.getParameter("id"));
            News news = newsDao.getSingleWhere("id",id).get(0);
            session.setAttribute("news",news);

            CommentDao commentDao = new CommentDaoImpl();
            List<Comment> comments = commentDao.getCommentsByNewsId(id);
            Map<Long, Comment> longCommentMap = CommentUtil.toMap(comments);
            request.setAttribute("comments",comments);
            request.setAttribute("commentMap", longCommentMap);

            TagDao tagDao = new TagDaoImpl();
            List<Tag> tags = tagDao.getByNewsIdTag(id);
            request.setAttribute("tags",tags);
        %>
        <h1 class="news-title">${news.title}</h1>
        <div class="news-status">25k阅读•<%= newsDao.pundateToString(news)%>
            <c:forEach items="${tags}" var="tag">
                <div class="label label-default">${tag.name}</div>
            </c:forEach>
        </div>
        <div class="news-content">${news.content}</div>
        <div>
            <h3>我要评论</h3>
            <form class="form-horizontal" action="${pageContext.request.contextPath}/Comment/putComment?newsId=${news.id}" method="post"
                  onsubmit="return setContent()">
                <div id="editor"></div>
                <input type="hidden" name="content" id="content">
                <button type="submit" class="btn btn-default" <c:if test="${empty sessionScope.user}">disabled</c:if>>发布</button>
                <c:if test="${empty sessionScope.user}">您还未登陆，请先<a href="login.jsp">登陆</a></c:if>
            </form>
        </div>
        <c:forEach items="${comments}" var="reply">
            <jsp:include page="common/comment.jsp">
                <jsp:param name="commentId" value="${reply.id}"/>
            </jsp:include>
        </c:forEach>
    </div>
    <div class="col-xs-4">
        <div class="side-bar-card">
            <div class="card-title">相关推荐</div>
            <div class="card-body">
                <div class="list">
                    <%
                        List<News> newsList = newsDao.RecommendNews();
                        for (News news1 : newsList) {%>
                    <div class="item clearfix">
                        <a href="news.jsp?id=<%= news1.getId()%>">
                            <div class="col-xs-5 no-padding-h"><img src="img/<%= news1.getImg()%>" alt=""></div>
                            <div class="col-xs-7">
                                <div class="title"><%= news1.getTitle()%></div>
                                <div class="desc">25k阅读•<%= newsDao.pundateToString(news1)%></div>
                            </div>
                        </a>
                    </div>
                    <% }%>
                </div>
            </div>
        </div>
        <jsp:include page="common/24h.jsp" flush="true"></jsp:include>
    </div>
</div>
<jsp:include page="common/tail.jsp" flush="true"></jsp:include>
<script>
    var toolbarOptions = {
        container: [
            ['bold', 'italic', 'underline', 'strike'],
            ['blockquote', 'code-block'],
            ['emoji']
        ],
        handlers: {
            'emoji': function () {}
        }
    };

    var quill = new Quill('#editor', {
        modules: {
            "toolbar": toolbarOptions,
            "emoji-toolbar": true,
            "emoji-shortname": true,
            "emoji-textarea": true
        },
        theme: 'snow'
    });

    function setContent() {
        if(quill && quill.getLength() > 1){
            $("#content").val(quill.root.innerHTML);
            return true;
        } else{
            alert("内容不能为空！")
            return false;
        }
    }

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

    function like(commentId) {
        let like = $.cookie('comment-like-' + commentId);  // 从Cookie中获取点赞记录
        let operate = like ? 'unlike' : 'like';

        $.post('${pageContext.request.contextPath}/Comment/Like?id='+commentId + '&operate=' + operate, function (num) {
            // 更新点赞数和图标状态
            console.log(num)
            console.log(typeof num)
            $('#like-num-'+commentId).text(num > 0 ? num : '赞');
            if(like){
                $('#like-icon-'+commentId).removeClass('alert-danger');
                $.removeCookie('comment-like-'+commentId, { path: "/"});  // 删除Cookie
            } else {
                $('#like-icon-'+commentId).addClass('alert-danger');
                $.cookie('comment-like-'+commentId, true, { path: "/"}); // 写入Cookie
            }
        });
    }

    function dislike(commentId) {
        let dislike = $.cookie('comment-dislike-' + commentId);  // 从Cookie中获取点踩记录
        let operate = dislike ? 'undislike' : 'dislike';

        $.post('${pageContext.request.contextPath}/Comment/Like?id='+commentId + '&operate=' + operate, function (num) {
            // 更新点赞数和图标状态
            $('#dislike-num-'+commentId).text(num > 0 ? num : '踩');
            if(dislike){
                $('#dislike-icon-'+commentId).removeClass('alert-danger');
                $.removeCookie('comment-dislike-'+commentId, { path: "/"});  // 删除Cookie
            } else {
                $('#dislike-icon-'+commentId).addClass('alert-danger');
                $.cookie('comment-dislike-'+commentId, true, { path: "/"}); // 写入Cookie
            }
        });
    }
</script>
</body>
</html>
