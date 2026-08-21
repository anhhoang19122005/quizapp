package anh.quizapp.mapper;

import anh.quizapp.dto.request.CreateQuizRequest;
import anh.quizapp.dto.response.QuizResponse;
import anh.quizapp.entity.Quiz;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    public Quiz toQuiz(CreateQuizRequest createQuizRequest);
    public QuizResponse toQuizResponse(Quiz quiz);
}
