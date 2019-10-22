<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="usereducation">
	<h2>Add Education</h2>
	<s:if test="error==true">
		<p><span style="color:red;">An error occurred. Please try again.</span></p>
	</s:if>
	<s:form action="InsertUserEducation">
		<s:textfield name="eduTitle" label="Title" labelSeparator="*" required="true"/>
		<s:textfield name="eduInstitution" label="Institution" labelSeparator="*" required="true"/>
		<s:textfield name="eduDuration" label="Duration" labelSeparator="*" required="true"/>
		<s:submit type="button" value="Update"/>
	</s:form>
	<a href="PullUserExperience.action">Back to Education</a>
</div>