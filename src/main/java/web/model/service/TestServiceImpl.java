package web.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.model.jpa.entities.Test;
import web.model.jpa.repos.TestRepo;

@Service("testService")
public class TestServiceImpl implements TestService{

	@Autowired
	TestRepo testRepo;
	
	public String getValue(Integer id) {
		Test test = testRepo.findOne(id);
		return test.getValue();
	}

}
