package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

//jdbc:hsqldb:hsql://localhost/

@Entity
public class Person implements java.io.Serializable {
	private Long id;

	private String name;
	private int age;
	private List<Person> potos = new ArrayList<Person>();

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	public List<Person> getPotos() {
		return this.potos;
	}

	public void setPotos(List<Person> a) {
		this.potos = a;
	}

	public void addPoto(Person p) {
		p.potos.add(this);
		this.potos.add(p);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]\n\n" + potos.toString();
	}

}
