<%-- 
    Document   : jsptry
    Created on : 11.07.2016, 11:20:32
    Author     : Alexander
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<form name="SimpleForm" action="index.jsp">

<input type="text" name="NameEditor" value="" size="30" />

<input type="checkbox" name="SomeCheckBox" value="ON" />

<br>


<input type="submit" value="Action 1" name="Action1Button" />

<input type="submit" value="Action 2" name="Action2Button" />

</form>
<% Enumeration names = request.getParameterNames();
if (names.hasMoreElements()) {
String name = null;
String value = null;%>
<table border="1">
<tbody>

</tbody>
</table>
<% } %>
</html>