package by.entity.abstractive;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractCarAd {
    private Integer age;
    private String brand;
    private String model;
    private CarCondition condition;
    private Integer mileage;
    private Integer engineSize;
    private Integer enginePower;

    public enum CarCondition {
        NEW, USED, BROKEN
    }
}
