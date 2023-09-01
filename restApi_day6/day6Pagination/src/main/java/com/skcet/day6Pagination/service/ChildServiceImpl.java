package com.skcet.day6Pagination.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.skcet.day6Pagination.model.Child;
import com.skcet.day6Pagination.repository.ChildRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ChildServiceImpl implements ChildService
{
	@Autowired
	private ChildRepo childrepo;

	@Override
	public boolean addValues(Child child) {
		// TODO Auto-generated method stub
		boolean dataExist=childrepo.existsByBabyId(child.getBabyId());
		if(!dataExist)
		{
			childrepo.save(child);
			return true;  
		}
		else
		return false;
	}

	@Override
	public List<Child> getValues(String babyFirstName) {
		// TODO Auto-generated method stub
		return childrepo.findByBabyFirstName(babyFirstName);
	}

	@Override
	public Page<Child> getPages(PageRequest pagerequest) {
		// TODO Auto-generated method stub
		return childrepo.findAll(pagerequest);
	}

}
