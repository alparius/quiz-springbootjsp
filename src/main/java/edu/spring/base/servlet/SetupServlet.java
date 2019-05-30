package edu.spring.base.servlet;

import edu.spring.base.model.Question;
import edu.spring.base.model.QuizUser;
import edu.spring.base.repository.QuestionRepository;
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
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "SetupServlet", urlPatterns = {"/servlets/setup"})
public class SetupServlet extends HttpServlet {

    @Autowired
    QuizUserRepository userRepo;
    @Autowired
    QuestionRepository qRepo;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        QuizUser user = userRepo.findByUsername(username);

        int perpage;
        int currpage;
        int perquiz;
        if (session.getAttribute("started") == null) {
            session.setAttribute("started", "started");
            perpage = Integer.parseInt(request.getParameter("perpage"));
            perquiz = Integer.parseInt(request.getParameter("perquiz"));
            currpage = 0;
            user.setCurrent_points(0);
            user.setCurrent_pageSize(perpage);
            user.setCurrent_quizSize(perquiz);
        } else {
            perpage = user.getCurrent_pageSize();
            currpage = user.getCurrent_page();
            perquiz = user.getCurrent_quizSize();
        }

        user.setCurrent_page(currpage + 1);
        userRepo.save(user);

        if (currpage*perpage >= perquiz) {
            session.removeAttribute("started");
            session.removeAttribute("quiz");
            request.setAttribute("correct", user.getCurrent_points());
            request.setAttribute("all", user.getCurrent_quizSize());
            float percent = (float)user.getCurrent_points() / (float)user.getCurrent_quizSize();
            if (percent > user.getHighscore()) {
                user.setHighscore(percent);
                userRepo.save(user);
            }
            request.setAttribute("highscore", user.getHighscore());
            request.getRequestDispatcher("/quizEnd.jsp").forward(request, response);
            return;
        }

        if ((currpage+1)*perpage > perquiz) {
            perpage = perquiz - currpage*perpage;
        }
        List<Question> page_request = qRepo.findAll();
        Collections.shuffle(page_request);
        List<Question> page = page_request.stream().skip(currpage*perpage).limit(perpage).collect(Collectors.toList());
        session.setAttribute("quiz", page);
        request.getRequestDispatcher("/quizPage.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}