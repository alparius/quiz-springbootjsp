package edu.spring.base.servlet;

import edu.spring.base.model.QuizUser;
import edu.spring.base.repository.QuizUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "LoginServlet", urlPatterns = {"/servlets/login"})
public class LoginServlet extends HttpServlet {

    @Autowired QuizUserRepository userRepo;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        QuizUser user = userRepo.findByUsername(username);

        RequestDispatcher rd;
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("login", true);
            rd = request.getRequestDispatcher("/quizStart.jsp");

        } else {
            rd = request.getRequestDispatcher("/loginFailed.jsp");
            if (user==null) {
                request.setAttribute("error","invalid username");
            } else {
                request.setAttribute("error","invalid password");
            }
        }

        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
