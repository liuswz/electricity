package com.electricity.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.electricity.model.Phone;

public interface PhoneDao  extends JpaRepository<Phone, Integer>{
	Phone findById(int id);
	
	@Query(nativeQuery=true,value="select * FROM phone where size<:size_rate and brand LIKE CONCAT('%',:must_brand,'%') and special LIKE CONCAT('%',:must_specail,'%') "
			+ "ORDER BY (ui_num+cpu_num+ai_num+rom_num+(gpu_num*:gpu_rate)+(front_cam_num*:front_cam_rate)+(back_cam_num*:back_cam_rate)+(front_beauty_num*:front_beauty_rate)+"
			+ "(back_beauty_num*:back_beauty_rate)+(hand_num*:hand_rate)+(screen_num*:screan_rate)+"
			+ "(consume_power_num*:battery_rate)+(+charge_num*:charge_rate)  )/(price1-different_price) DESC")
	List<Phone> findphonerecommand(@Param("gpu_rate") double gpu_rate,@Param("front_cam_rate") double front_cam_rate,
			@Param("back_cam_rate") double back_cam_rate,
			@Param("size_rate") double size_rate,@Param("front_beauty_rate")  double front_beauty_rate,
			@Param("back_beauty_rate") double back_beauty_rate,@Param("hand_rate") double hand_rate,@Param("screan_rate") double screan_rate,
			@Param("battery_rate") double battery_rate,	@Param("charge_rate") double charge_rate,
			@Param("must_brand") String  must_brand,@Param("must_specail") String  must_specail,Pageable pageable);

	
	//@Query("from Phone where id=")
	@Query("FROM Phone p WHERE p.name LIKE CONCAT('%',:value,'%') ORDER BY id DESC")
	List<Phone> findPhone(@Param("value") String value);
	
	Phone  findByName(String name);
	
	//SELECT *FROM phone ORDER BY id DESC LIMIT 0,4;
	@Query(nativeQuery=true,value="select * FROM phone ORDER BY click_num DESC")
	List<Phone> findPhoneIndex(Pageable pageable);

	
	@Query(nativeQuery=true,value="select * FROM phone ORDER BY sum_score/(price1-different_price) DESC")
	List<Phone> findPhoneTotalRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone ORDER BY (sum_score-gpu_num)/(price1-different_price) DESC")
	List<Phone> findPhoneCommonRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone ORDER BY (gpu_num+cpu_num)/(price1-different_price) DESC")
	List<Phone> findPhoneGameRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone ORDER BY (front_cam_num+back_cam_num)/(price1-different_price) DESC")
	List<Phone> findPhoneCameraRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone ORDER BY (consume_power_num+charge_num)/(price1-different_price) DESC")
	List<Phone> findPhoneXuHangRanking(Pageable pageable);

	
	@Query(nativeQuery=true,value="select * FROM phone ORDER BY sum_score DESC")
	List<Phone> findPhoneSumTotalRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone ORDER BY (uiSpeed_num+cpu_num) DESC")
	List<Phone> findPhoneSumCommonRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone ORDER BY (gpu_num+cpu_num) DESC")
	List<Phone> findPhoneSumGameRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone ORDER BY (front_cam_num+back_cam_num) DESC")
	List<Phone> findPhoneSumCameraRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone ORDER BY (consume_power_num+charge_num) DESC")
	List<Phone> findPhoneSumXuHangRanking(Pageable pageable);

	
	
	@Query(nativeQuery=true,value="select * FROM phone where brand ='小米' ORDER BY sum_score/(price1-different_price) DESC")
	List<Phone> findPhoneXiaomiRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone where brand ='荣耀' ORDER BY sum_score/(price1-different_price) DESC")
	List<Phone> findPhoneHonorRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone where brand ='华为' ORDER BY sum_score/(price1-different_price) DESC")
	List<Phone> findPhoneHuaweiRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone where brand ='苹果' ORDER BY sum_score/(price1-different_price) DESC")
	List<Phone> findPhoneIPhoneRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone where brand ='oppo' ORDER BY sum_score/(price1-different_price) DESC")
	List<Phone> findPhoneOppoRanking(Pageable pageable);
	
	@Query(nativeQuery=true,value="select * FROM phone where brand ='vivo' ORDER BY sum_score/(price1-different_price) DESC")
	List<Phone> findPhoneVivoRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone where brand ='魅族' ORDER BY sum_score/(price1-different_price) DESC")
	List<Phone> findPhoneMeizuRanking(Pageable pageable);


	
	@Query(nativeQuery=true,value="select * FROM phone where brand ='小米' ORDER BY sum_score DESC")
	List<Phone> findPhoneSumXiaomiRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone where brand ='荣耀' ORDER BY sum_score DESC")
	List<Phone> findPhoneSumHonorRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone where brand ='华为' ORDER BY sum_score DESC")
	List<Phone> findPhoneSumHuaweiRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone where brand ='苹果' ORDER BY sum_score DESC")
	List<Phone> findPhoneSumIPhoneRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone where brand ='oppo' ORDER BY sum_score DESC")
	List<Phone> findPhoneSumOppoRanking(Pageable pageable);
	
	@Query(nativeQuery=true,value="select * FROM phone where brand ='vivo' ORDER BY sum_score DESC")
	List<Phone> findPhoneSumVivoRanking(Pageable pageable);

	@Query(nativeQuery=true,value="select * FROM phone where brand ='魅族' ORDER BY sum_score DESC")
	List<Phone> findPhoneSumMeizuRanking(Pageable pageable);
	
		
	
}
