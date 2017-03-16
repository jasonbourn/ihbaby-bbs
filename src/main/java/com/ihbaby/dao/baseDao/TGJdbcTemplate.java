package com.ihbaby.dao.baseDao;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TGJdbcTemplate {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	protected static final int PAGE_SIZE=20;
	
	protected int save(String sql, Object object) {
		Validate.notEmpty(sql, "sql can't be empty", new Object[0]);
		String strColumn = StringUtils.substringBetween(sql, "INTO", "VALUES");
		if (StringUtils.isBlank(strColumn)) {
			strColumn = StringUtils.substringBetween(sql, "into", "VALUES");
		}
		if (StringUtils.isBlank(strColumn)) {
			strColumn = StringUtils.substringBetween(sql, "into", "values");
		}
		if (StringUtils.isBlank(strColumn)) {
			strColumn = StringUtils.substringBetween(sql, "INTO", "values");
		}
		strColumn = StringUtils.substringBetween(strColumn, "(", ")");
		String[] columnAry = StringUtils.split(strColumn, ",");
		Object[] params = new Object[columnAry.length];
		int i = 0;
		for (String c : columnAry) {
			c = CamelCaseUtils.toCamelCase(StringUtils.trim(c));
			params[(i++)] = ReflectionUtils.getFieldValue(object, c);
		}
		return jdbcTemplate.update(sql, params);
	}
	
	protected int update(String sql, Object object) {
		Validate.notEmpty(sql, "sql can't be empty", new Object[0]);
		String strColumn = StringUtils.substringBetween(sql, "SET", "WHERE");
		if (StringUtils.isBlank(strColumn)) {
			strColumn = StringUtils.substringBetween(sql, "set", "WHERE");
		}
		if (StringUtils.isBlank(strColumn)) {
			strColumn = StringUtils.substringBetween(sql, "set", "where");
		}
		if (StringUtils.isBlank(strColumn)) {
			strColumn = StringUtils.substringBetween(sql, "SET", "where");
		}
		strColumn = StringUtils.trim(StringUtils.replace(StringUtils.replace(strColumn, "=", ""), "?", ""));
		String[] columnAry = StringUtils.split(strColumn, ",");
		Object[] params = new Object[columnAry.length];
		int i = 0;
		for (String c : columnAry) {
			c = CamelCaseUtils.toCamelCase(StringUtils.trim(c));
			params[(i++)] = ReflectionUtils.getFieldValue(object, c);
		}
		return jdbcTemplate.update(sql, params);
	}
	
	protected int saveResultKey(PreparedStatementCreator pss, KeyHolder generatedKeyHolder) {
		return this.jdbcTemplate.update(pss, generatedKeyHolder);
	}
	
	protected Long saveResultKey(String sql, Object object) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		saveResultKey(new PreparedStatementCreator() {
			
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
				throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, 1);
				String strColumn = StringUtils.substringBetween(sql, "INTO", "VALUES");
				if (StringUtils.isBlank(strColumn)) {
					strColumn = StringUtils.substringBetween(sql, "into", "VALUES");
				}
				if (StringUtils.isBlank(strColumn)) {
					strColumn = StringUtils.substringBetween(sql, "into", "values");
				}
				if (StringUtils.isBlank(strColumn)) {
					strColumn = StringUtils.substringBetween(sql, "INTO", "values");
				}
				strColumn = StringUtils.substringBetween(strColumn, "(", ")");
				String[] columnAry = StringUtils.split(strColumn, ",");
				int i = 1;
				for (String c : columnAry) {
					c = CamelCaseUtils.toCamelCase(StringUtils.trim(c));
					ps.setObject(i++, ReflectionUtils.getFieldValue(object, c));
				}
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}


	protected int saveResultKey(String sql, Object... args) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		saveResultKey(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, 1);
				int i = 1;
				for (Object c : args) {
					ps.setObject(i++, c);
				}
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}



	/**
	 * 获取对象列表
	 * get a list of clazz
	 * @param sql
	 * @param params
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> list(String sql, Object[] params, Class<T> clazz) {

		//call jdbcTemplate to query for result
		List<T> list = null;
		if (params == null || params.length == 0) {
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(clazz));
		} else {
			list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper(clazz));
		}

		//return list
		return list;
	}

	/**
	 * 获取总行数
	 * get count
	 * @param sql
	 * @param params
	 * @return
	 */
	public int count(String sql, Object[] params) {

		int rowCount = 0;
		try{
			Map<String, Object> resultMap = null;
			if (params == null || params.length == 0) {
				resultMap = jdbcTemplate.queryForMap(sql);
			} else {
				resultMap = jdbcTemplate.queryForMap(sql, params);
			}
			Iterator<Map.Entry<String, Object>> it = resultMap.entrySet().iterator();
			if(it.hasNext()){
				Map.Entry<String, Object> entry = it.next();
				rowCount = ((Long)entry.getValue()).intValue();
			}
		}catch(EmptyResultDataAccessException e){
			logger.error(e.getMessage());
			return 0;
		}


		return rowCount;
	}
    public <T> T findById(String sql,long id,Class<T> clazz) throws Exception{
		return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<T>(clazz),id);
	}
}
