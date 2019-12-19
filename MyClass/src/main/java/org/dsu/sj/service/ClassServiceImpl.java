package org.dsu.sj.service;

import java.util.List;

import org.dsu.sj.domain.ClassVO;
import org.dsu.sj.domain.Criteria;
import org.dsu.sj.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClassServiceImpl implements ClassService{
	@Autowired
	private ClassMapper mapper;
	
	@Override
	public List<ClassVO> getList(Criteria cri) {

		log.info("[Class Get List with Criteria...] {}", cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public void register(ClassVO cla) {
		log.info("[Class Register...] {}", cla);
		mapper.insertSelectKey(cla);
	}

	@Override
	public boolean modify(ClassVO cla) {
		log.info("[Class Modify...] {}", cla);
		return mapper.update(cla) == 1;
	}
	
	@Override
	public ClassVO get(Long bno) {
		log.info("[Class Get...] bno={}", bno);
		return mapper.read(bno);
	}

}
