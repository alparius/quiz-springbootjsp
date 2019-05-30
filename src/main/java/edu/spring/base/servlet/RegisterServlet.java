package edu.spring.base.servlet;

import edu.spring.base.model.QuizUser;
import edu.spring.base.repository.QuizUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

@WebServlet(name = "RegisterServlet", urlPatterns = {"/servlets/register"})
public class RegisterServlet extends HttpServlet {

    @Autowired
    QuizUserRepository userRepo;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        QuizUser user = userRepo.findByUsername(username);

        boolean isValid = false;
        if (user == null && password1.equals(password2)) {
            isValid = true;
            QuizUser newUser = QuizUser.builder()
                    .username(username)
                    .password(password1)
                    .highscore(0)
                    .build();
            userRepo.save(newUser);
        }


        RequestDispatcher rd;
        if (isValid) {
            System.out.println(username +  ", " + password1);
            rd = request.getRequestDispatcher("/index.jsp");
        } else {
            rd = request.getRequestDispatcher("/registerFailed.jsp");
            if (user!=null) {
                request.setAttribute("error","username already in use");
            } else {
                request.setAttribute("error","passwords must match");
            }
        }

        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
