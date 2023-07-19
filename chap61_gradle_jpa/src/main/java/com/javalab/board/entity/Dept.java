package com.javalab.board.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_dept")
public class Dept {

	@Id
	@Column(length = 20, nullable = false)
	private String deptId;
	
	@Column(length = 50, nullable = false)
	private String deptName;
	
	/*
	 * cascade = CascadeType.REMOVE 조건 제외
	 *  - 부서가 삭제되면 해당 부서에 소속된 User Entity도 같이 삭제됨으로 위험
	 * mappedBy : User 엔티티를 참조하긴 하지만 엔티티와 외래키로 맺어지진 않는다. 단순히 참조만 할 뿐이다.
	 * dept : User Entity에 있는 Dept dept 멤버 변수 이름
	 */
	@OneToMany(mappedBy = "dept")
	private List<User> userList;
}
