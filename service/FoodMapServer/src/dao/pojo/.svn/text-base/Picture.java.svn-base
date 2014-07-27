package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Picture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "picture", catalog = "foodmap")
public class Picture implements java.io.Serializable {

	// Fields

	private Integer picId;
	private Integer type;
	private String picUrl;
	private Integer id;
	private String userId;
	private String datetime;

	// Constructors

	/** default constructor */
	public Picture() {
	}

	/** full constructor */
	public Picture(Integer type, String picUrl, Integer id, String userId,
			String datetime) {
		this.type = type;
		this.picUrl = picUrl;
		this.id = id;
		this.userId = userId;
		this.datetime = datetime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pic_id", unique = true, nullable = false)
	public Integer getPicId() {
		return this.picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "pic_url", length = 200)
	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "user_id", length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "datetime", length = 50)
	public String getDatetime() {
		return this.datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

}