package com.electricity.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.electricity.model.Advertisement;
import com.electricity.model.Collection;
import com.electricity.model.Comment;
import com.electricity.model.HotSearch;
import com.electricity.model.Phone;
import com.electricity.model.User;
import com.electricity.service.AppService;
import com.electricity.service.EleService;

import net.sf.json.JSONArray;


@Controller
public class AndroidController {

	@Autowired
	private EleService eleService;
	@Autowired
	private AppService appservice;
	private String projectPath = "/root/apache-tomcat-8.5.29/img2/";
	
	/*public String addComment(Comment c);
	public List<Comment> get_phoneComment(int productId,String type);
	public List<Comment> get_userComment(String  username,String type);
	public String delete_Comment(int id);
	public String delete_collection(int id);
	*/
	@RequestMapping(value = "/setApp_addComment", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  setApp_addComment(String username,int productId,String type,String content,String productname){
		Comment c=new Comment();
		c.setUsername(username);
		c.setProductId(productId);
		c.setType(type);
		c.setContent(content);
		c.setProduct_name(productname);
		User u = appservice.getuserbyusername(username);
		c.setNickname(u.getNickname());	
		c.setImgname(u.getImgname());
		
        return appservice.addComment(c);
	}
	@RequestMapping(value = "/setApp_deleteComment", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody  String  setApp_deleteComment(int id){
		
        return appservice.delete_Comment(id);
	}
	@RequestMapping(value = "/setApp_deleteCollection", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  setApp_deleteCollection(String username,String type,int product_id){
		
        return appservice.delete_collection(username, type, product_id);
	}
	@RequestMapping(value = "/getApp_phoneComment", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_phoneComment(int productId,String type,int index){
		List<Comment> list = appservice.get_phoneComment(productId,type,index);		
		String json = null ;
		json =JSONArray.fromObject(list).toString();			
        return json;	
	}
	@RequestMapping(value = "/getApp_userComment", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_userComment(String username,String type,int index){
		List<Comment> list = appservice.get_userComment(username,type,index);		
		String json = null ;
		json =JSONArray.fromObject(list).toString();			
        return json;	
	}
	
	
	@RequestMapping(value = "/getApp_Collection", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_Collection(String username){
		List<Phone> list = appservice.get_collection(username);		
		String json = null ;
		json =JSONArray.fromObject(list).toString();			
        return json;	
	}
	@RequestMapping(value = "/setApp_Collection", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  setApp_Collection(String username,int product_id,String type){
		Collection c=new Collection();
		c.setUsername(username);
		c.setProduct_id(product_id);
		c.setType(type);
				
        return appservice.add_collection(c);
	}
	@RequestMapping(value = "/getApp_password", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String getApp_password(String username,String password){
	
		User u= appservice.login(username, password);
		if(u!=null) {
			
		
	        return JSONArray.fromObject(u).toString();
			
		}else {
			 return "fail";
		}
       
		
	}

	@RequestMapping(value = "/getApp_register", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_register(String username,String password){
	
		User u=new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setNickname(username);
		u.setType("common");
		u.setImgname("");
		String value= appservice.register(u);
        return value;
		
	}
	@RequestMapping(value = "/setApp_nickname", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  setApp_nickname(String username,String nickname){		
	
		User u=appservice.getuserbyusername(username);
		u.setNickname(nickname);
		//System.out.println(u.getId()+"---"+u.getUsername()+"---"+u.getNickname());
		appservice.update_user(u);
		return "success";
	}
	
	@RequestMapping(value = "/setApp_picture",  method = RequestMethod.POST)  
	public @ResponseBody String  setApp_picture(HttpServletRequest request){		
		MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);   
		String username =params.getParameter("username");
		User u=appservice.getuserbyusername(username);
		MultipartFile picture = ((MultipartHttpServletRequest) request).getFile("picture");
		String photo_name=username+".png";
		u.setImgname(photo_name);
		
		String photo_url = projectPath+photo_name;
		  File file = new File(photo_url);
		  try {
			  picture.transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//System.out.println(u.getId()+"---"+u.getUsername()+"---"+u.getNickname());
		appservice.update_user(u);
		return "success";
	}
	
	
	
	
	@RequestMapping(value = "/getApp_PhoneRecommand", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_Test(double gpu_rate,double front_cam_rate,double back_cam_rate,double size_rate,
			double front_beauty_rate,double back_beauty_rate,double hand_rate,double screan_rate,double battery_rate,
			double charge_rate,String  must_brand,String  must_specail,int index){
	
		List<Phone> list = appservice.get_phonerecommand(gpu_rate, front_cam_rate, back_cam_rate, size_rate, front_beauty_rate, back_beauty_rate, hand_rate, screan_rate, battery_rate, charge_rate, must_brand, must_specail, index);
		
		String json = null ;
		json =JSONArray.fromObject(list).toString();
			
	
        return json;
		
	}
	
	
	@RequestMapping(value = "/getApp_Ad", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_Ad(int type){
	
		List<Advertisement> list = appservice.getAdvertisementsBytype(type);
		
		String json = null ;
		json =JSONArray.fromObject(list).toString();
			
	
        return json;
		
	}
	
	
	@RequestMapping(value = "/getApp_Search", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_Search(String value){
	
		
		List<Phone> list=eleService.getPhonebySearch(value);
		//	System.out.println(value+"——————————");
			//p = eleService.showPhones();
			
		
			
		String json = null ;
		json =JSONArray.fromObject(list).toString();
			
	
        return json;
		
	}
	
	@RequestMapping(value = "/getApp_Phone", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_Phone(int index){
	
		List<Phone> list = appservice.getPhone(index);
		
		String json = null ;
		json =JSONArray.fromObject(list).toString();
			
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_HotSearch", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_HotSearch(){
	
		
		List<HotSearch> list=eleService.showHotSearch();
		//	System.out.println(value+"——————————");
			//p = eleService.showPhones();
		
		String json = null ;
		json =JSONArray.fromObject(list).toString();
		
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneDetails", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneDetails(int id){
	
		 Phone p= eleService.get_phone(id);
		 List<Phone> list=new ArrayList<Phone>();
		 list.add(p);
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
		 p.setClick_num(p.getClick_num()+1);
		 appservice.add_clicknum(p);
		 
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneTotal_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneTotalRanking(int index){
	
		List<Phone> list= appservice.getTotal_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneCommon_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  Common_Ranking(int index){
	
		List<Phone> list= appservice.getCommon_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	//性价比排

	@RequestMapping(value = "/getApp_PhoneGame_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneGame_Ranking(int index){
	
		List<Phone> list= appservice.getGame_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneCamera_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneCamera_Ranking(int index){
	
		List<Phone> list= appservice.getCamera_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneXuHang_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneXuHang_Ranking(int index){
	
		List<Phone> list= appservice.getXuHang_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}


	

	@RequestMapping(value = "/getApp_PhoneTotal_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumTotal_Ranking(int index){
	
		List<Phone> list= appservice.getSumTotal_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneCommon_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumCommon_Ranking(int index){
	
		List<Phone> list= appservice.getSumCommon_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneGame_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumGame_Ranking(int index){
	
		List<Phone> list= appservice.getSumGame_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneCamera_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumCamera_Ranking(int index){
	
		List<Phone> list= appservice.getSumCamera_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneXuHang_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumXuHang_Ranking(int index){
	
		List<Phone> list= appservice.getSumXuHang_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	
	
	@RequestMapping(value = "/getApp_PhoneXiaomi_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneXiaomi_Ranking(int index){
	
		List<Phone> list= appservice.getXiaomi_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneHonor_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneHonor_Ranking(int index){
	
		List<Phone> list= appservice.getHonor_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneHuawei_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneHuawei_Ranking(int index){
	
		List<Phone> list= appservice.getHuawei_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneIPhone_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneIPhone_Ranking(int index){
	
		List<Phone> list= appservice.getIPhone_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneOppo_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneOppo_Ranking(int index){
	
		List<Phone> list= appservice.getOppo_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneVivo_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneVivo_Ranking(int index){
	
		List<Phone> list= appservice.getVivo_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneMeizu_Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneMeizu_Ranking(int index){
	
		List<Phone> list= appservice.getMeizu_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	
	
	@RequestMapping(value = "/getApp_PhoneXiaomi_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumXiaomi_Ranking(int index){
	
		List<Phone> list= appservice.getSumXiaomi_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneHonor_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumHonor_Ranking(int index){
	
		List<Phone> list= appservice.getSumHonor_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneHuawei_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumHuawei_Ranking(int index){
	
		List<Phone> list= appservice.getSumHuawei_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneIPhone_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumIPhone_Ranking(int index){
	
		List<Phone> list= appservice.getSumIPhone_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneOppo_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumOppo_Ranking(int index){
	
		List<Phone> list= appservice.getSumOppo_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneVivo_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumVivo_Ranking(int index){
	
		List<Phone> list= appservice.getSumVivo_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneMeizu_Ranking_All", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneSumMeizu_Ranking(int index){
	
		List<Phone> list= appservice.getSumMeizu_Ranking(index);
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	


	@RequestMapping(value = "/getApp_PhoneXiaomi_4Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneXiaomi_4Ranking(){
	
		List<Phone> list= appservice.getXiaomi_4Ranking();
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneHonor_4Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneHonor_4Ranking(){
	
		List<Phone> list= appservice.getHonor_4Ranking();
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneHuawei_2Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneHuawei_2Ranking(){
	
		List<Phone> list= appservice.getHuawei_2Ranking();
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneIPhone_2Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneIPhone_2Ranking(){
	
		List<Phone> list= appservice.getIPhone_2Ranking();
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneOppo_2Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneOppo_2Ranking(){
	
		List<Phone> list= appservice.getOppo_2Ranking();
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneVivo_2Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneVivo_2Ranking(){
	
		List<Phone> list= appservice.getVivo_2Ranking();
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
		
	}
	@RequestMapping(value = "/getApp_PhoneMeizu_2Ranking", method = RequestMethod.GET, produces = "application/x-www-form-urlencoded;charset=UTF-8")  
	public @ResponseBody String  getApp_PhoneMeizu_2Ranking(){
	
		List<Phone> list= appservice.getMeizu_2Ranking();
		
		 String json = null ;
		 json =JSONArray.fromObject(list).toString();
	
        return json;
	}


}
		
	//品牌首页
/*    gpu_rate = Double.parseDouble(intent.getStringExtra("gpu_rate"));//获取键值对的键名
front_cam_rate = Double.parseDouble(intent.getStringExtra("front_cam_rate"));
back_cam_rate = Double.parseDouble(intent.getStringExtra("back_cam_rate"));
size_rate = Double.parseDouble(intent.getStringExtra("size_rate"));
front_beauty_rate = Double.parseDouble(intent.getStringExtra("front_beauty_rate"));
back_beauty_rate = Double.parseDouble(intent.getStringExtra("back_beauty_rate"));
hand_rate = Double.parseDouble(intent.getStringExtra("hand_rate"));
screan_rate = Double.parseDouble(intent.getStringExtra("screan_rate"));
battery_rate = Double.parseDouble(intent.getStringExtra("battery_rate"));
charge_rate = Double.parseDouble(intent.getStringExtra("charge_rate"));
must_brand=intent.getStringExtra("must_brand");
must_specail=intent.getStringExtra("must_specail");*/
