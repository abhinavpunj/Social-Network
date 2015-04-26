<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Friends</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="resources/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="resources/assets/js/fancybox/jquery.fancybox.css" rel="stylesheet" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        
    <!-- Custom styles for this template -->
    <link href="resources/assets/css/style.css" rel="stylesheet">
    <link href="resources/assets/css/style-responsive.css" rel="stylesheet">
    <script src="resources/assets/js/jquery.js"></script>
    <script src="resources/assets/js/custom/search.js"></script>
	<script src="resources/assets/js/custom/readNotification.js"></script>
    <script src="resources/assets/js/custom/readMessages.js"></script>
    <SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
  </head>

  <body>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa-white fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
              </div>
            <!--logo start-->
            <a href="index.html" class="logo"><b>Zap</b></a>
            <!--logo end-->
            <div class="nav notify-row" id="top_menu">
                <!--  notification start -->
                <ul class="nav top-menu">
                    <!-- settings start -->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="notification dropdown-toggle" href="index.html#">
                            <i class="fa-white fa-th-list"></i>
                            <span class="badge bg-theme"><c:out value="${sessionScope.notificationCount}"></c:out></span>
                        </a>
                        <ul class="dropdown-menu extended tasks-bar">
                            <div class="notify-arrow notify-arrow-green"></div>
                            <li>
                                <p class="green">You have <c:out value="${sessionScope.notificationCount}"></c:out> pending notifications</p>
                            </li>
                            <c:forEach var="notification" items="${sessionScope.notificationList}">
	                            <li>
	                                <a href="post?postId=${notification.getPosts().getPostId()}">
	                                    <div class="task-info">
	                                        <div class="desc">${notification.getNotification()}</div>
	                                    </div>
	                                </a>
	                            </li>
                            </c:forEach>
                        </ul>
                    </li>
                    <!-- settings end -->
                    <!-- inbox dropdown start-->
                    <li id="header_inbox_bar" class="dropdown">
                        <a data-toggle="dropdown" class="messages dropdown-toggle" href="index.html#">
                            <i class="fa-white fa-envelope-o"></i>
                            <span class="badge bg-theme"><c:out value="${sessionScope.messageCount}"></c:out></span>
                        </a>
                        <ul class="dropdown-menu extended inbox">
                            <div class="notify-arrow notify-arrow-green"></div>
                            <li>
                                <p class="green">You have <c:out value="${sessionScope.messageCount}"></c:out> new messages</p>
                            </li>
                            <c:forEach var="person" items="${sessionScope.messageList}">
	                            <li>
	                                <a href="getMessage?personId=${person.getpId()}">
	                                    <div class="task-info">
	                                        <div class="desc">${person.getFirstName()} ${person.getLastName()}</div>
	                                    </div>
	                                </a>
	                            </li>
                            </c:forEach>
                            <li>
                                <a href="inbox">See all messages</a>
                            </li>
                        </ul>
                    </li>
                    <!-- inbox dropdown end -->
                    <!-- Search Bar -->
                    <li>
                    <div class="pull-left">
                	  <form class="form-inline"  role="form">
                          <div class="form-group">
                              <input type="text" style="width:500px;" class="form-control" id="search" placeholder="Search Everyone">
                          </div>
                          <button type="submit" class="btn btn-theme">Search</button>
                      </form>
          			</div><!-- /form-panel -->
          			</li>
                </ul>
                <!--  notification end -->	
                
                
            </div>
            <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><a class="logout" onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="" href="logout">Logout</a></li>
            	</ul>
            </div>
        </header>
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              
              	  <p class="centered"><a href="profile?personId=${sessionScope.user.getuId()}"><img onerror="this.src='resources/assets/img/default.jpg';" src="${sessionScope.user.getPerson().getProfilePicPath()}" class="img-circle" width="60" />
              	  <h5 class="centered">${sessionScope.user.getPerson().getFirstName()} ${sessionScope.user.getPerson().getLastName()}</h5></a></p>
              	  	
                  <li class="mt">
                      <a href="index">
                          <i class="fa fa-stack-exchange"></i>
                          <span>Home</span>
                      </a>
                  </li>

                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-child"></i>
                          <span>Friends</span>
                      </a>
                      <ul class="sub">
                          <li class="active"><a  href="friends">Your Friends</a></li>
                          <li><a  href="friendRequests">Friend Requests</a></li>
                          <li><a  href="pendingRequests">Pending Requests</a></li>
                      </ul>
                  </li>
				  <li class="sub-menu">
                      <a href="inbox" >
                          <i class="fa fa-calendar"></i>
                          <span>Inbox</span>
                      </a>
                  </li>                 

              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper site-min-height">
            <div class="row">
              <div id="searchArea" class="col-lg-9" >
          	
			<c:forEach var="person" items="${personList}">
              <div class="row mtpost">
                <div class="form-panel">
                  <form:form method="POST" action="unFriend">
                        <a href="profile?personId=${person.getpId()}"><h3><i class="fa fa-angle-right"></i> <img onerror="this.src='resources/assets/img/default.jpg';" src="${person.getProfilePicPath()}" width=150 height=150 class="img-circle" />
                        ${person.getFirstName()} ${person.getLastName()}</a>
                        <button type="submit" class="pull-right btn btn-danger btn-sm">UnFriend</button>
                        <input type="hidden" name="personId" value="${person.getpId()}" />
                        <a href="getMessage?personId=${person.getpId()}" class="pull-right btn btn-warning btn-sm">Send Message</a></h3>
                  </form:form>
                  
                </div>
              </div>
			</c:forEach>
      </div>
			<!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->                  
                  
                  <div class="col-lg-3 ds">
                    <!--COMPLETED ACTIONS DONUTS CHART-->
            
                       <!-- USERS ONLINE SECTION -->
            <h3>Friends Online</h3>
                      <!-- First Member -->
                      <div class="desc">
                        <div class="thumb">
                          <img class="img-circle" src="resources/assets/img/ui-divya.jpg" width="35px" height="35px" align="">
                        </div>
                        <div class="details">
                          <p><a href="#">Abhinav Punj</a><br/>
                             <muted>Available</muted>
                          </p>
                        </div>
                      </div>
                      <!-- Second Member -->
                      <div class="desc">
                        <div class="thumb">
                          <img class="img-circle" src="resources/assets/img/ui-sherman.jpg" width="35px" height="35px" align="">
                        </div>
                        <div class="details">
                          <p><a href="#">Harshit Shah</a><br/>
                             <muted>I am Busy</muted>
                          </p>
                        </div>
                      </div>
                      <!-- Third Member -->
                      <div class="desc">
                        <div class="thumb">
                          <img class="img-circle" src="resources/assets/img/ui-danro.jpg" width="35px" height="35px" align="">
                        </div>
                        <div class="details">
                          <p><a href="#">Apoorva Joshi</a><br/>
                             <muted>Available</muted>
                          </p>
                        </div>
                      </div>
                      <!-- Fourth Member -->
                      <div class="desc">
                        <div class="thumb">
                          <img class="img-circle" src="resources/assets/img/ui-zac.jpg" width="35px" height="35px" align="">
                        </div>
                        <div class="details">
                          <p><a href="#">Emily Peters</a><br/>
                             <muted>Available</muted>
                          </p>
                        </div>
                      </div>
                      <!-- Fifth Member -->
                      <div class="desc">
                        <div class="thumb">
                          <img class="img-circle" src="resources/assets/img/ui-sam.jpg" width="35px" height="35px" align="">
                        </div>
                        <div class="details">
                          <p><a href="#">John Bing</a><br/>
                             <muted>Available</muted>
                          </p>
                        </div>
                      </div>

                  </div><!-- /col-lg-3 -->
              </div><! --/row -->

      <!--main content end-->
  </section>
		</section><! --/wrapper -->
      </section><!-- /MAIN CONTENT -->

      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
				2015 - Zap.co
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="resources/assets/js/jquery.js"></script>
    <script src="resources/assets/js/bootstrap.min.js"></script>
    <script src="resources/assets/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script src="resources/assets/js/jquery.ui.touch-punch.min.js"></script>
    <script class="include" type="text/javascript" src="resources/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="resources/assets/js/jquery.scrollTo.min.js"></script>
    <script src="resources/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="resources/assets/js/fancybox/jquery.fancybox.js"></script>    


    <!--common script for all pages-->
    <script src="resources/assets/js/common-scripts.js"></script>

    <!--script for this page-->
  
  <script type="text/javascript">
      $(function() {
        //    fancybox
          jQuery(".fancybox").fancybox();
      });
    </script>
  <script>
      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });

  </script>

  </body>
</html>
