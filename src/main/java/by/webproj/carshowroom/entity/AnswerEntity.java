package by.webproj.carshowroom.entity;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Builder
public class AnswerEntity {
    private final Long id;
    private final String text;
    private final boolean answerTrue;
    private final QuestionEntity questionEntity;
}
