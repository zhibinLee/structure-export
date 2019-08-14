package cn.com.lrh.export.structure.service;

import java.util.List;
import java.util.Map;


public interface TableStructureService {

	List<Map<String, String>> queryStructureByTableName(String schemaName,String tableName);
	List<Map<String,Object>>queryTablesBySchemaName(String schemaName);
}
