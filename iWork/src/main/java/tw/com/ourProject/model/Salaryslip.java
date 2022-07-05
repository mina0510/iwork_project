package tw.com.ourProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="salaryslip")
public class Salaryslip {

	@Id
	@Column(name = "salaryid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer salaryId;
	
	
	@Column(name="empid",columnDefinition = "char(11)")
	private String empId;
	
	@Column(name = "paymonth")
	private String payMonth;
	
	@Column(name = "basesalary")
	private Integer baseSalary;
	
	@Column(name = "supervisorydifferentialpay")
	private Integer supervisoryDifferentialPay;
	
	@Column(name = "transpallowance")
	private Integer transpAllowance;
	
	@Column(name = "assignallowance")
	private Integer assignAllowance;
	
	@Column(name = "attendancebonus")
	private Integer attendanceBonus;
	
	@Column(name = "performancebonus")
	private Integer performanceBonus;
	
	@Column(name = "professionallowance")
	private Integer professionAllowance;
	
	@Column(name = "dueamount")
	private Integer dueAmount;
	
	@Column(name = "incometax")
	private Integer incomeTax;
	
	@Column(name = "laborinsurance")
	private Integer laborInsurance;
	
	@Column(name = "healthinsurancepremium")
	private Integer healthInsurancePremium;
	
	@Column(name = "empbenefit")
	private Integer empBenefit;
	
	@Column(name = "retirecompensation")
	private Integer retireCompensation;
	
	@Column(name = "empleave")
	private Integer empLeave;
	
	@Column(name = "cashadvance")
	private Integer cashAdvance;
	
	@Column(name = "deductionsubtotal")
	private Integer deductionSubtotal;
	
	@Column(name = "nettotal")
	private Integer netTotal;
	
	@Column(name = "allowance")
	private Integer allowance;
	
	@Column(name = "overtimepay")
	private Integer overtimePay;
	
	@Column(name = "nettotalovertime")
	private Integer netTotalOvertime;
	
	@Column(name = "overtimemultiple")
	private Float overtimeMultiple;
	
	@Column(name = "extrahours")
	private Integer extraHours;
	
	@Column(name = "annualleave")
	private Integer annualLeave;
	
	@Column(name = "usedannualleave")
	private Integer usedAnnualLeave;

	@Override
	public String toString() {
		return "Salaryslip [salaryId=" + salaryId + ", empId=" + empId + ", payMonth=" + payMonth + ", baseSalary="
				+ baseSalary + ", supervisoryDifferentialPay=" + supervisoryDifferentialPay + ", transpAllowance="
				+ transpAllowance + ", assignAllowance=" + assignAllowance + ", attendanceBonus=" + attendanceBonus
				+ ", performanceBonus=" + performanceBonus + ", professionAllowance=" + professionAllowance
				+ ", dueAmount=" + dueAmount + ", incomeTax=" + incomeTax + ", laborInsurance=" + laborInsurance
				+ ", healthInsurancePremium=" + healthInsurancePremium + ", empBenefit=" + empBenefit
				+ ", retireCompensation=" + retireCompensation + ", empLeave=" + empLeave + ", cashAdvance="
				+ cashAdvance + ", deductionSubtotal=" + deductionSubtotal + ", netTotal=" + netTotal + ", allowance="
				+ allowance + ", overtimePay=" + overtimePay + ", netTotalOvertime=" + netTotalOvertime
				+ ", overtimeMultiple=" + overtimeMultiple + ", extraHours=" + extraHours + ", annualLeave="
				+ annualLeave + ", usedAnnualLeave=" + usedAnnualLeave + "]";
	}

	public Integer getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(String payMonth) {
		this.payMonth = payMonth;
	}

	public Integer getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Integer baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Integer getSupervisoryDifferentialPay() {
		return supervisoryDifferentialPay;
	}

	public void setSupervisoryDifferentialPay(Integer supervisoryDifferentialPay) {
		this.supervisoryDifferentialPay = supervisoryDifferentialPay;
	}

	public Integer getTranspAllowance() {
		return transpAllowance;
	}

	public void setTranspAllowance(Integer transpAllowance) {
		this.transpAllowance = transpAllowance;
	}

	public Integer getAssignAllowance() {
		return assignAllowance;
	}

	public void setAssignAllowance(Integer assignAllowance) {
		this.assignAllowance = assignAllowance;
	}

	public Integer getAttendanceBonus() {
		return attendanceBonus;
	}

	public void setAttendanceBonus(Integer attendanceBonus) {
		this.attendanceBonus = attendanceBonus;
	}

	public Integer getPerformanceBonus() {
		return performanceBonus;
	}

	public void setPerformanceBonus(Integer performanceBonus) {
		this.performanceBonus = performanceBonus;
	}

	public Integer getProfessionAllowance() {
		return professionAllowance;
	}

	public void setProfessionAllowance(Integer professionAllowance) {
		this.professionAllowance = professionAllowance;
	}

	public Integer getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Integer dueAmount) {
		this.dueAmount = dueAmount;
	}

