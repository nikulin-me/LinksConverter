package avito.converter.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrettyUrl{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    private URL oldUrl;

    private URL newUrl;

    @Override
    public String toString() {
        return "PrettyUrl{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", oldUrl=" + oldUrl +
                ", newUrl=" + newUrl +
                '}';
    }
}
