package study_jpa_basic.ex1hellojpa.hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Item{

	private String author;
	private String isbn;
}
