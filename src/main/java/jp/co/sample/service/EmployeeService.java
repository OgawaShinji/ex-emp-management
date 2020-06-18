package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.EmployeeForm;
import jp.co.sample.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 従業員情報を全件取得するメソッドをリポジトリクラスから呼び出す
	 * 
	 * @return 取得したリストを返す
	 */
	public List<Employee> showList() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}

	/**
	 * 従業員情報を一件取得するメソッドをリポジトリクラスから呼び出す
	 * 
	 * @param id
	 * @return 取得した従業員情報を返す
	 */
	public Employee showDetail(Integer id) {
		Employee employee = employeeRepository.load(id);
		return employee;
	}
	/**
	 * 従業員情報を更新するメソッドをリポジトリクラスから呼び出す
	 * @param employee
	 */
	public void update(EmployeeForm employee) {
		employeeRepository.update(employee);
	}

	public Integer loadCount(Integer id) {
		Integer employee= employeeRepository.loadCount(id);
		return employee;
	}
}
