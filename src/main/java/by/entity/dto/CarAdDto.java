package by.entity.dto;

import by.entity.base.CarAd;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class CarAdDto {
    private Integer id;
    private Integer ownerId;
    private Integer age;
    private String brand;
    private String model;
    private Integer mileage;
    private Integer engineSize;
    private Integer enginePower;
    private Timestamp creationDate;
    private Timestamp lastEditDate;
    private CarAd.CarCondition condition;
}
