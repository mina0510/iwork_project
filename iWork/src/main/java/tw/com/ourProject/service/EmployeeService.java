package tw.com.ourProject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.model.Apart;
import tw.com.ourProject.model.Employee;
import tw.com.ourProject.model.Temptoken;
import tw.com.ourProject.repository.ApartRepo;
import tw.com.ourProject.repository.EmployeeRepo;
import tw.com.ourProject.repository.TempTokenRepo;
import tw.com.ourProject.utils.JWTUtil;
import tw.com.ourProject.utils.UploadFileUtil;

@Service
public class EmployeeService {
	@Autowired
	public EmployeeRepo employeeRepo;
	
	@Autowired
	JWTUtil jwtToken;
	
	@Autowired
	public TempTokenRepo tempTokenRepo;
	
	@Autowired
	public UploadFileUtil uploadPhoto;
	
	@Autowired
	public ApartRepo apartRepo;
	
	public Temptoken tempToken = new Temptoken();

	
	public JSONObject userVerify(String empId ,String passwd) {
		 Optional<Employee> checkId = employeeRepo.findById(empId);
		 JSONObject obj = new JSONObject();

		if(checkId.isEmpty()) {
			obj.put("status", 101);//查無帳號
			obj.put("token", "");
			return obj;		
			}
		
		//帳號密碼正確 -->取得身份 -->回傳token,同時存進資料庫
		if(employeeRepo.findById(empId).get().getPasswd().equals(passwd)) {
			String accountState = employeeRepo.findById(empId).get().getCellphone2();
			if(accountState.equals("100")) {
			HashMap<String, String> userInfo = new HashMap<>();
			userInfo.put("empId", empId);
			userInfo.put("passwd", passwd);
			userInfo.put("adm", employeeRepo.findById(empId).get().getAdm());
			String token = jwtToken.generateToken(userInfo);
			tempToken.setTokenValue(token);//存進tempToken
			tempTokenRepo.save(tempToken);
			obj.put("status", 100);//查無帳號
			obj.put("token", token);
			return obj;		
			}else {
				obj.put("status", 103);//帳號被關閉
				obj.put("token", "");
				return obj;	
			}
		}else {
			obj.put("status", 102);//密碼錯誤
			obj.put("token", "");
			return obj;	
		}
	}
	
	public List<Employee> selectAll() {
		return  employeeRepo.findAll();
	}
	
	public List<Employee> selectByConditionOfAdm(String adm){
		return employeeRepo.findByAdm(adm);
	}
	
	public List<Employee> selectByConditionOfApart(Integer apartId){
		Apart apart = apartRepo.findById(apartId).get();
		return employeeRepo.findByAparts(apart);
	}
	
	public List<Employee> selectByConditions(Integer apartId , String adm){
		Apart apart = apartRepo.findById(apartId).get();
		return employeeRepo.findByApartsAndAdm(apart,adm);
	}
	
	
	@Transactional
	public String verifyToken(String userToken) {
		try {
		jwtToken.validateToken(userToken);
		}catch(AuthException e) {
			System.out.println(e.toString());
			tempTokenRepo.deleteByTokenValue(userToken);
			return "201";
		}
		return "200";
	}

	public JSONObject checkAdm(String userToken) {
		JSONObject obj = new JSONObject();
		obj.put("userAdm", jwtToken.getInfoFromJwtToken(userToken, "adm"));
		return obj;
	}
	
	public JSONArray getEmpsInfo() {
		List<Employee> emps = employeeRepo.findAll();
		JSONArray arry = new JSONArray();
		for(Employee emp : emps) {
			JSONObject obj = new JSONObject();
			obj.put("apart", emp.getAparts().getApart());
			obj.put("empName", emp.getEmpName());
			obj.put("empTel", emp.getTel());
			obj.put("empPhone", emp.getCellphone1());
			obj.put("empEmail", emp.getEmail());
			arry.add(obj);
		}
		return arry;
	}
	
	public JSONObject getUserInfoByToken(String userToken) {
		String empId = jwtToken.getInfoFromJwtToken(userToken,"empId");
		Employee emp = employeeRepo.findById(empId).get();
			JSONObject obj = new JSONObject();
			obj.put("apart", emp.getAparts().getApart());
			obj.put("empId", empId);
			obj.put("empName", emp.getEmpName());
			obj.put("empPwd", emp.getPasswd());
			obj.put("empTel", emp.getTel());
			obj.put("empPhone", emp.getCellphone1());
			obj.put("empEmail", emp.getEmail());
			obj.put("empAddr", emp.getAddr());		
			obj.put("empPhoto", emp.getPhoto());		
			return obj;
	}
	
