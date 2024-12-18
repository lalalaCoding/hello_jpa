package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {

    @Id //PK 매핑
    private Long id;

    @Column(name = "name") //특정 컬럼명과 매핑
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) // enum 타입
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) // 날짜 타입
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;


    @Lob
    private String description;

    @Transient
    private int temp;


    public Member() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
