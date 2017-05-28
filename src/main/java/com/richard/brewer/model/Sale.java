package com.richard.brewer.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column(name = "creation_date")
	private LocalDateTime createionDate;
	
	@Column(name = "freight_value")
	private BigDecimal freightValue;
	
	@Column(name = "discount_value")
	private BigDecimal discountValue;
	
	@Column(name = "total_value")
	private BigDecimal totalValue;
	
	private String note;
	
	@Column(name = "delivery_hour_date")
	private LocalDateTime deliveryHourDate;
	
	@ManyToOne
	@JoinColumn(name = "code_client")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "code_user")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private SaleStatus status = SaleStatus.BUDGET;

	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
	private List<SalesItem> items;
	
	@Transient
	private String uuid;
	
	@Transient
	private LocalDate deliveryDate;
	
	@Transient
	private LocalTime deliveryHour;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public LocalDateTime getCreateionDate() {
		return createionDate;
	}

	public void setCreateionDate(LocalDateTime createionDate) {
		this.createionDate = createionDate;
	}

	public BigDecimal getFreightValue() {
		return freightValue;
	}

	public void setFreightValue(BigDecimal freightValue) {
		this.freightValue = freightValue;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getDeliveryHourDate() {
		return deliveryHourDate;
	}

	public void setDeliveryHourDate(LocalDateTime deliveryHourDate) {
		this.deliveryHourDate = deliveryHourDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SaleStatus getStatus() {
		return status;
	}

	public void setStatus(SaleStatus status) {
		this.status = status;
	}

	public List<SalesItem> getItems() {
		return items;
	}

	public void setItems(List<SalesItem> items) {
		this.items = items;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public LocalTime getDeliveryHour() {
		return deliveryHour;
	}

	public void setDeliveryHour(LocalTime deliveryHour) {
		this.deliveryHour = deliveryHour;
	}
	
	/** 
	 * Business 
	 */
	public boolean isNew() {
		return this.code == null;
	}
	
	public void addItems(List<SalesItem> items) {
		this.items = items;
		this.items.forEach(i -> i.setSale(this));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	

	
}
