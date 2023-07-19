package com.javalab.board.vo;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo {

	private String productId;	//상품ID
	private String name;		//상품명
	private Long price;		//상품가격(단가)
	private LocalDateTime receiptDate;	//입고일(수령일)
}
