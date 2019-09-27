package com.hujiang.project.zhgd.hjWorkersInformation.domain;

import com.hujiang.framework.aspectj.lang.annotation.Excel;

/**
 * 工人表 hj_workers_information
 * 
 * @author hujiang
 * @date 2019-07-03
 */
public class HjWorkersInformationPc
{
	/** ID */
	@Excel(name = "ID")
	private Integer id;
	/** 简易劳动合同 */
	@Excel(name = "简易劳动合同")
	private String laborContract;
	/** 两制确认书 */
	@Excel(name = "两制确认书")
	private String twoSystems;
	/** 进场确认书 */
	@Excel(name = "进场确认书")
	private String enter;
	/** 出场确认书 */
	@Excel(name = "出场确认书")
	private String come;
	/** 身份证正反面复印件 */
	@Excel(name = "身份证正反面复印件")
	private String identity;
	/** 资料是否齐全（0、不齐全，1、齐全） */
	@Excel(name = "资料是否齐全（0、不齐全，1、齐全） ")
	private Integer material;
	@Excel(name = "姓名")
	private String empName;	//姓名

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLaborContract() {
		return laborContract;
	}

	public void setLaborContract(String laborContract) {
		this.laborContract = laborContract;
	}

	public String getTwoSystems() {
		return twoSystems;
	}

	public void setTwoSystems(String twoSystems) {
		this.twoSystems = twoSystems;
	}

	public String getEnter() {
		return enter;
	}

	public void setEnter(String enter) {
		this.enter = enter;
	}

	public String getCome() {
		return come;
	}

	public void setCome(String come) {
		this.come = come;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Integer getMaterial() {
		return material;
	}

	public void setMaterial(Integer material) {
		this.material = material;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Override
	public String toString() {
		return "HjWorkersInformationPc{" +
				"id=" + id +
				", laborContract='" + laborContract + '\'' +
				", twoSystems='" + twoSystems + '\'' +
				", enter='" + enter + '\'' +
				", come='" + come + '\'' +
				", identity='" + identity + '\'' +
				", material=" + material +
				", empName='" + empName + '\'' +
				'}';
	}
}
