package by.entity.base;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.NaturalId;

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
@Table(name = "telephones")
public class Telephone {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User user;
    @NotNull
    @NaturalId
    @Unique
    private String number;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telephone_generator")
    @SequenceGenerator(name = "telephone_generator", sequenceName = "telephones_id_seq", allocationSize = 1)
    private Integer id;

    @Override
    public String toString() {
        return "Telephone{" +
                "number='" + number + '\'' +
                ", id=" + id +
                '}';
    }
}
