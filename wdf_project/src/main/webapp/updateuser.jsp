<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="userprofile">
<h2>Update Profile</h2>
<s:if test="error==true">
	<p><span style="color:red;">An error occurred. Please try again.</span></p>
</s:if>
<s:form action="UpdateUser" onsubmit="return validatePassword()">
	<s:textfield name="givenName" label="Given Name" labelSeparator="*" required="true" value="%{givenName}"/>
	<s:textfield name="surname" label="Surname" labelSeparator="*" required="true" value="%{surname}"/>
	<s:textfield name="contact" label="Contact" labelSeparator="*" required="true" value="%{contact}"/>
	<s:textfield name="country" label="Country" labelSeparator="*" required="true" value="%{country}"/>
	<s:textfield name="city" label="City" labelSeparator="*" required="true" value="%{city}"/>
	<s:textarea name="about" label="About" labelSeparator="*" required="true" value="%{about}"/>
	<s:password name="password" label="New Password" labelSeparator="*" required="true" />
	<s:password name="cfPassword" label="Confirm New Password" labelSeparator="*" required="true" />
	<s:submit type="button" label="Update" name="email" value="%{#session.email}"/>
</s:form>
<a href="UserProfile.action?email=<s:property value='#session.email'/>">Back to Profile</a>
</div>
<script type="text/javascript">
	function validatePassword() {
		var password = document.getElementById("UpdateUser_password").value;
		var cfPassword = document.getElementById("UpdateUser_cfPassword").value;
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