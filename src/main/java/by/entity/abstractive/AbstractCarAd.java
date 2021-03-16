package by.entity.abstractive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
