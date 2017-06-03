package com.marsyoung.transation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class BaseDao {

    protected final static Log LOG = LogFactory.getLog(BaseDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostConstruct
    protected void initTemplateConfig() {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
                jdbcTemplate);
    }

    protected JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return this.namedParameterJdbcTemplate;
    }

    protected <T> T queryForObject(String sql, RowMapper<T> rowMapper) {
        T obj = null;
        try {
            obj = this.jdbcTemplate.queryForObject(sql, rowMapper);
        } catch (IncorrectResultSizeDataAccessException e) {
            // do nothing
        } catch (DataAccessException e) {
            LOG.error(sql, e);
        }
        return obj;
    }

    protected <T> T queryForObject(String sql, RowMapper<T> rowMapper,
                                   Object... args) {
        T obj = null;
        try {
            obj = this.jdbcTemplate.queryForObject(sql, args, rowMapper);
        } catch (IncorrectResultSizeDataAccessException e) {
            // do nothing
        } catch (DataAccessException e) {
            LOG.error(sql, e);
        }
        return obj;
    }

    protected <T> T queryForObject(String sql, Class<T> elementType,
                                   Object... args) {

        T obj = null;
        try {
            obj = this.jdbcTemplate.queryForObject(sql, args,
                    new BeanPropertyRowMapper<T>(elementType));
        } catch (IncorrectResultSizeDataAccessException e) {
            // do nothing
        } catch (DataAccessException e) {
            LOG.error(sql, e);
        }
        return obj;

    }

    protected <T> T queryForPrimitive(String sql, Class<T> elementType,
                                      Object... args) {

        T obj = null;
        try {
            obj = this.jdbcTemplate.queryForObject(sql, args, elementType);
        } catch (IncorrectResultSizeDataAccessException e) {
            // do nothing
        } catch (DataAccessException e) {
            LOG.error(sql, e);
        }
        return obj;

    }


    protected <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        List<T> list = null;
        try {
            list = this.jdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException e) {
            LOG.error(sql, e);
        }
        return list;
    }

    protected <T> List<T> query(String sql, Class<T> elementType,
                                Object... args) {

        List<T> list = null;
        try {
            list = this.jdbcTemplate.query(sql, args,
                    new BeanPropertyRowMapper<T>(elementType));
        } catch (DataAccessException e) {
            LOG.error(sql, e);
        }
        return list;
    }

    protected boolean update(String tableName, Map<String, Object> sets,
                             Map<String, Object> wheres) {
        boolean b = false;
        if (sets != null && sets.size() > 0 && wheres != null
                && wheres.size() > 0) {
            StringBuilder sql = new StringBuilder();
            Object[] params = new Object[sets.size() + wheres.size()];
            int column = 0;
            sql.append("update ").append(tableName).append(" set");
            for (String columnName : sets.keySet()) {
                params[column++] = sets.get(columnName);
                sql.append(" `").append(columnName).append("`=?,");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(" where");
            for (String columnName : wheres.keySet()) {
                params[column++] = wheres.get(columnName);
                sql.append(" ").append(columnName).append("=? and");
            }
            sql.delete(sql.length() - 3, sql.length());

            int count = this.jdbcTemplate.update(sql.toString(), params);
            b = count > 0;
        }
        return b;
    }

    protected long insert(String tableName, Map<String, Object> record) {
        final String sql = concatInsertSql(tableName, record);
        final Object[] parameters = parseParameter(record);
        KeyHolder key = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                int length = parameters.length;
                for (int i = 0; i < length; i++) {
                    preparedStatement.setObject(i + 1, parameters[i]);
                }
                return preparedStatement;
            }
        }, key);
        return key.getKey().longValue();
    }

    private Object[] parseParameter(Map<String, Object> map) {
        Object[] paramter = new Object[map.size()];
        int column = 0;
        for (String columnName : map.keySet()) {
            paramter[column++] = map.get(columnName);
        }
        return paramter;
    }

    private String concatInsertSql(String tableName, Map<String, Object> map) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> map0 = map;
        StringBuilder valuesSql = new StringBuilder(" values (");
        sql.append("insert into ").append(tableName).append(" (");
        for (String columnName : map0.keySet()) {
            sql.append(" `").append(columnName).append("`,");
            valuesSql.append("?,");
        }
        if (map.size() > 0) {
            sql.deleteCharAt(sql.length() - 1);
        }
        sql.append(")");
        if (map.size() > 0) {
            valuesSql.deleteCharAt(valuesSql.length() - 1);
        }
        valuesSql.append(")");
        sql.append(valuesSql.toString());
        return sql.toString();
    }

    /**
     * @param tableName
     * @param where     must be not empty
     * @return success deleteed number
     */
    protected int delete(String tableName, Map<String, Object> where) {
        int num = 0;
        if (where != null && where.size() > 0) {
            StringBuilder sql = new StringBuilder();
            Object[] paramter = new Object[where.size()];
            int column = 0;
            sql.append("delete from ").append(tableName).append(" where ");
            for (String columnName : where.keySet()) {
                paramter[column++] = where.get(columnName);
                sql.append(" `").append(columnName).append("`=? and");
            }
            sql.delete(sql.length() - 3, sql.length());
            try {
                num = this.jdbcTemplate.update(sql.toString(), paramter);
            } catch (DataAccessException e) {
                LOG.error(e);
            }
        }
        return num;
    }

    /**
     * @param sql
     * @return 返回受影响的行数, -1代表数据库操作异常
     */
    protected int update(String sql) {
        int affected = 0;
        try {
            affected = this.jdbcTemplate.update(sql);
        } catch (DataAccessException e) {
            affected = -1;
            LOG.error(sql, e);
        }
        return affected;
    }

    /**
     * @param sql
     * @return 返回受影响的行数, -1代表数据库操作异常
     */
    protected int update(String sql, Object... args) {
        int affected = 0;
        try {
            affected = this.jdbcTemplate.update(sql, args);
        } catch (DataAccessException e) {
            affected = -1;
            LOG.error(sql, e);
        }
        return affected;
    }
}