package by.servlet.requestentity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

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
    private Timestamp lastEditDate;
}
