package kg.attractor.exam_7.mapper;

import kg.attractor.exam_7.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setRoleId(rs.getLong("role_id"));
        return user;
    }
}
