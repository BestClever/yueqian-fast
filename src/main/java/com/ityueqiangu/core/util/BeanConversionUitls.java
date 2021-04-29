package com.ityueqiangu.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class BeanConversionUitls {

    public static final Logger log = LoggerFactory.getLogger(BeanConversionUitls.class);

    /**
     * list->cope list
     *
     * @param target     目标list中实体类
     * @param sourceList 源list
     * @param <T>
     * @param <F>
     * @return
     */
    public static <T, F> List<F> listCopeToAnotherList(Class<F> target, List<T> sourceList) {
        List<F> targetList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sourceList)) {
            for (T t : sourceList) {
                try {
                    F f = target.newInstance();
                    BeanUtils.copyProperties(t, f);
                    targetList.add(f);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
            return targetList;
        }
        return targetList;
    }

    /**
     * object -> target
     * @param target 目标实体类
     * @param object 目标复制实体类
     * @param <F>
     * @return
     */
    public static <F> F copeProperties(Class<F> target, Object object) {
        F f = null;
        if (object != null) {
            try {
                f = target.newInstance();
                BeanUtils.copyProperties(object, f);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            return f;
        }
        return f;
    }
}
