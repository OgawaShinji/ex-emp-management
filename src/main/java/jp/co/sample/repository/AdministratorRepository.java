package jp.co.sample.repository;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * 管理者情報を操作するリポジトリクラス
 * 
 * @author rpuser
 *
 */
@Repository
public class AdministratorRepository {

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setName(rs.getString("mail_address"));
		administrator.setName(rs.getString("password"));
		return administrator;
	};
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 渡した管理者情報を保存
	 * 
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		String insertSql = "INSERT INTO administrators(name,mail_address,password)"
				+ " VALUES(:name,:mailAddress,:password)";
		template.update(insertSql, param);
	}

	/**
	 * メールアドレスとパスワードから管理者検索を行う
	 * 
	 * @param mailAddress
	 * @param password
	 * @return 検索された管理者
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,password FROM administrators"
				+ " WHERE mail_address=:mailAddress AND password=:password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
				password);
		Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if (Objects.isNull(administrator)) {
			return null;
		} else {
			return administrator;
		}
	}

}
