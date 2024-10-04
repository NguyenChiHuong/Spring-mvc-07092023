package com.javaweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.model.NewModel;

@Repository
public interface INewDao extends GenericDAO<NewModel> {
	List<NewModel> findAll();
}
