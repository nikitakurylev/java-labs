package itmo.tech.main.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class CatDTO {
    private int catId;
    private String name;
    private Timestamp dateOfBirth;
    private String type;
    private String color;
    private Integer ownerId;
    private List<Cat> friends = new ArrayList<>();

    public CatDTO(int catId, String name, Timestamp dateOfBirth, String type, String color, Integer ownerId, List<Cat> friends) {
        this.catId = catId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.color = color;
        this.ownerId = ownerId;
        this.friends = friends;
    }
}
