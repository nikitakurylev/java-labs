package itmo.tech.main.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner {
    private int ownerId;
    private String name;
    private Timestamp dateOfBirth;
    private List<Cat> cats = new ArrayList<>();

    @Id
    @GeneratedValue
    @Column(name = "owner_id")
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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

    @OneToMany
    @JoinTable(name="owner_cat", joinColumns=@JoinColumn(name="owner_id"),
            inverseJoinColumns = @JoinColumn(name="cat_id")
    )
    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;

        if (ownerId != owner.ownerId) return false;
        if (name != null ? !name.equals(owner.name) : owner.name != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(owner.dateOfBirth) : owner.dateOfBirth != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ownerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        return result;
    }
}
