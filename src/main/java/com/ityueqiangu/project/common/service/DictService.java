package com.ityueqiangu.project.common.service;

import com.ityueqiangu.project.system.domain.SysDictData;
import com.ityueqiangu.project.system.service.ISysDictDataService;
import com.ityueqiangu.project.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: html调用 thymeleaf 实现字典读取
 * @ProjectName: yueqian-base
 * @PackageName: com.ityueqiangu.project.common.service
 * @ClassName: DictService
 * @FileName: DictService.java
 * @CreateDate: 2022-03-22 09:57:48
 * @Author: FlowerStone
 * @Copyright
 */
@Service(value = "dict")
public class DictService {

    @Autowired
    private ISysDictDataService sysDictDataService;

    @Autowired
    private ISysDictTypeService sysDictTypeService;

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType
     * @return
     * @date 2022年03月22日 0022 10:02:53
     */
    public List<SysDictData> getType(String dictType) {
        return sysDictDataService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType
     * @param dictValue
     * @return
     * @date 2022年03月22日 0022 10:13:46
     */
    public String getLabel(String dictType, String dictValue){
        return sysDictDataService.selectDictLabel(dictType, dictValue);
    }
}
