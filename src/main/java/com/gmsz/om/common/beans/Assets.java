package com.gmsz.om.common.beans;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Assets {	

	/**
	 * 资产状态（使用中）
	 */
	public static final int STATUS_ASSETS_USEING = 2;
	/**
	 * 资产状态（报废）
	 */
	public static final int STATUS_ASSETS_SCRAP = 1;
	/**
	 * 资产状态（备用）
	 */
	public static final int STATUS_ASSETS_STANDBY = 3;
	/**
	 * 资产状态（维护中）
	 */
	public static final int STATUS_ASSETS_MAINTANING = 4;
	
	public static final Map<Integer,String> statusAssetsMap = new HashMap<Integer,String>(){
		private static final long serialVersionUID = -8282135285228713674L;
	{
	    put(STATUS_ASSETS_SCRAP, "报废");
	    put(STATUS_ASSETS_USEING, "使用中");
	    put(STATUS_ASSETS_STANDBY, "备用");
	    put(STATUS_ASSETS_MAINTANING, "维护中");
	}};
	
	/**
	 * 资产级别（无）
	 */
	public static final int LEVEL_OTHER = 4;
	/**
	 * 资产级别（高）
	 */
	public static final int LEVEL_HIGHT = 3;
	/**
	 * 资产级别（中）
	 */
	public static final int LEVEL_MIDDLE = 2;
	/**
	 * 资产级别（低）
	 */
	public static final int LEVEL_LOW = 1;
	
	public static final Map<Integer,String> assetLeveMap = new HashMap<Integer,String>(){
		private static final long serialVersionUID = 5895200909599169373L;
	{
		put(LEVEL_LOW, "低");
		put(LEVEL_MIDDLE, "中");
	    put(LEVEL_HIGHT, "高");	
	    put(LEVEL_OTHER, "无");	
	}};
	
	/**
	 * 厂家质保是否过保(未过保)
	 */
	public static final int OVER_MAKER_WARRANT_FALSE = 1;
	/**
	 * 厂家质保是否过保(过保) 
	 */
	public static final int OVER_MAKER_WARRANT_TRUE = 2;
	
	/**
	 * 管理方式 0: 不管理
	 */
	public static final int MANGER_MODE_UNMANGER = 0;
	/**
	 * 管理方式 1：不拆分管理
	 */
	public static final int MANGER_MODE_UNSPLIT_MANGER = 1;
	/**
	 * 管理方式 2：拆分管理
	 */
	public static final int MANGER_MODE_SPLIT_MANGER = 2;
	
	public static final Map<Integer,String> mangerModeMap = new HashMap<Integer,String>(){
		private static final long serialVersionUID = -6733419926966646470L;
	{
		put(MANGER_MODE_UNMANGER, "不管理");
		put(MANGER_MODE_UNSPLIT_MANGER, "整体管理");
	    put(MANGER_MODE_SPLIT_MANGER, "拆分管理");	
	}};
	
	private long id;
	private String serialNumber;
	private long categoryId;
	private long brandId;
	private String model;
	private int status;
	private int valuable;
	private long monitorPointId;
	private String position;
	private String memo;
	private Date addDate;
	private long parentId;
	private Date purchaseDate;
	private Date makerWarrantEndDate;
	private int overMakerWarrant;
	private Date useStartDate;
	private Date comWarrantEndDate;
	private Date lifeEndDate;
	private Integer makerWarrantMonths;
	private Integer comWarrantMonths;
	private Integer lifeEndMonths;
	private String unit;
	private String parentIdStr;
	private String rfid;
	private String name;
	private Date lastMaintainTime;
	private double number;
	private int mangerMode;
	private long assetsGroupid;
	private double charge;
	
	private String prop;
	
	
	/**
	 * @return the charge
	 */
	public double getCharge() {
		return charge;
	}
	/**
	 * @param charge the charge to set
	 */
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public void setMonitorPointId(long monitorPointId) {
		this.monitorPointId = monitorPointId;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public long getCategoryId() {
		return categoryId;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public long getMonitorPointId() {
		return monitorPointId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public int getValuable() {
		return valuable;
	}
	public void setValuable(int valuable) {
		this.valuable = valuable;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public long getBrandId() {
		return brandId;
	}
	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getParentIdStr() {
		return parentIdStr;
	}
	public void setParentIdStr(String parentIdStr) {
		this.parentIdStr = parentIdStr;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public Date getMakerWarrantEndDate() {
		return makerWarrantEndDate;
	}
	public void setMakerWarrantEndDate(Date makerWarrantEndDate) {
		this.makerWarrantEndDate = makerWarrantEndDate;
	}
	public Date getUseStartDate() {
		return useStartDate;
	}
	public void setUseStartDate(Date useStartDate) {
		this.useStartDate = useStartDate;
	}
	public Date getComWarrantEndDate() {
		return comWarrantEndDate;
	}
	public void setComWarrantEndDate(Date comWarrantEndDate) {
		this.comWarrantEndDate = comWarrantEndDate;
	}
	public Date getLifeEndDate() {
		return lifeEndDate;
	}
	public void setLifeEndDate(Date lifeEndDate) {
		this.lifeEndDate = lifeEndDate;
	}
	public int getOverMakerWarrant() {
		return overMakerWarrant;
	}
	public void setOverMakerWarrant(int overMakerWarrant) {
		this.overMakerWarrant = overMakerWarrant;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLastMaintainTime() {
		return lastMaintainTime;
	}
	public void setLastMaintainTime(Date lastMaintainTime) {
		this.lastMaintainTime = lastMaintainTime;
	}

	public Integer getMakerWarrantMonths() {
		return makerWarrantMonths;
	}
	public void setMakerWarrantMonths(Integer makerWarrantMonths) {
		this.makerWarrantMonths = makerWarrantMonths;
	}
	public Integer getComWarrantMonths() {
		return comWarrantMonths;
	}
	public void setComWarrantMonths(Integer comWarrantMonths) {
		this.comWarrantMonths = comWarrantMonths;
	}
	public Integer getLifeEndMonths() {
		return lifeEndMonths;
	}
	public void setLifeEndMonths(Integer lifeEndMonths) {
		this.lifeEndMonths = lifeEndMonths;
	}
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public int getMangerMode() {
		return mangerMode;
	}
	public void setMangerMode(int mangerMode) {
		this.mangerMode = mangerMode;
	}
	public long getAssetsGroupid() {
		return assetsGroupid;
	}
	public void setAssetsGroupid(long assetsGroupid) {
		this.assetsGroupid = assetsGroupid;
	}
	public String getProp() {
		return prop;
	}
	public void setProp(String prop) {
		this.prop = prop;
	}
	
}
