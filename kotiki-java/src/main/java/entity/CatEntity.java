package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cat", schema = "public", catalog = "postgres")
public class CatEntity {
    public CatEntity() {
        friends = new ArrayList<>();
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CatId")
    private int catId;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Birthday")
    private Date birthday;
    @Basic
    @Column(name = "Breed")
    private String breed;
    @Basic
    @Column(name = "Color")
    private String color;
    @Basic
    @Column(name = "OwnerId")
    private Integer ownerId;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Friend",
            joinColumns = { @JoinColumn(name = "firstCatId") },
            inverseJoinColumns = { @JoinColumn(name = "secondCatId") }
    )
    private List<CatEntity> friends;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public List<CatEntity> getFriends() {
        return friends;
    }

    public void setFriends(List<CatEntity> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CatEntity catEntity = (CatEntity) o;

        if (catId != catEntity.catId) return false;
        if (name != null ? !name.equals(catEntity.name) : catEntity.name != null) return false;
        if (birthday != null ? !birthday.equals(catEntity.birthday) : catEntity.birthday != null) return false;
        if (breed != null ? !breed.equals(catEntity.breed) : catEntity.breed != null) return false;
        if (color != null ? !color.equals(catEntity.color) : catEntity.color != null) return false;
        if (ownerId != null ? !ownerId.equals(catEntity.ownerId) : catEntity.ownerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = catId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (breed != null ? breed.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        return result;
    }
}
