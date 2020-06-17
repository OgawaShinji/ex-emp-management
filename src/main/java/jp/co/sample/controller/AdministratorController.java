package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	/**
	 * LoginFormオブジェクトをModelオブジェクトに格納するための処理
	 * @return
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		LoginForm form=new LoginForm();
		return form;
	}
	
	/**
	 * 従業員登録時のリクエストパラメータをModelオブジェクトに格納
	 * 
	 * @return 登録時の格納されたオブジェクト
	 */
	@ModelAttribute
	public InsertAdministratorForm setUpInsertAdministratorForm() {
		InsertAdministratorForm form = new InsertAdministratorForm();
		return form;
	}

	/**
	 * 登録画面へフォワード
	 * 
	 * @return パス指定
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert.html";
	}

	/**
	 * 管理者情報登録を実行するメソッド
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		administrator.setName(form.getName());
		administrator.setMailAddress(form.getMailAddress());
		administrator.setPassword(form.getPassword());

		administratorService.insert(administrator);
		return "redirect:/";
	}
	/**
	 * ログイン画面へフォワードする
	 * @return
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login.html";
	}
}
