package anh.quizapp.mapper;

import anh.quizapp.dto.request.CreateQuestionRecord;
import anh.quizapp.dto.request.QuestionRecord;
import anh.quizapp.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    public Question toQuestion(QuestionRecord record);
    public Question toQuestion(CreateQuestionRecord record);
    public QuestionRecord toQuestionRecord(Question question);
}
