package avito.converter.domain;

import lombok.Data;

import javax.persistence.*;
import java.net.URL;

@Entity
@Data
public class PrettyUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private URL oldUrl;
}
