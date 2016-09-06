package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<Product> {
	
	public static final Logger logger = LoggerFactory.getLogger(UserRowMapper.class);

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

		logger.info("In a User Row Mapper");
		Product product = new Product();
		product.setId(rs.getString("id"));
		product.setStoreid(rs.getString("storeid"));
		logger.info(product+":Productssss::::");
		return product;
	}

}