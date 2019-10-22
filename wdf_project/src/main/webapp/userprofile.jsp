<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="profile">
	<section class="userprofile">
		<h1>User Profile</h1>
		<table>
			<tr>
				<td>Given Name:</td>
				<td><s:property value="user.givenName"/></td>
			</tr>
			<tr>
				<td>Surname:</td>
				<td><s:property value="user.surname"/></td>
			</tr>
			<tr>
				<td>About:</td>
				<td><s:property value="user.about"/></td>
			</tr>
			<tr>
				<td>Country:</td>
				<td><s:property value="user.country"/></td>
			</tr>
			<tr>
				<td>City:</td>
				<td><s:property value="user.city"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><s:property value="user.email"/></td>
			</tr>
		</table>
		<s:if test="user.email==#session.email">
			<a href="PullUser.action?email=<s:property value="user.email"/>">Update Profile</a>
		</s:if>
		<s:elseif test="#session.admin==true">
			<a href="PullUser.action?email=<s:property value="user.email"/>">ADMIN: Update Profile</a>
		</s:elseif>
	</section>
	<section class="userexperience">
		<h3>Experience</h3>
		<s:iterator value="userExpList">
			<table class="userexperienceset">
				<tr>
					<td>Title:</td>
					<td><s:property value="expTitle"/></td>
				</tr>
				<tr>
					<td>Company:</td>
					<td><s:property value="expCompany"/></td>
				</tr>
				<tr>
					<td>Job Description:</td>
					<td><s:property value="expDesc"/></td>
				</tr>
				<tr>
					<td>Duration:</td>
					<td><s:property value="expDuration"/></td>
				</tr>
			</table>
		</s:iterator>
		<s:if test="user.email==#session.email">
			<a href="PullUserExperience.action">Update Experience</a>
		</s:if>
		<s:elseif test="#session.admin==true">
			<a href="PullUser.action?email=<s:property value="user.email"/>">ADMIN: Update Experience</a>
		</s:elseif>
	</section>
	<section class="usereducation">
		<h3>Education</h3>
		<s:iterator value="userEduList">
			<table class="usereducationset">
				<tr>
					<td>Title:</td>
					<td><s:property value="eduTitle"/></td>
				</tr>
				<tr>
					<td>Institution:</td>
					<td><s:property value="eduInstitution"/></td>
				</tr>
				<tr>
					<td>Duration:</td>
					<td><s:property value="eduDuration"/></td>
				</tr>
			</table>
		</s:iterator>
		<s:if test="user.email==#session.email">
			<a href="PullUserEducation.action">Update Education</a>
		</s:if>
		<s:elseif test="#session.admin==true">
			<a href="PullUser.action?email=<s:property value="user.email"/>">ADMIN: Update Education</a>
		</s:elseif>
	</section>
</div>