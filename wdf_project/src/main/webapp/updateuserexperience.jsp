<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="userexperience">
<h2>Update Experience</h2>
<s:if test="error==true">
	<p><span style="color:red;">An error occurred. Please try again.</span></p>
</s:if>
<s:iterator value="userExpList">
	<div class="userexperience-form">
		<s:form action="UpdateUserExperience" id="%{expId}">
			<s:textfield name="expTitle" label="Title" labelSeparator="*" required="true" value="%{expTitle}"/>
			<s:textfield name="expCompany" label="Company" labelSeparator="*" required="true" value="%{expCompany}"/>
			<s:textfield name="expDesc" label="Job Description" labelSeparator="*" required="true" value="%{expDesc}"/>
			<s:textfield name="expDuration" label="Duration" labelSeparator="*" required="true" value="%{expDuration}"/>
		</s:form>
		<button type="submit" form="<s:property value='expId'/>" name="expId" value="<s:property value='expId'/>">Update</button>
		<a href="DeleteUserExperience.action?expId=<s:property value='expId'/>">Delete</a>
	</div>
</s:iterator>
<br>
<a href="insertuserexperience.jsp">Add Experience</a>
<br>
<a href="UserProfile.action?email=<s:property value='#session.email'/>">Back to Profile</a>
</div>