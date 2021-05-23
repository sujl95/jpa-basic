package jpql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();


		try {
			Member member = new Member();
			member.setUsername("member1");
			member.setAge(10);
			em.persist(member);

			em.flush();
			em.clear();

			List<Member> members = em.createQuery("select m  from Member m order by  m.age desc ", Member.class)
					.setFirstResult(1)
					.setMaxResults(10)
					.getResultList();

			System.out.println("members.get(0) = " + members.get(0));



			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
