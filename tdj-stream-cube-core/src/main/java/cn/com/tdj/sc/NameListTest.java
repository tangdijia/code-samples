package cn.com.tdj.sc;

import cn.com.bsfit.sc.build.AbstractVarTask;
import cn.com.bsfit.sc.build.VarBuilder;
import cn.com.bsfit.sc.oper.MergeableMapObject;
import cn.com.bsfit.sc.oper.ReplacedObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class NameListTest {

    public static void main(String[] args) {

        List<AbstractVarTask> tasks = new ArrayList<>();

        tasks.add(VarBuilder.feature("同一名单类型、维度、名单值对应名单", cn.com.bsfit.namelist.entity.Namelist.class)
                .prefix("名单", "NAMELIST")
                .key(obj -> {
                    return String.join("-", obj.getNamelistType(), obj.getNamelistDim(), obj.getNamelistValue());
                })
                .apply(obj -> {
                    Object[] array = new Object[2];
                    array[0] = obj.getExpireTime();
                    array[1] = obj.getNamelistStatus();
                    if (obj.getTypeWay() == 2) {
                        return new MergeableMapObject(obj.getImmuneRule(), new ReplacedObject(array));
                    } else {
                        return new ReplacedObject(array);
                    }
                }).build());
    }
}
