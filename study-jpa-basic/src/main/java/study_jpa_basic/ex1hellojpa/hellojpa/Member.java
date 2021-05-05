package study_jpa_basic.ex1hellojpa.hellojpa;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

@Entity
@TableGenerator(
		name = "MEMBER_SEQ_GENERATOR",
		table = "MY_SEQUENCES",
		pkColumnName = "MEMBER_SEQ", allocationSize = 1
)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
	private Long id;

	@Column(name = "name", nullable = false)
	private String username;

	public Member() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
