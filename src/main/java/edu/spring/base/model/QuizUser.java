package edu.spring.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizUser {

    @Id
    private String username;
    private String password;

    private float highscore;
    private int current_quizSize;
    private int current_pageSize;
    private int current_page;
    private int current_points;
}
