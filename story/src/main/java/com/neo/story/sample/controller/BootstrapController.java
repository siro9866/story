package com.neo.story.sample.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neo.story.dto.BootstrapDto;
import com.neo.story.sample.service.BootstrapService;
import com.neo.story.util.CommonUtil;
import com.neo.story.util.PagingView;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/sample")
public class BootstrapController {

	private static final Logger logger = LoggerFactory.getLogger(BootstrapController.class);
	
	@Resource(name="bootstrapService")
	private BootstrapService bootstrapService;
	
	@RequestMapping(value="/bootstrapList")
	public ModelAndView bootstrapList(@ModelAttribute BootstrapDto bootstrapDto, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		List<BootstrapDto> resultList = null;
		resultList = bootstrapService.bootstrapList(bootstrapDto);
		
		if(resultList.size() > 0){
			//paging		
			BootstrapDto pg = resultList.get(0);
			PagingView pv = new PagingView(pg.getPageNum(), bootstrapDto.getPageSize(), bootstrapDto.getBlockSize(), pg.getTotCnt());
			mav.addObject("paging", pv.print());
			//paging		
		}
		
		logger.info("getSearchFromDt:"+bootstrapDto.getSearchFromDt());
		logger.debug("getSearchToDt:"+bootstrapDto.getSearchToDt());
		logger.debug("bootstrapDto:"+bootstrapDto.toString());
		
		mav.addObject("paramDto", bootstrapDto);
		mav.addObject("resultList", resultList);
		
		mav.setViewName("/sample/bootstrapList");
		
		return mav;
	}
	
	@RequestMapping(value="/bootstrapDetail")
	public ModelAndView bootstrapDetail(@ModelAttribute BootstrapDto bootstrapDto, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/sample/bootstrapDetail");
		BootstrapDto result = null;
		
		try {
			//게시글정보
			result = bootstrapService.bootstrapDetail(bootstrapDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mav.addObject("paramDto", bootstrapDto);
		mav.addObject("result", result);
		
		return mav;
	}
	
	@RequestMapping(value="/bootstrapDetailLayer")
	@ResponseBody
	public ModelAndView bootstrapDetailLayer(@ModelAttribute BootstrapDto bootstrapDto, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/sample/bootstrapDetailLayer");
		BootstrapDto result = null;
		
		try {
			//게시글정보
			result = bootstrapService.bootstrapDetail(bootstrapDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject("result", result);
		return mav;
	}
	
	@RequestMapping(value="/bootstrapForm")
	public ModelAndView bootstrapForm(@ModelAttribute BootstrapDto bootstrapDto, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/sample/bootstrapForm");
		
		BootstrapDto result = bootstrapService.bootstrapDetail(bootstrapDto);
		
		mav.addObject("paramDto", bootstrapDto);
		mav.addObject("result", result);
		return mav;
	}	
	
	@RequestMapping(value="/bootstrapInsert")
	public ModelAndView bootstrapInsert(@ModelAttribute BootstrapDto bootstrapDto, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		ModelAndView mav = new ModelAndView();
		JSONObject json = new JSONObject();
		mav.setViewName("jsonView");
		CommonUtil.getReturnCodeFail(json);
		
		try {
			//사용자정보
			CommonUtil.setInUserInfo(request, bootstrapDto);		
			CommonUtil.setUpUserInfo(request, bootstrapDto);
			json = bootstrapService.bootstrapInsert(bootstrapDto, request);
			//저장 성공시 코드값 세팅
			CommonUtil.getReturnCodeSuc(json);
			//저장후 페이지 이동
			json.put("goUrl", "/sample/bootstrapList.do");
		} catch (Exception e) {
			//저장 실패시 코드값 세팅
			logger.info("EXCEPTION insert E:" + e.toString());
			CommonUtil.getReturnCodeFail(json);
		}
		//결과값 전송
		logger.info(json.toString());
		mav.addObject("resultJson", json);
		return mav;
		
	}
	
	@RequestMapping(value="/bootstrapUpdate")
	public ModelAndView bootstrapUpdate(@ModelAttribute BootstrapDto bootstrapDto, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		ModelAndView mav = new ModelAndView();
		JSONObject json = new JSONObject();
		mav.setViewName("jsonView");
		CommonUtil.getReturnCodeFail(json);
		
		try {
			
			//사용자정보
			CommonUtil.setUpUserInfo(request, bootstrapDto);
			json = bootstrapService.bootstrapUpdate(bootstrapDto, request);
			//저장 성공시 코드값 세팅
			CommonUtil.getReturnCodeSuc(json);
			//저장후 페이지 이동
//			json.put("goUrl", "/sample/detail.do?board_id="+bootstrapDto.getBoard_id() + "&board_gbn=" + bootstrapDto.getBoard_gbn());
			json.put("goUrl", "/sample/bootstrapList.do");
		} catch (Exception e) {
			//저장 실패시 코드값 세팅
			logger.info("EXCEPTION insert E:" + e.toString());
			CommonUtil.getReturnCodeFail(json);
		}
		//결과값 전송
		logger.info(json.toString());
		mav.addObject("resultJson", json);
		return mav;
		
	}
	
	@RequestMapping(value="/bootstrapDelete")
	@ResponseBody
	public ModelAndView bootstrapDelete(@ModelAttribute BootstrapDto bootstrapDto, HttpServletRequest request, HttpServletResponse response, HttpSession session){
		JSONObject json = new JSONObject();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jsonView");
		try {
			//사용자정보
			CommonUtil.setUpUserInfo(request, bootstrapDto);			
			json = bootstrapService.bootstrapDelete(bootstrapDto, request);
			CommonUtil.getReturnCodeSuc(json);
			json.put("goUrl", "/sample/bootstrapList.do");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("PROGRAM_Exception:"+e);
			CommonUtil.getReturnCodeFail(json);
		}
		
		//결과값 전송
		logger.info(json.toString());
		mav.addObject("resultJson", json);
		return mav;
		
	}
	
}