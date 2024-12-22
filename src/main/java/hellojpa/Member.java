package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne //Member(Many) : Team(One)
    @JoinColumn(name = "TEAM_ID") //연관관계에서 조인할 컬럼 -> TEAM_ID의 컬럼 값을 team.getTeamID()의 값으로 셋팅해줌
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany
    @JoinTable(name = "member")
    private List<MemberProduct> products = new ArrayList<>();

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

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    /*
        연관 관계 편의 메서드
            이름은 setter보다는 별도의 이름을 사용하자.
            주인 또는 주인이 아닌 쪽 중 반드시 한 곳에만 작성해야 한다.
     */
    public void changeTeam(Team team) {
        this.team = team; //주인인 쪽에 값 설정
        team.getMembers().add(this); //주인이 아닌 쪽에 값 설정
    }
}
