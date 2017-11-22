package example.common.formbean;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String password;
	private Integer age;
	private Date birthday;
	
	public Student() {
		super();
	}
	
	public Student(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public Student(String name, String password, Integer age, Date birthday) {
		super();
		this.name = name;
		this.password = password;
		this.age = age;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", birthday=" + birthday + "]";
	}
	
	

}
