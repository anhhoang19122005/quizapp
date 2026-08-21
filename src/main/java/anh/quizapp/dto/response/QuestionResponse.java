package anh.quizapp.dto.response;

public record QuestionResponse(
        int id,
        String questionTitle,
        String option1,
        String option2,
        String option3,
        String option4,
        String difficultyLevel,
        String category
) {
}
