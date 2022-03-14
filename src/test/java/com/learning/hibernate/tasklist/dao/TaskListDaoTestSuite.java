package com.learning.hibernate.tasklist.dao;

import com.learning.hibernate.task.Task;
import com.learning.hibernate.task.TaskFinancialDetails;
import com.learning.hibernate.tasklist.TaskList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class TaskListDaoTestSuite {


    private static final String DESCRIPTION = "ToDo tasks";
    private static final String LIST_NAME = "ToDo List";
    @Autowired
    private TaskListDao taskListDao;

    @Test
    void testFindByListName() {
        // Given
        TaskList taskList = new TaskList(LIST_NAME, DESCRIPTION);
        taskListDao.save(taskList);
        // When
        List<TaskList> readTasksLists = taskListDao.findByListName(LIST_NAME);
        // Then
        assertEquals(5,readTasksLists.size());
        assertEquals(LIST_NAME, readTasksLists.get(0).getListName());
        // CleanUp;
        int id = taskList.getId();
        taskListDao.deleteById(id);

    }
    @Test
    void testTaskListDaoSaveWithTasks() {
        //Given
        Task task = new Task("Test: Learn Hibernate", 14);
        Task task2 = new Task("Test: write some entities", 3);
        TaskFinancialDetails tfd = new TaskFinancialDetails(new BigDecimal(20), false);
        TaskFinancialDetails tfd2 = new TaskFinancialDetails(new BigDecimal(10), false);
        task.setTaskFinancialDetails(tfd);
        task2.setTaskFinancialDetails(tfd2);
        TaskList taskList = new TaskList(LIST_NAME, DESCRIPTION);
        taskList.getTasks().add(task);
        taskList.getTasks().add(task2);
        task.setTaskList(taskList);
        task2.setTaskList(taskList);

        //When
        taskListDao.save(taskList);
        int id = taskList.getId();

        //Then
        assertNotEquals(0, id);

//       CleanUp
        taskListDao.deleteById(id);
    }
}

