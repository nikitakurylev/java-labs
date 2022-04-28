package entity;

import java.sql.Date;

public class OwnerInfo {
    OwnerEntity ownerEntity;

    public OwnerInfo(OwnerEntity ownerEntity) {
        this.ownerEntity = ownerEntity;
    }

    public String getName(){
        return ownerEntity.getName();
    }

    public Date getBirthday(){
        return ownerEntity.getBirthday();
    }
}
