package avito.converter.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PrettyUrl{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private URL oldUrl;

    private String newUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public URL getOldUrl() {
        return oldUrl;
    }

    public void setOldUrl(URL oldUrl) {
        this.oldUrl = oldUrl;
    }

    public String getNewUrl() {
        return newUrl;
    }

    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl;
    }

    @Override
    public String toString() {
        return "PrettyUrl{" +
                "id=" + id +
                ", user=" + user +
                ", oldUrl=" + oldUrl +
                ", newUrl='" + newUrl + '\'' +
                '}';
    }
}
