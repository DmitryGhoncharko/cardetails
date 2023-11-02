package by.webproj.carshowroom.entity;

import lombok.*;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class TestEntity {
    private final Long id;
    private final String testName;
    private final boolean testVisible;
}
