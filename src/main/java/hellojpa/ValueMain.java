package hellojpa;

public class ValueMain {
    public static void main(String[] args) {

        Address address1 = new Address("서울", "가로수길", "123");
        Address address2 = new Address("서울", "가로수길", "123");

        System.out.println("address1 == address2 : " + (address1 == address2)); //참조 주소값 비교
        System.out.println("address1 equals address2 : " + (address1.equals(address2))); //euqals()의 디폴트는 '==' 비교


    }
}
