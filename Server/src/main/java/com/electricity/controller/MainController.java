package com.electricity.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.electricity.model.Advertisement;
import com.electricity.model.Manager;
import com.electricity.model.Phone;
import com.electricity.service.EleService;

@Controller
public class MainController {
	@Autowired
	private EleService eleService;

	private String projectPath = "/root/apache-tomcat-8.5.29/img/";
	///private String projectPath = "H:/picpath/";
	

	@RequestMapping( value = "/add_advertisements", method = RequestMethod.POST)
	public String  add_advertisements(HttpServletRequest request) {
		
		  MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);    
		  int pageId = Integer.parseInt(params.getParameter("pageId"));
		  Advertisement ad=new Advertisement();
		  ad.setDetail(params.getParameter("details"));
		  ad.setStyle(Integer.parseInt(params.getParameter("style")));
		  ad.setType(Integer.parseInt(params.getParameter("type")));
		  ad.setPageId(pageId);
		  
		  //String projectPath = request.getSession().getServletContext().getRealPath("/")+"img/";
		  //String projectPath="H:\\tools_picture/";
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式		
		  String date  =df.format(new Date());
			//System.out.println(projectPath);
     
		 
     
          MultipartFile photo = ((MultipartHttpServletRequest) request).getFile("photo");
      	  String photo_name = date+"_1.png";
		  String photo_url = projectPath+photo_name;	
		  File file = new File(photo_url);
		  try {
				photo.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  ad.setPhoto(photo_name);
		  if(eleService.get_phone(pageId)!=null&&eleService.getAD(pageId)==null) // getAD
			  eleService.add_advertisements(ad);
		  return "redirect:showAds";
		  
	}
	@RequestMapping( value = "/updatePhone", method = RequestMethod.POST)
	public String  updatePhone(HttpServletRequest request) {
		
		  MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);   
		  
		  double ui_num=Double.parseDouble(params.getParameter("ui_num")),				
				  cpu_num=Double.parseDouble(params.getParameter("cpu_num")),
				  gpu_num=Double.parseDouble(params.getParameter("gpu_num")),
				  ai_num=Double.parseDouble(params.getParameter("ai_num")),
				  rom_num=Double.parseDouble(params.getParameter("rom_num")),
				  front_cam_num=Double.parseDouble(params.getParameter("front_cam_num")),
				  back_cam_num=Double.parseDouble(params.getParameter("back_cam_num")),
				  front_beauty_num=Double.parseDouble(params.getParameter("front_beauty_num")),
				  back_beauty_num=Double.parseDouble(params.getParameter("back_beauty_num")),
				  hand_num=Double.parseDouble(params.getParameter("hand_num")),
				  screen_num=Double.parseDouble(params.getParameter("screen_num")),			 
				  consume_power_num=Double.parseDouble(params.getParameter("consume_power_num")),
				  charge_num=Double.parseDouble(params.getParameter("charge_num")),
				  special_num=Double.parseDouble(params.getParameter("special_num"));
		  int  battery=Integer.parseInt(params.getParameter("battery"));
		  int different_price=Integer.parseInt(params.getParameter("different_price"));
		  Phone p=new Phone();
		  p.setId(Integer.parseInt(params.getParameter("id")));
		  p.setName(params.getParameter("name"));
		  p.setBrand(params.getParameter("brand"));
		  p.setSize(Double.parseDouble(params.getParameter("size")));
		  p.setUi(params.getParameter("ui"));
		 
		  p.setUi_num(ui_num);
		 
