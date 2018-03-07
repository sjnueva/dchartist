/**
 *    Copyright 2017-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.dchartist.tools.jpsegets.dao;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dchartist.tools.jpsegets.entity.Trade;

/**
 * @author Sir John Nueva
 */
public interface TradeRepository<T, ID extends Serializable> extends CrudRepository<Trade, ID> {
	
    @Query("select o from Trade o where o.id = :id")
    Stream<Trade> findByIdReturnStream(@Param("id") Long id);
	
	
	@Query("select o from Trade o where o.symbol=:symbol and o.tradingDate between :startTradingDate and :endTradingDate order by o.tradingDate")
	Stream<Trade> find(String symbol, Date startTradingDate,Date endTradingDate) throws Exception;
	
	
	@Query("select o.symbol from Trade o group by o.symbol")
	Stream<String> findAllSymbol() throws Exception;
	

    <S extends Trade> S save(S entity);

    //Trade findOne(ID primaryKey);

    Iterable<Trade> findAll();

    long count();

    void delete(Trade entity);

    //boolean exists(ID primaryKey);
}
