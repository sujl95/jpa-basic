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
			team.setName("TeamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			em.persist(member);

			team.addMember(member);

			em.flush();
			em.clear();

			Team findTeam = em.find(Team.class, team.getId());
			List<Member> members = findTeam.getMembers();

			System.out.println("=============");
			System.out.println("members = " + findTeam);
			System.out.println("=============");

			Member findMember = em.find(Member.class, member.getId());
			Team team1 = findMember.getTeam();
			System.out.println("team1.getId() = " + team1.getId());

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
