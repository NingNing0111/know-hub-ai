package me.pgthinker.system.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.pgthinker.system.mapper.SystemUserMapper;
import me.pgthinker.system.model.entity.user.SystemUser;
import me.pgthinker.system.service.system.SystemUserService;
import org.springframework.stereotype.Service;

/**
 * @author pgthinker
 * @description 针对表【system_user(系统用户表)】的数据库操作Service实现
 * @createDate 2025-03-30 18:28:12
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

}
