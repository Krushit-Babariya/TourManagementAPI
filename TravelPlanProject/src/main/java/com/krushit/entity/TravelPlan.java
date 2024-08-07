package com.krushit.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PROJ_TRAVEL_PLAN")
@Data
public class TravelPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer planId;
	@Column(length = 30)
	private String plan_name;
	@Column(length = 100)
	private String plan_description;
	private Double plan_budjet;
	private Integer plan_category_id;

	@Column(name = "ACTIVE_SW")
	private String avtiveSW = "active";

	@Column(name = "CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;
	@Column(name = "UPDATE_DATE", updatable = true, insertable = false)
	@UpdateTimestamp
	private LocalDateTime updateDate;

	@Column(name = "CREATED_BY")
	private String createdBy;
	@Column(name = "UPDATED_BY")
	private String updatedBy;
}
