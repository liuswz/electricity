package com.electricity.service;

import java.util.List;

import com.electricity.model.Advertisement;
import com.electricity.model.HotSearch;
import com.electricity.model.Manager;
import com.electricity.model.Phone;



public interface EleService {
	public List<Manager> showManagers();
	public void deleteManager(int id);
	public void add_manager(Manager m);
	public Manager get_password(String username);
	
	public void add_phone(Phone p);
	public List<Phone> showPhones();
	public void deletPhone(int id);
	public void update_phone(Phone p);
	public Phone get_phone(int id);
	public List<Phone> getPhonebySearch(String value);
	public Phone getphone_byname(String name);
	
	public void add_advertisements(Advertisement ad);
	public Advertisement getAD(int page_id);
	public List<Advertisement> show_Ads();
	public void deleteAdvertisement(int id);
	
	public void add_HotSearch(HotSearch h);
	public List<HotSearch> showHotSearch();
	public void deleteHotSearch(int id);
	public HotSearch get_value(String value);
}