	public Employee getUserInfoByEmpId(String empId) {
		Employee emp = employeeRepo.findById(empId).get();	
			return emp;
	}
	
	public JSONArray getUserInfoByEmpName(JSONObject data) {
		JSONArray arry = new JSONArray();
		JSONObject obj = new JSONObject();
		if(employeeRepo.existsByEmpName(data.getString("empName"))) {
			Employee emp = employeeRepo.findByEmpName(data.getString("empName")).get(0);	
			obj.put("info", emp);
			arry.add(obj);
			return arry;
		}else {
			obj.put("info", "noExist");
			arry.add(obj);
			return arry;
		}
	}
	
	
	
	@Transactional
	public void updateUserInfoById(JSONObject userInfo) {
		 Employee employee = employeeRepo.findById(jwtToken.getInfoFromJwtToken(userInfo.getString("userToken"),"empId")).get();
		 employee.setEmpName(userInfo.getString("empName"));
		 employee.setPasswd(userInfo.getString("empPwd"));
		 employee.setTel(userInfo.getString("empTel"));
		 employee.setCellphone1(userInfo.getString("empPhone"));
		 employee.setEmail(userInfo.getString("empEmail"));
		 employee.setAddr(userInfo.getString("empAddr"));
		 employeeRepo.save(employee);
	}
	
	@Transactional
	public String updateUserPhoto(MultipartFile multipartFile,String userToken) {
		 String empId = jwtToken.getInfoFromJwtToken(userToken,"empId");
		 String imgUrl = uploadPhoto.uploadUserPhoto(multipartFile, empId);		
		 System.out.println(imgUrl);
		 Employee employee = employeeRepo.findById(empId).get();
		 System.out.println("更新empPhoto");
		 employee.setPhoto(imgUrl);
		 employeeRepo.save(employee);
		 return imgUrl ;
	}
	
	@Transactional
	public void updateUserInfoByIdFromAdmin(JSONObject userInfo) {
	     String updatePerson = jwtToken.getInfoFromJwtToken(userInfo.getString("updatePerson"),"empId");
		 Employee employee = employeeRepo.findById(userInfo.getString("empId")).get();
		 employee.setPasswd(userInfo.getString("passwd"));
		 employee.setEmpName(userInfo.getString("empName"));
		 employee.setAparts(apartRepo.findById(userInfo.getInteger("apartId")).get());
		 employee.setTel(userInfo.getString("tel"));
		 employee.setGender(userInfo.getString("gender"));
		 employee.setCellphone1(userInfo.getString("cellphone1"));
		 employee.setEmail(userInfo.getString("email"));
		 employee.setAddr(userInfo.getString("addr"));
		 employee.setAdm(userInfo.getString("adm"));
		 employee.setUpdatePerson(updatePerson);
		 employeeRepo.save(employee);
	}
	@Transactional
	public  String insertUserInfoFromAdmin(JSONObject userInfo) {
		 if(!employeeRepo.existsById(userInfo.getString("empId"))) {
		     String createPerson = jwtToken.getInfoFromJwtToken(userInfo.getString("createPerson"),"empId");
			 Employee employee = new Employee();
			 employee.setEmpId(userInfo.getString("empId"));
			 employee.setPasswd(userInfo.getString("passwd"));
			 employee.setEmpName(userInfo.getString("empName"));
			 employee.setAparts(apartRepo.findById(userInfo.getInteger("apartId")).get());
			 employee.setTel(userInfo.getString("tel"));
			 employee.setGender(userInfo.getString("gender"));
			 employee.setCellphone1(userInfo.getString("cellphone1"));
			 employee.setCellphone2("100");
			 employee.setEmail(userInfo.getString("email"));
			 employee.setAddr(userInfo.getString("addr"));
			 employee.setAdm(userInfo.getString("adm"));
			 employee.setCreatePerson(createPerson);
			 employee.setUpdatePerson(createPerson);
			 employeeRepo.save(employee);
			 return "200" ;
		 }else {
			 return "201" ;
		 }
		 
	}
	
	public JSONObject getAddr(JSONObject data) {
	     String emId = jwtToken.getInfoFromJwtToken(data.getString("userToken"),"empId");
	     String addr = employeeRepo.findById(emId).get().getAddr();
	     JSONObject obj = new JSONObject();
	     obj.put("addr", addr);
	     return obj;

	}
	
	@Transactional
	public void changeAccountState(JSONObject data) {
	     Employee employee = employeeRepo.findById(data.getString("empId")).get();
	     employee.setCellphone2(data.getString("cellphone2"));
	     employeeRepo.save(employee);
	}
}
