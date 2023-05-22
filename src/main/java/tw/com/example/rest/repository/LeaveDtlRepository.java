package tw.com.example.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.example.rest.entity.LeaveDtlEntity;
import tw.com.example.rest.entity.LeaveEntity;

public interface LeaveDtlRepository extends JpaRepository<LeaveDtlEntity, String> {
	
	public List<LeaveDtlEntity> findByPkIden(String iden);

}
