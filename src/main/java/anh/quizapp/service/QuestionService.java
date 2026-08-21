package anh.quizapp.service;

import anh.quizapp.mapper.QuestionMapper;
import anh.quizapp.repository.QuestionRepository;
import anh.quizapp.dto.request.CreateQuestionRecord;
import anh.quizapp.dto.request.QuestionRecord;
import anh.quizapp.entity.Question;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionService {
    QuestionRepository questionRepository;
    QuestionMapper questionMapper;
    public List<QuestionRecord> getAllQuestion() {
        try {
            return questionRepository.findAll().stream().map(questionMapper::toQuestionRecord).toList();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new ArrayList<>();
    }


    public List<QuestionRecord> getQuestionsByCategory(String category) {
        return questionRepository.findAll().stream().filter(question -> question.getCategory().equals(category))
                .map(questionMapper::toQuestionRecord).toList();
    }

    public QuestionRecord addQuestion(CreateQuestionRecord record) {
        Question question = questionMapper.toQuestion(record);
        questionRepository.save(question);
        return questionMapper.toQuestionRecord(question);
    }
}
