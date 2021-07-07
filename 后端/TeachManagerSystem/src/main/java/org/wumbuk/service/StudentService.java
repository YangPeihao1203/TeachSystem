package org.wumbuk.service;

import org.wumbuk.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudent();

    /**
     * 模糊查询学生的信息，其中学生的学号是准确查询、学生的姓名是部分查询
     * @param keyWord
     * @return
     */
    List<Student> getStudentFuzzy(String keyWord);

    /**
     * 通过学生学号删除学生
     * @param sid
     * @return
     */
    int deleteStudentBySid(int sid);

    //添加学生
    int addStudent(Student student) throws Exception;

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    int editStudent(Student student);
}
