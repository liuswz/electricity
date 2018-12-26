package com.electricity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.electricity.dao.AdvertisementDao;
import com.electricity.dao.CollectionDao;
import com.electricity.dao.CommentDao;
import com.electricity.dao.PhoneDao;
import com.electricity.dao.UserDao;
import com.electricity.model.Advertisement;
import com.electricity.model.Collection;
import com.electricity.model.Comment;
import com.electricity.model.Phone;
import com.electricity.model.User;
import com.electricity.service.AppService;
@Service
public class AppServiceImpl implements AppService{
	@Autowired
	private PhoneDao phoneDao;
	@Autowired
	private AdvertisementDao advertisementDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CollectionDao collectionDao;
	@Autowired
	private CommentDao commentDao;
	
	@Cacheable(value="AdvertisementsBytype",key="#type")
	@Override
	public List<Advertisement> getAdvertisementsBytype(int type) {
		// TODO Auto-generated method stub
		return advertisementDao.findByType(type);
	}
	@Cacheable(value="getPhone",key="#index")
	@SuppressWarnings("deprecation")
	@Override
	public List<Phone> getPhone(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		return phoneDao.findPhoneIndex(pageable);
	}

	@Override
	public void add_clicknum(Phone p) {
		// TODO Auto-generated method stub
		phoneDao.save(p);
	}

