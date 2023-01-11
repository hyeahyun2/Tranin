package controller;

import dao.MemberSelectByNicknameDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkingDuplicate")
public class NicknameDuplicationCheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");

        String nickName = req.getParameter("nickname");


        MemberSelectByNicknameDao dao = new MemberSelectByNicknameDao();
        String state = dao.slectHimorHer(nickName);


        JSONObject responseJson = new JSONObject();
        responseJson.put("result", state);
        resp.getWriter().write(responseJson.toString());
    }

}

