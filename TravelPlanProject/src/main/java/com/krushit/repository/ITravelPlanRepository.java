package com.krushit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krushit.entity.TravelPlan;

public interface ITravelPlanRepository extends JpaRepository<TravelPlan, Integer> {

}
