package edu.spring.base.servlet;

import edu.spring.base.model.Question;
import edu.spring.base.model.QuizUser;
import edu.spring.base.repository.QuestionRepository;
import edu.spring.base.repository.QuizUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "EvaluateServlet", urlPatterns = {"/servlets/evaluate"})
public class EvaluateServlet extends HttpServlet {

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

        int correct = 0;
        List<Question> qs = (List<Question>) session.getAttribute("quiz");
        for (Question q : qs) {
            String answer = request.getParameter(q.getQuestion());
            if(answer != null) {
                if (answer.equals(q.getCorrect())) {
                    correct += 1;
                }
            }
        }
        user.setCurrent_points(user.getCurrent_points() + correct);
        userRepo.save(user);

        request.getRequestDispatcher("/servlets/setup").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
