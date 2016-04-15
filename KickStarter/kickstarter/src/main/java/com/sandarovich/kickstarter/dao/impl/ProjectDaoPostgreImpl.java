package com.sandarovich.kickstarter.dao.impl;


import com.sandarovich.kickstarter.dao.ProjectDao;
import com.sandarovich.kickstarter.model.Category;
import com.sandarovich.kickstarter.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProjectDaoPostgreImpl implements ProjectDao {

    private static final String SQL_FIND_PROJECTS_BY_CATEGORY =
            "SELECT id, name, description, required_budget, days_left, video_link, history " +
                    "FROM project " +
                    "WHERE categoryid=?;";
    private static final String SQL_FIND_GATHERED_BUDGET_BY_PROJECT =
            "SELECT SUM(amount) amount " +
                    "FROM  payment " +
                    "WHERE projectid=?;";
    private static final String SQL_FIND_BY_PROJECT_ID =
            "SELECT id, name, description, required_budget, days_left, video_link, history " +
                    "FROM project " +
                    "WHERE id=?;";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Project findById(int projectId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_PROJECT_ID, new Object[]{projectId}, new ProjectRowMapper());

    }

    @Override
    public List<Project> getByCategory(Category category) {
        return jdbcTemplate.query(
                SQL_FIND_PROJECTS_BY_CATEGORY,
                new Object[]{category.getId()},
                new ProjectRowMapper());
    }

    private double getGatheredBudget(Project project) {
        return jdbcTemplate.queryForObject(
                SQL_FIND_GATHERED_BUDGET_BY_PROJECT,
                new Object[]{project.getId()},
                Double.class);
    }

    private final class ProjectRowMapper implements RowMapper<Project> {
        @Override
        public Project mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Project project = new Project();
            project.setId(resultSet.getInt("id"));
            project.setName(resultSet.getString("name"));
            project.setDescription(resultSet.getString("description"));
            project.setRequiredBudget(resultSet.getDouble("required_budget"));
            project.setDaysLeft(resultSet.getInt("days_left"));
            project.setHistory(resultSet.getString("history"));
            project.setVideoLink(resultSet.getString("video_link"));
            project.setGatheredBudget(getGatheredBudget(project));
            return project;
        }
    }
}
