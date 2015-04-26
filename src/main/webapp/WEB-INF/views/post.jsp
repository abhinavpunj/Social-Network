<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Post</title>

    <!-- Bootstrap core CSS -->
    <link href="resources/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="resources/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="resources/assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="resources/assets/lineicons/style.css">    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Custom styles for this template -->
    <link href="resources/assets/css/style.css" rel="stylesheet">
    <link href="resources/assets/css/style-responsive.css" rel="stylesheet">
    <script src="resources/assets/js/custom/search.js"></script>
    <script src="resources/assets/js/custom/infiniteScrolling.js"></script>
    <script src="resources/assets/js/custom/likes.js"></script>
    <script src="resources/assets/js/custom/comments.js"></script>
    <script src="resources/assets/js/custom/readNotification.js"></script>
    <script src="resources/assets/js/custom/readMessages.js"></script>
    <script type="text/javascript">
    
    </script>

  </head>

  <body>
<%

//   response.addHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
//   response.addHeader("Pragma", "no-cache"); 
//   response.addDateHeader ("Expires", 0);
   %> 
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
            <a href="index" class="logo"><b>Zap</b></a>
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
              
              	  <p class="centered"><a href="profile?personId=${sessionScope.user.getuId()}"><img onerror="this.src='resources/assets/img/default.jpg';" src="resources/assets/img/ui-sam.jpg" class="img-circle" width="60" />
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
                          <li><a  href="friends">Your Friends</a></li>
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
          <section class="wrapper">
		
              <div class="row">
                <div id="searchArea" class="col-lg-9 main-chart">
				<c:if test="${not empty post }">
	            <div class="row mtpost">
	                    <div class="form-panel">
	                      <a href="profile.html"><img onerror="this.src='resources/assets/img/default.jpg';" src="${post.getPerson().getProfilePicPath()}" width=40px class="img-circle pull-left" />
	                      <h4>&nbsp; ${post.getPerson().getFirstName()} ${post.getPerson().getLastName()}</a></h4><br/>
	                      <p><c:out value="${post.getStatus()}"></c:out></p>
	                      <p><button class="submitLink addLike"><i class="fa fa-thumbs-up"></i> Like</button><span class="like">${post.getLikes()}</span></p>
	                  	  <hidden class="postId" value="${post.getPostId()}" />
	                    <div class="postEnd commentSection">
	                    <c:forEach var="comment" items="${post.getComments()}">
	                      <a href="profile?personId=${comment.getPerson().getpId()}"><img onerror="this.src='resources/assets/img/default.jpg';" src="${comment.getPerson().getProfilePicPath()}" width=20px class="img-circle pull-left" />
	                      <h5>&nbsp; ${comment.getPerson().getFirstName()} ${comment.getPerson().getLastName()}</a></h5>
	                      <p>${comment.getComment()}</p>
	                     </c:forEach>
	                    </div>
	                          <div class="form-group">
	                              <input type="text" autocomplete="off" class="form-control form-post comment" name="comment" placeholder="Comment" />
	                          </div>
	                          <button type="submit" class="addComment btn btn-theme btn-xs">Comment</button>
	                    </div><!-- /col-lg-9 -->
	            </div><!-- /row -->
			
			</c:if>
              
                  </div><!-- /col-lg-9 END SECTION MIDDLE -->
                  
                  
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
                      		<img onerror="this.src='resources/assets/img/default.jpg';" class="img-circle" src="resources/assets/img/ui-divya.jpg" width="35px" height="35px" align="">
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
                      		<img onerror="this.src='resources/assets/img/default.jpg';" class="img-circle" src="resources/assets/img/ui-sherman.jpg" width="35px" height="35px" align="">
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
                      		<img onerror="this.src='resources/assets/img/default.jpg';" class="img-circle" src="resources/assets/img/ui-danro.jpg" width="35px" height="35px" align="">
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
                      		<img onerror="this.src='resources/assets/img/default.jpg';" class="img-circle" src="resources/assets/img/ui-zac.jpg" width="35px" height="35px" align="">
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
                      		<img onerror="this.src='resources/assets/img/default.jpg';" class="img-circle" src="resources/assets/img/ui-sam.jpg" width="35px" height="35px" align="">
                      	</div>
                      	<div class="details">
                      		<p><a href="#">John Bing</a><br/>
                      		   <muted>Available</muted>
                      		</p>
                      	</div>
                      </div>

                  </div><!-- /col-lg-3 -->
              </div><! --/row -->
          </section>
      </section>

      <!--main content end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="resources/assets/js/jquery.js"></script>
    <script src="resources/assets/js/jquery-1.8.3.min.js"></script>
    <script src="resources/assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="resources/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="resources/assets/js/jquery.scrollTo.min.js"></script>
    <script src="resources/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="resources/assets/js/jquery.sparkline.js"></script>


    <!--common script for all pages-->
    <script src="resources/assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="resources/assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="resources/assets/js/gritter-conf.js"></script>
    
  </body>
</html>
