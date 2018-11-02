package com.neo.story.sample.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.neo.story.dao.AbstractDAO;
import com.neo.story.dto.BootstrapDto;


@Repository("bootstrapDAO")
public class BootstrapDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<BootstrapDto> bootstrapList(BootstrapDto bootstrapDto){
		return (List<BootstrapDto>)selectList("sample.bootstrap.bootstrapList", bootstrapDto);
	}
	
	public BootstrapDto bootstrapDetail(BootstrapDto bootstrapDto){
		return (BootstrapDto) selectOne("sample.bootstrap.bootstrapDetail", bootstrapDto);
	}

	public void bootstrapInsert(BootstrapDto bootstrapDto){
		insert("sample.bootstrap.bootstrapInsert", bootstrapDto);
	}

	public void bootstrapUpdate(BootstrapDto bootstrapDto){
		update("sample.bootstrap.bootstrapUpdate", bootstrapDto);
	}

	public void bootstrapDelete(BootstrapDto bootstrapDto){
		update("sample.bootstrap.bootstrapDelete", bootstrapDto);
	}
	
	public String getSeq(HashMap<String, String> map){
		return (String) selectOne("sample.bootstrap.autoSeq", map);
	}
	
}
