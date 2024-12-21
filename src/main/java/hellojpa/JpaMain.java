package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //연관 관계 주인이 아닌 쪽에 값을 넣는 경우
//            Member member = new Member();
//            member.setName("memberA");
//            em.persist(member); // Member.team(연관 관계 주인)을 기준으로 외래 키를 관리하기 때문에 MEMBER.TEAM_ID에는 NULL이 들어감
//
//            Team team = new Team();
//            team.setName("teamA");
//            team.getMembers().add(member); // 연관 관계의 주인이 아니기 때문에 읽기에만 사용 -> JPA가 SQL을 만들 때 무시하는 속성이다.
//            em.persist(team);
        
            //연관 관계 주인이 맞는 쪽에 값을 넣는 경우
            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.changeTeam(team);
            em.persist(member);

//            team.getMembers().add(member);
            /*
                주인의 반대편에 값을 세팅해주지 않을 때 문제점
                1. 객체 관점
                    객체 그래프에서는 양 방향 참조가 일어나야 하는데 한 쪽 객체에만 값이 설정되어 있는 논리적 문제점
                2. JPA 관점
                    플러시가 일어나지 않았을 때 em.find()로 반환된 Team 엔티티는 1차 캐시에 '이미' 존재하는 엔티티가 반한됨
                    즉, Team.members는 [] 인 상태로 밖에 접근이 불가능해지는 문제점
                * 결론 : 양방향 연관 관계는 양쪽 모두에 값을 세팅해야 한다!
                * 조언 : 연관 관계 양쪽에 값을 설정해주는 '연관 관계 생성 메서드'를 통해 한 번에 값을 설정해주자!
            */

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers(); //플러시가 발생하지 않았기 때문에 members = []인 상태

            System.out.println("===================");
            System.out.println("members = " + findTeam);
            System.out.println("===================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
