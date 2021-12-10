package org.edwith.webbe.securityexam.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.edwith.webbe.securityexam.dto.Member;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;

	private RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);
	
	public MemberDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("member")
				.usingGeneratedKeyColumns("id");
	}
	
	public Member getMemberByEmail(String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		
		return jdbc.queryForObject(MemberDaoSqls.SELECT_ALL_BY_EMAIL, map, rowMapper);
	}

	public Long insert(Member member) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(member);
		return insertAction.executeAndReturnKey(params).longValue();		
	}
}
