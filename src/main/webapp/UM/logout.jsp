<%
  session.removeAttribute("nickname");
  session.removeAttribute("manager");

  response.sendRedirect("../index.jsp");
%>