	public Integer getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(Integer incomeTax) {
		this.incomeTax = incomeTax;
	}

	public Integer getLaborInsurance() {
		return laborInsurance;
	}

	public void setLaborInsurance(Integer laborInsurance) {
		this.laborInsurance = laborInsurance;
	}

	public Integer getHealthInsurancePremium() {
		return healthInsurancePremium;
	}

	public void setHealthInsurancePremium(Integer healthInsurancePremium) {
		this.healthInsurancePremium = healthInsurancePremium;
	}

	public Integer getEmpBenefit() {
		return empBenefit;
	}

	public void setEmpBenefit(Integer empBenefit) {
		this.empBenefit = empBenefit;
	}

	public Integer getRetireCompensation() {
		return retireCompensation;
	}

	public void setRetireCompensation(Integer retireCompensation) {
		this.retireCompensation = retireCompensation;
	}

	public Integer getEmpLeave() {
		return empLeave;
	}

	public void setEmpLeave(Integer empLeave) {
		this.empLeave = empLeave;
	}

	public Integer getCashAdvance() {
		return cashAdvance;
	}

	public void setCashAdvance(Integer cashAdvance) {
		this.cashAdvance = cashAdvance;
	}

	public Integer getDeductionSubtotal() {
		return deductionSubtotal;
	}

	public void setDeductionSubtotal(Integer deductionSubtotal) {
		this.deductionSubtotal = deductionSubtotal;
	}

	public Integer getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(Integer netTotal) {
		this.netTotal = netTotal;
	}

	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}

	public Integer getOvertimePay() {
		return overtimePay;
	}

	public void setOvertimePay(Integer overtimePay) {
		this.overtimePay = overtimePay;
	}

	public Integer getNetTotalOvertime() {
		return netTotalOvertime;
	}

	public void setNetTotalOvertime(Integer netTotalOvertime) {
		this.netTotalOvertime = netTotalOvertime;
	}

	public Float getOvertimeMultiple() {
		return overtimeMultiple;
	}

	public void setOvertimeMultiple(Float overtimeMultiple) {
		this.overtimeMultiple = overtimeMultiple;
	}

	public Integer getExtraHours() {
		return extraHours;
	}

	public void setExtraHours(Integer extraHours) {
		this.extraHours = extraHours;
	}

	public Integer getAnnualLeave() {
		return annualLeave;
	}

	public void setAnnualLeave(Integer annualLeave) {
		this.annualLeave = annualLeave;
	}

	public Integer getUsedAnnualLeave() {
		return usedAnnualLeave;
	}

	public void setUsedAnnualLeave(Integer usedAnnualLeave) {
		this.usedAnnualLeave = usedAnnualLeave;
	}
	
}
