package com.javaweb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.dao.INewDao;
import com.javaweb.mapper.NewMapper;
import com.javaweb.model.NewModel;

@Repository
public class NewDao extends AbstractDAO<NewModel> implements INewDao {
	
	@Override
	public List<NewModel> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		return query(sql.toString(), new NewMapper());
	}

}
