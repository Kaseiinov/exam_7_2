package kg.attractor.exam_7.dao;

import kg.attractor.exam_7.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void register(User user) {
        String sql = "insert into users(phone, username, password, role_id, enabled)" +
                " values(:phone, :username, :password, :roleId, :enabled);";

        namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource()
                        .addValue("phone", user.getPhone())
                        .addValue("username", user.getUsername())
                        .addValue("password", user.getPassword())
                        .addValue("roleId", user.getRoleId())
                        .addValue("enabled", user.getEnabled())
        );
    }
}
