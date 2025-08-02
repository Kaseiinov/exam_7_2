package kg.attractor.exam_7.mapper;

import kg.attractor.exam_7.model.Account;
import kg.attractor.exam_7.model.History;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class HistoryMapper implements RowMapper<History> {
    @Override
    public History mapRow(ResultSet rs, int rowNum) throws SQLException {
        History history = new History();
        history.setId(rs.getLong("id"));
        history.setFromAcc(rs.getString("from_acc"));
        history.setToAcc(rs.getString("to_acc"));
        history.setAmountMoney(rs.getDouble("amount_money"));
        history.setApproved(rs.getBoolean("approved"));
        return history;
    }
}
