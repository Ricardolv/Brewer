package com.richard.brewer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
	
	@Column(name = "dicount_value")
	private BigDecimal dicountValue;
	
	@Column(name = "total_value")
	private BigDecimal totalValue;
	
	private String note;
	
	@Column(name = "delivery_date")
	private LocalDateTime deliveryDate;
	
	@ManyToOne
	@JoinColumn(name = "code_client")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "code_user")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private SaleStatus status;

	@OneToMany(mappedBy = "sale")
	private List<SaleItem> items;

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

	public BigDecimal getDicountValue() {
		return dicountValue;
	}

	public void setDicountValue(BigDecimal dicountValue) {
		this.dicountValue = dicountValue;
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

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
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

	public List<SaleItem> getItems() {
		return items;
	}

	public void setItems(List<SaleItem> items) {
		this.items = items;
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
