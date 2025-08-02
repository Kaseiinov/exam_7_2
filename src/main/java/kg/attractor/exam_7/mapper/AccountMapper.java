package kg.attractor.exam_7.mapper;

import kg.attractor.exam_7.dto.AccountDto;
import kg.attractor.exam_7.model.Account;
import kg.attractor.exam_7.model.Currency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setCurrencyId(rs.getLong("currency_id"));
        account.setUserId(rs.getLong("user_id"));
        account.setBalance(rs.getDouble("balance"));
        account.setUniqNumber(rs.getString("uniq_number"));
        return account;
    }
}
