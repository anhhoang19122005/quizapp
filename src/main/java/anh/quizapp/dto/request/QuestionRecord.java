package anh.quizapp.dto.request;

public record QuestionRecord(
        int id,
        String questionTitle,
        String option1,
        String option2,
        String option3,
        String option4,
        String difficultLevel,
        String category
) {
}
