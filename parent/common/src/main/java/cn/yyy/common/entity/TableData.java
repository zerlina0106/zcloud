package cn.yyy.common.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class TableData<T> implements Serializable {
    private Long total;
    private List<T> rows;
}
