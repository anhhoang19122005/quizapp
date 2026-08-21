package anh.quizapp.dto.response;


import anh.quizapp.entity.Question;

import java.util.List;

public record QuizResponse(
        String quizName,
        String numQuestions,
        List<Question> questions
) {
}
