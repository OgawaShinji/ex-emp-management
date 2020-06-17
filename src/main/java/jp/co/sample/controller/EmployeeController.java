package jp.co.sample.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
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
		model.addAttribute("employee", employee);
		return "employee/detail.html";
	}

	/**
	 * 受け取った扶養人数にidを使って従業員情報を更新する
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}

}
