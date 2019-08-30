package com.leyou.item.service.Impl;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.BeanHelper;
import com.leyou.item.dto.SpecGroupDTO;
import com.leyou.item.dto.SpecParamDTO;
import com.leyou.item.entity.SpecGroup;
import com.leyou.item.entity.SpecParam;
import com.leyou.item.mapper.SpecMapper;
import com.leyou.item.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * description: SpecServiceImpl <br>
 * date: 2019/8/28 20:30 <br>
 * author: 涛子 <br>
 * version: 1.0 <br>
 */
@Service
public class SpecServiceImpl implements SpecService {
    @Autowired
    private SpecMapper specMapper;

    /**
     * 根据分类cid查询规格组信息
     *
     * @param cid 分类id
     * @return 规格组集合
     */
    @Override
    public List<SpecGroupDTO> queryByCategoryCid(Long cid) {
        List<SpecGroup> specGroupList = specMapper.queryByCategoryCid(cid);
        if (CollectionUtils.isEmpty(specGroupList)) {
            throw new LyException(ExceptionEnum.SPECGROUP_NOT_FOUND);
        }
        return BeanHelper.copyWithCollection(specGroupList, SpecGroupDTO.class);
    }

    /**
     * 根据规格组id查询规格参数
     *
     * @param gid
     * @return
     */
    @Override
    public List<SpecParamDTO> querySpecParamByGroupId(Long gid) {
        List<SpecParam> specParamList = specMapper.querySpecParamByGroupId(gid);
        if (CollectionUtils.isEmpty(specParamList)) {
            throw new LyException(ExceptionEnum.SPECPARAM_NOT_FOUND);
        }
        return BeanHelper.copyWithCollection(specParamList, SpecParamDTO.class);
    }

    /**新增规格组
     * @param
     */
    /*根据*/
    @Override
    public void addGroup(SpecGroupDTO specGroupDTO) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(specGroupDTO.getCid());
        specGroup.setName(specGroupDTO.getName());
        specGroup.setCreateTime(new Date());
        specGroup.setUpdateTime(new Date());
        int count = specMapper.insert(specGroup);
        if (count != 1) {
            throw new LyException(ExceptionEnum.INSERT_SPECGROUP_FAIL);
        }
    }


}
