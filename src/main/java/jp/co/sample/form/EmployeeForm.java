package jp.co.sample.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

/**
 * 従業員情報を表すドメイン
 * 
 * @author rpuser
 *
 */
public class EmployeeForm {

	/** 従業員のid */
	private Integer id;
	/** 従業員の名前 */
	private String name;
	/** 従業員の写真 */
	private String image;
	/** 従業員の性別 */
	private String gender;
	/** 従業員の入社日 */
	private String hireDate;
	/** 従業員のメールアドレス */
	private String mailAddress;
	/** 従業員の郵便番号 */
	private String zipCode;
	/** 従業員の住所 */
	private String address;
	/** 従業員の電話番号 */
	private String telephone;
	/** 従業員の給料 */
	private Integer salary;
	/** 従業員の特性 */
	private String characteristics;
	/** 従業員の扶養人数 */
	@NotNull(message = "値を入力してください")
	@Size(min = 1,max = 3)
	private Integer dependentsCount;

	

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", image=" + image + ", gender=" + gender + ", hireDate="
				+ hireDate + ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address=" + address
				+ ", telephone=" + telephone + ", salary=" + salary + ", characteristics=" + characteristics
				+ ", dependentsCount=" + dependentsCount + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	

	

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}

	public Integer getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(Integer dependentsCount) {
		this.dependentsCount = dependentsCount;
	}

}
