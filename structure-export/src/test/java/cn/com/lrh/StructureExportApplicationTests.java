package cn.com.lrh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.lrh.export.structure.service.TableStructureService;
import cn.com.lrh.export.util.ExportExcel;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@MapperScan("cn.com.lrh.export.structure.mapper")
public class StructureExportApplicationTests {

	private static Set<String> skipSet = new HashSet<>();;
	{
		skipSet.add("DATABASECHANGELOG");
		skipSet.add("DATABASECHANGELOGLOCK");
	}

	@Resource
	private TableStructureService structureService;

	@Test
	public void contextLoads() throws IOException {
		String schemaName = "bim-dev"; 
		
		String[] titles = {"列名", "数据类型", "默认值", "长度", "允许空值", "索引类型", "备注"};
    	String[] columns = {"COLUMN_NAME", "COLUMN_TYPE", "COLUMN_DEFAULT", "CHARACTER_MAXIMUM_LENGTH", "IS_NULLABLE", "COLUMN_KEY", "COLUMN_COMMENT"};
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<Map<String, Object>> tablessList = structureService.queryTablesBySchemaName(schemaName);
		for (Map<String, Object> map : tablessList) { 
			if (skipSet.contains(map.get(ExportExcel.TABLE_NAME).toString())) {
				continue;
			}
			//System.out.println(ExportExcel.outputFormat(map.get(ExportExcel.TABLE_NAME).toString(),map.get(ExportExcel.TABLE_COMMENT).toString()));
			List<Map<String, String>> list = structureService.queryStructureByTableName(schemaName,map.get(ExportExcel.TABLE_NAME).toString());
			map.put(ExportExcel.COLUMN_LIST, list);
			dataList.add(map);
		}
		ExportExcel.createExcel(dataList, titles, columns, 3, 3);
	}

}
