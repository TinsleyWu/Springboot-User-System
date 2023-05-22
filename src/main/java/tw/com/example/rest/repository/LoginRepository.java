package tw.com.example.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.example.rest.entity.LoginEntity;


public interface LoginRepository extends JpaRepository<LoginEntity, String>{
	
	public Optional<LoginEntity> findByIden(String iden);
	
	public Optional<LoginEntity> findByToken(String token);

}
