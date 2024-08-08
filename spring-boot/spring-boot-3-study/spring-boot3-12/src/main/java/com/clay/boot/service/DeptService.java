package com.clay.boot.service;

import com.clay.boot.entity.Dept;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author clay
 */
@Service
public class DeptService {
    Map<Long, Dept> data = new ConcurrentHashMap<Long, Dept>();

    public void deleteDept(Long id) {
        data.remove(id);
    }

    public void saveDept(Dept dept) {
        data.put(dept.getId(), dept);
    }

    public List<Dept> getDepts() {
        return data.values().stream().toList();
    }

    public Dept getDeptById(Long id) {
        return data.get(id);
    }

}
