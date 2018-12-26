package com.electricity.controller;




import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.electricity.model.Advertisement;
import com.electricity.model.HotSearch;
import com.electricity.model.Manager;
import com.electricity.model.Phone;
import com.electricity.service.EleService;




@Controller
public class EleController {
	

	@Autowired
	private EleService eleService;

	@RequestMapping("/login")
	public String login() {
	       // map.put("hello", "欢迎进入HTML页面");
	    return "login";
	}
	
	
	@RequestMapping("/addManager")
	public String addManager(ModelMap map) {
	    map.put("manager", new Manager());
	    return "addManager";
	}

	@RequestMapping("/addHotSearch")
	public String addHotSearch() {
	   
	    return "add_HotSearch";
	}
	
	@RequestMapping("/addPhone")
	public String addPhone(ModelMap map) {
	//    map.put("phone", new Phone());
	    return "addPhone";
	}
	@RequestMapping(value = "/showPhone")//, method = RequestMethod.POST
	public String showPhone(Model model, String value) {
		List<Phone> p=new ArrayList<>();
	//	System.out.println(value+"——————————");
		//p = eleService.showPhones();
		if(value==null)
			 p = eleService.showPhones();
		else
			p = eleService.getPhonebySearch(value);
		model.addAttribute("phone", p);
		return "show_phone";
	}
	
	
	@RequestMapping(value = "/showManager", method = RequestMethod.GET)
	public String showManager(Model model) {
		List<Manager> m = eleService.showManagers();
		
		model.addAttribute("manager", m);
		return "show_manager";
	}
	@RequestMapping(value = "/showHotSearch", method = RequestMethod.GET)
	public String showHotSearch(Model model) {
		List<HotSearch> h = eleService.showHotSearch();
		
		model.addAttribute("HotSearch", h);
		return "show_HotSearch";
	}
	@RequestMapping(value = "/showAds", method = RequestMethod.GET)
	public String showAds(Model model) {
		List<Advertisement> ads = eleService.show_Ads();
		
		model.addAttribute("ads", ads);
		return "show_Ads";
	}
	
	
	@RequestMapping("/add_manager")
	public String  add_manager(@ModelAttribute Manager m){
		//System.out.println(m.getUsername()+"*******************");
		if(m.getUsername()!=null)
			eleService.add_manager(m);
		
	
		return "redirect:showManager";
	}
	@RequestMapping("/add_HotSearch")
	public String  add_HotSearch(String value){
		 
		HotSearch h = null;
		if(eleService.get_value(value)==null) {
			 h =new HotSearch();
			 h.setValue(value);
			eleService.add_HotSearch(h);
		}
			
		
	
		return "redirect:showHotSearch";
	}
	
	@RequestMapping("/delete_phone")
	public String  delete_phone(int id){
		//System.out.println(id+"*******************");
		eleService.deletPhone(id);
		
	
		return "redirect:showPhone";
	}
	@RequestMapping("/delete_manager")
	public String  delete_user(int id){
		//System.out.println(id+"*******************");
		eleService.deleteManager(id);	
	
		return "redirect:showManager";
	}
	@RequestMapping("/delete_HotSearch")
	public String  delete_HotSearch(int id){
		
		eleService.deleteHotSearch(id);	
	
		return "redirect:showHotSearch";
	}
	
	@RequestMapping("/delete_ads")
	public String  delete_ads(int id){
	//	System.out.println(id+"__________________________-");
		eleService.deleteAdvertisement(id);
	
		return "redirect:showAds";
	}
	
	@RequestMapping("/update_phone")
	public String  update_phone(Model model,int id){
	
		Phone p  = eleService.get_phone(id);
			
		model.addAttribute("p", p);
		return "update_phone";
	}
	@RequestMapping(value = "/get_phone", method = RequestMethod.POST)	
	@ResponseBody
	public String  get_phone(int id){
		
		 String name ="";
		 if(eleService.get_phone(id)!=null)
			 name= eleService.get_phone(id).getName();
			
	
		return name;
	}
	
	@RequestMapping("/addAdvertisements")
	public String addAdvertisements(ModelMap map) {
	   
	    return "addAdvertisements";
	}
}
