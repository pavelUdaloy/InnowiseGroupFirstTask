package by.controller.request.ad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UpdateAdRequest {
    private Integer id;
    private Integer age;
    private String brand;
    private String model;
    private Integer mileage;
    private Integer engineSize;
    private Integer enginePower;
    private LocalDateTime lastEditDate;
}
