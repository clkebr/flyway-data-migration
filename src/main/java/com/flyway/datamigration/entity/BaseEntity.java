package com.flyway.datamigration.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false,updatable = false)
	@CreatedDate
	public LocalDateTime createdAt;

	@Column(nullable = false,updatable = false)
	@CreatedBy
	public Long insertUserId;

	@Column(nullable = false)
	@LastModifiedDate
	public LocalDateTime updatedAt;

	@Column(nullable = false)
	@LastModifiedBy
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
}
