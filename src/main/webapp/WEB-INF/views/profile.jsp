<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Profile</title>

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
    <script src="resources/assets/js/custom/switch.js"></script>
    
    <script src="resources/assets/js/custom/likes.js"></script>
    <script src="resources/assets/js/custom/comments.js"></script>
    <script src="resources/assets/js/custom/readNotification.js"></script>
    <script src="resources/assets/js/custom/readMessages.js"></script>
    <script src="resources/assets/js/custom/validateEmail.js"></script>
    <script src="resources/assets/js/custom/username.js"></script>
    <script type="text/javascript">
    
    </script>
	<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
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
                            <li class="external">
                                <a href="#">See All Tasks</a>
                            </li>
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
          <section class="wrapper site-min-height">
            <div class="row">
              <div id="searchArea" class="col-lg-9" >
          	
          	<div class="row mt">
              <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 desc">
                <div class="project-wrapper">
                            <div class="project">
                                <div class="photo-wrapper">
                                    <div class="photo">
                                      <a class="fancybox" href="${person.getProfilePicPath()}"><img onerror="this.src='resources/assets/img/default.jpg';" class="img-responsive" alt="Profile Pic" src="${person.getProfilePicPath()}"></a>
                                    </div>
                                    <div class="overlay"></div>
				                    <div class="registration">
				                    <c:if test="${sessionScope.user.getuId() == person.getpId()}">
						                <span>
						                    <a data-toggle="modal" href="profile.html#imageUpload"> Change Profile Picture</a>
						                </span>
						            </c:if>
						            </div>
                                </div>
                            </div>
                        </div>
              </div><!-- col-lg-4 -->
              <div id="divSave" style="margin-top:0px;" class="col-lg-7 form-panel">
              <c:if test="${sessionScope.user.getuId() == person.getpId()}">
                <a class="pull-right" id="edit" href="#">Edit</a>
              </c:if>
                <div class="col-lg-7 col-md-4 col-sm-4 col-xs-12 desc">
                  <h3><i class="fa fa-angle-right"></i> ${person.getFirstName()} ${person.getLastName()}</h3>
                  <h4><i class="fa fa-male"></i> ${person.getGender()}</h4>
                  <h4><i class="fa fa-birthday-cake"></i> ${person.getDobString()}</h4>
                  <h4><i class="fa fa-envelope-o"></i> ${person.getEmail()}</h4>
                </div><!-- /col-md-4 -->
              </div><!-- col-lg-7-->
              <div id="divEdit" style="margin-top:0px;" class="col-lg-7 form-panel">
              <form:form class="form-inline" method="POST" commandName="userAccount" action="editDetails">
                <button type="submit" class="pull-right submitLink" id="save">Save</button>
                <div class="col-lg-7 col-md-4 col-sm-4 col-xs-12 desc">
                  			  <input type='hidden' name="pId" value="${person.getpId()}" />
                              <p><input type="text" required name="firstName" value="${person.getFirstName()}" placeholder="First Name" autocomplete="off" class="form-control placeholder-no-fix">
                              <p><input type="text" required name="lastName" value="${person.getLastName()}" placeholder="Last Name" autocomplete="off" class="form-control placeholder-no-fix"></p>
                              <p><input type="text" required name="username" value="${sessionScope.user.getUsername()}" placeholder="Username" autocomplete="off" class="form-control placeholder-no-fix"><h4><i style="color:green;" class="fa fa-check"></i><i style="color:red;" class="fa fa-times"></i></h4></p>
                              <p><input type="text" required id="email" onblur="validateEmail();" name="email" value="${person.getEmail()}" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix"></p>
                 			<p><span id="validEmail" class="validEmail"></span></p>
                 			 <p><input type="text" name="dob" placeholder="Date of Birth" value="${person.getDobString()}" autocomplete="off" class="form-control placeholder-no-fix"></p>
                 			 <input type="hidden" name="profilePicPath" value="${person.getProfilePicPath()}" />
                  			<c:if test="${person.getGender() == 'Male'}">
                              <p><input type="radio" checked="checked" name="gender" id="gender" value="Male" class="radio-inline"> Male</input>
                              <input type="radio" name="gender" id="gender" value="Female" class="radio-inline"> Female</input></p>
                            </c:if>
                            <c:if test="${person.getGender() == 'Female'}">
                              <p><input type="radio" name="gender" id="gender" value="Male" class="radio-inline"> Male</input>
                              <input type="radio" checked="checked" name="gender" id="gender" value="Female" class="radio-inline"> Female</input></p>
                            </c:if>
                            
                          </div>
                          </form:form>
                  </div>

        </div><!-- row-mt -->
		<c:if test="${sessionScope.user.getuId() == person.getpId()}">
        <div class="row mtpost">
                    <div class="form-panel">
                      <h4>Keep Calm and Share On....</h4>
                      <form class="form-horizontal tasi-form" action="addPosts" method="POST" role="form">
                          <div class="form-group">
                              <textarea class="form-control form-post" name="status" id="status" placeholder="Interest your buddies"></textarea>
                          </div>
                          <button type="submit" class="btn btn-theme">Spread it!</button>
                      </form>
                    </div>
                  </div>
         </c:if>
         <c:if test="${checkIfFriend || sessionScope.user.getuId() == person.getpId()}">
         <c:forEach var="post" items="${posts}">
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
			</c:forEach>
			</c:if>
      </div>
      <div aria-hidden="true" aria-labelledby="ImageUpload" role="dialog" tabindex="-1" id="imageUpload" class="modal fade">
      <form method="POST" action="update" enctype="multipart/form-data">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Upload Profile Picture</h4>
		                      </div>
		                      
		                      <div class="modal-body">
		                          <p>Browse Files to upload</p>
								  <input type="file" name="profilePic"/>
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
		                          <input class="btn btn-theme" value="Submit" type="submit"/>
		                      </div>
		                      
		                  </div>
		              </div>
		              </form>
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
  
  <script type="text/javascript">
  $(".fancybox").fancybox({
	    fitToView: false,
	    onStart: function () {
	        this.width = 10;
	        this.height = 10;
	    }
	});
  </script>

  </body>
</html>
