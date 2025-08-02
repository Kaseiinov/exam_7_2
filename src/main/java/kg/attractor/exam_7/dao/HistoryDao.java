package kg.attractor.exam_7.dao;

import kg.attractor.exam_7.mapper.HistoryMapper;
import kg.attractor.exam_7.model.History;
import kg.attractor.exam_7.model.RollBack;
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

    public void saveRollBack(RollBack rollBack) {
        String sql = "insert into ROLLBACKS(from_acc, to_acc, amount_money, SUCCESSFUL, ENABLED) " +
                "values (:fromAcc, :toAcc, :amountMoney, :successful, :enabled)";

        namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource()
                        .addValue("fromAcc", rollBack.getFromAcc())
                        .addValue("toAcc", rollBack.getToAcc())
                        .addValue("amountMoney", rollBack.getAmountMoney())
                        .addValue("successful", rollBack.getSuccessful())
                        .addValue("enabled", rollBack.getEnabled())
        );
    }

    public Optional<History> getTrueHistoryById(Long id){
        String sql = "select * from history where id = ? and APPROVED = true";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new HistoryMapper(), id));
    }

    public Optional<History> getHistoryById(Long id){
        String sql = "select * from history where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new HistoryMapper(), id));
    }

    public void approveTransaction(Long id ) {
        String sql = "update history set approved = true where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<History> getTransactionHistory() {
        String sql = "select * from history";
        return jdbcTemplate.query(sql, new HistoryMapper());
    }

    public List<History> getTransactionHistoryApproval() {
        String sql = "select * from history where APPROVED = false";
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
