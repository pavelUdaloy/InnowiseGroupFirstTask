package by.entity.base;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private CarAd carAd;
    @NotNull
    @Unique
    @NaturalId
    private String name;
    @NotNull
    @Column(name = "file_format")
    private String fileFormat;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_generator")
    @SequenceGenerator(name = "image_generator", sequenceName = "images_id_seq", allocationSize = 1)
    private Integer id;

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", fileFormat='" + fileFormat + '\'' +
                ", id=" + id +
                '}';
    }
}
