package by.webproj.carshowroom.command.dto;

import by.webproj.carshowroom.entity.QuestionEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class StartDto {
    private final QuestionEntity questionEntity;
    private final List<AnswerDto> answerDtos;
}
