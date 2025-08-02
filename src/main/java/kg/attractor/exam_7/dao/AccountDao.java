package kg.attractor.exam_7.dao;

import kg.attractor.exam_7.mapper.AccountMapper;
import kg.attractor.exam_7.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class AccountDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void makeTransaction(String fromAcc, String toAcc, Double amount) {
        String sqlFrom = "UPDATE accounts \n" +
                "SET balance = balance - :amount \n" +
                "WHERE uniq_number = :fromAcc \n;";

        String sqlTo = "update accounts " +
                "set balance = balance + :amount " +
                "where uniq_number = :toAcc \n;";

        namedParameterJdbcTemplate.update(sqlFrom,
                new MapSqlParameterSource()
                        .addValue("fromAcc", fromAcc)
                        .addValue("amount", amount)
        );

        namedParameterJdbcTemplate.update(sqlTo,
                new MapSqlParameterSource()
                        .addValue("toAcc", toAcc)
                        .addValue("amount", amount)
        );
    }

    public boolean isEnough(String accountNumber, Double amount) {
        String sql = "SELECT EXISTS ("
                + "SELECT 1 FROM accounts "
                + "WHERE balance >= ? "
                + "AND uniq_number = ?"
                + ")";

        return Boolean.TRUE.equals(
                jdbcTemplate.queryForObject(sql, Boolean.class, amount, accountNumber)
        );
    }

    public Optional<Account> getAccountByNum(String accountNum) {
        String sql = "select * from accounts where uniq_number = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new AccountMapper(), accountNum));
    }

    public List<Account> getAccounts() {
        String sql = "select * from accounts";
        return jdbcTemplate.query(sql, new AccountMapper());
    }

    public boolean isAccountOwnedByUser(String accountNumber, String userPhone) {
        String sql = """
            SELECT EXISTS (
                SELECT 1 
                FROM accounts a
                JOIN users u ON a.user_id = u.id
                WHERE a.uniq_number = ?
                AND u.phone = ?
            )
            """;

        return Boolean.TRUE.equals(
                jdbcTemplate.queryForObject(sql, Boolean.class, accountNumber, userPhone)
        );
    }

    public void toUp(Long userId, String accNum, Double amount) {
        String sql = "UPDATE accounts \n" +
                "SET balance = balance + :amount\n" +
                "WHERE user_id = :userId " +
                "and uniq_number = :accNum;";

        namedParameterJdbcTemplate.update(sql,
                new MapSqlParameterSource()
                        .addValue("userId", userId)
                        .addValue("accNum", accNum)
                        .addValue("amount", amount)

        );
    }

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
