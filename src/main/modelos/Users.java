package main.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users implements Serializable{
	
	private Integer userID,group_id,permiso_id;
	private String nickname,complete_name,password,mail;
	
	public Users() {
		
	}

	public Users(Integer userID, Integer group_id, Integer permiso_id, String nickname, String complete_name,
			String password, String mail) {
		this.userID = userID;
		this.group_id = group_id;
		this.permiso_id = permiso_id;
		this.nickname = nickname;
		this.complete_name = complete_name;
		this.password = password;
		this.mail = mail;
	}

	//Getters
	@Id
	@Column(name ="userID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserID() {
		return userID;
	}
	
	@Column(name ="groupID")
	public Integer getGroup_id() {
		return group_id;
	}
	
	@Column(name ="permisoID")
	public Integer getPermiso_id() {
		return permiso_id;
	}
	@Column(name ="nickname")
	public String getNickname() {
		return nickname;
	}
	@Column(name ="complete_name")
	public String getComplete_name() {
		return complete_name;
	}
	@Column(name ="password")
	public String getPassword() {
		return password;
	}
	@Column(name ="mail")
	public String getMail() {
		return mail;
	}
	//Setters
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	public void setPermiso_id(Integer permiso_id) {
		this.permiso_id = permiso_id;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setComplete_name(String complete_name) {
		this.complete_name = complete_name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Override
	public String toString() {
		return "Users [userID=" + userID + ", group_id=" + group_id + ", permiso_id=" + permiso_id + ", nickname="
				+ nickname + ", complete_name=" + complete_name + ", password=" + password + ", mail=" + mail + "]";
	}
}
