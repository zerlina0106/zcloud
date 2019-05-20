<%--
  Created by IntelliJ IDEA.
  User: zhanglupeng
  Date: 2018/11/13
  Time: 下午3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script language="JavaScript">

</script>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/animate.min.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/font-awesome.min.css" type="text/css"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/templatemo-style.css" type="text/css">
    <script src="${pageContext.servletContext.contextPath}/js/jquery.js" type="text/javascript"></script>
    <script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${pageContext.servletContext.contextPath}/js/jquery.singlePageNav.min.js" type="text/javascript"></script>
    <script src="${pageContext.servletContext.contextPath}/js/typed.js" type="text/javascript"></script>
    <script src="${pageContext.servletContext.contextPath}/js/wow.min.js" type="text/javascript"></script>
    <script src="${pageContext.servletContext.contextPath}/js/custom.js" type="text/javascript"></script>

</head>

<body id="top">

<!-- start preloader -->
<div class="preloader">
    <div class="sk-spinner sk-spinner-wave">
        <div class="sk-rect1"></div>
        <div class="sk-rect2"></div>
        <div class="sk-rect3"></div>
        <div class="sk-rect4"></div>
        <div class="sk-rect5"></div>
    </div>
</div>
<!-- end preloader -->

<!-- start header -->
<header>
    <div class="container">
        <div class="row">
            <div class="col-md-3 col-sm-4 col-xs-12">
                <c:if test="${user==null}">
                    <a href="${pageContext.request.contextPath}/login">Login</a>
                </c:if>
                <c:if test="${user!=null}">
                    <b>WELCOME! ${user.username}&nbsp;&nbsp;&nbsp;</b>
                    <a href="${pageContext.request.contextPath}/logout"> <i>Logout</i></a>
                </c:if>
            </div>
            <div class="col-md-3 col-sm-4 col-xs-12">
                <p><i class="fa fa-envelope-o"></i><span> Email</span><a href="#">awesome@company.com</a></p>
            </div>
            <div class="col-md-5 col-sm-4 col-xs-12">
                <ul class="social-icon">
                    <li><span>Meet us on</span></li>
                    <li><a href="#" class="fa fa-facebook"></a></li>
                    <li><a href="#" class="fa fa-twitter"></a></li>
                    <li><a href="#" class="fa fa-instagram"></a></li>
                    <li><a href="#" class="fa fa-apple"></a></li>
                </ul>
            </div>
        </div>
    </div>
</header>
<!-- end header -->

<!-- start navigation -->
<nav class="navbar navbar-default templatemo-nav" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon icon-bar"></span>
                <span class="icon icon-bar"></span>
                <span class="icon icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand">Zerlina_Cloud</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#top">HOME</a></li>
                <li><a href="#about">ABOUT</a></li>
                <li><a href="#team">TEAM</a></li>
                <li><a href="#service">SERVICE</a></li>
                <li><a href="#homepage">HOMEPAGE</a></li>
                <li><a href="#contact">CONTACT</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- end navigation -->

<!-- start home -->
<section id="home">
    <div class="container">
        <div class="row">
            <div class="col-md-offset-2 col-md-8">
                <h1 class="wow fadeIn" data-wow-offset="50" data-wow-delay="0.9s">We make templates that are <span>awesome</span></h1>
                <div class="element">
                    <div class="sub-element">Hello, this is Typed.js</div>
                    <div class="sub-element">Awesome Template is provided by templatemo.com website for everyone</div>
                    <div class="sub-element">Download, edit and apply this awesome template for your websites</div>
                </div>
                <a data-scroll href="#about" class="btn btn-default wow fadeInUp" data-wow-offset="50" data-wow-delay="0.6s">GET STARTED</a>
            </div>
        </div>
    </div>
</section>
<!-- end home -->

