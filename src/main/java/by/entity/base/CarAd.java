package by.entity.base;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "car_ads")
public class CarAd {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_ad_generator")
    @SequenceGenerator(name = "car_ad_generator", sequenceName = "car_ads_id_seq", allocationSize = 1)
    private Integer id;
    @NotNull
    private Integer age;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private Integer mileage;
    @NotNull
    @Column(name = "engine")
    private Integer engineSize;
    @NotNull
    @Column(name = "power")
    private Integer enginePower;
    @NotNull
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @NotNull
    @Column(name = "last_edit_date")
    private LocalDateTime lastEditDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Image> images;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CarCondition condition;

    public enum CarCondition {
        NEW, USED, BROKEN
    }

    @Override
    public String toString() {
        return "CarAd{" +
                "id=" + id +
                ", age=" + age +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                ", engineSize=" + engineSize +
                ", enginePower=" + enginePower +
                ", creationDate=" + creationDate +
                ", lastEditDate=" + lastEditDate +
                ", images=" + images +
                ", condition=" + condition +
                '}';
    }
}
