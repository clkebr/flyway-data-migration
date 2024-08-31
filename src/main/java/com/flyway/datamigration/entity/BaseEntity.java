package com.flyway.datamigration.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false,updatable = false)
	public LocalDateTime createdAt;

	@Column(nullable = false,updatable = false)
	public Long insertUserId;

	@Column(nullable = false)
	public LocalDateTime updatedAt;

	@Column(nullable = false)
	public Long lastUpdateUserId;

	private Boolean isDeleted = false;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		BaseEntity that = (BaseEntity) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@PrePersist
	public void onPrePersist() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.insertUserId = 1L;
		this.lastUpdateUserId = 1L;
	}

	@PreUpdate
	public void onPreUpdate() {
		this.updatedAt = LocalDateTime.now();
		this.lastUpdateUserId = 1L;
	}
}
