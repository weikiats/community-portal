<%@ taglib prefix="s" uri ="/struts-tags" %>
<s:include value="header.jsp" />
<section class="index1">
	<div class="index1-text">
		<h1>Community Portal</h1><br>
		<p>Make yourself known in the vast community of software engineers with Community Portal</p><br>
		<a href="registration.jsp">Register Now</a>
	</div>
	<div class="index1-pic">
		<img src="img/index1.jpg" alt="interconnected people" width="500">
	</div>
</section>
<section class="index2">
	<div class="index2-pic">
		<img src="img/index2.png" alt="personalisation" width="500">
	</div>
	<div class="index2-text">
		<h3>Customise your Profile</h3><br>
		<p>Personalise what your fellow peers can view about you</p><br>
		<a href="registration.jsp">Register Now</a>
	</div>
</section>
<section class="index3">
	<div class="index3-text">
		<h3>Looking for Someone?</h3>
	</div>
	<form action="Search.action" id="index3-searchform" class="index3-searchform">
		<input type="text" name="search" placeholder="Search by First Name, Last Name, Current Company, etc" class="index3-searchbar" required>
	</form>
	<button type="submit" form="index3-searchform" class="index3-searchicon">&#x2315;</button>
</section>
<s:include value="footer.jsp" />