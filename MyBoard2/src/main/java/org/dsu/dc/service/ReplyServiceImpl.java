package org.dsu.dc.service;

import java.util.List;

import org.dsu.dc.domain.Criteria;
import org.dsu.dc.domain.ReplyVO;
import org.dsu.dc.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO vo) {
		log.info("[REPLY] Register.... {}",vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("[REPLY] GET.... {}",rno);
		return mapper.read(rno);
	}

	@Override
	public int remove(Long rno) {
		log.info("[REPLY] Remove.... {}",rno);
		return mapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("[REPLY] Modify.... {}",vo);
		return mapper.update(vo);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("[REPLY] GetList.... {}",bno);
		return mapper.getListWithPaging(cri, bno);
	}

	@Override
	public int getTotal(Long bno) throws Exception {
		log.info("[Reply] Count of Reply List ... bno = {}", bno);
		return mapper.getTotalCount(bno);
	}
}
