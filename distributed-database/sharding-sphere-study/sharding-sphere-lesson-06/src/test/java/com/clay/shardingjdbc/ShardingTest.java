package com.clay.shardingjdbc;

import com.clay.shardingjdbc.entity.Dict;
import com.clay.shardingjdbc.mapper.DictMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShardingTest {

    @Autowired
    DictMapper dictMapper;

    /**
     * 水平分库之广播表：往每个数据库服务器中的 t_dict 表同时插入数据
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
     * 水平分库之广播表：查询 t_dict 表中的所有数据，只从任意一个节点查询数据（基于随机负载均衡规则）
     */
    @Test
    public void testSelectBroadcast() {
        List<Dict> dicts = dictMapper.selectList(null);
    }

}