package anh.quizapp.dto;

public record QuestionRecord(
        String questionTitle,
        String option1,
        String option2,
        String option3,
        String option4
) {
}