<!-- start about -->
<section id="about">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2 class="wow bounceIn" data-wow-offset="50" data-wow-delay="0.3s">WE ARE <span>AWESOME</span></h2>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12 wow fadeInLeft" data-wow-offset="50" data-wow-delay="0.6s">
                <div class="media">
                    <div class="media-heading-wrapper">
                        <div class="media-object pull-left">
                            <i class="fa fa-mobile"></i>
                        </div>
                        <h3 class="media-heading">FULLY RESPONSIVE</h3>
                    </div>
                    <div class="media-body">
                        <p>Awesome responsive template is provided by <a rel="nofollow" href="http://www.templatemo.com" target="_parent">templatemo</a> website. This is Bootstrap v3.3.2 layout built on HTML5 CSS3. You can use this for any purpose. Please tell your friends about it.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12 wow fadeInUp" data-wow-offset="50" data-wow-delay="0.9s">
                <div class="media">
                    <div class="media-heading-wrapper">
                        <div class="media-object pull-left">
                            <i class="fa fa-comment-o"></i>
                        </div>
                        <h3 class="media-heading">FREE SUPPORT</h3>
                    </div>
                    <div class="media-body">
                        <p>Credits go to <a rel="nofollow" href="http://pixabay.com">Pixabay</a> for homepage image and <a rel="nofollow" href="http://unsplash.com">Unsplash</a> for portfolio images. Lorem ipsum dolor sit amet, consectetur adipiscing elitquisque tempus ac eget diam et laoreet phasellus ut nisi id leo molestie.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-12 wow fadeInRight" data-wow-offset="50" data-wow-delay="0.6s">
                <div class="media">
                    <div class="media-heading-wrapper">
                        <div class="media-object pull-left">
                            <i class="fa fa-html5"></i>
                        </div>
                        <h3 class="media-heading">HTML5 &AMP; CSS3</h3>
                    </div>
                    <div class="media-body">
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elitquisque tempus ac eget diam et laoreet phasellus ut nisi id leo molestie. Adipiscing vitae vel quam proin eget mauris eget. Lorem ipsum dolor sit amet.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- end about -->

<!-- start team -->
<section id="team">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2 class="wow bounceIn" data-wow-offset="50" data-wow-delay="0.3s"><span>AWESOME</span> TEAM</h2>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12 wow fadeIn" data-wow-offset="50" data-wow-delay="1.3s">
                <div class="team-wrapper">
                    <img src="${pageContext.servletContext.contextPath}/images/team-img1.jpg" class="img-responsive" alt="team img 1">
                    <div class="team-des">
                        <h4>TRACY</h4>
                        <span>Designer</span>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elitquisque tempus ac eget diam et laoreet phasellus ut nisi id leo molest.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12 wow fadeIn" data-wow-offset="50" data-wow-delay="1.6s">
                <div class="team-wrapper">
                    <img src="${pageContext.servletContext.contextPath}/images/team-img2.jpg" class="img-responsive" alt="team img 2">
                    <div class="team-des">
                        <h4>MARY</h4>
                        <span>Developer</span>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elitquisque tempus ac eget diam et laoreet phasellus ut nisi id leo molest.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12 wow fadeIn" data-wow-offset="50" data-wow-delay="1.3s">
                <div class="team-wrapper">
                    <img src="${pageContext.servletContext.contextPath}/images/team-img3.jpg" class="img-responsive" alt="team img 3">
                    <div class="team-des">
                        <h4>JULIA</h4>
                        <span>Director</span>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elitquisque tempus ac eget diam et laoreet phasellus ut nisi id leo molest.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 col-xs-12 wow fadeIn" data-wow-offset="50" data-wow-delay="1.6s">
                <div class="team-wrapper">
                    <img src="${pageContext.servletContext.contextPath}/images/team-img4.jpg" class="img-responsive" alt="team img 4">
                    <div class="team-des">
                        <h4>LINDA</h4>
                        <span>Manager</span>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elitquisque tempus ac eget diam et laoreet phasellus ut nisi id leo molest.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- end team -->

<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >企业网站模板</a></div>

<!-- start service -->
<section id="service">


    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2 class="wow bounceIn" data-wow-offset="50" data-wow-delay="0.3s">OUR <span>AWESOME</span> THINGS</h2>
            </div>
            <div class="col-md-4 wow fadeIn" data-wow-offset="50" data-wow-delay="0.6s">
                <i class="fa fa-laptop"></i>
                <h4>Upload File</h4>
                <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
                    Select File：<input type="file" name="file" multiple="multiple"/><br/>
                    <input type="submit" value="UPLOAD"/>
                </form>
            </div>
            <div class="col-md-4 wow fadeIn" data-wow-offset="50" data-wow-delay="0.6s">
                <i class="fa fa-cloud"></i>
                <%--<h4>File Download</h4>--%>
                <%--<ul>--%>
                    <%--<c:forEach items="${filesEntity }" var="fileEntity">--%>
                            <%--<li>--%>
                                <%--<a href="${pageContext.servletContext.contextPath}/download?filename=${fileEntity.filename}">${fileEntity.filename}</a>--%>
                            <%--</li>--%>

                    <%--</c:forEach>--%>
                <%--</ul>--%>
            </div>
            <div class="col-md-4 wow fadeIn" data-wow-offset="50" data-wow-delay="0.6s">
                <i class="fa fa-cog"></i>
                <h4>UX Design</h4>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elitquisque tempus ac eget diam et laoreet phasellus ut nisi id leo molestie. Adipiscing vitae vel quam proin eget mauris eget. Lorem ipsum dolor sit amet, consectetur adipiscing elitquisque tempus ac eget diam et laoreet phasellus ut nisi id leo molestie.</p>
            </div>
        </div>
    </div>
