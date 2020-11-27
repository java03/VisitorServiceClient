package com.yueqian.VisitorServiceClient.feignClientService;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueqian.VisitorServiceClient.feignClientService.impl.RouteFeignServiceImpl;
import com.yueqian.ticketsMgr_domain_9000.domain.wayMgr.Station;

@FeignClient(name="way-service",path= "/sysWayProvider",fallback=RouteFeignServiceImpl.class)
public interface RouteFeignService {
	@RequestMapping("/station/getStations")
	public Map<String, List<Station>> getStations();
}
