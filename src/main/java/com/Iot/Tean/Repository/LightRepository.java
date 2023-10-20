package com.Iot.Tean.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Iot.Tean.Model.Light;

public interface LightRepository extends JpaRepository<Light,Integer>{
	@Query(value = "SELECT * FROM light  WHERE id BETWEEN ?1 AND ?2 ", nativeQuery = true)
	List<Light> getArrayCommand(int from , int to);
}
