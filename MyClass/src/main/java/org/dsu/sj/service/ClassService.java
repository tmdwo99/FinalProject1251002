package org.dsu.sj.service;

import java.util.List;

import org.dsu.sj.domain.ClassVO;
import org.dsu.sj.domain.Criteria;

public interface ClassService {
	List<ClassVO> getList(Criteria cri);
	
	void register(ClassVO cla);
	
	boolean modify(ClassVO cla);
	
	ClassVO get(Long bno);
}
