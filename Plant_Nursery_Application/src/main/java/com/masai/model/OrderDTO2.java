package com.masai.model;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class OrderDTO2 {
	
	private Integer orderId;
	private LocalDate localdate;
	
	private String transactionMode;
	private Integer quantity;
	private Integer totalcost;
	

}