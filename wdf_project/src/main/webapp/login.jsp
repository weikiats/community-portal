<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="login">
	<section class="login-text">
		<h1>Welcome Back to Community Portal</h1>
	</section>
	<section class="login-form">
		<s:if test="result=='input'">
			<p><span style="color:#900e0e;">You have entered a wrong email or password. Please try again.</span></p>
		</s:if>
		<form action="UserLogin.action" method="post" id="login-input" class="login-input">
			<input type="text" name="email" placeholder="Email" required>
			<input type="password" name="password" placeholder="Password" required>
			<button type="submit" form="login-input">Sign in</button>
		</form>
		<p><a href="forgotpw.jsp">Forgot password?</a></p>
		<p>New to Community Portal? <a href="registration.jsp">Join now</a></p>
	</section>
</div>