<%--
  Created by IntelliJ IDEA.
  User: zhanglupeng
  Date: 2018/11/14
  Time: 下午3:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script language="JavaScript">
    var times=6;
    clock();
    function clock()
    {
        window.setTimeout('clock()',1000);
        times=times-1;
        document.getElementById("time").innerHTML = times;
    }
</script>
<head>
    <title>Title</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv= "Refresh" content= "5;url=index ">

    <title>registerSuccess</title>

    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500" type="text/css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/font-awesome/css/font-awesome.css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/form-elements.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/assets/css/style.css" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js" type="text/javascript"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js" type="text/javascript"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.servletContext.contextPath}/assets/ico/apple-touch-icon-144-precomposed.png" type="image/x-icon">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.servletContext.contextPath}/assets/ico/apple-touch-icon-114-precomposed.png" type="image/x-icon">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.servletContext.contextPath}/assets/ico/apple-touch-icon-72-precomposed.png" type="image/x-icon">
    <link rel="apple-touch-icon-precomposed" href="${pageContext.servletContext.contextPath}/assets/ico/apple-touch-icon-57-precomposed.png" type="image/x-icon">

</head>
<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1>Welcome to<strong>ZCloud</strong></h1>
                    <div class="description">
                        <p>
                            欢迎欢迎~ 您已成功注册ZCloud<br/>
                            将会自动跳转至首页,  Timeout =  <div  id= "time"> 5 </div>
                        <br/><br/>
                            如果未能自动跳转，请点击此处<a href="index"><strong>跳转首页</strong></a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<!-- Javascript -->
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery.backstretch.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/assets/js/scripts.js"></script>

<!--[if lt IE 10]>
<script src="/assets/js/placeholder.js"></script>
<![endif]-->

</body>
</html>
