package kg.attractor.exam_7.dao;

import kg.attractor.exam_7.mapper.HistoryMapper;
import kg.attractor.exam_7.model.History;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HistoryDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<History> getHistoryByAccNum(String accNum) {
        String sql = "select * from history where from_acc = ?;";
        return jdbcTemplate.query(sql, new HistoryMapper(), accNum);
    }
}
