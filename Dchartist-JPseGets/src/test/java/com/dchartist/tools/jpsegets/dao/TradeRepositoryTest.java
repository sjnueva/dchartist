package com.dchartist.tools.jpsegets.dao;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.dchartist.tools.jpsegets.entity.Trade;
import com.dchartist.tools.jpsegets.util.TradeConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeRepositoryTest {
	final static String MARK="------------------------------------------------------------------------";
	@Autowired
	TradeRepository tradeRepository;
	@Autowired
	TradeConfiguration tradeConfiguration;
	
	Trade o;
	
	@Before
	public void setUp() {
		o = new Trade();
		
		o.setSymbol("DAVIN");
		o.setTradingDate(new Date());
	
	}
	
	@Test
	public void save() {
		try {
			System.out.println(MARK);
			System.out.println("Testing TradeRepository.save.");
			System.out.println(MARK);
			System.out.println(o.HeaderToArray(tradeConfiguration));
			System.out.println(MARK);
			System.out.println(o.toArray());
			System.out.println(MARK);
			
			Assert.notNull(tradeRepository.save(o));
			System.out.println(MARK);
			
		}catch(Exception e) {
			
			e.printStackTrace();
			Assert.isNull(e);
		}
	}
	
}
