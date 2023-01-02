<%
  session.removeAttribute("memberId");

  response.sendRedirect("./index.jsp");
%>
