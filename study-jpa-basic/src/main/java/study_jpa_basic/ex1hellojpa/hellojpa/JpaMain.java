package study_jpa_basic.ex1hellojpa.hellojpa;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // DB연결이 다 된다

		EntityManager em = emf.createEntityManager(); // createEntityManager 를 꺼내야한다
		EntityTransaction tx = em.getTransaction(); // transaction 을 얻는다
		tx.begin(); // 트랜잭션 시작


		try {

			Member member = new Member();
			member.setUsername("member1");
			member.setHomeAddress(new Address("homeCity1", "street", "10000"));

			member.getFavoriteFoods().add("치킨");
			member.getFavoriteFoods().add("피자");
			member.getFavoriteFoods().add("족발");
			member.getFavoriteFoods().add("햄버거");

			member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
			member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

			em.persist(member);

			em.flush();
			em.clear();

			System.out.println("==========================");
			Member findMember = em.find(Member.class, member.getId());
			// Address homeAddress = findMember.getHomeAddress();
			// findMember.setHomeAddress(new Address("newCity", homeAddress.getStreet(), homeAddress.getZipcode()));
			//
			// // 치킨 -> 한식
			// findMember.getFavoriteFoods().remove("치킨");
			// findMember.getFavoriteFoods().add("한식");
			//
			//
			// findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "10000"));
			// findMember.getAddressHistory().add(new AddressEntity("newCity1", "street", "10000"));

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}

	// private static void printMember(Member member) {
	// 	System.out.println("member.getUsername() = " + member.getUsername());
	// }
	//
	// private static void printMemberAndTeam(Member member) {
	// 	String username = member.getUsername();
	// 	System.out.println("username = " + username);
	//
	// 	Team team = member.getTeam();
	// 	System.out.println("team.getName() = " + team.getName());
	// }
}
