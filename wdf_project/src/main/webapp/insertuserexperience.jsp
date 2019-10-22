<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="userexperience">
	<h2>Add Experience</h2>
	<s:if test="error==true">
		<p><span style="color:red;">An error occurred. Please try again.</span></p>
	</s:if>
	<s:form action="InsertUserExperience">
		<s:textfield name="expTitle" label="Title" labelSeparator="*" required="true"/>
		<s:textfield name="expCompany" label="Company" labelSeparator="*" required="true"/>
		<s:textfield name="expDesc" label="Job Description" labelSeparator="*" required="true"/>
		<s:textfield name="expDuration" label="Duration" labelSeparator="*" required="true"/>
		<s:submit type="button" value="Update"/>
	</s:form>
	<a href="PullUserExperience.action">Back to Experience</a>
</div>