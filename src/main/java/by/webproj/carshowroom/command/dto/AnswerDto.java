package by.webproj.carshowroom.command.dto;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Builder
public class AnswerDto {
    private final Long id;
    private final String text;
    private final boolean answerTrue;
}
