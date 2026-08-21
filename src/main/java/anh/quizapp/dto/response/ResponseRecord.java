package anh.quizapp.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseRecord {
    private Integer id;
    private String response;
}
