package com.example.demo.repository;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

// Spring JDBC를 이용해서 Database프로그래밍
// @Repisitory는 @Component이고 컨테이너가 관리하는 Bean이 된다.,
@Repository
public class UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate; // 필드를 final로 선언하면 반드시 생성자에서 초기화 한다.
    private SimpleJdbcInsertOperations insertAction;

    // 생성자에 파라미터를 넣어주면 스프링 부트가 자동으로 주입한다. 생성자 주입.
    public UserDao(DataSource dataSource) {
        System.out.println("UserDao 생성자 호출");
        System.out.println(dataSource.getClass().getName());
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        insertAction = new SimpleJdbcInsert(dataSource).withTableName("user");
    }

    // User 테이블에 한 건 저장. 저장을 성공하면 true, 실패하면 flase를 반환한다.
    public boolean addUser(User user) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(user);
        int result = insertAction.execute(params);
        return result == 1;
    }

    public boolean deleteUser(int userId) {
        String sql = "delete from user where user_id=:userId";
        SqlParameterSource params = new MapSqlParameterSource("userId", userId);
        int result = jdbcTemplate.update(sql, params);

        return result==1;
    }

    public User getUser(int userId) {
        String sql = "select user_id, email, name, password, regdate from user where user_id=:userId";

        try {
            SqlParameterSource params = new MapSqlParameterSource("userId", userId);
            RowMapper<User> roleRowMapper = BeanPropertyRowMapper.newInstance(User.class);
            return jdbcTemplate.queryForObject(sql, params, roleRowMapper);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getUsers() {
        String sql = "select user_id, email, name, password, regdate from user order by user_id desc";
        RowMapper<User> roleRowMapper = BeanPropertyRowMapper.newInstance(User.class);
        return jdbcTemplate.query(sql, roleRowMapper);
    }
}
