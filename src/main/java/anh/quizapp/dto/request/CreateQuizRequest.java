package anh.quizapp.dto.request;

public record CreateQuizRequest(
        String quizName,
        int numQuestions,
        String category
) {
}
