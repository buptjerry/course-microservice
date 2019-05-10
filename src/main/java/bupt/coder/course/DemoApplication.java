package bupt.coder.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class DemoApplication {

    @Bean
    @Profile("create")
    CommandLineRunner init(CourseRepository courseRepository) {
        return (args -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse("2019-01-01");
            Date endDate = sdf.parse("2019-06-30");
            // 和用户管理微服务初始化数据相对应，teachId为1是指 TheShy 老师
            Course course = new Course("SPM", 1, startDate, endDate, "教三504");
            Course course2 = new Course("SPM2", 1, startDate, endDate, "教三504");
            Course course3 = new Course("SPM3", 1, startDate, endDate, "教三504");
            Course course4 = new Course("SPM4", 1, startDate, endDate, "教三504");
            Course course5 = new Course("SPM5", 1, startDate, endDate, "教三504");
            courseRepository.save(course);
            courseRepository.save(course2);
            courseRepository.save(course3);
            courseRepository.save(course4);
            courseRepository.save(course5);
        });

    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
