import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookService {
    public static void main(String[] args) {
        SpringApplication.run(BookService.class, args);
    }
}
