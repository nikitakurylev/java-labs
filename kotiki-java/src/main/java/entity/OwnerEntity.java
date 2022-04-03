package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Owner", schema = "public", catalog = "postgres")
public class OwnerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "OwnerId")
    private int ownerId;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Birthday")
    private Date birthday;

    @OneToMany
    @JoinTable(name="OwnerCat", joinColumns=@JoinColumn(name="OwnerId"),
            inverseJoinColumns = @JoinColumn(name="CatId")
    )
    private List<CatEntity> cats;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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

    public List<CatEntity> getCats() {
        return cats;
    }

    public void setCats(List<CatEntity> cats) {
        for (CatEntity cat : cats) {
            cat.setOwnerId(ownerId);
        }
        this.cats = cats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnerEntity that = (OwnerEntity) o;

        if (ownerId != that.ownerId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ownerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}
