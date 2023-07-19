package com.javalab.board.dao;

import com.javalab.board.vo.Criteria;
import com.javalab.board.vo.Department;
import com.javalab.board.vo.EmployeeCommonDto;
import com.javalab.board.vo.Employees;
import com.javalab.board.vo.Job;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Dao Layer Test
 * @Test : 테스트할 메소드에 필요한 어노테이션
 * @Disabled : 테스트에서 제외할 메소드에 필요한 어노테이션
 *  둘중에 하나를 선택해서 테스트 할 것.
 * @Transactional : 트랜잭션 걸면 기본이 롤백됨.
 *  - @Commit : 실제로 디비에 넣고 싶으면 메소드 적용해줄것.
 *  @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 *   - application.properties에 설정된 대로 데이터베이스 설정 사용해서 테스트
 */
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @BeforeEach
    public void setup() {
    	log.info("여기는 테스트 하기 전에 매번 실행되는 메소드");
    }

    @Test
    //@Disabled 
    public void testGetEmployeesList() {
        EmployeeCommonDto dto = new EmployeeCommonDto();
        List<EmployeeCommonDto> employeesList = employeeDao.getEmployeesList(dto);
        assertNotNull(employeesList);
        for (EmployeeCommonDto employee : employeesList) {
            log.info(employee.toString());
        }
    }
    
    @Test
    @Disabled 
    public void testGetEmployees() {
        int employeeId = 100;
        EmployeeCommonDto employee = employeeDao.getEmployees(employeeId);
        assertNotNull(employee);
        assertEquals(employeeId, employee.getEmployeeId());
    }

    @Test
    @Disabled 
    public void testGetTotalEmployees() {
        Criteria cri = new Criteria();
        int totalEmployees = employeeDao.getTotalEmployees(cri);
        assertEquals(0, totalEmployees);
    }

    @Disabled 
    @Test
    //@Commit
    public void testRegister() {
        Employees emp = new Employees();
        emp.setEmployeeId(208);
        emp.setFirstName("정희");
        emp.setLastName("안");
        emp.setEmail("dream@a.com");
        emp.setHireDate("2023-06-05");
        emp.setSalary(50000L);

        // 처리 결과 건수(성공-1)
        int result = employeeDao.register(emp);
        assertEquals(1, result);
    }
}
