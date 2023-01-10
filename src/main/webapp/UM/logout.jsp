<%
  session.invalidate();

  Cookie[] c = request.getCookies();
  if (c != null) {
    for (Cookie cf : c) {
      if (cf.getName().equals("id")) {
        cf.setMaxAge(0);
        cf.setPath("/");
        response.addCookie(cf);
      }
    }
  }

  response.sendRedirect("../index.jsp");
%>
