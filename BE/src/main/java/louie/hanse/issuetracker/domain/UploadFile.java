package louie.hanse.issuetracker.domain;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class UploadFile {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String originalFilename;
    private String storeFilename;
    private String url;
}
