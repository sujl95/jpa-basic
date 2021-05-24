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
			Team teamA = new Team();
			teamA.setName("팀A");
			em.persist(teamA);

			Member member = new Member();
			member.setUsername("회원1");
			member.setTeam(teamA);
			em.persist(member);

			Team teamB = new Team();
			teamB.setName("팀B");
			em.persist(teamB);

			Member member2 = new Member();
			member2.setUsername("회원2");
			member2.setTeam(teamA);
			em.persist(member2);

			Member member3 = new Member();
			member3.setUsername("회원3");
			member3.setTeam(teamB);
			em.persist(member3);

			em.flush();
			em.clear();

			String query = "select m from Member m";

			List<Member> result = em.createQuery(query, Member.class)
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
