package com.neo.story.sample.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.neo.story.dao.AbstractDAO;
import com.neo.story.dto.BootstrapDto;


@Repository("bootstrapDAO")
public class BootstrapDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<BootstrapDto> bootstrapList(BootstrapDto bootstrapDto) throws Exception{
		return (List<BootstrapDto>)selectList("sample.bootstrap.bootstrapList", bootstrapDto);
	}
	
	public BootstrapDto bootstrapDetail(BootstrapDto bootstrapDto) throws Exception{
		return (BootstrapDto) selectOne("sample.bootstrap.bootstrapDetail", bootstrapDto);
	}

	public void bootstrapInsert(BootstrapDto bootstrapDto) throws Exception{
		insert("sample.bootstrap.bootstrapInsert", bootstrapDto);
	}

	public void bootstrapUpdate(BootstrapDto bootstrapDto) throws Exception{
		update("sample.bootstrap.bootstrapUpdate", bootstrapDto);
	}

	public void bootstrapDelete(BootstrapDto bootstrapDto) throws Exception{
		update("sample.bootstrap.bootstrapDelete", bootstrapDto);
	}
	
	public String getSeq(HashMap<String, String> map) throws Exception{
		return (String) selectOne("sample.bootstrap.autoSeq", map);
	}
	
}
