<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<div class="search">
	<s:if test="numberOfResults>0">
		<section class="numberofresults">
			<p><s:property value="numberOfResults"/> results for "<s:property value="search"/>"</p>
		</section>
		<section class="listofresults">
			<ul>
				<s:iterator value="searchList">
					<li>
						<s:if test="#session.login==true">
							<a href="UserProfile.action?email=<s:property value='email'/>"></a>
						</s:if>
						<h3><s:property value="givenName" /></h3>
						<h4><s:property value="surname" /></h4>
						<p><s:property value="email" /></p>
						<p><s:property value="country" /></p>
						<s:else>
							<p>Login or sign up to see more information about <s:property value="givenName" /></p>
						</s:else>
					</li>
				</s:iterator>
			</ul>
		</section>
	</s:if>
	<s:else>
		<section class="numberofresults">
			<p>Oops! There are no results based on your search query. Try again</p>
		</section>
	</s:else>
</div>
