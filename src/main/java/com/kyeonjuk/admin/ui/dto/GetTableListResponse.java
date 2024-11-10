package com.kyeonjuk.admin.ui.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTableListResponse<T> {

    private int totalCount;
    private List<T> tableData;

}
