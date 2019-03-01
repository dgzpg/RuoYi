package com.application.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.application.Entitys.Position;
import com.application.Entitys.User;
import com.application.service.PositionService;

@Controller
public class PositionManagerController {

	@Autowired
	private PositionService positionService;
	
	public void alert(HttpServletResponse response) throws IOException 
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('您没有权限进行此操作！')");
		out.println("history.back()");
		out.println("</script>");
	}
	public void alertDelete(HttpServletResponse response) throws IOException 
	{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('删除失败！')");
		out.println("history.back()");
		out.println("</script>");
	}
	
	//添加岗位跳转页面控制
	@GetMapping("/addPosition")
	public String addPosition(HttpServletResponse response,HttpServletRequest request) throws IOException
	{
		User user=(User)request.getSession().getAttribute("user");
		if(user.getPower()>2)
		{
			return "/addPosition";
		}
		else
		{
			alert(response);
			return null;
		}
		
		
	}
	
	//添加岗位控制
	@PostMapping("/getAddPosition")

	public String getAddPosition(@RequestParam("name")String name,@RequestParam("coding")String coding,@RequestParam("department") String department,Model model,Map<String , Object>map)
	{
		Position position=new Position();
		position.setPositionName(name);
		position.setCoding(coding);
		position.setUserNumber(0);
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		position.setCreateTime(simpleDateFormat.format(date));
		if(positionService.addPosition(position, department)==false)
		{
			map.put("msg", "该岗位已存在或无该部门！");
			//return name+coding;
			return "/addPosition";
		}
		else
		{
			return "redirect:mainPage";
		}
		
	}
	
	@GetMapping("/searchPosition")
	public String searchPosition(@RequestParam("name")String name, Model model)
	{
		List<Position>positions=positionService.queryByName(name);
		for(Position position : positionService.queryByCoding(name))
		{
			positions.add(position);
		}
		model.addAttribute("positions", positions);
		
		return "MainPage";
	}
	
	@GetMapping("/deletePosition")
	public String deletePosition(HttpServletResponse response,HttpServletRequest request,@PathParam("id")int id,Map<String , Object>map) throws IOException
	{
		User user=(User)request.getSession().getAttribute("user");
		if(user.getPower()>2)
		{
			if(positionService.deleteById(id)==false)
			{
				alertDelete(response);
			}
			return "redirect:/mainPage";
		}
		else
		{
			alert(response);
			return null;
		}
		
		
	}
	
	//修改跳转控制
	@GetMapping("/edit")
	public String edit(@PathParam("id")int id,Model model,HttpServletResponse response,HttpServletRequest request) throws IOException
	{
		User user=(User)request.getSession().getAttribute("user");
		if(user.getPower()>2)
		{
			Position position=positionService.queryById(id);
			model.addAttribute("position",position);
			return "/editPosition";
		}
		else
		{
			alert(response);
			return null;
		}
		
	}
	
	@PostMapping("/getEdit")
	public String getEdit(@PathParam("id")int id,@RequestParam("name")String name,@RequestParam("Coding")String Coding,Model model,Map<String , Object>map)
	{
		
		if(positionService.editPosition(id, name, Coding))
		{
			return "redirect:mainPage";
		}
		else
		{
			Position position=positionService.queryById(id);
			model.addAttribute("position", position);
			map.put("msg", "修改失败！");
			return "/editPosition";
		}
			
		
	}
}
