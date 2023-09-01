package com.skcet.day6Pagination.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.skcet.day6Pagination.model.Child;

public interface ChildService {
       boolean addValues(Child child);
       List<Child> getValues(String babyFirstName);
       Page<Child> getPages(PageRequest pagerequest);
}
