<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="registration">
<section class="registration-text">
	<h1>Join the Community Portal</h1>
	<p>Enhance your professional career by widening your social network</p>
</section>
<section class="registration-form">
	<s:fielderror />
	<s:form action="CreateUser">
		<s:textfield name="givenName" label="Given Name" required="true" labelSeparator="*" />
		<s:textfield name="surname" label="Surname" required="true" labelSeparator="*" />
		<s:textfield type="email" name="email" label="Email" required="true" labelSeparator="*" />
		<s:password name="password" label="Password" required="true" labelSeparator="*" />
		<s:password name="cfPassword" label="Confirm Password" required="true" labelSeparator="*" />
		<s:submit value="Sign Up!" />
	</s:form>
	<p>Already signed up? <a href="login.jsp">Sign in</a></p>
</section>
</div>