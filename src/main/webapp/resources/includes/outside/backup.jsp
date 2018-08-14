<%-- 
    Document   : backup
    Created on : Jun 21, 2017, 11:33:27 PM
    Author     : Olakunle Awotunbo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<style>

    .glyphicon { margin-right:5px; }
    .thumbnail
    {
        margin-bottom: 20px;
        padding: 0px;
        -webkit-border-radius: 0px;
        -moz-border-radius: 0px;
        border-radius: 0px;
    }

    .item.list-group-item
    {
        float: none;
        width: 100%;
        background-color: #fff;
        margin-bottom: 10px;
    }
    .item.list-group-item:nth-of-type(odd):hover,.item.list-group-item:hover
    {
        background: #428bca;
    }

    .item.list-group-item .list-group-image
    {
        margin-right: 10px;
    }
    .item.list-group-item .thumbnail
    {
        margin-bottom: 0px;
    }
    .item.list-group-item .caption
    {
        padding: 9px 9px 0px 9px;
    }
    .item.list-group-item:nth-of-type(odd)
    {
        background: #eeeeee;
    }

    .item.list-group-item:before, .item.list-group-item:after
    {
        display: table;
        content: " ";
    }

    .item.list-group-item img
    {
        float: left;
    }
    .item.list-group-item:after
    {
        clear: both;
    }
    .list-group-item-text
    {
        margin: 0 0 11px;
    }

    /*The below is for another thing: Making the div clickable*/
    div.feature {
        position: relative;
    }

    div.feature a {
        position: absolute;
        width: 100%;
        height: 100%;
        top: 0;
        left: 0;
        text-decoration: none; /* No underlines on the link */
        z-index: 10; /* Places the link above everything else in the div */
        background-color: #FFF; /* Fix to make div clickable in IE */
        opacity: 0; /* Fix to make div clickable in IE */
        filter: alpha(opacity=1); /* Fix to make div clickable in IE */
    }

    /*- https://codepen.io/ajaypatelaj/pen/zIBjJ*/      


</style>



<style>
    html,
    body {
        overflow-x: hidden; /* Prevent scroll on narrow devices */
    }
    body {
        padding-top: 70px;
    }
    footer {
        padding: 30px 0;
    }


    /*
     * Off Canvas
     * --------------------------------------------------
     */
    @media screen and (max-width: 767px) {
        .row-offcanvas {
            position: relative;
            -webkit-transition: all .25s ease-out;
            -o-transition: all .25s ease-out;
            transition: all .25s ease-out;
        }

        .row-offcanvas-right {
            right: 0;
        }

        .row-offcanvas-left {
            left: 0;
        }

        .row-offcanvas-right
        .sidebar-offcanvas {
            right: -60%; /* 6 columns */
        }

        .row-offcanvas-left
        .sidebar-offcanvas {
            left: -60%; /* 6 columns */
        }

        .row-offcanvas-right.active {
            right: 60%; /* 6 columns */
        }

        .row-offcanvas-left.active {
            left: 60%; /* 6 columns */
        }

        .sidebar-offcanvas {
            position: absolute;
            top: 0;
            width: 60%; /* 6 columns */
        }
    }
</style>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GamePlay |</title>
        <link href="<c:url value='/resources/css/bootstrap.min.css'/>"rel="stylesheet"></link>   
    </head>
    <body>

 
        <!--
        http://blog.codeply.com/2016/05/18/bootstrap-sidebar-responsive-examples/ 
        -->

    <nav class="navbar navbar-fixed-top navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">WINGAMEPLAY</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>
            </div><!-- /.nav-collapse -->
        </div><!-- /.container -->
    </nav><!-- /.navbar -->



    <div class="container">

        <div class="row row-offcanvas row-offcanvas-right">

            <div class="col-xs-12 col-sm-9">
                <p class="pull-right visible-xs">
                    <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
                </p>
                <!--        
                <div class="jumbotron">
                            <h1>Hello, world!</h1>
                            <p>This is an example to show the potential of an offcanvas layout pattern in Bootstrap. Try some responsive-range viewport sizes to see it in action.</p>
                </div>
                -->
                <div class="row">
                    <div id="games" class="row list-group">
                        <!--https://codepen.io/ajaypatelaj/pen/zIBjJ-->        

                        <c:forEach var="item" items="${gameList}" >
                            <a href="<c:url value='/gameSection-${item.id}-${item.gameCode}' />">
                                <div class="item  col-xs-12 col-md-6">
                                    <div class="thumbnail">
                                        <!--<img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />-->
                                        <div class="caption">
                                            <h4 class="group inner list-group-item-heading">
                                                ${item.gameName}</h4>
                                                
<!--                                            <p class="group inner list-group-item-text">
                                                Product description... Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
                                                sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.</p>
                                            <div class="row">
                                                <div class="col-xs-12 col-md-6">
                                                    <p class="lead">
                                                        $21.000</p>
                                                </div>
                                                <div class="col-xs-12 col-md-6">
                                                    <a class="btn btn-success" href="http://www.jquery2dotnet.com">Add to cart</a>
                                                </div>
                                            </div>
                                            -->
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </c:forEach>

                    </div>
<!--                    
                    <div class="col-xs-8 col-lg-4">
                        <h2>Heading</h2>
                        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
                        <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
                    </div>
                    -->
                    <!--/.col-xs-6.col-lg-4-->
                    <div class=" col-xs-12 ">
                        <h2>Adverts area</h2>
                        <p>Adverts area Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>
                    </div>
                </div><!--/row-->
            </div><!--/.col-xs-12.col-sm-9-->

            <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
                <div class="list-group">
                    
                    <!--    
                    <a href="#" class="list-group-item active">Link</a>
                    <a href="#" class="list-group-item">Link</a>
                    <a href="#" class="list-group-item">Link</a>
                    <a href="#" class="list-group-item">Link</a>
                    <a href="#" class="list-group-item">Link</a>
                    <a href="#" class="list-group-item">Link</a>
                    <a href="#" class="list-group-item">Link</a>
                    <a href="#" class="list-group-item">Link</a>
                    <a href="#" class="list-group-item">Link</a>
                    <a href="#" class="list-group-item">Link</a>
                    -->
                       <h2>Adverts area</h2>
                   <p>Adverts area Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui.</p>

                </div>
            </div><!--/.sidebar-offcanvas-->
        </div><!--/row-->

        <hr>

        <footer>
            <p>&copy; 2017 GamePlay, Inc.</p>
        </footer>

    </div><!--/.container-->


    <script src="<c:url value='/resources/js/jquery.min.js' />"></script> 

    <script>
        $(document).ready(function () {
            console.log("Clicked..");
            $('[data-toggle="offcanvas"]').click(function () {
                $('.row-offcanvas').toggleClass('active')
            });
        });

    </script>


    <script>

        $(document).ready(function () {
            $('#list').click(function (event) {
                event.preventDefault();
                $('#games .item').addClass('list-group-item');
            });
            $('#grid').click(function (event) {
                event.preventDefault();
                $('#games .item').removeClass('list-group-item');
                $('#games .item').addClass('grid-group-item');
            });
        });

    </script>



</body>
</html>

