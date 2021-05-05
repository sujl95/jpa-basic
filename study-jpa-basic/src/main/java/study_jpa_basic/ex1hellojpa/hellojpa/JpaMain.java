package study_jpa_basic.ex1hellojpa.hellojpa;

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
			member.setUsername("A");
			Member member1 = new Member();
			member.setUsername("B");
			Member member2 = new Member();
			member.setUsername("C");

			System.out.println("============");
			em.persist(member);
			// DB SEQ = 1	|	1
			// DB SEQ = 51	|	2
			// DB SEQ = 101	|	3

			em.persist(member1);
			em.persist(member2);

			System.out.println("member.getId() = " + member.getId());
			System.out.println("member1.getId() = " + member1.getId());
			System.out.println("member2.getId() = " + member2.getId());

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
