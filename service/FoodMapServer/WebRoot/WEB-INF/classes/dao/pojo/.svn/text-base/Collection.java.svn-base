package dao.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Collection entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "collection", catalog = "foodmap")
public class Collection implements java.io.Serializable {

	// Fields

	private Integer collectionId;
	private Shop shop;
	private User user;

	// Constructors

	/** default constructor */
	public Collection() {
	}

	/** minimal constructor */
	public Collection(User user) {
		this.user = user;
	}

	/** full constructor */
	public Collection(Shop shop, User user) {
		this.shop = shop;
		this.user = user;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "collection_id", unique = true, nullable = false)
	public Integer getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_id")
	public Shop getShop() {
		return this.shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}