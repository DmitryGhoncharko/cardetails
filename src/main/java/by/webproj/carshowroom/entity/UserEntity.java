package by.webproj.carshowroom.entity;

import lombok.*;

@EqualsAndHashCode
@Getter
@ToString
@RequiredArgsConstructor
@Builder
public class UserEntity {
    private final long id;
    private final String login;
    private final String password;
    private final Role role;
}
