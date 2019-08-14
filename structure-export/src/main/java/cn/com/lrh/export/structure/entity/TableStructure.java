package cn.com.lrh.export.structure.entity;


public class TableStructure {

	private String columnName;
	private String columnType;
	private String columnDefault;
	private String columnMaxLength;
	private String isNullable;
	private String isPrimaryKey;
	
	@Override
	public String toString() {
		return "TableStructure [columnName=" + columnName + ", columnType=" + columnType + ", columnDefault="
				+ columnDefault + ", columnMaxLength=" + columnMaxLength + ", isNullable=" + isNullable
				+ ", isPrimaryKey=" + isPrimaryKey + ", columnComment=" + columnComment + "]";
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getColumnDefault() {
		return columnDefault;
	}
	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}
	public String getColumnMaxLength() {
		return columnMaxLength;
	}
	public void setColumnMaxLength(String columnMaxLength) {
		this.columnMaxLength = columnMaxLength;
	}
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	public String getIsPrimaryKey() {
		return isPrimaryKey;
	}
	public void setIsPrimaryKey(String isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	private String columnComment;
	
	
}
