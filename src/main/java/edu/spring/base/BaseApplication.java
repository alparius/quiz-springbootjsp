package edu.spring.base;

import edu.spring.base.model.Question;
import edu.spring.base.model.QuizUser;
import edu.spring.base.repository.QuestionRepository;
import edu.spring.base.repository.QuizUserRepository;
import edu.spring.base.service.Service;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BaseApplication.class, args);

//        Service service = context.getBean(Service.class);
//        service.getAllTodos().forEach(System.out::println);
    }

    // Bootstrap some test data into the in-memory database
    @Bean
    ApplicationRunner init(QuizUserRepository urepo, QuestionRepository qrepo) {
        return args -> {
            Stream.of("alparius", "banderas", "calamino", "domingo", "engelsius").forEach(name -> {
                QuizUser user = QuizUser.builder()
                        .username(name)
                        .password("password")
                        .highscore(0)
                        .current_pageSize(0)
                        .current_quizSize(0)
                        .current_points(0)
                        .build();
                urepo.save(user);
            });
            Stream.of("What is life?", "Are you?", "Am I?", "Why?", "Are you sure?", "To think is to die?", "But really?", "Pinky swear?", "Why is life","Yes or no?", "Yes?", "No?", "Maybe?", "Mayben't?", "YAS?", "Not yes?", "Yesn't it maybe?").forEach(question -> {
                Question q = Question.builder()
                        .question(question)
                        .answer1("yes")
                        .answer2("no")
                        .answer3("maybe")
                        .correct("maybe")
                        .build();
                qrepo.save(q);
            });
            qrepo.findAll().forEach(System.out::println);
            urepo.findAll().forEach(System.out::println);
        };
    }

//    // Fix the CORS errors
//    @Bean
//    public FilterRegistrationBean simpleCorsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        // *** URL below needs to match the Vue client URL and port ***
//        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200")); //9000 maybe?
//        config.setAllowedMethods(Collections.singletonList("*"));
//        config.setAllowedHeaders(Collections.singletonList("*"));
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return bean;
//    }

}
