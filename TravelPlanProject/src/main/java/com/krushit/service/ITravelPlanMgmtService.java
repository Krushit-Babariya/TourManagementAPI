package com.krushit.service;

import java.util.List;
import java.util.Map;

import com.krushit.entity.TravelPlan;

public interface ITravelPlanMgmtService {
	
	public String registerTravelPlan(TravelPlan plan);
	public Map<Integer, String> getTrvelPlanCategories();
	public List<TravelPlan> showAllTravelPlan();
	public TravelPlan showTravelPlanByID(Integer planID);
	public String updateTravelPlan(TravelPlan plan);
	public String deleteTravelPlan(Integer planID);
	public String changeTravelPlanStatus(Integer planID, String status);
}
