package com.electricity.service;

import java.util.List;

import com.electricity.model.Advertisement;
import com.electricity.model.Collection;
import com.electricity.model.Comment;
import com.electricity.model.Phone;
import com.electricity.model.User;

public interface AppService {
	
//评价
	public String addComment(Comment c);
	public List<Comment> get_phoneComment(int productId,String type,int index);
	public List<Comment> get_userComment(String  username,String type,int index);
	public String delete_Comment(int id);
//登入注册
	public User login(String username,String password);
	public String register(User u);
	public void update_user(User u);
	public User getuser(int id);
	public User getuserbyusername(String username);
//收藏
	public List<Phone> get_collection(String username);
	public String add_collection(Collection c);
	public String delete_collection(String username,String type,int product_id);
	public List<Advertisement> getAdvertisementsBytype(int type);
	public List<Phone> getPhone(int index);
	public void add_clicknum(Phone p);
//性价比排行
	public List<Phone> getTotal_Ranking(int index);
	public List<Phone> getCommon_Ranking(int index);
	public List<Phone> getGame_Ranking(int index);
	public List<Phone> getCamera_Ranking(int index);
	public List<Phone> getXuHang_Ranking(int index);
//总体排行	
	public List<Phone> getSumTotal_Ranking(int index);
	public List<Phone> getSumCommon_Ranking(int index);
	public List<Phone> getSumGame_Ranking(int index);
	public List<Phone> getSumCamera_Ranking(int index);
	public List<Phone> getSumXuHang_Ranking(int index);
//品牌性价比
	public List<Phone> getXiaomi_Ranking(int index);
	public List<Phone> getHonor_Ranking(int index);
	public List<Phone> getHuawei_Ranking(int index);
	public List<Phone> getIPhone_Ranking(int index);
	public List<Phone> getOppo_Ranking(int index);
	public List<Phone> getVivo_Ranking(int index);
	public List<Phone> getMeizu_Ranking(int index);	
//品牌总排名	
	public List<Phone> getSumXiaomi_Ranking(int index);
	public List<Phone> getSumHonor_Ranking(int index);
	public List<Phone> getSumHuawei_Ranking(int index);
	public List<Phone> getSumIPhone_Ranking(int index);
	public List<Phone> getSumOppo_Ranking(int index);
	public List<Phone> getSumVivo_Ranking(int index);
	public List<Phone> getSumMeizu_Ranking(int index);		
	
//品牌首页
	public List<Phone> getXiaomi_4Ranking();
	public List<Phone> getHonor_4Ranking();
	public List<Phone> getHuawei_2Ranking();	
	public List<Phone> getIPhone_2Ranking();
	public List<Phone> getOppo_2Ranking();
	public List<Phone> getVivo_2Ranking();
	public List<Phone> getMeizu_2Ranking();
//测试	
	public List<Phone> get_phonerecommand(double gpu_rate,double front_cam_rate,double back_cam_rate,double size_rate,
			double front_beauty_rate,double back_beauty_rate,double hand_rate,double screan_rate,double battery_rate,
			double charge_rate,String  must_brand,String  must_specail,int index);	
}
