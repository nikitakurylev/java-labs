package entity;

import java.sql.Date;

public class CatInfo {
    CatEntity catEntity;

    public CatInfo(CatEntity catEntity) {
        this.catEntity = catEntity;
    }

    public String getName(){
        return catEntity.getName();
    }

    public String getBreed(){
        return catEntity.getBreed();
    }

    public String getColor(){
        return catEntity.getColor();
    }

    public Date getBirthday(){
        return catEntity.getBirthday();
    }
}
