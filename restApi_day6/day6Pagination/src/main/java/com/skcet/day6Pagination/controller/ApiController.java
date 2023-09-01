package com.skcet.day6Pagination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skcet.day6Pagination.model.Child;
import com.skcet.day6Pagination.service.ChildService;

@RestController
@RequestMapping("api/v1/controller")
public class ApiController {

	@Autowired
	private ChildService childservice;
	
	@PostMapping("/add")
	public ResponseEntity<String> addValue(@RequestBody  Child child)
	{
		boolean dataSaved=childservice.addValues(child);
		if(dataSaved)
		{
			return ResponseEntity.status(200).body("Successfully added");
		}
		else
		{
			return ResponseEntity.status(404).body("Not Successfully added");	
		}
	}
	@GetMapping("/get/{babyFirstName}")
	public ResponseEntity<List<Child>> getValueBybabyFirstName(@PathVariable String babyFirstName)
	{
		return ResponseEntity.status(200).body(childservice.getValues(babyFirstName));
	}
	
	@GetMapping("/getpages")
	public ResponseEntity<Page<Child>> getPage(
			@RequestParam(defaultValue = "0") int offset, 
			@RequestParam(defaultValue = "3") int limit)
	{
		PageRequest pagerequest=PageRequest.of(offset, limit);
		Page<Child> child=childservice.getPages(pagerequest);
		if(child.getTotalElements()>0)
		{
			return ResponseEntity.status(200).body(child);
		}
		else
		{
			return ResponseEntity.status(404).body(null);
		}
	}
	
	@GetMapping("/getpages/{babyFirstName}")
	public ResponseEntity<Page<Child>> getPagewithfield(
			@RequestParam(defaultValue = "0") int offset, 
			@RequestParam(defaultValue = "3") int limit,
			@RequestParam String babyFirstName
	        )
	{
		PageRequest pagerequest=PageRequest.of(offset, limit);
		Page<Child> child=childservice.getPages(pagerequest);
		if(child.getTotalElements()>0)
		{
			return ResponseEntity.status(200).body(child);
		}
		else
		{
			return ResponseEntity.status(404).body(null);
		}
	}
	
	@GetMapping("/getpagess")
	public ResponseEntity<Page<Child>> getPagewithsort(
			@RequestParam(defaultValue = "0") int offset, 
			@RequestParam(defaultValue = "3") int limit,
			@RequestParam(defaultValue = "babyFirstName") String sortField,
			@RequestParam(defaultValue = "asc") String sortOrder
	        )
	{
		PageRequest pagerequest=PageRequest.of(offset, limit,Sort.by(Sort.Direction.fromString(sortOrder),sortField));
		Page<Child> child=childservice.getPages(pagerequest);
		if(child.getTotalElements()>0)
		{
			return ResponseEntity.status(200).body(child);
		}
		else
		{
			return ResponseEntity.status(404).body(null);
		}
	}
	
}
