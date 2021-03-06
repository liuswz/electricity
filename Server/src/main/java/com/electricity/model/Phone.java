package com.electricity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "phone")
public class  Phone {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
//名称	
	@Column(name = "name")
	private String name;
//牌子		
	@Column(name = "brand")
	private String brand;
//尺寸	
	@Column(name = "size")
	private double size;
//ui系统	
	@Column(name = "ui")
	private String ui;
	@Column(name = "ui_num")
	private double ui_num;
	

//性能	
	@Column(name = "cpu")
	private String cpu;
	@Column(name = "cpu_num")
	private double cpu_num;
	@Column(name = "gpu_num")
	private double gpu_num;
	@Column(name = "ai_num")
	private double ai_num;
	@Column(name = "rom")
	private String rom;
	@Column(name = "rom_num")
	private double rom_num;
	
//拍照	
	@Column(name = "front_cam")
	private String front_cam;
	@Column(name = "front_cam_num")
	private double front_cam_num;
	@Column(name = "back_cam")
	private String back_cam;
	@Column(name = "back_cam_num")
	private double back_cam_num;
//外观	
	@Column(name = "front_beauty")
	private String front_beauty;
	@Column(name = "front_beauty_num")
	private double front_beauty_num;
	@Column(name = "back_beauty")
	private String back_beauty;
	@Column(name = "back_beauty_num")
	private double back_beauty_num;
	@Column(name = "hand_num")
	private double hand_num;
	

//屏
	@Column(name = "screen")
	private String screen;
	@Column(name = "screen_num")
	private double screen_num;
//续航
	@Column(name = "battery")
	private int battery;
	@Column(name = "consume_power")
	private String consume_power;
	@Column(name = "consume_power_num")
	private double consume_power_num;
	@Column(name = "charge")
	private String charge;
	@Column(name = "charge_num")
	private double charge_num;


//特殊
	@Column(name = "special")
	private String special;
	@Column(name = "special_num")
	private double special_num;
//价格
	@Column(name = "different_price")
	private int different_price;	
	@Column(name = "type1")
	private String type1;	
	@Column(name = "type2")
	private String type2;	
	@Column(name = "type3")
	private String type3;	
	@Column(name = "price1")
	private int price1;	
	@Column(name = "price2")
	private int price2;	
	@Column(name = "price3")
	private int price3;	
//图片
	@Column(name = "picture")
	private String picture;
	@Column(name = "picture2")
	private String picture2;
	@Column(name = "picture3")
	private String picture3;
	@Column(name = "picture4")
	private String picture4;
	@Column(name = "picture5")
	private String picture5;
	
//链接	
	@Column(name = "officialShopLink")
	private String officialShopLink;
	@Column(name = "cheapestShopLink")
	private String cheapestShopLink;
	@Column(name = "self_comment")
	private String self_comment;
	@Column(name = "click_num")
	private int click_num;
	@Column(name = "sum_score")
	private double sum_score;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getUi() {
		return ui;
	}
	public void setUi(String ui) {
		this.ui = ui;
	}

	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}

	public double getUi_num() {
		return ui_num;
	}
	public void setUi_num(double ui_num) {
		this.ui_num = ui_num;
	}
	public String getRom() {
		return rom;
	}
	public void setRom(String rom) {
		this.rom = rom;
	}
	public double getRom_num() {
		return rom_num;
	}
	public void setRom_num(double rom_num) {
		this.rom_num = rom_num;
	}


	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public double getCpu_num() {
		return cpu_num;
	}
	public void setCpu_num(double cpu_num) {
		this.cpu_num = cpu_num;
	}
	public double getGpu_num() {
		return gpu_num;
	}
	public void setGpu_num(double gpu_num) {
		this.gpu_num = gpu_num;
	}
	
	public double getAi_num() {
		return ai_num;
	}
	public void setAi_num(double ai_num) {
		this.ai_num = ai_num;
	}
	public String getFront_cam() {
		return front_cam;
	}
	public void setFront_cam(String front_cam) {
		this.front_cam = front_cam;
	}
	public double getFront_cam_num() {
		return front_cam_num;
	}
	public void setFront_cam_num(double front_cam_num) {
		this.front_cam_num = front_cam_num;
	}
	public String getBack_cam() {
		return back_cam;
	}
	public void setBack_cam(String back_cam) {
		this.back_cam = back_cam;
	}
	public double getBack_cam_num() {
		return back_cam_num;
	}
	public void setBack_cam_num(double back_cam_num) {
		this.back_cam_num = back_cam_num;
	}
	public String getFront_beauty() {
		return front_beauty;
	}
	public void setFront_beauty(String front_beauty) {
		this.front_beauty = front_beauty;
	}
	public double getFront_beauty_num() {
		return front_beauty_num;
	}
	public void setFront_beauty_num(double front_beauty_num) {
		this.front_beauty_num = front_beauty_num;
	}
	public String getBack_beauty() {
		return back_beauty;
	}
	public void setBack_beauty(String back_beauty) {
		this.back_beauty = back_beauty;
	}
	public double getBack_beauty_num() {
		return back_beauty_num;
	}
	public void setBack_beauty_num(double back_beauty_num) {
		this.back_beauty_num = back_beauty_num;
	}
	
	public double getHand_num() {
		return hand_num;
	}
	public void setHand_num(double hand_num) {
		this.hand_num = hand_num;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public double getScreen_num() {
		return screen_num;
	}
	public void setScreen_num(double screen_num) {
		this.screen_num = screen_num;
	}
	public int getBattery() {
		return battery;
	}
	public void setBattery(int battery) {
		this.battery = battery;
	}
	public String getConsume_power() {
		return consume_power;
	}
	public void setConsume_power(String consume_power) {
		this.consume_power = consume_power;
	}
	public double getConsume_power_num() {
		return consume_power_num;
	}
	public void setConsume_power_num(double consume_power_num) {
		this.consume_power_num = consume_power_num;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public double getCharge_num() {
		return charge_num;
	}
	public void setCharge_num(double charge_num) {
		this.charge_num = charge_num;
	}

	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public double getSpecial_num() {
		return special_num;
	}
	public void setSpecial_num(double special_num) {
		this.special_num = special_num;
	}
	
	
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public String getType3() {
		return type3;
	}
	public void setType3(String type3) {
		this.type3 = type3;
	}
	public int getPrice1() {
		return price1;
	}
	public void setPrice1(int price1) {
		this.price1 = price1;
	}
	public int getPrice2() {
		return price2;
	}
	public void setPrice2(int price2) {
		this.price2 = price2;
	}
	public int getPrice3() {
		return price3;
	}
	public void setPrice3(int price3) {
		this.price3 = price3;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPicture2() {
		return picture2;
	}
	public void setPicture2(String picture2) {
		this.picture2 = picture2;
	}
	public String getPicture3() {
		return picture3;
	}
	public void setPicture3(String picture3) {
		this.picture3 = picture3;
	}
	public String getPicture4() {
		return picture4;
	}
	public void setPicture4(String picture4) {
		this.picture4 = picture4;
	}
	public String getPicture5() {
		return picture5;
	}
	public void setPicture5(String picture5) {
		this.picture5 = picture5;
	}
	public String getOfficialShopLink() {
		return officialShopLink;
	}
	public void setOfficialShopLink(String officialShopLink) {
		this.officialShopLink = officialShopLink;
	}
	public String getCheapestShopLink() {
		return cheapestShopLink;
	}
	public void setCheapestShopLink(String cheapestShopLink) {
		this.cheapestShopLink = cheapestShopLink;
	}
	public String getSelf_comment() {
		return self_comment;
	}
	public void setSelf_comment(String self_comment) {
		this.self_comment = self_comment;
	}
	public int getClick_num() {
		return click_num;
	}
	public void setClick_num(int click_num) {
		this.click_num = click_num;
	}
	public double getSum_score() {
		return sum_score;
	}
	public void setSum_score(double sum_score) {
		this.sum_score = sum_score;
	}
	public int getDifferent_price() {
		return different_price;
	}
	public void setDifferent_price(int different_price) {
		this.different_price = different_price;
	}

	
}
