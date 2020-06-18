package jp.co.sample.form;

import javax.validation.constraints.NotBlank;

public class InsertAdministratorForm {

	/** 名前 */
	@NotBlank(message = "値を入力してください")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "値を入力してください")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message = "値を入力してください")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

}
