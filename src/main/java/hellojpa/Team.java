package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    //mappedBy: 양방향 연관관계일 때 연관된 상대 Entity의 필드명을 지정
    //상대 엔티티의 필드(team)에 걸려있는 @JoinColumn 정보를 바탕으로 연관된 Member 엔티티를 모두 찾아준다.
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>(); // add() 시에 NPE가 발생하지 않도록 초기화 (관례)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

//    @Override
//    public String toString() {
//        return "Team{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", members=" + members +
//                '}';
//    }
}
