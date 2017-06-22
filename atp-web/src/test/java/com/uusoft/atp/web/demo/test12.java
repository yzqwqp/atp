package com.uusoft.atp.web.demo;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uusoft.cfg.service.MerchantGroupService;
import com.uusoft.cfg.service.MerchantService;
import com.uusoft.cif.service.CustCheckService;
import com.uusoft.cif.service.CustService;
import com.uusoft.cif.service.FundAccoService;
import com.uusoft.cif.service.model.AcctCreateInfo;
import com.uusoft.trs.service.SpecialTradeService;
import com.uusoft.trs.service.TrsAppService;
import com.uusoft.trs.service.model.AgentBuyInfo;
import com.uusoft.trs.service.model.ChangeDividendTypeInfo;
import com.uusoft.trs.service.model.ConsumeInfo;
import com.uusoft.trs.service.model.TreasureBuyInfo;
import com.uusoft.trs.service.model.TrsInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class test12 {
	@Resource
	private CustService custService;
	@Resource
	private CustCheckService custCheckService;
	@Resource
	private FundAccoService fundAccoService;
	
	@Resource
	private TrsAppService trsAppService;
	@Resource
	private MerchantService merchantService;
	@Resource
	private MerchantGroupService merchantGroupService;


	@Resource
	private SpecialTradeService specialTradeService;
	
	
	
	private TrsInfo sinfo = new TrsInfo();
	
	
	private AcctCreateInfo info = new AcctCreateInfo();
	
	
	private ChangeDividendTypeInfo dinfo = new ChangeDividendTypeInfo();
	
	private ConsumeInfo cinfo = new ConsumeInfo();

	private TreasureBuyInfo tinfo = new TreasureBuyInfo();
	
	private AgentBuyInfo binfo = new AgentBuyInfo();




	/**
	 * 开户
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void test001() {
		info.setMerchantno("HT0000001");
		info.setCustname("账户3");
		info.setCertificatetype("0");
		info.setCertificateno("110101198006010052");
		info.setDepositacct("1111115");
		info.setPreservedMobileno("18724694113");
		info.setAcceptmethod("1");
		info.setBranchinterbankcode("103290035080");
		info.setIsauth("1");
		System.out.println("asachangs" + info.getCustname());
		// assertTrue( custService.openAcct(info).isSuccess());
		System.out.println(custService.openAcct(info));
	}
	
	/**
	 * 开户
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void test002() {
		info.setMerchantno("HT0000001");
		info.setCustname("个人2");
		info.setCertificatetype("0");
		info.setCertificateno("21010119800101033X");
		info.setDepositacct("72252211277746512");
		info.setPreservedMobileno("18724694186");
		info.setAcceptmethod("1");
		info.setBranchinterbankcode("103290035080");
		info.setIsauth("1");
		System.out.println("asachangs" + info.getCustname());
		System.out.println(custService.openAcct(info));
	}
	
	/**
	 * 开户
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void test003() {
		info.setMerchantno("HT0000001");
		info.setCustname("个人3");
		info.setCertificatetype("0");
		info.setCertificateno("210101198001010356");
		info.setDepositacct("72252211277746513");
		info.setPreservedMobileno("18724694187");
		info.setAcceptmethod("1");
		info.setBranchinterbankcode("103290035080");
		info.setIsauth("1");
		System.out.println("asachangs" + info.getCustname());
		// assertTrue( custService.openAcct(info).isSuccess());
		System.out.println(custService.openAcct(info));
	}

	/**
	 * 认购20
	 */
	@Test
	public void test004() {
		sinfo.setFundcode("250001");
		sinfo.setOrderflag("0");
		sinfo.setPlatformtype("2");
		sinfo.setApply(new BigDecimal("10000"));
		sinfo.setMoneyaccountno(new BigDecimal("260035"));
		sinfo.setNetaddr("192.168.0.1");
		sinfo.setMerchantno("HT0000001");
		sinfo.setTano("25");
		System.out.println(trsAppService.subscribe(sinfo));//认购
	}
	
	/**
	 * 申购22
	 */
	@Test
	public void test005() {
  		sinfo.setFundcode("000686");
		sinfo.setOrderflag("0");
		sinfo.setPlatformtype("2");
		sinfo.setApply(new BigDecimal("70000"));
		sinfo.setMoneyaccountno(new BigDecimal("260050"));//1265\1993、1368
		sinfo.setNetaddr("192.168.0.1");
		sinfo.setMerchantno("HT0000001");
		sinfo.setTano("53");
		System.out.println(trsAppService.purchase(sinfo));//申购
	}
	
	/**
	 * 赎回测试24
	 */
	@Test
	public void test007(){
		sinfo.setFundcode("250002");
		sinfo.setOrderflag("0");
		sinfo.setPlatformtype("1");
		sinfo.setTano("25");
		sinfo.setMerchantno("HT0000001");
		sinfo.setMoneyaccountno(new BigDecimal("260030"));
		sinfo.setApply(new BigDecimal("10000000"));
		sinfo.setNetaddr("192.168.0.1");
		System.out.println(trsAppService.redeem(sinfo));
		//assertTrue(trsAppService.redeem(info).isSuccess());
		}
	/**
	 * 98
	 * 快速赎回
	 */
	@Test
	public void test008(){
		sinfo.setFundcode("000686");
		sinfo.setOrderflag("0");
		sinfo.setPlatformtype("2");
		sinfo.setTano("53");
		sinfo.setMerchantno("HT0000001");
		sinfo.setMoneyaccountno(new BigDecimal("260050"));
		sinfo.setApply(new BigDecimal("300000"));
		sinfo.setNetaddr("192.168.0.1");
		System.out.println(trsAppService.quickRedeem(sinfo));
	}
	
	/**
	 *changeDividendType
	 *修改分红方式
	 */
	@Test
	public void test009(){
		dinfo.setDefDividendMethod("1");
		dinfo.setFundcode("000686");
		dinfo.setMerchantno("HT0000001");
		dinfo.setMoneyaccountno(new BigDecimal("260050"));
		dinfo.setNetaddr("192.168.0.29");
		dinfo.setPlatformtype("2");
		dinfo.setTano("53");
		dinfo.setOrderflag("0");
		System.out.println(specialTradeService.changeDividendType(dinfo));
	}
	
	/**
	 * redeem 
	 * 24
	 */
	@Test
	public void test010(){
		sinfo.setFundcode("000686");
		sinfo.setOrderflag("0");
		sinfo.setPlatformtype("0");
		sinfo.setTano("53");
		sinfo.setMerchantno("HT0000001");
		sinfo.setMoneyaccountno(new BigDecimal("260055"));
		sinfo.setApply(new BigDecimal("5000"));
		sinfo.setNetaddr("192.168.0.1");
		System.out.println(trsAppService.redeem(sinfo));
		}
	
	/**
	 * D3
	 * T0存入
	 */
	@Test
	public void test011(){
		sinfo.setFundcode("000686");
		sinfo.setOrderflag("0");
		sinfo.setPlatformtype("2");
		sinfo.setApply(new BigDecimal("60000"));
		sinfo.setMoneyaccountno(new BigDecimal("260054"));
		sinfo.setNetaddr("192.168.0.1");
		sinfo.setMerchantno("HT0000001");
		sinfo.setTano("53");
		System.out.println(trsAppService.t0Purchase(sinfo));
	}
	
	
	/**
	 * D1
	 * 消费
	 */
	@Test
	public void test012(){
		cinfo.setApply(new BigDecimal("60000"));
		cinfo.setFundcode("000686");
		cinfo.setMerchantno("HT0000001");
		cinfo.setMoneyaccountno(new BigDecimal("260054"));	
		cinfo.setNetaddr("192.168.0.29");
		cinfo.setTano("53");
		cinfo.setOrderflag("0");
		cinfo.setConsumerno("X00000131");
		cinfo.setPlatformtype("2");
		System.out.println(trsAppService.consume(cinfo));
	}
	
	
	/**
	 * 宝买基金
	 */
	@Test
	public void test013(){
		tinfo.setTreasurefundcode("000686");
		tinfo.setTreasuretano("53");
		tinfo.setMerchantno("HT0000001");
		tinfo.setMoneyaccountno(new BigDecimal("260051"));
		tinfo.setNetaddr("192.168.0.29");
		tinfo.setApply(new BigDecimal("50000"));
		tinfo.setFundcode("250002");
		tinfo.setPlatformtype("2");
		tinfo.setTano("25");
		tinfo.setOrderflag("0");
		System.out.println(specialTradeService.treasureBuy(tinfo));
	}
	
	/**
	 * 代认申购
	 * 代申购交易申请下单
	 * 888888
	 */
	@Test
	public void test014(){
		binfo.setApply(new BigDecimal("30000"));
		binfo.setFundcode("000686");
		binfo.setMerchantno("HT0000001");
		binfo.setMoneyaccountno(new BigDecimal("260053"));
		binfo.setNetaddr("192.168.0.29");
		binfo.setTano("53");
		binfo.setOrderflag("1");
		binfo.setPlatformtype("2");
		binfo.setDepositacct("6222222222222222");
		binfo.setPaytype("03");
		System.out.println(specialTradeService.agentBuy(binfo));
	}
	
	/**
	 * T0赎回
	 * D4
	 */
	@Test
	public void test015(){
		sinfo.setFundcode("000686");
		sinfo.setOrderflag("0");
		sinfo.setPlatformtype("2");
		sinfo.setMoneyaccountno(new BigDecimal("260054"));
		sinfo.setNetaddr("192.168.0.1");
		sinfo.setMerchantno("HT0000001");
		sinfo.setTano("53");
		sinfo.setApply(new BigDecimal("60000"));
		System.out.println(trsAppService.t0Redeem(sinfo));
	}
	
	
	
	
	
	}
