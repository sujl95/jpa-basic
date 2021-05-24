package jpql;

import static jpql.MemberType.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();


		try {
			Member member = new Member();
			member.setUsername("관리자1");
			em.persist(member);

			Member member2 = new Member();
			member2.setUsername("관리자2");
			em.persist(member2);

			em.flush();
			em.clear();

			String query = "select size(t.members) from Team t";

			List<Integer> result = em.createQuery(query, Integer.class)
					.getResultList();

			result.forEach(System.out::println);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
