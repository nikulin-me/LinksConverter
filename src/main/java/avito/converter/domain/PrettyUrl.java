package avito.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrettyUrl{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user   ;

    private URL oldUrl;

    private String newUrl;
}
