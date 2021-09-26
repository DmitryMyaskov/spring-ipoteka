package com.dima.spring;

import com.dima.spring.DAO.*;
import com.dima.spring.controller.myController;
import com.dima.spring.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@WebMvcTest(myController.class)
class IpotekSApplicationTests {

	private static Long ID=1L;
	private static Long UserCustomerID=1L;
	private static BigDecimal CREDIT_AMOUNT=new BigDecimal(10000);
	private static int CREDIT_TREM = 20;
	private static String MORTGAGE_OBJECT = "flat";
	private static LocalDate BIRTH_OF_DAY_USER1 = LocalDate.of(1994,12,16);

	private static Long UserSellerID=2L;
	private static FaceItem INDIV_FACE = FaceItem.IndivFace;
	private static FaceItem PHIS_FACE = FaceItem.PhisFace;

	private static Long ITN_INCORRECT = 500100732255L;
	private static Long ITN = 500100732259L;
	private static BigDecimal TOTAL_PURCHASE_VALUE=new BigDecimal(4000);
	private static LocalDate BIRTH_OF_DAY_USER2 = LocalDate.of(1998,12,16);
	private static LocalDate BIRTH_OF_DAY_USER2_INCORRECT = LocalDate.of(1998,12,16);


	@Autowired
    private MockMvc mockMvc;

	@Autowired
	private myController myController;

	@MockBean
	private CustomerDAO customerDAO;


	@Test
	public void customerRequest() throws Exception {
		Customer customer = getCustomer();
		User user = getUser();
		User user2 = getUser2();
		Seller seller = getSeller();

		seller.setUserId(user2);
		customer.setSellerId(seller);
		customer.setUserId(user);
		when(customerDAO.save(any(Customer.class))).thenReturn(customer);
		mockMvc.perform(post("/customer/").content(TestUtils.createJson(customer)).contentType(MediaType.APPLICATION_JSON))
//				-Customer
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id",is(ID.intValue())))
				.andExpect(jsonPath("$.totalPurchaseValue",is(TOTAL_PURCHASE_VALUE.intValue())))
				.andExpect(jsonPath("$.creditAmount",is(CREDIT_AMOUNT.intValue())))
				.andExpect(jsonPath("$.creditTerm",is(CREDIT_TREM)))
				.andExpect(jsonPath("$.mortgageObject",is(MORTGAGE_OBJECT)))

//				-Customer->user

				.andExpect(jsonPath("$.userId.id", is(1)))
				.andExpect(jsonPath("$.userId.firstName", is("Dima")))
				.andExpect(jsonPath("$.userId.secondName", is("Myaskov")))
				.andExpect(jsonPath("$.userId.lastName", is("Alekseevich")))
				.andExpect(jsonPath("$.userId.dateOfBirth"
						,is(BIRTH_OF_DAY_USER1.toString())))

//				-Customer->seller

				.andExpect(jsonPath("$.sellerId.id", is(1)))
				.andExpect(jsonPath("$.sellerId.faceItem", is(INDIV_FACE.toString())))
				.andExpect(jsonPath("$.sellerId.itn", is(ITN)))

//				-Customer->seller->user

				.andExpect(jsonPath("$.sellerId.userId.id", is(2)))
				.andExpect(jsonPath("$.sellerId.userId.firstName", is("Vova")))
				.andExpect(jsonPath("$.sellerId.userId.secondName", is("Nespaniv")))
				.andExpect(jsonPath("$.sellerId.userId.lastName", is("Alekseevich")))
				.andExpect(jsonPath("$.sellerId.userId.dateOfBirth"
						,is(BIRTH_OF_DAY_USER2.toString())));
		verify(customerDAO).save(any(Customer.class));
	}



	@Test
	public void user() throws Exception {
		Customer customer = getCustomer();
		User user = getUser();
		User user2 = getUser2();
		Seller seller = getSeller();

		seller.setUserId(user2);
		customer.setSellerId(seller);
		customer.setUserId(user);
        System.out.println(customer.getUserId().getDateOfBirth());
		when(customerDAO.findById(1L)).thenReturn(Optional.of(customer));
		mockMvc.perform(get("/customer/"+ID).contentType(MediaType.APPLICATION_JSON))
//				-Customer
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id",is(ID.intValue())))
				.andExpect(jsonPath("$.totalPurchaseValue",is(TOTAL_PURCHASE_VALUE.intValue())))
				.andExpect(jsonPath("$.creditAmount",is(CREDIT_AMOUNT.intValue())))
				.andExpect(jsonPath("$.creditTerm",is(CREDIT_TREM)))
				.andExpect(jsonPath("$.mortgageObject",is(MORTGAGE_OBJECT)))

//				-Customer->user

				.andExpect(jsonPath("$.userId.id", is(UserCustomerID.intValue())))
				.andExpect(jsonPath("$.userId.firstName", is("Dima")))
				.andExpect(jsonPath("$.userId.secondName", is("Myaskov")))
				.andExpect(jsonPath("$.userId.lastName", is("Alekseevich")))
				.andExpect(jsonPath("$.userId.dateOfBirth"
						,is(BIRTH_OF_DAY_USER1.toString())))

//				-Customer->seller

				.andExpect(jsonPath("$.sellerId.id", is(ID.intValue())))
				.andExpect(jsonPath("$.sellerId.faceItem", is(INDIV_FACE.toString())))
				.andExpect(jsonPath("$.sellerId.itn", is(ITN)))

//				-Customer->seller->user

				.andExpect(jsonPath("$.sellerId.userId.id", is(UserSellerID.intValue())))
				.andExpect(jsonPath("$.sellerId.userId.firstName", is("Vova")))
				.andExpect(jsonPath("$.sellerId.userId.secondName", is("Nespaniv")))
				.andExpect(jsonPath("$.sellerId.userId.lastName", is("Alekseevich")))
				.andExpect(jsonPath("$.sellerId.userId.dateOfBirth"
						,is(BIRTH_OF_DAY_USER2.toString())));

		verify(customerDAO).findById(1L);

	}

	private User getUser() throws ParseException {
		User user = new User();
		user.setId(UserCustomerID);
		user.setFirstName("Dima");
		user.setSecondName("Myaskov");
		user.setLastName("Alekseevich");
		user.setDateOfBirth(BIRTH_OF_DAY_USER1);
		return user;
	}

	private User getUser2() throws ParseException {
		User user = new User();
		user.setId(UserSellerID);
		user.setFirstName("Vova");
		user.setSecondName("Nespaniv");
		user.setLastName("Alekseevich");
		user.setDateOfBirth(BIRTH_OF_DAY_USER2);
		return user;
	}

	private Customer getCustomer() {
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setCreditAmount(CREDIT_AMOUNT);
		customer.setCreditTerm(CREDIT_TREM);
		customer.setMortgageObject(MORTGAGE_OBJECT);
		customer.setTotalPurchaseValue(TOTAL_PURCHASE_VALUE);

		return customer;
	}

	private Seller getSeller(){
		Seller seller = new Seller();
		seller.setId(ID);
		seller.setFaceItem(INDIV_FACE);
		seller.setITN(ITN);
		return seller;
	}

}
