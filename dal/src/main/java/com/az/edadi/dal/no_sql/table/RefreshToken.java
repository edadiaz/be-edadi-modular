package com.az.edadi.dal.no_sql.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
 public class RefreshToken {

     private String tokenId;

     private String userId;

     private LocalDate startDate;


}
