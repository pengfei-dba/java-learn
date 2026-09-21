package net.spfwork.forum.util;

import org.apache.commons.dbutils.GenerousBeanProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 扩展GenerousBeanProcessor，支持数据库datetime/date/time列
 * 映射到java.time的LocalDateTime/LocalDate/LocalTime属性
 */
public class LocalDateTimeBeanProcessor extends GenerousBeanProcessor {

    @Override
    protected Object processColumn(ResultSet rs, int index, Class<?> propType) throws SQLException {
        if (propType.equals(LocalDateTime.class)) {
            Timestamp timestamp = rs.getTimestamp(index);
            return timestamp == null ? null : timestamp.toLocalDateTime();
        }
        if (propType.equals(LocalDate.class)) {
            java.sql.Date date = rs.getDate(index);
            return date == null ? null : date.toLocalDate();
        }
        if (propType.equals(LocalTime.class)) {
            java.sql.Time time = rs.getTime(index);
            return time == null ? null : time.toLocalTime();
        }
        return super.processColumn(rs, index, propType);
    }
}