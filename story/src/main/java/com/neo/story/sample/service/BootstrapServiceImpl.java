package com.neo.story.sample.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.neo.story.dto.BootstrapDto;
import com.neo.story.sample.dao.BootstrapDAO;

import net.sf.json.JSONObject;


@Service("bootstrapService")
public class BootstrapServiceImpl implements BootstrapService{

	private static final Logger logger = LoggerFactory.getLogger(BootstrapServiceImpl.class);
	
	@Resource(name="bootstrapDAO")
	private BootstrapDAO bootstrapDAO;

	@Value("#{config['TABLESC']}") String TABLESC;
	@Value("#{config['TABLE_T_BOOTSTRAP']}") String TABLE_T_BOOTSTRAP;
	
	@Override
	public List<BootstrapDto> bootstrapList(BootstrapDto bootstrapDto) throws Exception {
		return bootstrapDAO.bootstrapList(bootstrapDto);
	}
	
	@Override
	public BootstrapDto bootstrapDetail(BootstrapDto bootstrapDto) throws Exception {
		return (BootstrapDto) bootstrapDAO.bootstrapDetail(bootstrapDto);
	}

	@Override
	public JSONObject bootstrapInsert(BootstrapDto bootstrapDto, HttpServletRequest request) throws Exception {

		JSONObject json = new JSONObject();
		
		String autoSeq = "";
		//BOARD autoincre 값 가져옴
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("tableSC", TABLESC);
		map.put("tableNM", TABLE_T_BOOTSTRAP);
		
		try {
			autoSeq = bootstrapDAO.getSeq(map);		
			bootstrapDAO.bootstrapInsert(bootstrapDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//화면단에 넘겨줌
		json.put("autoSeq", autoSeq);
		
		return json;
	}

	@Override
	public JSONObject bootstrapUpdate(BootstrapDto bootstrapDto, HttpServletRequest request) throws Exception {
		
		JSONObject json = new JSONObject();
		
		try {
			bootstrapDAO.bootstrapUpdate(bootstrapDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}

	@Override
	public JSONObject bootstrapDelete(BootstrapDto bootstrapDto, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		
		try {
			bootstrapDAO.bootstrapDelete(bootstrapDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}

	@Override
	public String getSeq(HashMap<String, String> map) throws Exception {
		return bootstrapDAO.getSeq(map);
	}

}
