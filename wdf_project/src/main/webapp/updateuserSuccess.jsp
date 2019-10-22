<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="registration-success">
	<h1>Update Profile Success!</h1>
	<p>If you are not automatically redirected, <a href="UserProfile.action?email=<s:property value='#session.email'/>">click here</a></p>
</div>
<script>
	var email = '<%= session.getAttribute("email") %>';
	setTimeout(function(){
    	window.location.href = 'UserProfile.action?email='+email;
 	}, 5000);
 	
 	document.getElementById("topbar").style.display = "none";
</script>