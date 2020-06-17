package jp.co.sample.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository administratorRepository;

	/**
	 * 渡された管理者情報を挿入
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	public Administrator login(String mailAddress,String password) {
		if(Objects.isNull(administratorRepository.findByMailAddressAndPassword(mailAddress, password))) {
			return null;
		}else {
			return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
		}
	}
	
}
