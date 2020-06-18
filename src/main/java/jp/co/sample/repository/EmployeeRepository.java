package jp.co.sample.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.EmployeeForm;

/**
 * 従業員情報を操作するリポジトリクラス
 * 
 * @author rpuser
 *
 */
@Repository
public class EmployeeRepository {

	private static final RowMapper<Integer> COUNT_ROW_MAPPER=(rs,i)->{
		Employee employee=new Employee();
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee.getDependentsCount();
	};
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getTimestamp("hire_date")); // timestamp型をdate型にset?
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 従業員情報一覧を入社日順で取得するSQLを実行するメソッド
	 * 
	 * @return 従業員情報一覧
	 */
	public List<Employee> findAll() {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count"
				+ " FROM employees ORDER BY hire_date";
		if (Objects.isNull(template.query(sql, EMPLOYEE_ROW_MAPPER))) {
			List<Employee> employeeList = new ArrayList<Employee>();
			return employeeList;
		} else {
			List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
			return employeeList;
		}
	}

	/**
	 * 主キー検索を行う
	 * 
	 * @param id
	 * @return 検索した従業員情報
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count "
				+ "FROM employees WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		try {
			Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
			return employee;
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}

	}
	public Integer loadCount(Integer id) {
		String sql = "SELECT dependents_count "
				+ "FROM employees WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		try {
			Integer employee = template.queryForObject(sql, param,COUNT_ROW_MAPPER);
			return employee;
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			// TODO: handle exception
			return null;
		}

	}

	/**
	 * 渡した従業員情報の扶養人数を変更するSQLを実行するメソッド
	 * 
	 * @param employee
	 */
	public void update(EmployeeForm employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		String updateSql = "UPDATE employees SET dependents_count=:dependentsCount WHERE id=:id";
		template.update(updateSql, param);
	}
}
