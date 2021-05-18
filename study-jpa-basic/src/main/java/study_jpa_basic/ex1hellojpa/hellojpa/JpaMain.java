package study_jpa_basic.ex1hellojpa.hellojpa;

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
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			Team teamB = new Team();
			team.setName("teamB");
			em.persist(teamB);

			Member member1 = new Member();
			member1.setUsername("member1");
			member1.setTeam(team);
			em.persist(member1);

			Member member2 = new Member();
			member2.setUsername("member2");
			member2.setTeam(teamB);
			em.persist(member2);

			em.flush();
			em.clear();

			// Member m = em.find(Member.class, member1.getId());
			List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
					.getResultList();

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}

	private static void printMember(Member member) {
		System.out.println("member.getUsername() = " + member.getUsername());
	}

	private static void printMemberAndTeam(Member member) {
		String username = member.getUsername();
		System.out.println("username = " + username);

		Team team = member.getTeam();
		System.out.println("team.getName() = " + team.getName());
	}
}
