package com.neo.story.sample.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neo.story.dto.BootstrapDto;

import net.sf.json.JSONObject;



public interface BootstrapService {
	
	public List<BootstrapDto> bootstrapList(BootstrapDto bootstrapDto) throws Exception;
	public BootstrapDto bootstrapDetail(BootstrapDto bootstrapDto) throws Exception;
	public JSONObject bootstrapInsert(BootstrapDto bootstrapDto, HttpServletRequest request) throws Exception;
	public JSONObject bootstrapUpdate(BootstrapDto bootstrapDto, HttpServletRequest request) throws Exception;
	public JSONObject bootstrapDelete(BootstrapDto bootstrapDto, HttpServletRequest request) throws Exception;
	public String getSeq(HashMap<String, String> map) throws Exception;
	
}
