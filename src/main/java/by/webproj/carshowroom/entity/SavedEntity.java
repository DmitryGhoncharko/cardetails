package by.webproj.carshowroom.entity;

import lombok.*;

@Builder
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class SavedEntity {
    private final Long id;
    private final String name;
    private final BodyEntity bodyEntity;
    private final EngineEntity engineEntity;
    private final SalonEntity salonEntity;
    private final SeparaterlyEntity separaterlyEntity;
    private final SuspensionEntity suspensionEntity;
    private final AdditionEntity additionEntity;

    private final Integer additionCount;
    private final Integer bodyCount;
    private final Integer engineCount;
    private final Integer salonCount;
    private final Integer separaterlyCount;
    private final Integer suspensionCount;
}
