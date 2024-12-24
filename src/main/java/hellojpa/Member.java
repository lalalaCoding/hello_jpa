package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) //Member(Many) : Team(One)
    @JoinColumn(name = "TEAM_ID") //연관관계에서 조인할 컬럼 -> TEAM_ID의 컬럼 값을 team.getTeamID()의 값으로 셋팅해줌
    private Team team;

    @OneToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<MemberProduct> products = new ArrayList<>();

    //기간 Period
    @Embedded
    private Period workPeriod;

    //주소 Address
    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city", column=@Column(name="WORK_CITY")),
            @AttributeOverride(name="street", column=@Column(name="WORK_STREET")),
            @AttributeOverride(name="zipcode", column=@Column(name="WORK_ZIPCODE"))
    })
    private Address workAddress;

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public List<MemberProduct> getProducts() {
        return products;
    }

    public void setProducts(List<MemberProduct> products) {
        this.products = products;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
