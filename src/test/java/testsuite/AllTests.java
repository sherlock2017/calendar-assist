package testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.boot.test.context.SpringBootTest;

import com.calendar.assist.service.CalendarServiceTest;
import com.calendar.assist.service.EmployeeServiceTest;
import com.calendar.assist.service.MeetingServiceTest;
import com.calendar.assist.service.TimeSlotServiceTest;

/**
 * TestSuite for all Service Test
 * 
 * @author Rishabh Jain
 * @since 4/14/2021
 *
 */
@SpringBootTest
@RunWith(Suite.class)
@SuiteClasses({ CalendarServiceTest.class, EmployeeServiceTest.class, MeetingServiceTest.class,
		TimeSlotServiceTest.class })
public class AllTests {

}
