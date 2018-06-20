package web.model.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account_settings")
public class AccountSetting implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6849223550312096800L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="pen_name")
	private String penName;
	
	@Column(name="introduction")
	private String introduction;
	
	@Column(name="profile_picture_file_id")
	private String profilePictureFileId;
	
	@Column(name="my_tags")
	private String myTags = ""; //mysql mediumtext probably can't set default value 
	
	@OneToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	public AccountSetting() {}
	public AccountSetting(String penName, String introduction) {
		this.penName = penName;
		this.introduction = introduction;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPenName() {
		return penName;
	}
	public void setPenName(String penName) {
		this.penName = penName;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getProfilePictureFileId() {
		return profilePictureFileId;
	}
	public void setProfilePictureFileId(String profilePictureFileId) {
		this.profilePictureFileId = profilePictureFileId;
	}
	public String getMyTags() {
		return myTags;
	}
	public void setMyTags(String myTags) {
		this.myTags = myTags;
	}

}
