package anh.quizapp.dto.request;

public record CreateQuestionRecord(
        String questionTitle,
        String option1,
        String option2,
        String option3,
        String option4,
        String rightAnswer,
        String category,
        String difficultyLevel
) {
}
