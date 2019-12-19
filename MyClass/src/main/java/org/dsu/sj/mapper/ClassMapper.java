package org.dsu.sj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.dsu.sj.domain.ClassVO;
import org.dsu.sj.domain.Criteria;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClassMapper {
	List<ClassVO> getListWithPaging(Criteria cri);
	
	void insertSelectKey(ClassVO cla);
	
	int update(ClassVO cla);
	
	ClassVO read(Long bno);
}
