package me.pgthinker.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.pgthinker.system.model.entity.user.SystemRolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pgthinker
 * @description 针对表【system_role_permission(角色-权限关联表)】的数据库操作Mapper
 * @createDate 2025-03-30 19:34:28
 * @Entity generator.domain.SystemRolePermission
 */
@Mapper
public interface SystemRolePermissionMapper extends BaseMapper<SystemRolePermission> {

}
