package kg.attractor.exam_7.dao;

import kg.attractor.exam_7.mapper.HistoryMapper;
import kg.attractor.exam_7.model.History;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HistoryDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<History> getTransactionHistory() {
        String sql = "select * from history";
        return jdbcTemplate.query(sql, new HistoryMapper());
    }

    public void save(History history) {
        String sql = "insert into history(from_acc, to_acc, amount_money, approved) " +
                "values (:fromAcc, :toAcc, :amountMoney, :approved)";

        namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource()
                        .addValue("fromAcc", history.getFromAcc())
                        .addValue("toAcc", history.getToAcc())
                        .addValue("amountMoney", history.getAmountMoney())
                        .addValue("approved", history.getApproved())
        );
    }

    public List<History> getHistoryByAccNum(String accNum) {
        String sql = "select * from history where from_acc = ?;";
        return jdbcTemplate.query(sql, new HistoryMapper(), accNum);
    }
}
