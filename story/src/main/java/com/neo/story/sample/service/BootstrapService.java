package com.neo.story.sample.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neo.story.dto.BootstrapDto;

import net.sf.json.JSONObject;



public interface BootstrapService {
	
	public List<BootstrapDto> bootstrapList(BootstrapDto bootstrapDto);
	public BootstrapDto bootstrapDetail(BootstrapDto bootstrapDto);
	public JSONObject bootstrapInsert(BootstrapDto bootstrapDto, HttpServletRequest request);
	public JSONObject bootstrapUpdate(BootstrapDto bootstrapDto, HttpServletRequest request);
	public JSONObject bootstrapDelete(BootstrapDto bootstrapDto, HttpServletRequest request);
	public String getSeq(HashMap<String, String> map);
	
}
