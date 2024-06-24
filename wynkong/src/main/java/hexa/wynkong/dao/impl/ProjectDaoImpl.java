package hexa.wynkong.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import hexa.wynkong.dao.ProjectDao;
import hexa.wynkong.entity.UserEntity;
import hexa.wynkong.sql.ProjectSql;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private DataSource dataSource;    
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
   
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

   

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public int deleteById(int id) {
        int deleteData = jdbcTemplate.update(ProjectSql.QUERY_FOR_DELETE, id);
        if (deleteData == 0) {
            System.out.println("data not deleted in database");
        } else {
            System.out.println("data deleted in database successfully");
        }
        return deleteData;
    }

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> users = new ArrayList<>();
        try {
            users = jdbcTemplate.query(ProjectSql.SQL_GET_ALL_USERS, new RowMapper<UserEntity>() {
                @Override
                public UserEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                    UserEntity user = new UserEntity();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPanCardNo(resultSet.getString("panCardNo"));
                    user.setAddress(resultSet.getString("address"));
                    return user;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

 
	
    /*@Override
    public List<UserEntity> getDetailById(int id) {
        List<UserEntity> tempList = new ArrayList<>();
        try {
            tempList = jdbcTemplate.query(ProjectSql.SQL_GET_USER_DETAILS, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, id);
                }
            }, new RowMapper<UserEntity>() {
                @Override
                public UserEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                    UserEntity user = new UserEntity();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPanCardNo(resultSet.getString("panCardNo"));
                    user.setAddress(resultSet.getString("address"));
                    return user;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempList;
    }*/
    



    @Override
    public int save(UserEntity userEntity) {
        int insertData = jdbcTemplate.update(ProjectSql.SQL_ADD_DATA, 
            userEntity.getName(),
            userEntity.getPanCardNo(),
            userEntity.getAddress()
        );
        if (insertData == 0) {
            System.out.println("data not inserted in database"+insertData);
        } else {
            System.out.println("data inserted in database successfully");
        }
        return insertData;
    }

    @Override
    public int update(UserEntity userEntity) {
        int updateData = jdbcTemplate.update(ProjectSql.SQL_UPDATE_DATA, 
            userEntity.getName(),
            userEntity.getPanCardNo(),
            userEntity.getAddress(),
            userEntity.getId()
        );
        if (updateData == 0) {
            System.out.println("data not updated in database");
        } else {
            System.out.println("data updated in database successfully");
        }
        return updateData;
    }

    @Override
    public List<UserEntity> getDetailById(int id) {
        List<UserEntity> tempList = new ArrayList<>();
        try {
            tempList = jdbcTemplate.query(ProjectSql.SQL_GET_USER_DETAILS, new Object[]{id}, new RowMapper<UserEntity>() {
                @Override
                public UserEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                    UserEntity user = new UserEntity();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPanCardNo(resultSet.getString("panCardNo"));
                    user.setAddress(resultSet.getString("address"));
                    return user;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempList;
    }

	public UserEntity findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserEntity saveUser(UserEntity createdUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
