package by.entity.abstractive;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public abstract class AbstractImage {
    private String name;
    private String fileFormat;
}
