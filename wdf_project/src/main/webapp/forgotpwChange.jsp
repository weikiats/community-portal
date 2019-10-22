<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="forgotpwchange">
	<h3>Reset your password</h3>
	<p>Please enter your new password</p>
	<s:if test="result==0">
		<p><span style="color:red;">An error occurred. Please try again.</span></p>
	</s:if>
	<form action="ChangePassword.action" id="forgotpwchange-form" class="forgotpwchange-form" method="post" onsubmit="return validatePassword()">
		<input type="hidden" name="email" id="email" value="<s:property value='email' />">
		<input type="password" name="password" id="password" placeholder="Enter your new password" required><br>
		<input type="password" name="cfPassword" id="cfPassword" placeholder="Retype new password" required><br>
		<a href="login.jsp">Cancel</a>
		<button type="submit" form="forgotpwchange-form">Search</button>
	</form>
	<script type="text/javascript">
		function validatePassword() {
			var password = document.getElementById("password").value;
			var cfPassword = document.getElementById("cfPassword").value;
			if (password.length<6) {
				alert("Password must be at least 6 characters long.");
				return false;
			} else if (password!==cfPassword) {
				alert("Password fields do not match.");
				return false;
			} else {
				return true;
			}
		}
	</script>
</div>