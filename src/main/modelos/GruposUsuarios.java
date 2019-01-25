package main.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usergroups")
public class GruposUsuarios {

	private Integer groupID,ProjectID;
	private String group_name;
	//Getters
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getGroupID() {
		return groupID;
	}
	
	@Column
	public Integer getProjectID() {
		return ProjectID;
	}
	@Column
	public String getGroup_name() {
		return group_name;
	}
	//Setters
	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}
	public void setProjectID(Integer projectID) {
		ProjectID = projectID;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
}
