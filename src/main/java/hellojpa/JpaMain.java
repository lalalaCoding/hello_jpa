package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //회원 등록
            /*
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);
            */
            
            //회원 목록 조회
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(5) // 5번째 데이터부터
                    .setMaxResults(8) // 8번째 데이터까지
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }


            //회원 수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            //회원 삭제
            //em.remove(findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
