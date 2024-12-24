package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address = new Address("서울", "가로수길", "123");

            Member member1 = new Member();
            member1.setName("member1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Address modifyAddress = new Address("뉴욕", address.getStreet(), address.getZipcode());
            member1.setHomeAddress(modifyAddress); //값을 통째로 갈아치워야 함!

//            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode()); //임베디드 타입은 복사해서 사용해야 한다.
//
//            Member member2 = new Member();
//            member2.setName("member2");
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);
            
            //member1과 member2의 homeAddress는 같은 주소 값을 참조하고 있음
            //member1.getHomeAddress().setCity("뉴욕"); //사이드 이펙트 발생



            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }


}
