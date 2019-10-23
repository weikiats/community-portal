<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri ="/struts-tags" %>
<html>
<head>
	<link rel="stylesheet" href="css/styles.css">
	<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
<div class="admin">
	<div class="admin-header">
		<h1>Hello administrator <s:property value="#session.name"/></h1>
		<div class="admin-nav">
			<p><s:a action="AdminSearchbar">Database Search</s:a></p>
			<p><s:a action="RetrieveMailingList">Retrieve Mailing List</s:a></p>
			<p><s:a action="Logout">Logout</s:a></p>
		</div>
	</div>
	<div class="admin-result">
		<s:if test="result==2">
			<p><span style="color:red">An error occurred. Please try again.</span></p>
		</s:if>
		<s:elseif test="result==1">
			<p><span style="color:green">Update success!</span></p>
		</s:elseif>
	</div>
	<div class="admin-main">
		<s:if test="method=='searchbar'||method=='search'||method=='userProfile'||method=='deleteUser'">
			<h2>Database Search</h2>
			<s:form action="AdminSearch">
				<s:textfield name="search" label="Search for a user" required="true" />
				<s:submit value="Search" />
			</s:form>
		</s:if>
		<s:if test="method=='search'">
			<h3>Search Results</h3>
			<s:if test="searchList.size()>0">
				<table>
					<tr>
						<th>user_id</th>
						<th>given_name</th>
						<th>surname</th>
						<th>email</th>
						<th>contact</th>
						<th>country</th>
						<th>city</th>
						<th>date_joined</th>
					</tr>
					<s:iterator value="searchList">
						<tr>
							<td>${userId}</td>
							<td>${givenName}</td>
							<td>${surname}</td>
							<td>${email}</td>
							<td>${contact}</td>
							<td>${country}</td>
							<td>${city}</td>
							<td>${dateJoined}</td>
							<td><a href="AdminUserProfile.action?email=${email}">Edit</a></td>
						</tr>
					</s:iterator>
				</table>
			</s:if>
			<s:else>
				<p>No results. Please search again.</p>
			</s:else>
		</s:if>
		<s:elseif test="method=='userProfile'&&result!=2">
			<h3>User Profile</h3>
			<table>
				<tr>
					<th>user_id</th>
					<th>given_name</th>
					<th>surname</th>
					<th>email</th>
					<th>contact</th>
					<th>country</th>
					<th>city</th>
					<th>date_joined</th>
				</tr>
				<tr>
					<td>${user.userId}</td>
					<td>${user.givenName}</td>
					<td>${user.surname}</td>
					<td>${user.email}</td>
					<td>${user.contact}</td>
					<td>${user.country}</td>
					<td>${user.city}</td>
					<td>${user.dateJoined}</td>
				</tr>
			</table>
			<h3>User Experience</h3>
			<table>
				<tr>
					<th>exp_id</th>
					<th>exp_title</th>
					<th>exp_company</th>
					<th>exp_desc</th>
					<th>exp_duration</th>
				</tr>
				<s:if test="userExpList.size()>0">
					<s:iterator value="userExpList">
						<tr>
							<td>${expId}</td>
							<td>${expTitle}</td>
							<td>${expCompany}</td>
							<td>${expDesc}</td>
							<td>${expDuration}</td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<tr>
						<td>No experience found</td>
					</tr>
				</s:else>
			</table>
			<h3>User Education</h3>
			<table>
				<tr>
					<th>edu_id</th>
					<th>edu_title</th>
					<th>edu_institution</th>
					<th>edu_duration</th>
				</tr>
				<s:if test="userEduList.size()>0">
					<s:iterator value="userEduList">
						<tr>
							<td>${eduId}</td>
							<td>${eduTitle}</td>
							<td>${eduInstitution}</td>
							<td>${eduDuration}</td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<tr>
						<td>No education found</td>
					</tr>
				</s:else>
			</table>
			<br>
			<p><a href="AdminChangePassword.action?email=${user.email}&password=password">Reset password to 'password'</a></p>
			<p><a href="DeleteUser.action?email=${user.email}">Delete user account</a></p>
		</s:elseif>
		<s:elseif test="method=='deleteUser'&&result!=2">
			<h3>User Profile</h3>
			<table>
				<tr>
					<th>user_id</th>
					<th>given_name</th>
					<th>surname</th>
					<th>email</th>
					<th>contact</th>
					<th>country</th>
					<th>city</th>
					<th>date_joined</th>
				</tr>
				<tr>
					<td>${user.userId}</td>
					<td>${user.givenName}</td>
					<td>${user.surname}</td>
					<td>${user.email}</td>
					<td>${user.contact}</td>
					<td>${user.country}</td>
					<td>${user.city}</td>
					<td>${user.dateJoined}</td>
				</tr>
			</table>
			<br>
			<s:form action="DeleteUser">
				<s:hidden name="email" value="%{user.email}"/>
				<s:hidden name="method" value="confirmDelete" />
				<s:textfield name="confirm" label="To confirm user account deletion, submit 'delete' into the form" required="true" />
				<s:submit />
			</s:form>
		</s:elseif>
		<s:elseif test="method=='mailingList'">
			<h2>Mailing List</h2>
			<table>
				<tr>
					<th>mail_name</th>
					<th>mail_email</th>
				</tr>
				<s:if test="mailList.size()>0">
					<s:iterator value="mailList">
						<tr>
							<td>${givenName}</td>
							<td>${email}</td>
							<td><a href="DeleteMailingList.action?email=${email}">Delete</a></td>
						</tr>
					</s:iterator>
				</s:if>
				<s:else>
					<tr>
						<td>No data found</td>
					</tr>
				</s:else>
			</table>
			<h3>Add to Mailing List</h3>
			<s:form action="AddMailingList">
				<s:textfield name="mailName" label="Name of Recipient" value="" required="true" />
				<s:textfield name="mailEmail" type="email" label="Email Address" value="" required="true" />
				<s:submit />
			</s:form>
			<s:if test="mailList.size()>0">
				<h3>Send Bulk Email</h3>
				<p><s:a action="SendBulkMail" onclick="return confirm('Upon sending of bulk mail, mailing list will be deleted. Are you sure?')">Send Bulk Mail</s:a></p>
			</s:if>
		</s:elseif>
	</div>
</div>
<script>
window.onload = function() {
  document.getElementById("AddMailingList_mailName").focus();
};
</script>
</body>
</html>