package itmo.tech.main.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cat {
    private int catId;
    private String name;
    private Timestamp dateOfBirth;
    private String type;
    private String color;
    private Integer ownerId;

    private List<Cat> friends = new ArrayList<>();

    public Cat(){
    }

    public Cat(int catId, String name, Timestamp dateOfBirth, String type, String color, Integer ownerId, List<Cat> friends) {
        this.catId = catId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.color = color;
        this.ownerId = ownerId;
        this.friends = friends;
    }

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "friends",
            joinColumns = { @JoinColumn(name = "first_friend_id") },
            inverseJoinColumns = { @JoinColumn(name = "second_friend_id") }
    )
    public List<Cat> getFriends() {
        return friends;
    }

    public void setFriends(List<Cat> friends) {
        this.friends = friends;
    }

    @Id
    @GeneratedValue
    @Column(name = "cat_id")
    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date_of_birth")
    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "owner_id")
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        if (catId != cat.catId) return false;
        if (name != null ? !name.equals(cat.name) : cat.name != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(cat.dateOfBirth) : cat.dateOfBirth != null) return false;
        if (type != null ? !type.equals(cat.type) : cat.type != null) return false;
        if (ownerId != null ? !ownerId.equals(cat.ownerId) : cat.ownerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = catId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        return result;
    }
}