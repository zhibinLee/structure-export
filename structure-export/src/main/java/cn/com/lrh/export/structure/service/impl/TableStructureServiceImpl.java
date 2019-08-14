package cn.com.lrh.export.structure.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.lrh.export.structure.mapper.TableStructureMapper;
import cn.com.lrh.export.structure.service.TableStructureService;

@Service
public class TableStructureServiceImpl implements TableStructureService {

	@Resource
	private TableStructureMapper tableStructureMapper;
	@Override
	public List<Map<String, String>> queryStructureByTableName(String schemaName, String tableName) {
		return tableStructureMapper.queryStructureByTableName(schemaName, tableName);
	}
	@Override
	public List<Map<String, Object>> queryTablesBySchemaName(String schemaName) {
		
		return tableStructureMapper.queryTablesBySchemaName(schemaName);
	}

}