</section>
<!-- end servie -->

<!-- start homepage -->
<section id="homepage">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2 class="wow bounceIn" data-wow-offset="50" data-wow-delay="0.3s"><span>PERSONAL</span> HOMEPAGE</h2>
            </div>
            <div class="col-md-3 col-sm-12 col-xs-12 wow fadeIn" data-wow-offset="50" data-wow-delay="0.6s">
                <form role="form" action="${pageContext.servletContext.contextPath}/modifyUser" method="post" class="user-form">
                    <div class="portfolio-overlay">
                        <label class="sr-only" for="username">Username</label>
                        <input type="text" name="username" placeholder="${user.username}" class="form-username form-control" id="username">
                    </div>
                    <div class="portfolio-overlay">
                        <h4>Email: ${user.email}</h4>
                    </div>
                    <button type="submit" class="btn-default" >Restore!</button>
                </form>
            </div>
            <div class="col-md-6 col-sm-12 col-xs-12 wow fadeIn" data-wow-offset="50" data-wow-delay="0.6s">
                <div class="portfolio-thumb">
                    <h3>File Repository</h3>
                    <ul>
                        <c:forEach items="${filesEntity }" var="fileEntity">
                            <li>
                                <input type="checkbox" id="fileEntity">
                                <a href="${pageContext.servletContext.contextPath}/download?filename=${fileEntity.filename}">${fileEntity.filename}</a>
                            </li>

                        </c:forEach>
                    </ul>
                </div>
            </div>

        </div>
    </div>
</section>
<!-- end homepage -->

<!-- start contact -->
<section id="contact">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2 class="wow bounceIn" data-wow-offset="50" data-wow-delay="0.3s">CONTACT <span>AWESOME</span></h2>
            </div>
            <div class="col-md-6 col-sm-6 col-xs-12 wow fadeInLeft" data-wow-offset="50" data-wow-delay="0.9s">
                <form action="#" method="post">
                    <label>NAME</label>
                    <input name="fullname" type="text" class="form-control" id="fullname">

                    <label>EMAIL</label>
                    <%--<input name="email" type="email" class="form-control" id="email">--%>

                    <label>MESSAGE</label>
                    <textarea name="message" rows="4" class="form-control" id="message"></textarea>

                    <input type="submit" class="form-control">
                </form>
            </div>
            <div class="col-md-6 col-sm-6 col-xs-12 wow fadeInRight" data-wow-offset="50" data-wow-delay="0.6s">
                <address>
                    <p class="address-title">OUR ADDRESS</p>
                    <span>Lorem ipsum dolor sit amet, consectetur adipiscing elitquisque tempus ac eget diam et laoreet phasellus ut nisi id leo molestie.</span>
                    <p><i class="fa fa-phone"></i> 010-020-0340</p>
                    <p><i class="fa fa-envelope-o"></i> awesome@company.com</p>
                    <p><i class="fa fa-map-marker"></i> 663 New Walk Roadside, Birdeye View, GO 11020</p>
                </address>
                <ul class="social-icon">
                    <li><h4>WE ARE SOCIAL</h4></li>
                    <li><a href="#" class="fa fa-facebook"></a></li>
                    <li><a href="#" class="fa fa-twitter"></a></li>
                    <li><a href="#" class="fa fa-instagram"></a></li>
                </ul>
            </div>
        </div>
    </div>
</section>
<!-- end contact -->

<!-- start copyright -->
<footer id="copyright">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <p class="wow bounceIn" data-wow-offset="50" data-wow-delay="0.3s">
                    Copyright &copy; 2084 Company Name. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
            </div>
        </div>
    </div>
</footer>
<!-- end copyright -->

</body>
</html>