		  p.setCpu(params.getParameter("cpu"));
		  p.setCpu_num(cpu_num);
		  p.setGpu_num(gpu_num); 
		  p.setAi_num(ai_num);
		  p.setRom(params.getParameter("rom"));
		  p.setRom_num(rom_num); 
		  p.setFront_cam(params.getParameter("front_cam"));
		  p.setFront_cam_num(front_cam_num); 
		  p.setBack_cam(params.getParameter("back_cam"));	  
		  p.setBack_cam_num(back_cam_num);
		  p.setFront_beauty(params.getParameter("front_beauty"));
		  p.setFront_beauty_num(front_beauty_num);		  
		  p.setBack_beauty(params.getParameter("back_beauty"));
		  p.setBack_beauty_num(back_beauty_num);
		  p.setHand_num(hand_num);  
		  p.setScreen(params.getParameter("screen"));
		  p.setScreen_num(screen_num); 
		  p.setBattery(battery);
		  p.setConsume_power(params.getParameter("consume_power"));
		  p.setConsume_power_num(consume_power_num); 
		  p.setCharge(params.getParameter("charge"));
		  p.setCharge_num(charge_num);		  
		  p.setSpecial(params.getParameter("special"));
		  p.setSpecial_num(special_num);
		  p.setType1(params.getParameter("type1"));
		  p.setPrice1(Integer.parseInt(params.getParameter("price1")));
		  p.setOfficialShopLink(params.getParameter("officialLink"));
		  p.setCheapestShopLink(params.getParameter("cheapestLink"));
		  p.setSelf_comment(params.getParameter("self_comment"));
		  p.setClick_num(Integer.parseInt(params.getParameter("click_num")));
		  p.setDifferent_price(different_price);

		  
		  p.setSum_score(ui_num+cpu_num+gpu_num+ai_num+rom_num+front_cam_num+back_cam_num+
				  front_beauty_num+back_beauty_num+hand_num+screen_num+consume_power_num+charge_num+special_num+battery/100);
		  if(!params.getParameter("type2").equals("")&&params.getParameter("price2")!=null){
			//  System.out.println("--------------type2:-"+params.getParameter("type2"));
			  p.setType2(params.getParameter("type2"));
			  p.setPrice2(Integer.parseInt(params.getParameter("price2")));
		  }
		  if(!params.getParameter("type3").equals("")&&params.getParameter("price3")!=null){
			//  System.out.println("--------------type3-"+params.getParameter("type3"));
			  p.setType3(params.getParameter("type3"));
			  p.setPrice3(Integer.parseInt(params.getParameter("price3")));
		  }
		  
		  
		//  String projectPath = request.getSession().getServletContext().getRealPath("/")+"img/";
		 // String projectPath = request.getSession().getServletContext().getRealPath("/")+"img/";
		  
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式		
		  String date  =df.format(new Date());
			//System.out.println(projectPath);
      /*        
          String photo1_name = date+"_1.png";
          String photo1_url = projectPath+photo1_name;	
          File file1 = new File(photo1_url);
          picture1.transferTo(file1);
          p.setPicture(photo1_name);*/
		 
