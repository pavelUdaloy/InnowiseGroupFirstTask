package by.entity.abstractive;

import lombok.Data;

import java.sql.Timestamp;

@Data
public abstract class AbstractCarAd {
    private String age;
    private String brand;
    private String model;
    private CarCondition condition;
    private Integer mileage;
    private Integer engineSize;
    private Integer enginePower;
    private Timestamp creationDate;
    private Timestamp lastEditDate;

    public enum CarCondition {
        NEW,USED,BROKEN
    }
}
