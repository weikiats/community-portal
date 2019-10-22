<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="registration-success">
	<h1>Update Education Success!</h1>
	<p>If you are not automatically redirected, <a href="PullUserEducation.action">click here</a></p>
</div>
<script>
	setTimeout(function(){
    	window.location.href = 'PullUserEducation.action';
 	}, 5000);
 	
 	document.getElementById("topbar").style.display = "none";
</script>