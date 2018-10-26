package com.neo.story.sample.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.neo.story.dao.Dao;
import com.neo.story.dto.BootstrapDto;

import net.sf.json.JSONObject;


@Service("bootstrapService")
public class BootstrapServiceImpl implements BootstrapService{

	private static final Logger logger = LoggerFactory.getLogger(BootstrapServiceImpl.class);
	
	@Autowired
	private Dao dao;

	@Value("#{config['TABLESC']}") String TABLESC;
	@Value("#{config['TABLE_T_BOOTSTRAP']}") String TABLE_T_BOOTSTRAP;
	
	@Override
	public List<BootstrapDto> bootstrapList(BootstrapDto bootstrapDto) {
		String sql = "sample.bootstrap.bootstrapList";
		
		List<BootstrapDto> result = (List<BootstrapDto>) dao.list(sql, bootstrapDto);
		return result;
	}
	
	@Override
	public BootstrapDto bootstrapDetail(BootstrapDto bootstrapDto) {
		String sql = "sample.bootstrap.bootstrapDetail";
		
		BootstrapDto result = (BootstrapDto) dao.detail(sql, bootstrapDto);
		return result;
	}

	@Override
	public JSONObject bootstrapInsert(BootstrapDto bootstrapDto, HttpServletRequest request) {
		String sql = "sample.bootstrap.bootstrapInsert";
		String sql2 = "sample.bootstrap.autoSeq";

		JSONObject json = new JSONObject();
		
		String autoSeq = "";
		//BOARD autoincre 값 가져옴
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("tableSC", TABLESC);
		map.put("tableNM", TABLE_T_BOOTSTRAP);
		
		try {
			autoSeq = dao.getString(sql2, map);		
			dao.insert(sql, bootstrapDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//화면단에 넘겨줌
		json.put("autoSeq", autoSeq);
		
		return json;
	}

	@Override
	public JSONObject bootstrapUpdate(BootstrapDto bootstrapDto, HttpServletRequest request) {
		String sql = "sample.bootstrap.bootstrapUpdate";
		
		JSONObject json = new JSONObject();
		
		try {
			dao.update(sql, bootstrapDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}

	@Override
	public JSONObject bootstrapDelete(BootstrapDto bootstrapDto, HttpServletRequest request) {
		String sql = "sample.bootstrap.bootstrapDelete";

		JSONObject json = new JSONObject();
		
		try {
			dao.delete(sql, bootstrapDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}

	@Override
	public String getString(HashMap<String, String> map) {
		String sql = "sample.sample.autoSeq";
		return dao.getString(sql, map);
	}

}
