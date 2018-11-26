package com.cdk.dao;

import com.cdk.entity.Role;

import java.util.Map;

public interface RoleDao {
    public int addRole(Role role);

    public Map<String, Object> getRole(Role role, String isPage, int pageNo, int pageSize);

    public int editRole(Role role);

    public int changeStateToFrozen_Role(Role role);

    public int changeStateToNormal_Role(Role role);

    public int deleteRole(Role role);

    public int deleteRoleRights(int id, String[] rightList);

    public int InsertRoleRights(int id, String[] rightList);

    public int[] deleteAllRole(String[] roleList);
}
