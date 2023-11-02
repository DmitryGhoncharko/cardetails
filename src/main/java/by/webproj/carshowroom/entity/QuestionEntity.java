package by.webproj.carshowroom.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Builder
public class QuestionEntity {
    private final Long id;
    private final String text;
    private final String rule;
    private final TestEntity testEntity;
}
