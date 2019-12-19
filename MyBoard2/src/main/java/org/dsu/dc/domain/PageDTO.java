package org.dsu.dc.domain;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
@Slf4j
public class PageDTO {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	private int displayPageNum = 10;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		endPage = (int)(Math.ceil(cri.getPageNum() * 1.0 / displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int)(Math.ceil(total * 1.0 / cri.getAmount()));
		log.info("cri.getAmount() : {}",cri.getAmount()); 
		if(endPage > tempEndPage)
			endPage = tempEndPage;
		
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getAmount() >= total ? false : true;
	}
}
