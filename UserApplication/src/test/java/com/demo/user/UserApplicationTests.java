package com.demo.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.user.dto.ResultModel;
import com.demo.user.entity.Users;
import com.demo.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class UserApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//	
	Users mock1=new Users();
	
//	@Before
//	public void setUp() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}
	
	@Test
	public void getAllUsersTest() throws Exception {			
		List<Users> userList=new ArrayList<Users>();
		ResultModel resultModel = new ResultModel();
		userList.add(this.getUserData(mock1));
		userList.add(this.getUserData2(mock1));
		resultModel.setData(userList);
		resultModel.setMessage("Success");
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		RequestBuilder requestBuilder= MockMvcRequestBuilders.get(RestUrls.getAllUsersUrl).accept(MediaType.APPLICATION_JSON);
		this.compareOk(requestBuilder, resultModel);		
	}	
	
	@Test
	public void deleteUserByIdTest() throws Exception {
		ResultModel resultModel=new ResultModel();
		resultModel.setMessage("Success");		
		Mockito.when(userService.deleteUser(Mockito.anyInt())).thenReturn("Success");		
		RequestBuilder requestBuilder= MockMvcRequestBuilders.delete(RestUrls.deleteByIdUrl).accept(MediaType.APPLICATION_JSON);		
		this.compareOk(requestBuilder, resultModel);		
	}

	@Test
	public void getUserByUserId() throws Exception{		
		this.getUserData(this.getUserData(mock1));		
		ResultModel resultModel=new ResultModel();
		resultModel.setData(mock1);
		resultModel.setMessage("Success");	
		Mockito.when(userService.getById(Mockito.anyString())).thenReturn(mock1);		
		RequestBuilder requestBuilder= MockMvcRequestBuilders.get(RestUrls.getByUserIdUrl).accept(MediaType.APPLICATION_JSON);		
		this.compareOk(requestBuilder, resultModel);	
	}
	
	@Test
	public void createUserTest() throws Exception {
		List<String> stringList = new ArrayList<String>();
		stringList.add("Success");
		ResultModel resultModel = new ResultModel();
		resultModel.setMessage("Success");
		resultModel.setData(stringList);
		String inputInJson = this.mapToJson(resultModel);
		Mockito.when(userService.saveUser(Mockito.any(Users.class))).thenReturn(stringList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(RestUrls.getSaveUpdateUrl).accept(MediaType.APPLICATION_JSON).content(inputInJson).contentType(MediaType.APPLICATION_JSON);		
		this.compareOk(requestBuilder, resultModel);
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper=new ObjectMapper();		
		return objectMapper.writeValueAsString(object);
	}
	
	private void compareOk(RequestBuilder requestBuilder , ResultModel resultModel) throws Exception {
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();		
		MockHttpServletResponse response= result.getResponse();		
		String outputInJson=result.getResponse().getContentAsString();	
		String expectedJson=this.mapToJson(resultModel);
		assertThat(outputInJson).isEqualTo(expectedJson);
		if(response.getStatus()==200) {;
			assertEquals(HttpStatus.OK.value(), response.getStatus());
		}else if(response.getStatus()==201){
			assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		}
	}
	
	private Users getUserData(Users mock2) {
		mock2.setId(1); mock2.setName("Piyush");
		mock2.setSurname("Koli"); mock2.setUserId("Neosoft_1");
		mock2.setPassword("piyush123"); mock2.setEmail("piyush@gmail.com");
		mock2.setDob(null); mock2.setCity("pune");mock2.setContactNumber("987645621");
		mock2.setPinCode(425001); mock2.setDesignation("Angular Developer");
		mock2.setRole("Developer"); mock2.setJoiningDate(null);
		return mock2;
		}
	
	private Users getUserData2(Users mock2) {
		mock2.setId(2); mock2.setName("Girish"); mock2.setSurname("Patil");
		mock2.setUserId("Neosoft_2"); mock2.setPassword("girish123");
		mock2.setEmail("girish@gmail.com"); mock2.setDob(null);
		mock2.setCity("Mumbai"); mock2.setContactNumber("987645621");
		mock2.setPinCode(425001); mock2.setDesignation("Angular Developer");
		mock2.setRole("Developer"); mock2.setJoiningDate(null);
		return mock2;
	}
	
	/*	private void compareCreate(RequestBuilder requestBuilder , String inputInJson) throws Exception {
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(inputInJson);
		System.out.println(response.getStatus());
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}*/
}