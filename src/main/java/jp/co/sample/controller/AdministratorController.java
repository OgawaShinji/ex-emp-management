package jp.co.sample.controller;

import java.util.Objects;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

	@Autowired
	private HttpSession session;

	/**
	 * LoginFormオブジェクトをModelオブジェクトに格納するための処理
	 * 
	 * @return
	 */
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		LoginForm form = new LoginForm();
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
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login.html";
	}

	@RequestMapping("/login")
	public String login(@Valid LoginForm form, BindingResult result, Model model) {
		if (Objects.isNull(administratorService.login(form.getMailAddress(), form.getPassword()))) {
			result.rejectValue("mailAddress", null, "メールアドレスまたはパスワードが違います");
			result.rejectValue("password", null, "メールアドレスまたはパスワードが違います");
			return "administrator/login";
		} else {
			Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
			session.setAttribute("administratorName", administrator.getName());
			return "forward:/employee/showList";
		}
	}
}
