package kg.attractor.exam_7.mapper;

import kg.attractor.exam_7.model.Currency;
import kg.attractor.exam_7.model.User;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.plaf.ColorUIResource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyMapper implements RowMapper<Currency> {
    @Override
    public Currency mapRow(ResultSet rs, int rowNum) throws SQLException {
        Currency currency = new Currency();
        currency.setId(rs.getLong("id"));
        currency.setCurrency(rs.getString("currency"));
        return currency;
    }
}
