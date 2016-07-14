<%-- 
    Document   : jsptry
    Created on : 11.07.2016, 11:20:32
    Author     : Alexander
--%>
<html>
<%@page session="true"%>
<%@ page import="converter.Converter, converter.ConverterHome,
         java.math.*, javax.ejb.*, javax.naming.*,
         javax.rmi.PortableRemoteObject, java.rmi.RemoteException" %>
    <%
        Converter converter = session.getAttribute("converter");
        if (converter == null) {
            InitialContext ic = new InitialContext();
            Object objRef = ic.lookup("ejb/Converter");
            ConverterHome home
                    = (ConverterHome) PortableRemoteObject.narrow(objRef,
                            ConverterHome.class);
            converter = home.create();
            session.setAttribute("converter", converter);
        }
    %>
</html>


