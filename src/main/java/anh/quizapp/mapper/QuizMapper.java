package anh.quizapp.mapper;

import anh.quizapp.dto.request.CreateQuizRecord;
import anh.quizapp.dto.response.QuizResponse;
import anh.quizapp.entity.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    public Quiz toQuiz(CreateQuizRecord createQuizRecord);

    @Mapping(target = "questions", ignore = true)
    public QuizResponse toQuizResponse(Quiz quiz);
}
