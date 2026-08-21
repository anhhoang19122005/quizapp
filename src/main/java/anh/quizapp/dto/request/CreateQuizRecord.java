package anh.quizapp.dto.request;

public record CreateQuizRecord(
        String quizName,
        int numQuestions,
        String category
) {
}
