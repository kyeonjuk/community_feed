package com.kyeonjuk.common.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pageable {

    private int pageIndex;
    private int pageSize;   // 한페이지 당 출력될 데이터 개수

    public Pageable() {
        this.pageIndex = 1;
        this.pageSize = 10;
    }

    public Pageable(int pageIndex, int pageSize) {
        if (pageIndex < 1) {
            throw new IllegalArgumentException("페이지 인덱스는 1 이상이여야 합니다.");
        }
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (pageIndex - 1) * pageSize;
    }

    public int getLimit() {
        return pageSize;
    }
}
