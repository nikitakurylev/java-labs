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
}
