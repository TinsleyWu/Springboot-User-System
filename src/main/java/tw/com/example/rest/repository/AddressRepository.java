package tw.com.example.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.example.rest.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, String> {
	
	public List <AddressEntity> findByPkIden(String iden);

}
