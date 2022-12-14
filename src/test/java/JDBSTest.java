import com.job_tracking_system.App;
import com.job_tracking_system.entity.ERole;
import com.job_tracking_system.entity.EStatus;
import com.job_tracking_system.entity.Person;
import com.job_tracking_system.entity.Task;
import com.job_tracking_system.repository.PersonJpaRepository;
import com.job_tracking_system.repository.TaskJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class JDBSTest {
    @Autowired
    private PersonJpaRepository personJpaRepository;
    @Autowired
    private TaskJpaRepository taskJpaRepository;

    //TEST NAMING STRUCTURE *method name* + *RepositoryName* + Test

    @Test
    public void savePersonTest() {
        Person person = new Person("login", "password", ERole.ROLE_IMPLEMENTER);
        Person savedPerson = personJpaRepository.save(person);
        assertThat(savedPerson).usingRecursiveComparison().ignoringFields("id").isEqualTo(person);
        personJpaRepository.deleteAll();
    }

    @Test
    public void saveTaskTest() {
        Task task = new Task("name", EStatus.STATUS_CREATED, 1.0, "desc");
        Task savedTask = taskJpaRepository.save(task);
        assertThat(savedTask).usingRecursiveComparison().ignoringFields("id").isEqualTo(task);
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findByIdPersonTest() {
        Person person = new Person("login", "password", ERole.ROLE_MANAGER);
        Person savedPerson = personJpaRepository.save(person);
        assertThat(savedPerson).usingRecursiveComparison().isEqualTo(personJpaRepository.findById(savedPerson.getId()).get());
        personJpaRepository.deleteAll();
    }

    @Test
    public void findByIdTaskTest() {
        Task task = new Task("name", EStatus.STATUS_CREATED, 1.0, "desc");
        Task savedTask = taskJpaRepository.save(task);
        assertThat(savedTask).usingRecursiveComparison().isEqualTo(taskJpaRepository.findById(savedTask.getId()).get());
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findAllPersonTest() {
        Person person1 = new Person("login1", "password1", ERole.ROLE_IMPLEMENTER);
        Person person2 = new Person("login2", "password2", ERole.ROLE_MANAGER);
        personJpaRepository.save(person1);
        personJpaRepository.save(person2);
        assertThat(personJpaRepository.findAll()).asList().size().isEqualTo(2);
        personJpaRepository.deleteAll();
    }

    @Test
    public void findAllTaskTest() {
        Task task1 = new Task("name1", EStatus.STATUS_CREATED, 1.0, "desc1");
        Task task2 = new Task("name2", EStatus.STATUS_ASSIGNED, 1.0, "desc2");
        taskJpaRepository.save(task1);
        taskJpaRepository.save(task2);
        assertThat(taskJpaRepository.findAll()).asList().size().isEqualTo(2);
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findByNameTaskTest() {
        Task task = new Task("name", EStatus.STATUS_CREATED, 1.0, "desc");
        Task savedTask = taskJpaRepository.save(task);
        assertThat(taskJpaRepository.findByName(savedTask.getName())).usingRecursiveComparison().isEqualTo(savedTask);
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findByStatusTaskTest() {
        Task task1 = new Task("name1", EStatus.STATUS_CREATED, 1.0, "desc1");
        Task task2 = new Task("name2", EStatus.STATUS_ASSIGNED, 2.0, "desc2");
        Task task3 = new Task("name3", EStatus.STATUS_CREATED, 3.0, "desc3");
        taskJpaRepository.save(task1);
        taskJpaRepository.save(task2);
        taskJpaRepository.save(task3);
        List<Task> list = List.of(task1, task3);
        assertThat(taskJpaRepository.findByStatus(task1.getStatus())).usingRecursiveComparison().isEqualTo(list);
       taskJpaRepository.deleteAll();
    }

    @Test
    public void findByDifficultyTaskTest() {
        Task task1 = new Task("name1", EStatus.STATUS_CREATED, 1.0, "desc1");
        Task task2 = new Task("name2", EStatus.STATUS_ASSIGNED, 2.0, "desc2");
        Task task3 = new Task("name3", EStatus.STATUS_COMPLETED, 1.0, "desc3");
        taskJpaRepository.save(task1);
        taskJpaRepository.save(task2);
        taskJpaRepository.save(task3);
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task3);
        assertThat(taskJpaRepository.findByDifficulty(task1.getDifficulty())).usingRecursiveComparison().isEqualTo(list);
        taskJpaRepository.deleteAll();
    }

    @Test
    public void findByImplementerIdTaskTest() {
        Task task1 = new Task("name1", EStatus.STATUS_CREATED, 1.0, "desc1");
        Task task2 = new Task("name2", EStatus.STATUS_ASSIGNED, 2.0, "desc2");
        Task task3 = new Task("name3", EStatus.STATUS_COMPLETED, 1.0, "desc3");
        Person person = new Person("login", "password", ERole.ROLE_IMPLEMENTER);
        Person savedPerson = personJpaRepository.save(person);
        task1.setPerson(savedPerson);
        task3.setPerson(savedPerson);
        taskJpaRepository.save(task1);
        taskJpaRepository.save(task2);
        taskJpaRepository.save(task3);
        List<Task> list = List.of(task1, task3);
        assertThat(taskJpaRepository.findByPersonId(task1.getPerson().getId())).usingRecursiveComparison().isEqualTo(list);
        taskJpaRepository.deleteAll();
        personJpaRepository.deleteAll();
    }

    @Test
    public void findByLoginPersonTest() {
        Person person = new Person("login1", "password1", ERole.ROLE_MANAGER);
        Person savedPerson = personJpaRepository.save(person);
        assertThat(personJpaRepository.findByLogin(savedPerson.getLogin()).get()).usingRecursiveComparison().isEqualTo(savedPerson);
        personJpaRepository.deleteAll();
    }

    @Test
    public void findByPositionPersonTest() {
        Person person1 = new Person("login1", "password1", ERole.ROLE_MANAGER);
        Person person2 = new Person("login2", "password2", ERole.ROLE_MANAGER);
        Person person3 = new Person("login3", "password3", ERole.ROLE_IMPLEMENTER);
        Person savedPerson1 = personJpaRepository.save(person1);
        Person savedPerson2 = personJpaRepository.save(person2);
        Person savedPerson3 = personJpaRepository.save(person3);
        List<Person> list = List.of(savedPerson1, savedPerson2);
        assertThat(personJpaRepository.findByRole(savedPerson1.getRole())).usingRecursiveComparison().isEqualTo(list);
        personJpaRepository.deleteAll();
    }
    @Test
    public void findByloginAndPasswordPersonTest(){
        Person person = new Person("login1", "password1", ERole.ROLE_MANAGER);
        Person savedPerson = personJpaRepository.save(person);
        assertThat(personJpaRepository.findPersonByLoginAndPassword(savedPerson.getLogin(), savedPerson.getPassword())).usingRecursiveComparison().isEqualTo(savedPerson);
        personJpaRepository.deleteAll();
    }
}
