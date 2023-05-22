package tw.com.example.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.example.rest.entity.SysStaffEntity;

public interface SysStaffRepository extends JpaRepository<SysStaffEntity, String> {
	
	public Optional<SysStaffEntity> findByIden(String iden);

}
