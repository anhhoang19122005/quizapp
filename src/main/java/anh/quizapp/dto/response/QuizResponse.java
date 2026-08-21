package anh.quizapp.dto.response;


import anh.quizapp.entity.Question;

import java.util.List;

public record QuizResponse(
        Integer id,
        String quizName,
        Integer numQuestions
) {
}
