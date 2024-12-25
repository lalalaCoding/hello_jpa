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
            //값 타입 저장
            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(new Address("homeCity", "street1", "zip1"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("요거트");
            member.getFavoriteFoods().add("커피");

//            member.getAddressHistory().add(new Address("old1", "street1", "zip1"));
//            member.getAddressHistory().add(new Address("old2", "street1", "zip1"));
            member.getAddressHistory().add(new AddressEntity("old1", "street1", "zip1"));
            member.getAddressHistory().add(new AddressEntity("old2", "street1", "zip1"));

            em.persist(member);

            em.flush();
            em.clear();
            
            //값 타입 조회
            System.out.println("======== FIND =======");
            Member findMember = em.find(Member.class, member.getId()); //값 타입은 '즉시 로딩', 값 타입 컬렉션은 '지연 로딩'
            
//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address의 city = " + address.getCity());
//            }

            //값 타입 수정: homeCity -> newCity **값 타입은 불변 객체로 설계하기 때문에 통째로 갈아치워야 함**
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            //값 타입 컬렉션 Set<String> 수정1: 치킨 -> 한식 **update SQL이 아님**
            findMember.getFavoriteFoods().remove("치킨"); // 한 행에 대한 delete SQL
            findMember.getFavoriteFoods().add("한식"); // 한 행에 대한 insert SQL

            //값 타입 컬렉션 List<Address> 수정2:
            //Collection.remove(Object o)는 euqals()를 객체를 찾아서 제거해줌
//            findMember.getAddressHistory().remove(new Address("old1", "street1", "zip1")); //여러 행에 대한 delete SQL
//            findMember.getAddressHistory().add(new Address("newCity1", "street1", "zip1")); //여러 행에 대한 insert SQL
            findMember.getAddressHistory().remove(new AddressEntity("old1", "street1", "zip1")); //여러 행에 대한 delete SQL
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street1", "zip1")); //여러 행에 대한 insert SQL



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
