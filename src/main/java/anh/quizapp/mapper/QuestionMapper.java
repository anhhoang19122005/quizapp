package anh.quizapp.mapper;

import anh.quizapp.dto.request.CreateQuestionRequest;
import anh.quizapp.dto.response.QuestionResponse;
import anh.quizapp.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    public Question toQuestion(CreateQuestionRequest record);
    public QuestionResponse toQuestionRecord(Question question);
}