          try {
        	MultipartFile picture1_name = ((MultipartHttpServletRequest) request).getFile("picture1_name");
        	MultipartFile picture2_name = ((MultipartHttpServletRequest) request).getFile("picture2_name");
        	MultipartFile picture3_name = ((MultipartHttpServletRequest) request).getFile("picture3_name");
        	MultipartFile picture4_name = ((MultipartHttpServletRequest) request).getFile("picture4_name");
        	MultipartFile picture5_name = ((MultipartHttpServletRequest) request).getFile("picture5_name");
        	
        	
  			/*String photo1_name = date+"_1.png";
  			String photo1_url = projectPath+photo1_name;	
  			File file1 = new File(photo1_url);
  			picture1_name.transferTo(file1);
  			p.setPicture(photo1_name);*/
        	Phone phone  = eleService.get_phone(Integer.parseInt(params.getParameter("id")));
  			if(picture1_name.getOriginalFilename().equals("")){
  				
  				p.setPicture(phone.getPicture());
  			}else{
  				
  				String photo1_name = date+"_1.png";
  	  			String photo1_url = projectPath+photo1_name;	
  	  			File file1 = new File(photo1_url);
  	  			picture1_name.transferTo(file1);
  	  			p.setPicture(photo1_name);
  			}

  			if(picture2_name.getOriginalFilename().equals("")){
  				
  				p.setPicture2(phone.getPicture2());
  			}else{
  				
  				String photo2_name = date+"_2.png";
  				String photo2_url = projectPath+photo2_name;
  				File file2 = new File(photo2_url);
  				picture2_name.transferTo(file2);
  				p.setPicture2(photo2_name);
  			}
  			
  			if(picture3_name.getOriginalFilename().equals("")){
  				
  				p.setPicture3(phone.getPicture3());
  			}else{
  				
  				String photo3_name = date+"_3.png";
  				String photo3_url = projectPath+photo3_name;
  				File file3 = new File(photo3_url);
  				picture3_name.transferTo(file3);
  				p.setPicture3(photo3_name);
  			}
  			if(picture4_name.getOriginalFilename().equals("")){
  				
  				p.setPicture4(phone.getPicture4());
  			}else{
  				
  				String photo4_name = date+"_4.png";
  				String photo4_url = projectPath+photo4_name;
  				File file4 = new File(photo4_url);
  				picture4_name.transferTo(file4);
  				p.setPicture4(photo4_name);
  			}
  			if(picture5_name.getOriginalFilename().equals("")){
  				
  				p.setPicture5(phone.getPicture5());
  			}else{
  				
  				String photo5_name = date+"_5.png";
  				String photo5_url = projectPath+photo5_name;
  				File file5 = new File(photo5_url);
  				picture5_name.transferTo(file5);
  				p.setPicture5(photo5_name);
  			}
  			
  		
  	        eleService.update_phone(p);
  	        
  	        
  		} catch (IllegalStateException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
          
         
        
       //   BufferedOutputStream stream = null;      
		
		return "redirect:showPhone";
	}
	
	
	@RequestMapping( value = "/add_phone", method = RequestMethod.POST)
	public String  add_phone(HttpServletRequest request) {
		
		  MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);    
		  if(eleService.getphone_byname(  params.getParameter("name"))==null) {
		
		  Phone p=new Phone();
		  
		  double ui_num=Double.parseDouble(params.getParameter("ui_num")),			 
				  cpu_num=Double.parseDouble(params.getParameter("cpu_num")),
				  gpu_num=Double.parseDouble(params.getParameter("gpu_num")),
				  ai_num=Double.parseDouble(params.getParameter("ai_num")),
				  rom_num=Double.parseDouble(params.getParameter("rom_num")),
				  front_cam_num=Double.parseDouble(params.getParameter("front_cam_num")),
				  back_cam_num=Double.parseDouble(params.getParameter("back_cam_num")),
				  front_beauty_num=Double.parseDouble(params.getParameter("front_beauty_num")),
				  back_beauty_num=Double.parseDouble(params.getParameter("back_beauty_num")),
				  hand_num=Double.parseDouble(params.getParameter("hand_num")),
				  screen_num=Double.parseDouble(params.getParameter("screen_num")),			 
				  consume_power_num=Double.parseDouble(params.getParameter("consume_power_num")),
				  charge_num=Double.parseDouble(params.getParameter("charge_num")),
				  special_num=Double.parseDouble(params.getParameter("special_num"));
		  int  battery=Integer.parseInt(params.getParameter("battery"));
		  int different_price=Integer.parseInt(params.getParameter("different_price"));
		
		
		  p.setName(params.getParameter("name"));
		  p.setBrand(params.getParameter("brand"));
		  p.setSize(Double.parseDouble(params.getParameter("size")));
		  p.setUi(params.getParameter("ui"));
		 
		  p.setUi_num(ui_num);
		  p.setCpu(params.getParameter("cpu"));
		  p.setCpu_num(cpu_num);
		  p.setGpu_num(gpu_num); 
		  p.setAi_num(ai_num);
		  p.setRom(params.getParameter("rom"));
		  p.setRom_num(rom_num); 
		  p.setFront_cam(params.getParameter("front_cam"));
		  p.setFront_cam_num(front_cam_num); 
		  p.setBack_cam(params.getParameter("back_cam"));	  
		  p.setBack_cam_num(back_cam_num);
		  p.setFront_beauty(params.getParameter("front_beauty"));
		  p.setFront_beauty_num(front_beauty_num);		  
		  p.setBack_beauty(params.getParameter("back_beauty"));
		  p.setBack_beauty_num(back_beauty_num);
		  p.setHand_num(hand_num);  
		  p.setScreen(params.getParameter("screen"));
		  p.setScreen_num(screen_num); 
		  p.setBattery(battery);
		  p.setConsume_power(params.getParameter("consume_power"));
		  p.setConsume_power_num(consume_power_num); 
		  p.setCharge(params.getParameter("charge"));
		  p.setCharge_num(charge_num);		  
		  p.setSpecial(params.getParameter("special"));
		  p.setSpecial_num(special_num);
		  p.setType1(params.getParameter("type1"));
		  p.setPrice1(Integer.parseInt(params.getParameter("price1")));
		  p.setOfficialShopLink(params.getParameter("officialLink"));
		  p.setCheapestShopLink(params.getParameter("cheapestLink"));
		  p.setSelf_comment(params.getParameter("self_comment"));
		  p.setDifferent_price(different_price);
		  p.setSum_score(ui_num+cpu_num+gpu_num+ai_num+rom_num+front_cam_num+back_cam_num+
				  front_beauty_num+back_beauty_num+hand_num+screen_num+consume_power_num+charge_num+special_num+battery/100);
		  p.setClick_num(0);
		  if(!params.getParameter("type2").equals("")&&params.getParameter("price2")!=null){
			//  System.out.println("--------------type2:-"+params.getParameter("type2"));
			  p.setType2(params.getParameter("type2"));
			  p.setPrice2(Integer.parseInt(params.getParameter("price2")));
		  }
		  if(!params.getParameter("type3").equals("")&&params.getParameter("price3")!=null){
			//  System.out.println("--------------type3-"+params.getParameter("type3"));
			  p.setType3(params.getParameter("type3"));
			  p.setPrice3(Integer.parseInt(params.getParameter("price3")));
		  }
		  
		  
		  //String projectPath = request.getSession().getServletContext().getRealPath("/")+"img/";
		  //String projectPath = request.getServletContext().getRealPath("templates/images/");
		 // String projectPath ="H:\\picpath\\";
		  
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式		
		  String date  =df.format(new Date());
			//System.out.println(projectPath);
      /*        
          String photo1_name = date+"_1.png";
          String photo1_url = projectPath+photo1_name;	
          File file1 = new File(photo1_url);
          picture1.transferTo(file1);
          p.setPicture(photo1_name);*/
		 
          try {
        	MultipartFile picture1_name = ((MultipartHttpServletRequest) request).getFile("picture1_name");
        	MultipartFile picture2_name = ((MultipartHttpServletRequest) request).getFile("picture2_name");
        	MultipartFile picture3_name = ((MultipartHttpServletRequest) request).getFile("picture3_name");
        	MultipartFile picture4_name = ((MultipartHttpServletRequest) request).getFile("picture4_name");
        	MultipartFile picture5_name = ((MultipartHttpServletRequest) request).getFile("picture5_name");
        	
        	
  			String photo1_name = date+"_1.png";
  			String photo1_url = projectPath+photo1_name;	
  			File file1 = new File(photo1_url);
  			picture1_name.transferTo(file1);
  			p.setPicture(photo1_name);
  			
  			if(picture2_name.getOriginalFilename().equals("")){
  				
  				p.setPicture2("");
  			}else{
  				
  				String photo2_name = date+"_2.png";
  				String photo2_url = projectPath+photo2_name;
  				File file2 = new File(photo2_url);
  				picture2_name.transferTo(file2);
  				p.setPicture2(photo2_name);
  			}
  			
  			if(picture3_name.getOriginalFilename().equals("")){
  				
  				p.setPicture3("");
  			}else{
  				
  				String photo3_name = date+"_3.png";
  				String photo3_url = projectPath+photo3_name;
  				File file3 = new File(photo3_url);
  				picture3_name.transferTo(file3);
  				p.setPicture3(photo3_name);
  			}
  			if(picture4_name.getOriginalFilename().equals("")){
  				
  				p.setPicture4("");
  			}else{
  				
  				String photo4_name = date+"_4.png";
  				String photo4_url = projectPath+photo4_name;
  				File file4 = new File(photo4_url);
  				picture4_name.transferTo(file4);
  				p.setPicture4(photo4_name);
  			}
  			if(picture5_name.getOriginalFilename().equals("")){
  				
  				p.setPicture5("");
  			}else{
  				
  				String photo5_name = date+"_5.png";
  				String photo5_url = projectPath+photo5_name;
  				File file5 = new File(photo5_url);
  				picture5_name.transferTo(file5);
  				p.setPicture5(photo5_name);
  			}
  			
  			/* System.out.println(photo1_name);
  	          System.out.println(p.getPicture());
  	          System.out.println(p.getPicture2());*/
  	        eleService.add_phone(p);
  	        
  	        
  		} catch (IllegalStateException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
          
         
		}
       //   BufferedOutputStream stream = null;      
		
		return "redirect:showPhone";
	}
	
	
	
	@RequestMapping(value = "/getpassword", method=RequestMethod.POST)
	 public void getpassword(HttpSession session,HttpServletResponse response,String username,String password){
		boolean bol=false;
		
		try {
			
			response.setCharacterEncoding("utf-8");
			PrintWriter out=response.getWriter();
			
			
			Manager m=eleService.get_password(username);
			if(m==null||!m.getPassword().equals(password)){
				
				bol=false;
				
			}else if(m.getPassword().equals(password)){
				bol=true;
				session.setAttribute("username", username);
				
			}
			out.print(bol);
			out.flush();
			out.close();
		
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}
