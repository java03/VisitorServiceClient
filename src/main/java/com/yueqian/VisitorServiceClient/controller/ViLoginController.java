package com.yueqian.VisitorServiceClient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueqian.VisitorServiceClient.commom.Constants;
import com.yueqian.VisitorServiceClient.feignClientService.CustomerFeignClientService;
import com.yueqian.VisitorServiceClient.feignClientService.RouteFeignService;
import com.yueqian.ticketsMgr_domain_9000.domain.trip.Customer;
import com.yueqian.ticketsMgr_domain_9000.domain.wayMgr.Station;

@Controller
@RequestMapping("/cus")
public class ViLoginController { 
	@Resource
	private CustomerFeignClientService cusService;
	@Resource
	private RouteFeignService routeService;
	
	@RequestMapping("/loginPage")
	public String loginPage() {
		
		return "VisitorLogin";
	}
	@RequestMapping("/login")
	public String Login(Customer customer, ModelMap mm, HttpSession session) {
		Customer logUser =cusService.cusLogin(customer);
		session.setAttribute("logUser", logUser);
		if(logUser == null) {
			mm.addAttribute(Constants.ERROR_MESSAGE_KEY,"账号或密码有误！请输入完整！");
			return "VisitorLogin";
		}
		return "redirect:/cus/getAllStation";
	}
	
	
	@RequestMapping("/registerPage")
	public String registerPage() {
		return "VisitorRegister";
	}
	@RequestMapping("/register")
	public String register(Customer customer, ModelMap mm) {
		String result = cusService.regEmp(customer);
		if("success".equals(result)) {
			return "redirect:/cus/loginPage";
		}else if("exists".equals(result)) {
			mm.addAttribute(Constants.ERROR_MESSAGE_KEY, "该账号已注册，请直接登录");
			return "VisitorLogin";
		}else {
			mm.addAttribute(Constants.ERROR_MESSAGE_KEY, "系统繁忙，注册失败，请稍后重试！");
		}
		return "VisitorRegister";
	}
	
	@RequestMapping("/changerInfoPage")
	public String changeInforPage(ModelMap mm,HttpSession session) {
		Customer cus=(Customer)session.getAttribute("logUser");
		if(cus == null) {
			mm.addAttribute(Constants.ERROR_MESSAGE_KEY, "请先登录");
			return "VisitorLogin";
		}
		mm.addAttribute("cus", cus);
		return "VisitorChangeInfor";
	}
	@RequestMapping("/changerInfo")
	public String changeInfor(Customer customer,ModelMap mm) {
		String result = cusService.changeInfo(customer);
		if("success".equals(result)) {
			return "redirect:/cus/bookTicketsPage";
			}else {
				mm.addAttribute(Constants.ERROR_MESSAGE_KEY, "系统繁忙，更新失败，请稍后重试！");
			}
		return "redirect:/cus/changerInfoPage";
		
	}
	
	@RequestMapping("/changerPwdPage")
	public String changerPwdPag(ModelMap mm,HttpSession session) {
		Customer cust=(Customer)session.getAttribute("logUser");
		mm.addAttribute("cust", cust);
		return "VisitorChangePassw";
	}
	@RequestMapping("/changePwd")
	public String ChangePwd(Customer customer,ModelMap mm) {
		String result = cusService.changePwd(customer);
		if("success".equals(result)) {
			return "redirect:/cus/loginPage";
			}else {
		mm.addAttribute(Constants.ERROR_MESSAGE_KEY, "系统繁忙，密码修改失败，请稍后重试！");
			}
		return "VisitorChangePassw";
	}
	
	
	@RequestMapping("/getAllStation")
	public String getStation(ModelMap mm){
		Map<String, List<Station>> map=routeService.getStations();
		List<Station> list=new ArrayList<>();
		for(List<Station> stations : map.values()){
			for(Station station:stations) {
				list.add(station);
			}
		}
		mm.addAttribute("stations", list);

		return "VisitorBooTickets";
	}
	@RequestMapping("/bookTicketsPage")
	public String BooTickets(HttpSession session,ModelMap mm) {
		return "VisitorBooTickets";
	}
	
	@RequestMapping("/rereatPage")
	public String RereatTickets() {
		return "VisitoRereatTickets";
	}
}
