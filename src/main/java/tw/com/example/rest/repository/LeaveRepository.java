package tw.com.example.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.example.rest.entity.AddressEntity;
import tw.com.example.rest.entity.HrSysCodeEntity;
import tw.com.example.rest.entity.LeaveEntity;

public interface LeaveRepository extends JpaRepository<LeaveEntity, String> {
	
	public List<LeaveEntity> findByPkIden(String iden);

}
