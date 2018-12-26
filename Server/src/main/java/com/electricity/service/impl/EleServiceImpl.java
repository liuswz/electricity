package com.electricity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.electricity.dao.AdvertisementDao;
import com.electricity.dao.HotSearchDao;
import com.electricity.dao.ManagerDao;
import com.electricity.dao.PhoneDao;
import com.electricity.model.Advertisement;
import com.electricity.model.HotSearch;
import com.electricity.model.Manager;
import com.electricity.model.Phone;
import com.electricity.service.EleService;



@Service
public class EleServiceImpl implements EleService{

	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private PhoneDao phoneDao;
	@Autowired
	private AdvertisementDao advertisementDao;
	@Autowired
	private HotSearchDao hotSearchDao;



	@Override
	public Manager get_password(String username) {
		// TODO Auto-generated method stub
		return managerDao.findByUsername(username);
	}



	@Override
	public List<Manager> showManagers() {
		// TODO Auto-generated method stub
		return managerDao.findAll();
	}



	@Override
	public void deleteManager(int id) {
		// TODO Auto-generated method stub
		managerDao.deleteById(id);
	}



	@Override
	public void add_manager(Manager m) {
		// TODO Auto-generated method stub
		managerDao.save(m);
	}



	@Override
	public void add_phone(Phone p) {
		// TODO Auto-generated method stub
		phoneDao.save(p);
	}



	@Override
	public List<Phone> showPhones() {
		// TODO Auto-generated method stub
		return phoneDao.findAll();
	}



	@Override
	public void deletPhone(int id) {
		// TODO Auto-generated method stub
		phoneDao.deleteById(id);
	}



	@Override
	public void update_phone(Phone p) {
		// TODO Auto-generated method stub
		phoneDao.save(p);
	}



	@Override
	public Phone get_phone(int id) {
		// TODO Auto-generated method stub
		return phoneDao.findById(id);
	}



	@Override
	public void add_advertisements(Advertisement ad) {
		// TODO Auto-generated method stub
		advertisementDao.save(ad);
	}



	@Override
	public Advertisement getAD(int pageId) {
		// TODO Auto-generated method stub
		return advertisementDao.findByPageId(pageId);
	}



	@Override
	public List<Advertisement> show_Ads() {
		// TODO Auto-generated method stub
		return advertisementDao.findAll();
	}



	@Override
	public void deleteAdvertisement(int id) {
		// TODO Auto-generated method stub
		advertisementDao.deleteById(id);
	}



	@Override
	public List<Phone> getPhonebySearch(String value) {
		// TODO Auto-generated method stub
		return phoneDao.findPhone(value);
	}



	@Override
	public Phone getphone_byname(String name) {
		// TODO Auto-generated method stub
		return phoneDao.findByName(name);
	}



	@Override
	public void add_HotSearch(HotSearch h) {
		// TODO Auto-generated method stub
		 hotSearchDao.save(h);
	}



	@Override
	public List<HotSearch> showHotSearch() {
		// TODO Auto-generated method stub
		return hotSearchDao.findAll();
	}



	@Override
	public void deleteHotSearch(int id) {
		// TODO Auto-generated method stub
		hotSearchDao.deleteById(id);
	}



	@Override
	public HotSearch get_value(String value) {
		// TODO Auto-generated method stub
		return hotSearchDao.findByValue(value);
	}
}
