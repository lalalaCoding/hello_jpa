package hellojpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A") //부모 테이블의 구분자 컬럼에 들어갈 값, 기본 값은 엔티티 이름(Album)
public class Album extends Item {

    private String artist;
}
