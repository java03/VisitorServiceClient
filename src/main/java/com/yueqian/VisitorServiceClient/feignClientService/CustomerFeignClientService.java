package com.yueqian.VisitorServiceClient.feignClientService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueqian.ticketsMgr_domain_9000.domain.trip.Customer;

@FeignClient(name = "visitor-service", path = "/VisitorServiceProvider/cus")
public interface CustomerFeignClientService {
	@RequestMapping("/register")
	public String regEmp(Customer customer);
	@RequestMapping("/login")
	public Customer cusLogin(Customer customer);
	@RequestMapping("/changeInfo")
	public String changeInfo(Customer customer); 
	@RequestMapping("/changePwd")
	public String changePwd(Customer customer);
	@RequestMapping("/showInfo")
	public Customer showInfo(String mobile);
}
