package com.application.serviceImpl;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.application.Entitys.Department;
import com.application.Entitys.Position;
import com.application.Entitys.Role;
import com.application.Entitys.User;
import com.application.Util.FileUtil;
import com.application.Util.MyUserPrincipal;
import com.application.reposity.DepartmentRepository;
import com.application.reposity.PositionRepository;
import com.application.reposity.RoleRepository;
import com.application.reposity.UserRepository;
import com.application.service.UserService;




@Service
public class UserServiceImpl implements UserService,UserDetailsService{

	@Autowired
	private UserRepository userRepository; 
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired 
	private PositionRepository positionRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	
	private Role role;
	
	private Position position;
	
	private Department department;
	
	/*
	 * 根据账号和密码来进行查询，验证是否存在该对象
	 * 用于登录验证和主页服务需要
	 */
	public User queryForLogin(String  countId,String password) {
		
		
		return this.userRepository.queryByName(countId, password);
	
	}
	
	public User queryForRegistry(String countId, String password) {
		
		if(userRepository.queryByCountId(countId)==null)
		{
			User user=new User();
			user.setCountId(countId);
			user.setPassword(password);
			return user;
		}
		return null;
	}
	
	
	
	/*
	 * 根据用户账号来查询
	 * 用于注册验证服务需要
	 */
	public User queryByCountId(String  countId) {
		
		return userRepository.queryByCountId(countId);
	}

	

	/*
	 * 保存一个账号，用于注册服务需要
	 * 
	 */
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userRepository.saveAndFlush(user);
		
	}

	public List<User> getAll() {
		
		return userRepository.findAll();
	}

	

	public void deleteUser(int id) {
		
		userRepository.deleteById(id);
	}

	public List<User> queryUserByUserName(String userName) {
		List<User> users=userRepository.queryByUserName(userName);
		return users;
	}

	public void savePicture(String countId,MultipartFile file) {
		
		
			//获取文件名称，包含后缀
			String fileName=file.getOriginalFilename();
			System.out.println(fileName);
			System.out.println(file.getContentType());
			
			//存放在这个路径下，该路径在工程的static文件下
			//通过浏览器输入本地服务器地址加文件后缀名就可以访问该文件
			String path=ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/img";
			
			try {
				FileUtil.fileupload(file.getBytes(), path, fileName);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println(fileName);
			User user=userRepository.queryByCountId(countId);
			Optional<User> optional=userRepository.findById(user.getId());
			User user2=optional.get();
			user2.setPath("img/"+fileName);
			userRepository.saveAndFlush(user2);
	}

	public void updateUser(int id, String role, String position, String department) {
		Optional<User>optional=userRepository.findById(id);
		User user=optional.get();
		user.setRole(role);
		user.setPosition(position);
		user.setDepartment(department);
		userRepository.saveAndFlush(user);
	}

	
	/*
	 *根据用户id来修改密码
	 */
	public void updatePassword(int id, String password) {
		
		User user=userRepository.findById(id).get();
		user.setPassword(password);
		userRepository.saveAndFlush(user);
		
	}

	
	//用户修改用户信息
	public void editUser(int id, String userName, String sex, String telephone, String personalDescription,
			String email) {
		User user=userRepository.findById(id).get();
		user.setUserName(userName);
		user.setSex(sex);
		user.setTelephone(telephone);
		user.setPersonalDescription(personalDescription);
		user.setEmail(email);
		userRepository.saveAndFlush(user);
		
	}

	//在角色中添加用户
	public boolean roleAndPositionAddUser(User user) {
		
		
		
		if(roleRepository.queryByRoleName(user.getRole()).size()==0||positionRepository.queryByName(user.getPosition()).size()==0)
		{
			return false;
		}
		
		
		role=roleRepository.queryByRoleName(user.getRole()).get(0);
		position=positionRepository.queryByName(user.getPosition()).get(0);
		department=departmentRepository.queryByName(user.getDepartment()).get(0);
		
		
		user.setPower(role.getPower());
		userRepository.save(user);
		try {
		role.getUsers().add(user);
		role.setNum(role.getNum()+1);
		roleRepository.saveAndFlush(role);
		
		position.setUserNumber(position.getUserNumber()+1);
		position.getUsers().add(user);
		positionRepository.saveAndFlush(position);
		
		department.setUserNumber(department.getUserNumber()+1);
		departmentRepository.saveAndFlush(department);
		} catch (Exception e) {
			
			return false;
		}
			
		return true;
	}

	//导出用户信息
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.queryByCountId(username);
		if(user==null)
		{
			throw new UsernameNotFoundException(username);
		}
		return new MyUserPrincipal(user);
		
	}

	

	
	
	
	

}





