<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="forgotpw">
	<h3>Find your account</h3>
	<p>Please enter your email</p>
	<s:if test="userId==0">
		<p><span style="color:red;">The email you entered is not registered with us. Please try again.</span></p>
	</s:if>
	<form action="CheckUser.action" id="forgotpw-form" class="forgotpw-form" method="post">
		<input type="email" name="email" placeholder="Enter your email" required><br>
		<a href="login.jsp">Cancel</a>
		<button type="submit" form="forgotpw-form">Search</button>
	</form>
</div>