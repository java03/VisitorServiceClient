package com.yueqian.VisitorServiceClient.feignClientService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueqian.ticketsMgr_domain_9000.domain.tripMgr.TripVO;

@FeignClient(name = "trip-service", path = "/tripProv/trip")
public interface TripService {
	
		@RequestMapping("/getTrips")
		public List<TripVO> getTrips();

	}


