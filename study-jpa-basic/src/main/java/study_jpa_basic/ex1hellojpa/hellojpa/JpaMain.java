package study_jpa_basic.ex1hellojpa.hellojpa;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

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
			member.setUsername("hello");
			member.setHomeAddress(new Address("city", "street", "10000"));
			member.setWorkPeriod(new Period());

			em.persist(member);

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
