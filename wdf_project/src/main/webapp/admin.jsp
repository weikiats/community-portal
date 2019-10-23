<%@ taglib prefix="s" uri ="/struts-tags" %>
<div class="admin">
	<h1>Hello administrator <s:property value="#session.name"/></h1>
	<p>User interface will be improved in the future</p>
	<p><s:a action="Logout">Logout</s:a></p>
	<h2>Database Search</h2>
	<s:form action="AdminSearch">
		<s:textfield name="search" label="Search for a user" required="true" />
		<s:submit value="Search" />
	</s:form>
	<h2>Bulk Mailing</h2>
	<p><s:a action="RetrieveMailingList">Retrieve Mailing List</s:a></p>
	<s:if test="result==2">
		<p><span style="color:red">An error occurred. Please try again.</span></p>
	</s:if>
	<s:elseif test="result==1">
		<p><span style="color:green">Update success!</span></p>
	</s:elseif>
	<s:if test="method=='search'">
		<s:if test="searchList.size()>0">
			<table>
				<tr>
					<td>user_id</td>
					<td>given_name</td>
					<td>surname</td>
					<td>email</td>
					<td>contact</td>
					<td>country</td>
					<td>city</td>
					<td>date_joined</td>
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
				<td>user_id</td>
				<td>given_name</td>
				<td>surname</td>
				<td>email</td>
				<td>contact</td>
				<td>country</td>
				<td>city</td>
				<td>date_joined</td>
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
				<td>exp_id</td>
				<td>exp_title</td>
				<td>exp_company</td>
				<td>exp_desc</td>
				<td>exp_duration</td>
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
				<td>edu_id</td>
				<td>edu_title</td>
				<td>edu_institution</td>
				<td>edu_duration</td>
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
		<p><a href="AdminChangePassword.action?email=${user.email}&password=password">Reset password to 'password'</a></p>
		<p><a href="DeleteUser.action?email=${user.email}">Delete user account</a></p>
	</s:elseif>
	<s:elseif test="method=='deleteUser'&&result!=2">
		<h3>User Profile</h3>
		<table>
			<tr>
				<td>user_id</td>
				<td>given_name</td>
				<td>surname</td>
				<td>email</td>
				<td>contact</td>
				<td>country</td>
				<td>city</td>
				<td>date_joined</td>
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
		<h3>Mailing List</h3>
		<table>
			<tr>
				<td>mail_name</td>
				<td>mail_email</td>
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
	
	
	<script>
	window.onload = function() {
	  document.getElementById("AddMailingList_mailName").focus();
	};
	</script>
</div>