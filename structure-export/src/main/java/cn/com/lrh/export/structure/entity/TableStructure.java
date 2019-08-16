package cn.com.lrh.export.structure.entity;

import lombok.Data;

@Data
public class TableStructure {

	private String columnName;
	private String columnType;
	private String columnDefault;
	private String columnMaxLength;
	private String isNullable;
	private String isPrimaryKey;
	
	
}
