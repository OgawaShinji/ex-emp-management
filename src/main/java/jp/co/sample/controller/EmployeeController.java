package jp.co.sample.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.EmployeeForm;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@ModelAttribute
	private UpdateEmployeeForm setUpUpdateEmployeeForm() {
		UpdateEmployeeForm updateEmployeeForm = new UpdateEmployeeForm();
		return updateEmployeeForm;
	};

	@ModelAttribute
	private EmployeeForm setUpEmployeeForm() {
		EmployeeForm employeeForm = new EmployeeForm();
		return employeeForm;
	}

	/**
	 * 従業員全件検索してリクエストスコープに格納
	 * 
	 * @param model
	 * @return employee/listにフォワード
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/**
	 * idを受け取って従業員情報一件を表示する
	 * 
	 * @param id
	 * @param model
	 * @return 表示するhtmlにフォワード
	 */
	@RequestMapping("/showDetail")
	public String showDetail(Integer id, Model model) {
		Employee employee = employeeService.showDetail(id);
		EmployeeForm form=new EmployeeForm();
		BeanUtils.copyProperties(employee,form );
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		form.setHireDate(dateFormat.format(employee.getHireDate()));
		model.addAttribute("employeeForm", form);
		return "employee/detail.html";
	}

	/**
	 * 受け取った扶養人数にidを使って従業員情報を更新する
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/update")
	public String update(@Validated EmployeeForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("employeeForm", form);
			return "employee/detail";
		}

		Employee employee = new Employee();
		
		employee.setId(form.getId());
		employee.setName(form.getName());
		employee.setImage(form.getImage());
		employee.setGender(form.getGender());
		//employeeにhiredateをセットする
		//formはstringで受け取ってくる。それをempにセットする。でも任意の文字列を変換しなければならない
		
		employee.setMailAddress(form.getMailAddress());
		employee.setZipCode(form.getZipCode());
		employee.setAddress(form.getAddress());
		employee.setTelephone(form.getTelephone());
		employee.setSalary(form.getSalary());
		employee.setCharacteristics(form.getCharacteristics());
		employee.setDependentsCount(form.getDependentsCount());

		employeeService.update(form);
		return "redirect:/employee/showList";
	}

}
