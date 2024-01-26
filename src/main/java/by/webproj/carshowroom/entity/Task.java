package by.webproj.carshowroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;

@Data
@AllArgsConstructor
@Getter
public class Task {
    private Long id;
    private String desc;
    private int price;
    private Date dateEnd;
    private Category category;
}
