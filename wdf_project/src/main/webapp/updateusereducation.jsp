<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="usereducation">
<h2>Update Education</h2>
<s:if test="error==true">
	<p><span style="color:red;">An error occurred. Please try again.</span></p>
</s:if>
<s:iterator value="userEduList">
	<div class="usereducation-form">
		<s:form action="UpdateUserEducation" id="%{eduId}">
			<s:textfield name="eduTitle" label="Title" labelSeparator="*" required="true" value="%{eduTitle}"/>
			<s:textfield name="eduInstitution" label="Institution" labelSeparator="*" required="true" value="%{eduInstitution}"/>
			<s:textfield name="eduDuration" label="Duration" labelSeparator="*" required="true" value="%{eduDuration}"/>
		</s:form>
		<button type="submit" form="<s:property value='eduId'/>" name="eduId" value="<s:property value='eduId'/>">Update</button>
		<a href="DeleteUserEducation.action?eduId=<s:property value='eduId'/>">Delete</a>
	</div>
</s:iterator>
<br>
<a href="insertusereducation.jsp">Add Education</a>
<br>
<a href="UserProfile.action?email=<s:property value='#session.email'/>">Back to Profile</a>
</div>