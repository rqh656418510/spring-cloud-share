package com.clay.shardingsphere;

import com.clay.shardingsphere.entity.Dict;
import com.clay.shardingsphere.mapper.DictMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShardingTest {

    @Autowired
    DictMapper dictMapper;

    /**
     * 广播表：插入数据<br>
     * 每个数据库服务器中的 t_dict 表会同时插入数据
     */
    @Test
    public void testInsertBroadcast() {
        Dict dict = new Dict();
        dict.setDictType("gender");
        dict.setDictCode("male");
        dict.setDictValue("男");
        dictMapper.insert(dict);
    }

    /**
     * 广播表：查询数据<br>
     * 只会从任意一个节点的 t_dict 表中查询数据（基于随机负载均衡规则）
     */
    @Test
    public void testSelectBroadcast() {
        List<Dict> dicts = dictMapper.selectList(null);
    }

}