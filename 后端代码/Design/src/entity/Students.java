package entity;

import java.io.Serializable;

/**
 * 学生实体类
 * @author Administrator
 *
 */
public class Students implements Serializable{
	private static final long serialVersionUID = 437833505148084882L;
	private String id;//学号
	private String name;//姓名
	private String gender;//性别
	private String politicalStatus;//政治面貌
	private String birthday;//出生年月
	private String profession;//专业
	private String birthplace;//籍贯
	
	public Students() {
	}
	
	public Students(String id, String name, String gender, String politicalStatus, String birthday, String profession, String birthplace) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.politicalStatus = politicalStatus;
		this.birthday = birthday;
		this.profession = profession;
		this.birthplace = birthplace;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + name + ", gender=" + gender + ", politicalStatus=" + politicalStatus
				+ ", birthday=" + birthday + ", profession=" + profession + "birthplace=" + birthplace + "]";
	}
}
