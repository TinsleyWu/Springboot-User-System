package tw.com.example.rest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tw.com.example.rest.entity.HrSysCodeEntity;
import tw.com.example.rest.entity.HrSysCodePkey;

public interface HrSysCodeRepository extends CrudRepository<HrSysCodeEntity, HrSysCodePkey> {
	
	public List<HrSysCodeEntity> findByPkGp(String gp);
	
}
