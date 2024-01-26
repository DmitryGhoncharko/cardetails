package by.webproj.carshowroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Getter
public class RevDto {
    private Task task;
    private String chatId;
    private String photoUrl;
}
