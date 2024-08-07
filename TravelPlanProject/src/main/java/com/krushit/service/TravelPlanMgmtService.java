package com.krushit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krushit.config.AppConfigProperties;
import com.krushit.constatnts.TravelPlanConstants;
import com.krushit.entity.PlanCategory;
import com.krushit.entity.TravelPlan;
import com.krushit.repository.IPlanCategoryRepository;
import com.krushit.repository.ITravelPlanRepository;

@Service
public class TravelPlanMgmtService implements ITravelPlanMgmtService {
	
	@Autowired
	private ITravelPlanRepository travelRepo;
	@Autowired
	private IPlanCategoryRepository planCategoryRepo;
	@Autowired
	private Map<String, String> map;
	
	public TravelPlanMgmtService(AppConfigProperties config) {
		map = config.getMessages();
	}

	@Override
	public String registerTravelPlan(TravelPlan plan) {
		TravelPlan tp = travelRepo.save(plan);
		return tp != null ? map.get(TravelPlanConstants.SAVE_SUCCESS) + tp.getPlanId() : map.get(TravelPlanConstants.SAVE_FALIURE);
	}

	@Override
	public Map<Integer, String> getTrvelPlanCategories() {
		List<PlanCategory> lst = planCategoryRepo.findAll();
		Map<Integer, String> hm = new HashMap<>();
		lst.forEach(category -> {
			hm.put(category.getCategory_id(), category.getCategory_name());
		});

		return hm;
	}

	@Override
	public List<TravelPlan> showAllTravelPlan() {
		return travelRepo.findAll();
	}

	@Override
	public TravelPlan showTravelPlanByID(Integer planID) {
		/*	
		 * Optional<TravelPlan> opt = travelRepo.findById(planID);
		 * if(opt.isPresent()) {
		 * return opt.get();
		 * }
		 * throw new IllegalArgumentException("Sorry!! plan not found");*/
		
		return travelRepo.findById(planID).orElseThrow(()->new IllegalArgumentException(map.get(TravelPlanConstants.FIND_BY_ID_FAILURE))); 
	}

	@Override
	public String updateTravelPlan(TravelPlan plan) {	
	    Optional<TravelPlan> existingPlan = travelRepo.findById(plan.getPlanId());
	    if (existingPlan.isPresent()) {
	        travelRepo.save(plan);
	        return map.get(TravelPlanConstants.UPDATE_SUCCESS) + plan.getPlanId();
	    } else {
	        return map.get(TravelPlanConstants.UPDATE_FAILURE);
	    }
	}

	@Override
	public String deleteTravelPlan(Integer planID) {
		Optional<TravelPlan> plan = travelRepo.findById(planID);
	    if (plan.isPresent()) {
	    	travelRepo.deleteById(planID);
	        return map.get(TravelPlanConstants.DELETE_SUCCESS) + planID;
	    } else {
	        return map.get(TravelPlanConstants.DELETE_FAILURE);
	    }
	}

	@Override
	public String changeTravelPlanStatus(Integer planID, String status) {
		Optional<TravelPlan> opt = travelRepo.findById(planID);
	    if (opt.isPresent()) {
	    	TravelPlan plan = opt.get();
	    	plan.setAvtiveSW(status);
	    	travelRepo.save(plan);
	        return map.get(TravelPlanConstants.STATUS_CHANGE_SUCCESS) + planID;
	    } else {
	        return map.get(TravelPlanConstants.STATUS_CHANGE_FAILURE);
	    }
	}

}
