package servlet;

import DAO.UserDAO;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users=UserService.getInstance().getAllUsers();
            req.setAttribute("users",users);
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
