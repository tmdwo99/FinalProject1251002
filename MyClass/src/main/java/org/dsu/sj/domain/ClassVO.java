package org.dsu.sj.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ClassVO {

	private long bno;
	private long haknum;
	private String hakname;
	private long pcnum;
	private Date startDate;
	private Date endDate;
}
