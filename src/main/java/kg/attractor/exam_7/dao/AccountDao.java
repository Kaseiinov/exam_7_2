package kg.attractor.exam_7.dao;

import kg.attractor.exam_7.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<Double> getBalanceByNum(String accNum) {
        String sql = "SELECT balance FROM accounts WHERE uniq_number = :accNum";
        Map<String, Object> params = Collections.singletonMap("accNum", accNum);

        Double balance = jdbcTemplate.queryForObject(sql, Double.class, accNum);
        return Optional.ofNullable(balance);
    }
    public void createAcc(Account account) {
        String sql = "insert into accounts(currency_id, user_id, balance, uniq_number) " +
                "values(:currencyId, :userId, :balance, :uniqNumber);";
        namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource()
                        .addValue("currencyId", account.getCurrencyId())
                        .addValue("userId", account.getUserId())
                        .addValue("balance", account.getBalance())
                        .addValue("uniqNumber", account.getUniqNumber())
        );
    }
}
