<%
  session.removeAttribute("nickname");

  response.sendRedirect("../index.jsp");
%>