	@Cacheable(value="total_Ranking",key="#index")
	@Override
	public List<Phone> getTotal_Ranking(int index) {
		// TODO Auto-generated method stub
		//System.out.println("**************************************888");
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneTotalRanking(pageable);
		return list;
	}
	@Cacheable(value="Common_Ranking",key="#index")
	@Override
	public List<Phone> getCommon_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneCommonRanking(pageable);
		return list;
	}
	@Cacheable(value="Game_Ranking",key="#index")
	@Override
	public List<Phone> getGame_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneGameRanking(pageable);
		return list;
	}
	@Cacheable(value="Camera_Ranking",key="#index")
	@Override
	public List<Phone> getCamera_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneCameraRanking(pageable);
		return list;
	}
	@Cacheable(value="XuHang_Ranking",key="#index")
	@Override
	public List<Phone> getXuHang_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneXuHangRanking(pageable);
		return list;
	}
	@Cacheable(value="SumTotal_Ranking",key="#index")
	@Override
	public List<Phone> getSumTotal_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneSumTotalRanking(pageable);
		return list;
	}
	@Cacheable(value="SumCommon_Ranking",key="#index")
	@Override
	public List<Phone> getSumCommon_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneSumCommonRanking(pageable);
		return list;
	}
	@Cacheable(value="SumGame_Ranking",key="#index")
	@Override
	public List<Phone> getSumGame_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneSumGameRanking(pageable);
		return list;
	}
	@Cacheable(value="SumCamera_Ranking",key="#index")
	@Override
	public List<Phone> getSumCamera_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneSumCameraRanking(pageable);
		return list;
	}
	@Cacheable(value="SumXuHang_Ranking",key="#index")
	@Override
	public List<Phone> getSumXuHang_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneSumXuHangRanking(pageable);
		return list;
	}
	@Cacheable(value="Xiaomi_Ranking",key="#index")
	@Override
	public List<Phone> getXiaomi_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneXiaomiRanking(pageable);
		return list;
	}
	@Cacheable(value="Honor_Ranking",key="#index")
	@Override
	public List<Phone> getHonor_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneHonorRanking(pageable);
		return list;
	}
	@Cacheable(value="Huawei_Ranking",key="#index")
	@Override
	public List<Phone> getHuawei_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneHuaweiRanking(pageable);
		return list;
	}
	@Cacheable(value="IPhone_Ranking",key="#index")
	@Override
	public List<Phone> getIPhone_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneIPhoneRanking(pageable);
		return list;
	}
	@Cacheable(value="Oppo_Ranking",key="#index")
	@Override
	public List<Phone> getOppo_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneOppoRanking(pageable);
		return list;
	}
	@Cacheable(value="Vivo_Ranking",key="#index")
	@Override
	public List<Phone> getVivo_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneVivoRanking(pageable);
		return list;
	}
	@Cacheable(value="Meizu_Ranking",key="#index")
	@Override
	public List<Phone> getMeizu_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneMeizuRanking(pageable);
		return list;
	}
	@Cacheable(value="SumXiaomi_Ranking",key="#index")
	@Override
	public List<Phone> getSumXiaomi_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneSumXiaomiRanking(pageable);
		return list;
	}
	@Cacheable(value="SumHonor_Ranking",key="#index")
	@Override
	public List<Phone> getSumHonor_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneSumHonorRanking(pageable);
		return list;
	}
	@Cacheable(value="SumHuawei_Ranking",key="#index")
	@Override
	public List<Phone> getSumHuawei_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneCommonRanking(pageable);
		return list;
	}
	@Cacheable(value="SumIPhone_Ranking",key="#index")
	@Override
	public List<Phone> getSumIPhone_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneSumIPhoneRanking(pageable);
		return list;
	}
	@Cacheable(value="SumOppo_Ranking",key="#index")
	@Override
	public List<Phone> getSumOppo_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneSumOppoRanking(pageable);
		return list;
	}
	@Cacheable(value="SumVivo_Ranking",key="#index")
	@Override
	public List<Phone> getSumVivo_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneSumVivoRanking(pageable);
		return list;
	}
	@Cacheable(value="SumMeizu_Ranking",key="#index")
	@Override
	public List<Phone> getSumMeizu_Ranking(int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		List<Phone> list=phoneDao.findPhoneSumMeizuRanking(pageable);
		return list;
	}
	@Cacheable(value="Xiaomi_4Ranking")
	@Override
	public List<Phone> getXiaomi_4Ranking() {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(0,4);
		List<Phone> list=phoneDao.findPhoneXiaomiRanking(pageable);
		return list;
	}
	@Cacheable(value="Honor_4Ranking")
	@Override
	public List<Phone> getHonor_4Ranking() {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(0,4);
		List<Phone> list=phoneDao.findPhoneHonorRanking(pageable);
		return list;
	}
	@Cacheable(value="Huawei_2Ranking")
	@Override
	public List<Phone> getHuawei_2Ranking() {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(0,2);
		List<Phone> list=phoneDao.findPhoneHuaweiRanking(pageable);
		return list;
	}
	@Cacheable(value="IPhone_2Ranking")
	@Override
	public List<Phone> getIPhone_2Ranking() {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(0,2);
		List<Phone> list=phoneDao.findPhoneIPhoneRanking(pageable);
		return list;
	}
	@Cacheable(value="Oppo_2Ranking")
	@Override
	public List<Phone> getOppo_2Ranking() {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(0,2);
		List<Phone> list=phoneDao.findPhoneOppoRanking(pageable);
		return list;
	}
	@Cacheable(value="Vivo_2Ranking")
	@Override
	public List<Phone> getVivo_2Ranking() {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(0,2);
		List<Phone> list=phoneDao.findPhoneVivoRanking(pageable);
		return list;
	}
	@Cacheable(value="Meizu_2Ranking")
	@Override
	public List<Phone> getMeizu_2Ranking() {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(0,2);
		List<Phone> list=phoneDao.findPhoneMeizuRanking(pageable);
		return list;
	}

	@Override
	public List<Phone> get_phonerecommand(double gpu_rate,double front_cam_rate,double back_cam_rate,double size_rate,
			double front_beauty_rate,double back_beauty_rate,double hand_rate,double screan_rate,double battery_rate,
			double charge_rate,String  must_brand,String  must_specail,int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
//		System.out.println(gpu_rate);
//		System.out.println(size_rate);
//		System.out.println(must_brand);
//		System.out.println(must_specail);
	
		List<Phone> list=phoneDao.findphonerecommand(gpu_rate, front_cam_rate, back_cam_rate, size_rate, 
				front_beauty_rate, back_beauty_rate, hand_rate, screan_rate, battery_rate, charge_rate, must_brand, must_specail,pageable);
		return list;
	}

	
	public String getUsername(String username) {
		// TODO Auto-generated method stub
		User u =userDao.findByUsername(username);
		if(u!=null)
			return userDao.findByUsername(username).getPassword();
		else
			return null;
	}

	@Override
	public String register(User u) {
		// TODO Auto-generated method stub
		if(getUsername(u.getUsername())==null) {
			userDao.save(u);
			return "success";
		}else {
			return "fail";
		}
			
	}

	@Override
	public void update_user(User u) {
		// TODO Auto-generated method stub
		
		userDao.save(u);
	}

	@Override
	public User getuser(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public List<Phone> get_collection(String username) {
		// TODO Auto-generated method stub
		List<Collection> list = collectionDao.findByUsername(username);
		List<Phone> phone_list=new ArrayList<Phone>();
		if(list!=null||list.size()!=0) {
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getType().equals("phone")) {
					phone_list.add(phoneDao.findById(list.get(i).getProduct_id()));
				}
				
			}
			
			
		}
		
		return phone_list;
	}

	@Override
	public String add_collection(Collection c) {
		// TODO Auto-generated method stub
		if(collectionDao.findByAll(c.getUsername(), c.getType(), c.getProduct_id())==null) {
			collectionDao.save(c);
			return "success";
		}else {
			return "fail";
		}
		
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User u = userDao.findByUsername(username);
		if(u==null||!(u.getPassword().equals(password))) {
			
			return null;
		}else {
			
			return u;
		}
		
	}

	@Override
	public User getuserbyusername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

	@Override
	public String addComment(Comment c) {
		// TODO Auto-generated method stub
		commentDao.save(c);
		return "success";
	}

	@Override
	public List<Comment> get_phoneComment(int productId,String type,int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		return commentDao.findByProductId(productId, type,pageable);
	}

	@Override
	public List<Comment> get_userComment(String username,String type,int index) {
		// TODO Auto-generated method stub
		Pageable pageable = new PageRequest(index-1,10);
		return commentDao.findByUsername(username, type,pageable);
	}

	@Override
	public String delete_Comment(int id) {
		// TODO Auto-generated method stub
		if(commentDao.findById(id)!=null)
			commentDao.deleteById(id);
		return "success";
	}

	@Override
	public String delete_collection(String username,String type,int product_id) {
		// TODO Auto-generated method stub
		/*
		if(collectionDao.findByAll(username, type, product_id)!=null) {
			System.out.println(username+type+product_id);
			Collection c=new Collection();
			c.setProduct_id(product_id);
			c.setType(type);
			c.setUsername(username);
			collectionDao.delete(c);
			return "success";
		}else {
			return "fail";
		}*/
			collectionDao.deleteByAll(username, type, product_id);
			return "success";
		
	}

	

}
