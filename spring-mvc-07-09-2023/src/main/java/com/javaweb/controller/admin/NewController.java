package com.javaweb.controller.admin;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.dto.NewDTO;
import com.javaweb.service.ICategoryService;
import com.javaweb.service.INewService;
import com.javaweb.util.MessageUtil;

@Controller(value = "newControllerOfAdmin")
public class NewController {
	
	@Autowired
	private INewService newService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page,
									@RequestParam("limit") int limit,HttpServletRequest request)  {
		ModelAndView mav = new ModelAndView("admin/new/list");
		NewDTO model = new NewDTO();
		model.setPage(page);
		model.setLimit(limit);
		Pageable pageable = new PageRequest(page - 1, limit);//từ vị trí thứ 0,giới hạn =2
		model.setListResult(newService.findAll(pageable));// findAll dựa trên phân trang
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));//tổng page = tổng số item / 2
		if(request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message",message.get("message"));
			mav.addObject("alert",message.get("alert"));
		}
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editNew(@RequestParam(value = "id",required = false) Long id,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/new/edit");
		NewDTO model = new NewDTO();
		if(id != null) { // khi cập nhật
			model = newService.findById(id);// find 1 phần tử lên client 
		}
		if(request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message",message.get("message"));
			mav.addObject("alert",message.get("alert"));
		}
		mav.addObject("categories", categoryService.findAll());
		mav.addObject("model", model);
		return mav;
	}
}
