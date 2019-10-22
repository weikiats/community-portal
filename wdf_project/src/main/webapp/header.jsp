<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri ="/struts-tags" %>
<html>
<head>
<meta charset="UTF-8">    
<link rel="stylesheet" href="css/styles.css">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
</head>
<body>
<nav class="topbar" id="topbar">
	<div class="topbar-brand">
		<s:if test="#session.login==true">
			<a href="home.jsp">Community Portal</a>
		</s:if>
		<s:else>
			<a href="index.jsp">Community Portal</a>
		</s:else>
	</div>
	<div class="topbar-search">
		<form action="Search.action" id="topbar-searchform" class="topbar-searchform">
			<input type="text" name="search" placeholder="Search for a user" class="topbar-searchbar" required>
			<button type="submit" form="topbar-searchform" class="topbar-searchicon">&#x2315;</button>
		</form>
	</div>
	<div class="topbar-user-buttons">
	<s:if test="#session.login==true">
		<div class="dropdown">
			<button class="dropdown-button">
				Hi <s:property value="#session.name" />!
			</button>
			<div class="dropdown-content">
				<a href="UserProfile.action?email=<s:property value='#session.email'/>">View Profile</a>
				<a href="Logout.action">Logout</a>
			</div>
		</div>
	</s:if>
	<s:else>
		<a href="login.jsp">Login</a>
		<a href="registration.jsp">Sign Up</a>
	</s:else>
	</div>
</nav>