package cn.com.lrh.export.structure.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TableStructureMapper {

	List<Map<String, String>> queryStructureByTableName(@Param("schemaName")String schemaName,@Param("tableName")String tableName);
	
	List<Map<String,Object>>queryTablesBySchemaName(@Param("schemaName")String schemaName);
}
