package io.hades.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.hades.dto.Role;

@Service
public class RoleRepositoryService {
	
	@Autowired
	private DataSource dataSource;
	
	private Connection connection;
	
	@PostConstruct
	public void initializeConnectionObject() throws SQLException{
		connection = dataSource.getConnection();
	}

	
	public Role getRoleById(Long id){

		Role role = null;
		try
		{
			String sql ="select * from role where id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				role = new Role();
				role.setId(rs.getLong("id"));
				role.setRoleName(rs.getString("role_name"));
			}

		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return role;

	}
		
}
