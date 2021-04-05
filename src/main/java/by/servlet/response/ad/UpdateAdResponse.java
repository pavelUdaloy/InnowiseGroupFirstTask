package by.servlet.response.ad;

import by.entity.dto.CarAdDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateAdResponse {
    private CarAdDto carAdDto;
}
