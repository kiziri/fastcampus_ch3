package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class A1DaoTest {
    @Autowired
    A1Dao a1Dao;

    @Autowired
    DataSource ds;

    @Autowired
    DataSourceTransactionManager tm;    // 빈 등록으로 아래 선언 코드 없이 자동으로 주입

    @Test
    public void insert() throws Exception {
        // TxManager를 생성
//        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);  
        TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition(new DefaultTransactionDefinition()));
        // Tx 시작
        try {
            a1Dao.deleteAll();
            a1Dao.insert(1, 100);   // 성공
            a1Dao.insert(1, 200);   // 실패
            tm.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            tm.rollback(status);
        } finally {

        }
    }
}