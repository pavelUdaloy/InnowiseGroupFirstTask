package by.entity.dto;

import by.entity.base.CarAd;
import by.entity.validator.IdConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CarAdDto {
    @IdConstraint
    private Integer id;
    private Integer ownerId;
    private Integer age;
    private String brand;
    private String model;
    private Integer mileage;
    private Integer engineSize;
    private Integer enginePower;
    private LocalDateTime creationDate;
    private LocalDateTime lastEditDate;
    private CarAd.CarCondition condition;
}
